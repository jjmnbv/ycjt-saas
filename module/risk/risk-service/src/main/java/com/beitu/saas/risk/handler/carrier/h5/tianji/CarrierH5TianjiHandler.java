package com.beitu.saas.risk.handler.carrier.h5.tianji;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.beitu.saas.common.config.ConfigUtil;
import com.beitu.saas.risk.domain.carrier.h5.enums.CarrierH5StatusEnum;
import com.beitu.saas.risk.domain.carrier.h5.enums.CarrierH5TypeEnum;
import com.beitu.saas.risk.domain.enums.ErrorCodeEnums;
import com.beitu.saas.risk.domain.exception.BizException;
import com.beitu.saas.risk.domain.platform.rongScore.client.ClientManager;
import com.beitu.saas.risk.domain.platform.rongScore.utils.RequestUtil;
import com.fqgj.base.services.redis.RedisClient;
import com.fqgj.common.utils.StringUtils;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import com.beitu.saas.risk.handler.carrier.RestCallbackUtils;
import com.beitu.saas.risk.handler.carrier.h5.CarrierH5Handler;
import com.beitu.saas.risk.handler.carrier.h5.tianji.enums.CarrierTianjiStatusEnum;
import com.beitu.saas.risk.handler.carrier.h5.tianji.vo.CarrierTianjiCacheVo;
import com.beitu.saas.risk.handler.carrier.h5.tianji.vo.CarrierTianjiNotifyVo;
import com.beitu.saas.risk.handler.carrier.h5.vo.CarrierH5ResultVo;
import com.beitu.saas.risk.handler.carrier.h5.vo.CarrierH5Vo;
import com.beitu.saas.risk.helpers.CollectionUtils;
import com.beitu.saas.risk.helpers.JSONUtils;
import com.beitu.saas.risk.helpers.SerializeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by zwh @yuntu-inc.com
 *
 * @description
 * @create 2017/10/30 0030 下午 8:02
 */
@Service
public class CarrierH5TianjiHandler implements CarrierH5Handler {

    private static final Log LOGGER = LogFactory.getLog(CarrierH5TianjiHandler.class);

    @Autowired
    private ConfigUtil configUtil;

    @Autowired
    private RedisClient redisClient;

    private static final String REQUEST_REPORT_METHOD = "tianji.api.tianjireport.collectuser";
    private static final String QUERY_DATA_METHOD = "wd.api.mobilephone.getdatav2";
    private static final String REPORT_RESPONSE = "tianji_api_tianjireport_collectuser_response";
    private static final String DATA_RESPONSE = "wd_api_mobilephone_getdatav2_response";

    //    private static final String NOTIFY_URL = "http://112.124.113.218:7005/triple/carrier/callback/1";
    private static final String PLATFORM = "web";//请求的平台，可取值范围是api,web
    private static final String TYPE = "mobile_crawl";//运营商认证
    private static final String DONE_SUCCESS = "200";
    private static final Integer QUERY_FAIL = 0;
    private static final Integer QUERY_SUCCESS = 1;
    private static final String CACHE_TASKID_KEY = "carrier_tianji_taskId";

    @Override
    public CarrierH5ResultVo requestCarrierUrl(CarrierH5Vo carrierH5Vo) {
        try {
            LOGGER.info("...............submitAccount............ userCode: {}, tripleCarrierInput: {}", carrierH5Vo.getUserCode(), JSONUtils.obj2jsonNoException(carrierH5Vo));
            Map<String, Object> bizData = new HashMap<>();
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("carrierType", CarrierH5TypeEnum.CARRIER_TIANJI.getType() + "");
            String returnUrl = getUrl(carrierH5Vo.getReturnUrl(), paramMap);
            bizData.put("type", TYPE);
            bizData.put("platform", PLATFORM);
            bizData.put("name", carrierH5Vo.getName());
            bizData.put("phone", carrierH5Vo.getPhone());
            bizData.put("idNumber", carrierH5Vo.getIdNumber());
            bizData.put("userId", carrierH5Vo.getUserCode());// 调用方生成的用户ID（调用方定义）
            bizData.put("outUniqueId", UUID.randomUUID().toString().replace("-", ""));//调用方生成的会话唯一标识id，建议使用流水号生成（调用方定义）
            bizData.put("notifyUrl", configUtil.getH5TianjiCarrierNotifyUrl());
//            bizData.put("notifyUrl", NOTIFY_URL);
            bizData.put("returnUrl", returnUrl);//H5重定向的get地址,拼接运营商标识
            String result = "";
            try {
                result = RequestUtil.request(REQUEST_REPORT_METHOD, bizData);
            } catch (Exception e) {
                //打印真实错误信息
                LOGGER.info("...............submitAccount error............ userCode: {}, error: {}", carrierH5Vo.getUserCode(), e);
                throw new BizException(ErrorCodeEnums.CARRIER_PARAMS_ERROR);
            }
            if (StringUtils.isEmpty(result)) {
                throw new BizException(ErrorCodeEnums.CARRIER_TIMEOUT_ERROR);
            }
            JSONObject resultObject = JSONObject.parseObject(result);
            if (!resultObject.getString("error").equals(DONE_SUCCESS)) {
                throw new BizException(resultObject.getString("msg"));
            }
            String taskId = resultObject.getJSONObject(REPORT_RESPONSE).getString("outUniqueId");
            String redirectUrl = resultObject.getJSONObject(REPORT_RESPONSE).getString("redirectUrl");
            CarrierH5ResultVo carrierH5ResultVo = new CarrierH5ResultVo();
            carrierH5ResultVo.setTaskId(taskId);
            carrierH5ResultVo.setRedirectUrl(redirectUrl);
            carrierH5ResultVo.setCarrierH5TypeEnum(CarrierH5TypeEnum.CARRIER_TIANJI);
            LOGGER.info("...............submitAccount response............ userCode: {}, tripleCarrierOutput: {}", carrierH5Vo.getUserCode(), JSONUtils.obj2jsonNoException(carrierH5ResultVo));

            CarrierTianjiCacheVo carrierTianjiCacheVo = new CarrierTianjiCacheVo()
                    .setUserCode(carrierH5Vo.getUserCode())
                    .setMobile(carrierH5Vo.getPhone())
                    .setRealName(carrierH5Vo.getName())
                    .setIdCard(carrierH5Vo.getIdNumber())
                    .setAppUrl(carrierH5Vo.getAppUrl());
            setCacheData(taskId, carrierTianjiCacheVo);
            return carrierH5ResultVo;
        } catch (Exception e) {
            LOGGER.info("========submitAccount exception. userCode:{}, mobile:{},e:", carrierH5Vo.getUserCode(), carrierH5Vo.getPhone(), e);
            throw e;
        }
    }

    @Override
    public CarrierTianjiCacheVo getCacheByTaskId(String taskId) {
        return getCacheData(taskId);
    }


    //等待运营商回调，这个过程和用户提交验证码一样
    public void appCallBack(CarrierTianjiNotifyVo carrierTianjiNotifyVo) {
        try {
            LOGGER.info("...............carrier callback............ userCode: {}, carrierTianjiNotifyVo: {}", carrierTianjiNotifyVo.getUserCode(), JSONUtils.obj2jsonNoException(carrierTianjiNotifyVo));
            //先查字典
            String taskId = carrierTianjiNotifyVo.getOutUniqueId();
            if (StringUtils.isEmpty(taskId)) {
                throw new BizException("taskId can not be empty");
            }
            CarrierTianjiCacheVo carrierTianjiCacheVo = getCacheData(taskId);
            String appUrl = carrierTianjiCacheVo.getAppUrl();
            String status = CarrierH5StatusEnum.DONE_FAIL.getName();
            String state = carrierTianjiNotifyVo.getState();
            String reportData = "";
            if (StringUtils.isNotEmpty(state)) {
                if (state.equals(CarrierTianjiStatusEnum.INIT.getType())
                        || state.equals(CarrierTianjiStatusEnum.LOGIN.getType())
                        || state.equals(CarrierTianjiStatusEnum.CRAWL.getType())) {
//                    status = CarrierH5StatusEnum.DOING.getType();
                    return;
                }
                if (state.equals(CarrierTianjiStatusEnum.REPORT.getType())) {
                    //重复调用三次
                    for (int i = 0; i < 3; i++) {
                        try {
                            reportData = queryCarrierInfo(carrierTianjiNotifyVo.getSearchId(), carrierTianjiCacheVo);
                            status = CarrierH5StatusEnum.DONE_SUCCESS.getName();
                            break;
                        } catch (Exception e) {
                            LOGGER.info("queryCarrierInfo error,userCode: {},i:{} e:", carrierTianjiCacheVo.getUserCode(), i, e);
                        }
                    }
                    //成功记录
                }
            }
            Map<String, Object> boby = new HashMap<>();
            boby.put("taskId", taskId);
            boby.put("status", status);
            boby.put("data", reportData);
            boby.put("userCode", carrierTianjiCacheVo.getUserCode());
            boby.put("carrierType", CarrierH5TypeEnum.CARRIER_TIANJI);

            //正常回调app流程
            for (int i = 0; i < 3; i++) {
                //回调
                if (RestCallbackUtils.getCallbackResult(appUrl, boby)) {
                    LOGGER.info("...............app callback............ userCode: {},app callback success", carrierTianjiNotifyVo.getUserCode());
                    return;
                } else {
                    LOGGER.info("...............app callback............ userCode: {},app callback fail,运营商返回reportData：{}", carrierTianjiNotifyVo.getUserCode(), reportData);
                }
            }
            LOGGER.info("...............app callback error............ userCode: {},  app callback fail", carrierTianjiNotifyVo.getUserCode());
        } catch (Exception e) {
            //直接请求就挂了发生的是io异常自己捕捉便是此时我方是无感知的;有请求就一定有响应，若是异常响应我们一定要自己包一层，不能交给系统包一层
            LOGGER.error("========callback exception. userCode:{}, e:", carrierTianjiNotifyVo.getUserCode(), e);
            throw e;
        }
    }

    public String queryCarrierInfo(String searchId, CarrierTianjiCacheVo carrierTianjiCacheVo) {
        String result = "";

        try {
            LOGGER.info("............queryCarrierInfo.............. userCode: {}, searchId: {}, carrierTianjiCacheVo： {}", carrierTianjiCacheVo.getUserCode(), searchId, JSONUtils.obj2jsonNoException(carrierTianjiCacheVo));
            Map<String, Object> bizData = new HashMap<>();
            bizData.put("user_id", searchId);
            bizData.put("merchant_id", ClientManager.getAppId());
            bizData.put("id_card", carrierTianjiCacheVo.getIdCard());
            bizData.put("real_name", carrierTianjiCacheVo.getRealName());
            bizData.put("cellphone", carrierTianjiCacheVo.getMobile());

            try {
                result = RequestUtil.request(QUERY_DATA_METHOD, bizData);
            } catch (Exception e) {
                //http参数错误信息
                LOGGER.error("...............queryCarrierInfo error............ userCode: {}, result:{},error: {}", carrierTianjiCacheVo.getUserCode(), result, e);
                throw new BizException(ErrorCodeEnums.CARRIER_PARAMS_ERROR);
            }
            if (StringUtils.isEmpty(result)) {
                LOGGER.error("...............queryCarrierInfo error............ userCode: {}, result:{}", carrierTianjiCacheVo.getUserCode(), result);
                throw new BizException(ErrorCodeEnums.CARRIER_TIMEOUT_ERROR);
            }
            JSONObject resultObject = JSONObject.parseObject(result);
            if (!resultObject.getString("error").equals(DONE_SUCCESS)) {
                LOGGER.error("...............queryCarrierInfo error............ userCode: {}, result:{}", carrierTianjiCacheVo.getUserCode(), result);
                throw new BizException(resultObject.getString("msg"));
            }
            LOGGER.info("............queryCarrierInfo response.............. userCode: {}, queryCarrierInfo success", carrierTianjiCacheVo.getUserCode());
            //需要获取数据和进行解析
//            return result;
            return transformTianjiToRong(resultObject.getJSONObject(DATA_RESPONSE).getJSONObject("data").getJSONArray("data_list").getString(0));
        } catch (Exception e) {
            LOGGER.error("========queryCarrierInfo exception. userCode:{}, mobile:{},result:{},e:", carrierTianjiCacheVo.getUserCode(), carrierTianjiCacheVo.getMobile(), result, e);
            throw e;
        }
    }

    //拼接get请求url
    private String getUrl(String url, Map<String, String> paramMap) {
        if (CollectionUtils.isEmpty(paramMap)) {
            return url;
        }
        if (url.contains("?")) {
            String[] arrUrl = url.split("\\?");
            url = arrUrl[0];
            String[] arrParam = arrUrl[1].split("&");
            for (String param : arrParam) {
                String[] arrMap = param.split("=");
                paramMap.put(arrMap[0], arrMap[1]);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String s : paramMap.keySet()) {
            if (sb.length() == 0) {
                sb.append(url).append("?");
            } else {
                sb.append("&");
            }
            String value = paramMap.get(s);
            sb.append(s).append("=").append(value);
        }
        return sb.toString();
    }

    private void setCacheData(String taskId, CarrierTianjiCacheVo cacheVO) {
        byte[] inBytes = SerializeUtil.serialize(cacheVO);
        redisClient.set(CACHE_TASKID_KEY.replace("taskId", taskId), inBytes, 3000);
    }

    public CarrierTianjiCacheVo getCacheData(String taskId) {
        // 对象反序列化
        byte[] outBytes = redisClient.get(CACHE_TASKID_KEY.replace("taskId", taskId));
        Object object = SerializeUtil.unserialize(outBytes);
        if (object instanceof CarrierTianjiCacheVo) {
            return (CarrierTianjiCacheVo) object;
        }
        throw new BizException(ErrorCodeEnums.CARRIER_CACHE_ERROR);
    }

    private String transformTianjiToRong(String data) {
        //报表数据对象
        JSONObject dataObject = JSONObject.parseObject(data);
        JSONObject mobileobject = new JSONObject();
        //基础信息mobile.borrower
        JSONObject userdataObject = dataObject.getJSONObject("userdata");
        JSONObject userObject = userdataObject;
        //通话记录mobile.tel[].teldata[]
        JSONArray teldataArray = dataObject.getJSONArray("teldata");
        JSONArray telArray = new JSONArray();
        for (int i = 0; i < teldataArray.size(); i++) {
            JSONObject teldataObject = teldataArray.getJSONObject(i);
            JSONObject telObject = new JSONObject();
            telObject.put("month", teldataObject.getString("month"));
            telObject.put("total_size", teldataObject.getString("total_size"));
            telObject.put("teldata", teldataObject.getJSONArray("items"));
            telArray.add(telObject);
        }
        //短信信息mobile.msg[].msgdata[]
        JSONArray msgdataArray = dataObject.getJSONArray("msgdata");
        JSONArray msgArray = new JSONArray();
        for (int i = 0; i < msgdataArray.size(); i++) {
            JSONObject msgdataObject = msgdataArray.getJSONObject(i);
            JSONObject msgObject = new JSONObject();
            msgObject.put("month", msgdataObject.getString("month"));
            msgObject.put("total_size", msgdataObject.getString("total_size"));
            msgObject.put("msgdata", msgdataObject.getJSONArray("items"));
            msgArray.add(msgObject);
        }
        //月账单记录mobile.bill[]
        JSONArray billdataArray = dataObject.getJSONArray("billdata");
        JSONArray billArray = billdataArray;
        //流量值记录mobile.net[].netdata[]
        JSONArray netdataArray = dataObject.getJSONArray("netdata");
        JSONArray netArray = new JSONArray();
        for (int i = 0; i < netdataArray.size(); i++) {
            JSONObject netdataObject = netdataArray.getJSONObject(i);
            JSONObject netObject = new JSONObject();
            netObject.put("month", netdataObject.getString("month"));
            netObject.put("total_size", netdataObject.getString("total_size"));
            netObject.put("netdata", netdataObject.getJSONArray("items"));
            netArray.add(netObject);
        }
        //充值记录mobile.recharge[]
        JSONArray rechargedataArray = dataObject.getJSONArray("rechargedata");
        JSONArray rechargeArray = rechargedataArray;
        mobileobject.put("borrower", userObject);
        mobileobject.put("tel", telArray);
        mobileobject.put("msg", msgArray);
        mobileobject.put("bill", billArray);
        mobileobject.put("net", netArray);
        mobileobject.put("recharge", rechargeArray);
        mobileobject.put("netlogdata", dataObject.getJSONArray("netlogdata"));

        return JSON.toJSONString(mobileobject);
    }

}
