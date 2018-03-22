package com.beitu.saas.risk.handler.platform.zmxy;

import com.alibaba.fastjson.JSON;
import com.antgroup.zmxy.openplatform.api.DefaultZhimaClient;
import com.antgroup.zmxy.openplatform.api.ZhimaApiException;
import com.antgroup.zmxy.openplatform.api.request.*;
import com.antgroup.zmxy.openplatform.api.response.*;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import com.beitu.saas.risk.domain.enums.TripleStatusEnum;
import com.beitu.saas.risk.domain.exception.BizException;
import com.beitu.saas.risk.domain.platform.zmxy.antiFraud.AntiFraudApply;
import com.beitu.saas.risk.domain.platform.zmxy.antiFraud.AntiFraudAttention;
import com.beitu.saas.risk.domain.platform.zmxy.antiFraud.AntiFraudVerify;
import com.beitu.saas.risk.domain.platform.zmxy.antiFraud.TripleZmxyAntiFraudInput;
import com.beitu.saas.risk.domain.platform.zmxy.data.ZmxyMatch;
import com.beitu.saas.risk.domain.platform.zmxy.data.ZmxyScore;
import com.beitu.saas.risk.domain.platform.zmxy.score.TripleZmxyScoreOutput;
import com.beitu.saas.risk.domain.platform.zmxy.watch.TripleZmxyIvsWatchInput;
import com.beitu.saas.risk.domain.platform.zmxy.watch.TripleZmxyIvsWatchOutput;
import com.beitu.saas.risk.helpers.JSONUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author 柳朋朋
 * @Create 2017-03-18 14:23
 */
@Component
public class ZmxyData implements Serializable {
    private Log LOGGER = LogFactory.getLog(ZmxyData.class);
    private String serverUrl = "https://zmopenapi.zmxy.com.cn/openapi.do";
    private String charset = "UTF-8";


    /**
     * 芝麻分 json.
     *
     * @throws ZhimaApiException
     */
    public String queryScoreJson(String openId, ZmxyParams zmxyParams) throws Exception {
        ZhimaCreditScoreGetRequest creditScoreGetRequest = new ZhimaCreditScoreGetRequest();
        creditScoreGetRequest.setPlatform("zmop");
        creditScoreGetRequest.setChannel("api");
        //transactionId，该标记是商户每次请求的唯一标识。建议使用uuid进行传递，
        creditScoreGetRequest.setTransactionId(UUID.randomUUID().toString());
        creditScoreGetRequest.setProductCode("w1010100100000000001");
        creditScoreGetRequest.setOpenId(openId);
        DefaultZhimaClient client = new DefaultZhimaClient(serverUrl, zmxyParams.getAppId(), charset, zmxyParams.getPrivateKey(),
                zmxyParams.getZhimaPublicKey());
        ZhimaCreditScoreGetResponse creditScoreGetResponse = client.execute(creditScoreGetRequest);
        String scoreJson = creditScoreGetResponse.getBody();
        return scoreJson;
    }

    /**
     * 芝麻分.
     *
     * @throws ZhimaApiException
     */
    public TripleZmxyScoreOutput queryScore(String openId, ZmxyParams zmxyParams) throws Exception {
        TripleZmxyScoreOutput tripleZmxyScoreOutput = new TripleZmxyScoreOutput();
        tripleZmxyScoreOutput.setOpenId(openId);
        ZhimaCreditScoreGetRequest creditScoreGetRequest = new ZhimaCreditScoreGetRequest();
        creditScoreGetRequest.setPlatform("zmop");
        creditScoreGetRequest.setChannel("api");
        //transactionId，该标记是商户每次请求的唯一标识。建议使用uuid进行传递，
        creditScoreGetRequest.setTransactionId(UUID.randomUUID().toString());
        creditScoreGetRequest.setProductCode("w1010100100000000001");
        creditScoreGetRequest.setOpenId(openId);
        DefaultZhimaClient client = new DefaultZhimaClient(serverUrl, zmxyParams.getAppId(), charset, zmxyParams.getPrivateKey(),
                zmxyParams.getZhimaPublicKey());
        ZhimaCreditScoreGetResponse creditScoreGetResponse = client.execute(creditScoreGetRequest);
        String scoreJson = creditScoreGetResponse.getBody();
        ZmxyScore zmxyScore = JSONUtils.json2pojo(scoreJson, ZmxyScore.class);
        if ("true".equals(zmxyScore.getSuccess())) {
            tripleZmxyScoreOutput.setZmxyStatus(TripleStatusEnum.success.getValue());
            tripleZmxyScoreOutput.setZmScore(Integer.valueOf(zmxyScore.getZmScore()));
            tripleZmxyScoreOutput.setZmScoreBizNo(zmxyScore.getBizNo());
        } else {
            LOGGER.info("芝麻信用分查询结果为false，openId：{}，数据为：{}", openId, scoreJson);
//            tripleZmxyScoreOutput.setZmxyStatus(TripleStatusEnum.zmxy_score_fail.getValue());
            throw new BizException(TripleStatusEnum.zmxy_score_fail.getName());
        }
        return tripleZmxyScoreOutput;
    }

    /**
     * 芝麻ivs和 watch .
     *
     * @throws ZhimaApiException
     */
    public TripleZmxyIvsWatchOutput queryIvs(TripleZmxyIvsWatchInput zmxyIvsInput, ZmxyParams zmxyParams) throws Exception {
        TripleZmxyIvsWatchOutput zmxyCreditResult = new TripleZmxyIvsWatchOutput();
        ZhimaCreditIvsDetailGetRequest creditIvsGetRequest = new ZhimaCreditIvsDetailGetRequest();
        creditIvsGetRequest.setChannel("api");
        creditIvsGetRequest.setPlatform("zmop");
        //transactionId，该标记是商户每次请求的唯一标识。建议使用uuid进行传递，
        creditIvsGetRequest.setTransactionId(UUID.randomUUID().toString());
        creditIvsGetRequest.setProductCode("w1010100000000000103");
        if (StringUtils.isNotEmpty(zmxyIvsInput.getCertNo())) {//身份证
            creditIvsGetRequest.setCertNo(zmxyIvsInput.getCertNo());
        }
        if (StringUtils.isNotEmpty(zmxyIvsInput.getName())) {//姓名
            creditIvsGetRequest.setName(zmxyIvsInput.getName());
        }
        if (StringUtils.isNotEmpty(zmxyIvsInput.getMobile())) {//手机号
            creditIvsGetRequest.setMobile(zmxyIvsInput.getMobile());
        }
        if (StringUtils.isNotEmpty(zmxyIvsInput.getEmail())) {//邮箱
            creditIvsGetRequest.setEmail(zmxyIvsInput.getEmail());
        }
        if (StringUtils.isNotEmpty(zmxyIvsInput.getBankCard())) {//银行卡
            creditIvsGetRequest.setBankCard(zmxyIvsInput.getBankCard());
        }
        if (StringUtils.isNotEmpty(zmxyIvsInput.getAddress())) {//地址
            creditIvsGetRequest.setAddress(zmxyIvsInput.getAddress());
        }
        if (StringUtils.isNotEmpty(zmxyIvsInput.getWifimac())) {//wife
            creditIvsGetRequest.setWifimac(zmxyIvsInput.getWifimac());
        }
        if (StringUtils.isNotEmpty(zmxyIvsInput.getImei())) {//imei
            creditIvsGetRequest.setImei(zmxyIvsInput.getImei());
        }
        if (StringUtils.isNotEmpty(zmxyIvsInput.getImsi())) {//imsi
            creditIvsGetRequest.setImsi(zmxyIvsInput.getImsi());
        }
//        DefaultZhimaClient client = new DefaultZhimaClient(serverUrl, zmxyParams.getAppId(), charset, zmxyParams.getPrivateKey(),
//                zmxyParams.getZhimaPublicKey());
//        ZhimaCreditIvsDetailGetResponse response = client.execute(creditIvsGetRequest);
//        String ivsJson = response.getBody();
//        ZmxyIvs zmxyIvs = JSONUtils.json2pojo(ivsJson, ZmxyIvs.class);
//        if ("true".equals(zmxyIvs.getSuccess())) {
//            zmxyCreditResult.setIvsDetails(zmxyIvs.getIvsDetail());
//            zmxyCreditResult.setIvsBizNo(zmxyIvs.getBizNo());
//            zmxyCreditResult.setIvsScore(zmxyIvs.getIvsScore());
//        } else {
//            LOGGER.info("芝麻ivs接口返回false，openId:{}数据为：{}", zmxyIvsInput.getOpenId(), ivsJson);
//        }
        //关注名单
        String watchJson = queryWatch(zmxyIvsInput.getOpenId(), zmxyParams);
        ZmxyMatch zmxyMatch = JSONUtils.json2pojo(watchJson, ZmxyMatch.class);
        if ("true".equals(zmxyMatch.getSuccess())) {
            zmxyCreditResult.setWatchDetails(zmxyMatch.getDetails());
            zmxyCreditResult.setWatchBizNo(zmxyMatch.getBizNo());
            zmxyCreditResult.setWatchMatched(zmxyMatch.getMatched());
        } else {
            LOGGER.info("芝麻watchJson接口false，openId:{}数据为：{}", zmxyIvsInput.getOpenId(), watchJson);
        }
//        if (!"true".equals(zmxyIvs.getSuccess()) && !"true".equals(zmxyMatch.getSuccess())) {
//            throw new BizException(ErrorCodeEnums.PLATFORM_SUBSTRIPTION_RESULT_ERROR);
//        }
        return zmxyCreditResult;
    }

    /**
     * 测试行业关注名单接口.
     *
     * @throws ZhimaApiException
     */
    public String queryWatch(String openId, ZmxyParams zmxyParams) throws ZhimaApiException {
        ZhimaCreditWatchlistiiGetRequest creditWatchlistGetRequest = new ZhimaCreditWatchlistiiGetRequest();
        creditWatchlistGetRequest.setPlatform("zmop");//来源平台，默认为zmop
        creditWatchlistGetRequest.setChannel("api");//api:商户后台调用  apppc:商户pc端调用  app:商户移动app调用
        //transactionId，该标记是商户每次请求的唯一标识。建议使用uuid进行传递，
        creditWatchlistGetRequest.setTransactionId(UUID.randomUUID().toString());
        creditWatchlistGetRequest.setProductCode("w1010100100000000022");
        creditWatchlistGetRequest.setOpenId(openId);
        DefaultZhimaClient client = new DefaultZhimaClient(serverUrl, zmxyParams.getAppId(), charset, zmxyParams.getPrivateKey(),
                zmxyParams.getZhimaPublicKey());
        ZhimaCreditWatchlistiiGetResponse response = client.execute(creditWatchlistGetRequest);
        return response.getBody();
    }

    /**
     * 是否授权  接口.
     *
     * @throws ZhimaApiException
     */
    public Boolean zmxyIsAuthorization(String identityNo, String realName, ZmxyParams zmxyParams) throws Exception {
        ZhimaAuthInfoAuthqueryRequest req = new ZhimaAuthInfoAuthqueryRequest();
        req.setIdentityType("2");// 必要参数
        Map identityParam = new HashMap<>();
        identityParam.put("certNo", identityNo);
        identityParam.put("certType", "IDENTITY_CARD");
        identityParam.put("name", realName);
        req.setIdentityParam(JSONUtils.obj2json(identityParam));// 必要参数
        DefaultZhimaClient client = new DefaultZhimaClient(serverUrl, zmxyParams.getAppId(), zmxyParams.getPrivateKey(),
                zmxyParams.getZhimaPublicKey());
        ZhimaAuthInfoAuthqueryResponse response = client.execute(req);
        String result = (String) JSON.toJSON(response.getBody());
        Map resultMap = JSONUtils.json2map(result);
        return (Boolean) resultMap.get("authorized");
    }


    /**
     * 芝麻信用 欺诈信息验证接口
     *
     * @param zmxyIvsInput
     * @param zmxyParams
     * @return
     */
    public AntiFraudVerify zmxyAntiFraudVerify(TripleZmxyAntiFraudInput zmxyIvsInput, ZmxyParams zmxyParams) {
        ZhimaCreditAntifraudVerifyRequest req = new ZhimaCreditAntifraudVerifyRequest();
        req.setChannel("api");
        req.setPlatform("zmop");
        req.setCertType("IDENTITY_CARD");// 必要参数
        //transactionId，该标记是商户每次请求的唯一标识。建议使用uuid进行传递，
        req.setTransactionId(UUID.randomUUID().toString());
        req.setProductCode("w1010100000000002859");
        if (StringUtils.isNotEmpty(zmxyIvsInput.getCertNo())) {//身份证
            req.setCertNo(zmxyIvsInput.getCertNo());
        }
        if (StringUtils.isNotEmpty(zmxyIvsInput.getName())) {//姓名
            req.setName(zmxyIvsInput.getName());
        }
        if (StringUtils.isNotEmpty(zmxyIvsInput.getMobile())) {//手机号
            req.setMobile(zmxyIvsInput.getMobile());
        }
//        if (StringUtils.isNotEmpty(zmxyIvsInput.getEmail())) {//邮箱
//            req.setEmail(zmxyIvsInput.getEmail());
//        }
        if (StringUtils.isNotEmpty(zmxyIvsInput.getBankCard())) {//银行卡
            req.setBankCard(zmxyIvsInput.getBankCard());
        }
        if (StringUtils.isNotEmpty(zmxyIvsInput.getWifimac())) {//wife
            req.setWifimac(zmxyIvsInput.getWifimac());
        }
        if (StringUtils.isNotEmpty(zmxyIvsInput.getImei())) {//imei
            req.setImei(zmxyIvsInput.getImei());
        }
        if (StringUtils.isNotEmpty(zmxyIvsInput.getAddress())) {//地址
            req.setAddress(zmxyIvsInput.getAddress());
        }
        if (StringUtils.isNotEmpty(zmxyIvsInput.getIp())) {//ip
            req.setIp(zmxyIvsInput.getIp());
        }
        DefaultZhimaClient client = new DefaultZhimaClient(serverUrl, zmxyParams.getAppId(), zmxyParams.getPrivateKey(),
                zmxyParams.getZhimaPublicKey());
        try {
            ZhimaCreditAntifraudVerifyResponse response = client.execute(req);
            String result = (String) JSON.toJSON(response.getBody());
            AntiFraudVerify antiFraudVerify = JSONUtils.json2pojo(result, AntiFraudVerify.class);
            if (antiFraudVerify.getSuccess().equals("false")) {
                LOGGER.info("芝麻信用 欺诈信息验证接口查询失败,入参{},返回json",
                        JSONUtils.obj2json(zmxyIvsInput), result);
                return null;
            }
            return antiFraudVerify;
        } catch (Exception e) {
            LOGGER.info("芝麻信用 欺诈信息验证接口异常，数据为：{}", JSONUtils.obj2jsonNoException(zmxyIvsInput));
        }
        return null;
    }


    /**
     * 芝麻信用 申请欺诈评分
     *
     * @param zmxyIvsInput
     * @param zmxyParams
     * @return
     */
    public AntiFraudApply zmxyAntiFraudApply(TripleZmxyAntiFraudInput zmxyIvsInput, ZmxyParams zmxyParams) {
        ZhimaCreditAntifraudScoreGetRequest req = new ZhimaCreditAntifraudScoreGetRequest();
        req.setChannel("api");
        req.setPlatform("zmop");
        req.setCertType("IDENTITY_CARD");// 必要参数
        //transactionId，该标记是商户每次请求的唯一标识。建议使用uuid进行传递，
        req.setTransactionId(UUID.randomUUID().toString());
        req.setProductCode("w1010100003000001100");
        if (StringUtils.isNotEmpty(zmxyIvsInput.getCertNo())) {//身份证
            req.setCertNo(zmxyIvsInput.getCertNo());
        }
        if (StringUtils.isNotEmpty(zmxyIvsInput.getName())) {//姓名
            req.setName(zmxyIvsInput.getName());
        }
        if (StringUtils.isNotEmpty(zmxyIvsInput.getMobile())) {//手机号
            req.setMobile(zmxyIvsInput.getMobile());
        }
//        if (StringUtils.isNotEmpty(zmxyIvsInput.getEmail())) {//邮箱
//            req.setEmail(zmxyIvsInput.getEmail());
//        }
        if (StringUtils.isNotEmpty(zmxyIvsInput.getBankCard())) {//银行卡
            req.setBankCard(zmxyIvsInput.getBankCard());
        }
        if (StringUtils.isNotEmpty(zmxyIvsInput.getWifimac())) {//wife
            req.setWifimac(zmxyIvsInput.getWifimac());
        }
        if (StringUtils.isNotEmpty(zmxyIvsInput.getImei())) {//imei
            req.setImei(zmxyIvsInput.getImei());
        }
        if (StringUtils.isNotEmpty(zmxyIvsInput.getAddress())) {//地址
            req.setAddress(zmxyIvsInput.getAddress());
        }
        if (StringUtils.isNotEmpty(zmxyIvsInput.getIp())) {//ip
            req.setIp(zmxyIvsInput.getIp());
        }
        DefaultZhimaClient client = new DefaultZhimaClient(serverUrl, zmxyParams.getAppId(), zmxyParams.getPrivateKey(),
                zmxyParams.getZhimaPublicKey());
        try {
            ZhimaCreditAntifraudScoreGetResponse response = client.execute(req);
            String result = (String) JSON.toJSON(response.getBody());
            AntiFraudApply antiFraudApply = JSONUtils.json2pojo(result, AntiFraudApply.class);
            if (antiFraudApply.getSuccess().equals("false")) {
                LOGGER.info("芝麻信用 欺诈信息验证接口查询失败,入参{},返回json",
                        JSONUtils.obj2json(zmxyIvsInput), result);
                return null;
            }
            return antiFraudApply;
        } catch (Exception e) {
            LOGGER.info("芝麻信用 申请欺诈评分接口异常，数据为：{}", JSONUtils.obj2jsonNoException(zmxyIvsInput));
        }
        return null;
    }


    /**
     * 芝麻信用 欺诈关注清单
     *
     * @param zmxyIvsInput
     * @param zmxyParams
     * @return
     */
    public AntiFraudAttention zmxyAntiFraudAttention(TripleZmxyAntiFraudInput zmxyIvsInput, ZmxyParams zmxyParams) {
        ZhimaCreditAntifraudRiskListRequest req = new ZhimaCreditAntifraudRiskListRequest();
        req.setChannel("api");
        req.setPlatform("zmop");
        req.setCertType("IDENTITY_CARD");// 必要参数
        //transactionId，该标记是商户每次请求的唯一标识。建议使用uuid进行传递，
        req.setTransactionId(UUID.randomUUID().toString());
        req.setProductCode("w1010100003000001283");
        if (StringUtils.isNotEmpty(zmxyIvsInput.getCertNo())) {//身份证
            req.setCertNo(zmxyIvsInput.getCertNo());
        }
        if (StringUtils.isNotEmpty(zmxyIvsInput.getName())) {//姓名
            req.setName(zmxyIvsInput.getName());
        }
        if (StringUtils.isNotEmpty(zmxyIvsInput.getMobile())) {//手机号
            req.setMobile(zmxyIvsInput.getMobile());
        }
//        if (StringUtils.isNotEmpty(zmxyIvsInput.getEmail())) {//邮箱
//            req.setEmail(zmxyIvsInput.getEmail());
//        }
        if (StringUtils.isNotEmpty(zmxyIvsInput.getBankCard())) {//银行卡
            req.setBankCard(zmxyIvsInput.getBankCard());
        }
        if (StringUtils.isNotEmpty(zmxyIvsInput.getImei())) {//imei
            req.setImei(zmxyIvsInput.getImei());
        }

        if (StringUtils.isNotEmpty(zmxyIvsInput.getWifimac())) {//wife
            req.setWifimac(zmxyIvsInput.getWifimac());
        }
        if (StringUtils.isNotEmpty(zmxyIvsInput.getAddress())) {//地址
            req.setAddress(zmxyIvsInput.getAddress());
        }
        if (StringUtils.isNotEmpty(zmxyIvsInput.getIp())) {//ip
            req.setIp(zmxyIvsInput.getIp());
        }
        DefaultZhimaClient client = new DefaultZhimaClient(serverUrl, zmxyParams.getAppId(), zmxyParams.getPrivateKey(),
                zmxyParams.getZhimaPublicKey());
        try {
            ZhimaCreditAntifraudRiskListResponse response = client.execute(req);
            String result = (String) JSON.toJSON(response.getBody());
            AntiFraudAttention antiFraudAttention = JSONUtils.json2pojo(result, AntiFraudAttention.class);
            if (antiFraudAttention.getSuccess().equals("false")) {
                LOGGER.info("芝麻信用 欺诈信息验证接口查询失败入参{},返回json",
                        JSONUtils.obj2json(zmxyIvsInput), result);
                return null;
            }
            return antiFraudAttention;
        } catch (Exception e) {
            LOGGER.info("芝麻信用 欺诈关注清单接口异常，数据为：{}", JSONUtils.obj2jsonNoException(zmxyIvsInput));
        }
        return null;
    }
}
