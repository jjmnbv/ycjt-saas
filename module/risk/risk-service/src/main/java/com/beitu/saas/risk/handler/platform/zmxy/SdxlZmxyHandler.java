package com.beitu.saas.risk.handler.platform.zmxy;

import com.antgroup.zmxy.openplatform.api.DefaultZhimaClient;
import com.antgroup.zmxy.openplatform.api.FileItem;
import com.antgroup.zmxy.openplatform.api.request.ZhimaDataBatchFeedbackRequest;
import com.antgroup.zmxy.openplatform.api.response.ZhimaDataBatchFeedbackResponse;
import com.beitu.saas.common.config.ConfigUtil;
import com.beitu.saas.risk.domain.platform.zmxy.antiFraud.*;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import com.beitu.saas.risk.domain.enums.ErrorCodeEnums;
import com.beitu.saas.risk.domain.exception.BizException;
import com.beitu.saas.risk.domain.platform.zmxy.score.TripleZmxyScoreOutput;
import com.beitu.saas.risk.domain.platform.zmxy.watch.TripleZmxyIvsWatchInput;
import com.beitu.saas.risk.domain.platform.zmxy.watch.TripleZmxyIvsWatchOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @Author 柳朋朋
 * @Create 2016-10-24 23:01
 */
@Component
public class SdxlZmxyHandler {
    private static final Log LOGGER = LogFactory.getLog(SdxlZmxyHandler.class);
    private String serverUrl = "https://zmopenapi.zmxy.com.cn/openapi.do";
    private String charset = "UTF-8";

    @Autowired
    private ZmxyData zmxyData;

    @Autowired
    private ConfigUtil configUtil;

    /**
     * 芝麻 数据的获取
     *
     * @return
     */
    public TripleZmxyScoreOutput zmxyScoreSubscription(String openId) throws Exception {
        return zmxyData.queryScore(openId, new ZmxyParams(configUtil.getZmxyAppId(), configUtil.getZmxyPrivateKey(), configUtil.getZmxyZhimaPublicKey()));
    }


    public Boolean zmxyIsAuthorization(String identityNo, String realName) throws Exception {
        return zmxyData.zmxyIsAuthorization(identityNo, realName, new ZmxyParams(configUtil.getZmxyAppId(), configUtil.getZmxyPrivateKey(), configUtil.getZmxyZhimaPublicKey()));
    }

    /**
     * 芝麻数据的查询
     *
     * @return
     */
    public TripleZmxyIvsWatchOutput zmxyIvsAndWatchSubscription(TripleZmxyIvsWatchInput zmxyIvsInput) throws Exception {
        return zmxyData.queryIvs(zmxyIvsInput, new ZmxyParams(configUtil.getZmxyAppId(), configUtil.getZmxyPrivateKey(), configUtil.getZmxyZhimaPublicKey()));
    }

    /**
     * 芝麻反欺诈信息
     *
     * @param zmxyIvsInput
     * @return
     * @throws Exception
     */
    public TripleZmxyAntiFraudOutput zmxyAntiFraudSubscription(TripleZmxyAntiFraudInput zmxyIvsInput) throws Exception {
//        LOGGER.info("芝麻信用芝麻反欺诈信息信息查询,入参为:{}", JSONUtils.obj2json(ivsQuery));
        TripleZmxyAntiFraudOutput tripleAntiFraudOutput = new TripleZmxyAntiFraudOutput();
        AntiFraudVerify antiFraudVerify = zmxyData.zmxyAntiFraudVerify(zmxyIvsInput, new ZmxyParams(configUtil.getZmxyAppId(), configUtil.getZmxyPrivateKey(), configUtil.getZmxyZhimaPublicKey()));
        if (antiFraudVerify != null) {
            tripleAntiFraudOutput
                    .setVerifyCodeList(antiFraudVerify.getVerifyCodeList())
                    .setVerifyBizNo(antiFraudVerify.getBizNo());
        }
        AntiFraudApply antiFraudApply = zmxyData.zmxyAntiFraudApply(zmxyIvsInput, new ZmxyParams(configUtil.getZmxyAppId(), configUtil.getZmxyPrivateKey(), configUtil.getZmxyZhimaPublicKey()));
        if (antiFraudApply != null) {
            tripleAntiFraudOutput.setApplyScore(antiFraudApply.getScore())
                    .setApplyBizNo(antiFraudApply.getBizNo());
        }
        AntiFraudAttention antiFraudAttention = zmxyData.zmxyAntiFraudAttention(zmxyIvsInput, new ZmxyParams(configUtil.getZmxyAppId(), configUtil.getZmxyPrivateKey(), configUtil.getZmxyZhimaPublicKey()));
        if (antiFraudAttention != null) {
            tripleAntiFraudOutput.setAttentionCodeList(antiFraudAttention.getRiskCodeList())
                    .setAttentionHit(antiFraudAttention.getHit())
                    .setAttentionBizNo(antiFraudAttention.getBizNo());
        }
        if (antiFraudVerify == null && antiFraudApply == null && antiFraudAttention == null) {
            throw new BizException(ErrorCodeEnums.PLATFORM_SUBSTRIPTION_RESULT_ERROR);
        }
        return tripleAntiFraudOutput;
    }

    /**
     * 芝麻数据回馈
     *
     * @param
     */
    public Boolean feedBackResult(String typeFileName) {
        Boolean status = false;
        String filePath = configUtil.getZmxyZhimaFilePath() + typeFileName;
        if (new File(filePath).exists()) {
            status = feedBack(filePath);
        } else {
            LOGGER.info("芝麻反馈文件：{}不存在", configUtil.getZmxyZhimaFilePath() + typeFileName);
        }
        return status;
    }

    /**
     * 芝麻数据回馈
     *
     * @param
     */
    public void feedPaidBackResult(String date) {
        String fileName = date + ".json";
        String historyFilePath = configUtil.getZmxyZhimaFilePath() + "paid" + fileName;
        if (new File(historyFilePath).exists()) {
            feedBack(historyFilePath);
        } else {
            LOGGER.info("芝麻反馈文件：{}不存在", configUtil.getZmxyZhimaFilePath() + "paid" + fileName);
        }

    }

    private Boolean feedBack(String filePath) {
        Boolean flag = false;
        try {
            ZhimaDataBatchFeedbackRequest req = new ZhimaDataBatchFeedbackRequest();
            req.setPlatform("zmop");
            req.setFileType("json_data");// 必要参数
            req.setFileCharset("UTF-8");// 必要参数
            req.setRecords("100");// 必要参数
            req.setColumns("user_name," +
                    "user_credentials_type," +
                    "user_credentials_no," +
                    "order_no," +
                    "biz_type," +
                    "order_status," +
                    "create_amt," +
                    "pay_month," +
                    "gmt_ovd_date," +
                    "overdue_cnt," +
                    "overdue_amt," +
                    "gmt_pay," +
                    "memo");// 必要参数
            req.setPrimaryKeyColumns("order_no");// 必要参数
            req.setFileDescription("昨日订单更新数据");//
            req.setTypeId(configUtil.getZmxyFeedbackTypeId());// 必要参数
            req.setFile(new FileItem(filePath));// 必要参数
            DefaultZhimaClient client = new DefaultZhimaClient(serverUrl, configUtil.getZmxyAppId(), configUtil.getZmxyPrivateKey(), configUtil.getZmxyZhimaPublicKey());

            ZhimaDataBatchFeedbackResponse response = client.execute(req);
            Boolean result = response.isSuccess();
            if (!result) {
                LOGGER.info("{}文件,芝麻反馈数据异常：errorCode{},errorMessage:{}", filePath, response.getErrorCode(), response.getErrorMessage());
            } else {
                LOGGER.info("{}文件,芝麻反馈成功", filePath);
                flag = true;
            }
        } catch (Exception e) {
            LOGGER.info("{}文件,芝麻反馈异常，异常信息为：{}", filePath, e);
        }
        return flag;
    }

    public String getFilePath() {
        return configUtil.getZmxyZhimaFilePath();
    }


}
