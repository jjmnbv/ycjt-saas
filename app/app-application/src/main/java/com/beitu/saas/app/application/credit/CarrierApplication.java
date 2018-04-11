package com.beitu.saas.app.application.credit;

import com.alibaba.fastjson.JSON;
import com.beitu.saas.app.application.credit.vo.CarrierH5CallbackVo;
import com.beitu.saas.borrower.client.SaasBorrowerRealInfoService;
import com.beitu.saas.borrower.client.SaasBorrowerService;
import com.beitu.saas.borrower.domain.SaasBorrowerRealInfoVo;
import com.beitu.saas.borrower.domain.SaasBorrowerVo;
import com.beitu.saas.borrower.enums.BorrowerErrorCodeEnum;
import com.beitu.saas.common.config.ConfigUtil;
import com.beitu.saas.common.consts.RedisKeyConsts;
import com.beitu.saas.common.consts.TimeConsts;
import com.beitu.saas.common.enums.ProductTypeEnum;
import com.beitu.saas.common.enums.RestCodeEnum;
import com.beitu.saas.common.handle.oss.OSSService;
import com.beitu.saas.credit.client.SaasCreditCarrierService;
import com.beitu.saas.credit.domain.SaasCreditCarrierVo;
import com.beitu.saas.credit.enums.CreditErrorCodeEnum;
import com.beitu.saas.finance.client.SaasCreditHistoryService;
import com.beitu.saas.finance.client.enums.CreditConsumeEnum;
import com.beitu.saas.risk.client.service.TripleCarrierService;
import com.beitu.saas.risk.domain.carrier.h5.enums.CarrierH5TypeEnum;
import com.beitu.saas.risk.domain.carrier.h5.input.CarrierRequestUrlInput;
import com.beitu.saas.risk.domain.carrier.h5.input.CarrierTaskIdInfoInput;
import com.beitu.saas.risk.domain.carrier.h5.output.CarrierRequestUrlOutput;
import com.beitu.saas.risk.domain.carrier.h5.output.CarrierTaskIdInfoOutput;
import com.beitu.saas.risk.domain.enums.triple.TripleServiceTypeEnum;
import com.beitu.saas.risk.domain.platform.vo.TripleServiceResult;
import com.fqgj.base.services.redis.RedisClient;
import com.fqgj.common.utils.MD5;
import com.fqgj.exception.common.ApplicationException;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * Created by linchengyu on 17/6/23.
 */
@Component
public class CarrierApplication {

    private static final Log LOGGER = LogFactory.getLog(CarrierApplication.class);

    @Autowired
    private RedisClient redisClient;

    @Autowired
    private OSSService ossService;

    @Autowired
    private TripleCarrierService tripleCarrierService;

    @Autowired
    private ConfigUtil configUtil;

    @Autowired
    private SaasBorrowerService saasBorrowerService;

    @Autowired
    private SaasBorrowerRealInfoService saasBorrowerRealInfoService;

    @Autowired
    private SaasCreditCarrierService saasCreditCarrierService;

    @Autowired
    private CarrierReportApplication carrierReportApplication;

    @Autowired
    private DunningReportApplication dunningReportApplication;

    @Autowired
    private SaasCreditHistoryService saasCreditHistoryService;

    public String getCarrierH5Url(String channelCode, String borrowerCode, String mobile, Integer type) {
        String taskId = redisClient.get(RedisKeyConsts.H5_CARRIER_CRAWLING, borrowerCode + "");
        if (StringUtils.isNotEmpty(taskId)) {
            throw new ApplicationException(RestCodeEnum.REPEAT_REQUEST);
        }
        SaasBorrowerRealInfoVo saasBorrowerRealInfoVo = saasBorrowerRealInfoService.getBorrowerRealInfoByBorrowerCode(borrowerCode);
        if (saasBorrowerRealInfoVo == null) {
            throw new ApplicationException(BorrowerErrorCodeEnum.USER_PROFILE_NEED_REAL_NAME);
        }
        if (saasCreditCarrierService.effectivenessCreditCarrier(borrowerCode)) {
            throw new ApplicationException(CreditErrorCodeEnum.CREDIT_CARRIER_REPORT_EXIST);
        }
        CarrierRequestUrlInput carrierRequestUrlInput = new CarrierRequestUrlInput();
        carrierRequestUrlInput.setName(saasBorrowerRealInfoVo.getName());
        carrierRequestUrlInput.setPhone(mobile);
        carrierRequestUrlInput.setIdNumber(saasBorrowerRealInfoVo.getIdentityCode());
        carrierRequestUrlInput.setUserCode(channelCode + "_" + borrowerCode);
        carrierRequestUrlInput.setReturnUrl(configUtil.getApiWebPath() + "/credit/carrier/h5/crawling");
        carrierRequestUrlInput.setAppUrl(configUtil.getApiWebPath() + "/credit/carrier/callback/1");

        redisClient.set(RedisKeyConsts.SAAS_OPEN_CARRIER_H5_BROWSER_TYPE, type, TimeConsts.TEN_MINUTES, borrowerCode);

        TripleServiceResult response = tripleCarrierService.getCarrierServiceResult(ProductTypeEnum.YCJT, TripleServiceTypeEnum.CARRIER_REQUEST_URL, carrierRequestUrlInput);
        if (response.isSuccess()) {
            CarrierRequestUrlOutput carrierRequestUrlOutput = (CarrierRequestUrlOutput) response.getData();
            return carrierRequestUrlOutput.getRedirectUrl();
        } else {
            LOGGER.error("====== CARRIER H5 ERROR: {} ======", JSON.toJSONString(response));
            throw new ApplicationException(RestCodeEnum.CARRIER_CRAWL_ERROR.setMsg(response.getMsg()));
        }
    }

    public Boolean carrierTaskAndUserMatch(String userId, String mobile, String taskId) {
        CarrierTaskIdInfoInput carrierTaskIdInfoInput = new CarrierTaskIdInfoInput();
        carrierTaskIdInfoInput.setTaskId(taskId);
        carrierTaskIdInfoInput.setMobile(mobile);

        TripleServiceResult response = tripleCarrierService.getCarrierServiceResult(ProductTypeEnum.YCJT, TripleServiceTypeEnum.CARRIER_TASKID_INFO, carrierTaskIdInfoInput);
        if (response.isSuccess()) {
            if (response.getData() == null) {
                return Boolean.FALSE;
            }
            CarrierTaskIdInfoOutput carrierTaskIdInfoOutput = (CarrierTaskIdInfoOutput) response.getData();
            return Objects.equals(userId, carrierTaskIdInfoOutput.getUserCode());
        } else {
            return Boolean.FALSE;
        }
    }

    public void carrierH5Callback(CarrierH5CallbackVo h5CallbackVo) {
        String data = h5CallbackVo.getData();
        if (StringUtils.isEmpty(data)) {
            LOGGER.error("=============carrier h5 callback empty data=============" + JSON.toJSONString(h5CallbackVo));
        }
        String status = h5CallbackVo.getStatus();
        String taskId = h5CallbackVo.getTaskId();
        String userCode = h5CallbackVo.getUserCode();
        CarrierH5TypeEnum typeEnum = h5CallbackVo.getCarrierType();
        if ("DONE_SUCCESS".equals(status)) {
            String task = redisClient.get(RedisKeyConsts.H5_CARRIER_CRAWLING, userCode);
            if (taskId.equals(task)) {
                SaasBorrowerVo saasBorrowerVo = saasBorrowerService.getByBorrowerCode(userCode);
                if (saasBorrowerVo == null) {
                    throw new ApplicationException(RestCodeEnum.BORROWER_NOT_EXIST_ERROR);
                }
                carrierH5Upload(data, typeEnum, saasBorrowerVo.getMerchantCode(), saasBorrowerVo.getBorrowerCode());
                SaasBorrowerRealInfoVo saasBorrowerRealInfoVo = saasBorrowerRealInfoService.getBorrowerRealInfoByBorrowerCode(saasBorrowerVo.getBorrowerCode());
                saasCreditHistoryService.addExpenditureCreditHistory(saasBorrowerVo.getMerchantCode(), saasBorrowerRealInfoVo.getName(), CreditConsumeEnum.RISK_CARRIER);
            }
        } else if ("DONE_FAIL".equals(status)) {
            //失败处理
        }
        redisClient.del(RedisKeyConsts.H5_CARRIER_CRAWLING, userCode);
    }

    private void carrierH5Upload(String data, CarrierH5TypeEnum typeEnum, String merchantCode, String borrowerCode) {
        String carrierUrl = "carrierData/";
        if (configUtil.isServerTest()) {
            carrierUrl += "test/";
        }

        String userTime = borrowerCode + "_" + new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        carrierUrl += typeEnum.getName() + "_" + userTime + "_" + MD5.md5(userTime + System.currentTimeMillis()) + ".json";
        carrierUrl = ossService.uploadFile(carrierUrl, data);
        SaasCreditCarrierVo addSaasCreditCarrierVo = new SaasCreditCarrierVo();
        addSaasCreditCarrierVo.setMerchantCode(merchantCode);
        addSaasCreditCarrierVo.setBorrowerCode(borrowerCode);
        addSaasCreditCarrierVo.setType(typeEnum.getType());
        addSaasCreditCarrierVo.setUrl(carrierUrl);
        addSaasCreditCarrierVo.setSuccess(Boolean.FALSE);
        saasCreditCarrierService.addSaasCreditCarrier(addSaasCreditCarrierVo);
        carrierReportApplication.generateCarrierReport(merchantCode, borrowerCode);
        dunningReportApplication.generateDunningReport(merchantCode, borrowerCode);
    }

}
