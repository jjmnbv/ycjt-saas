package com.beitu.saas.common.config;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import org.springframework.stereotype.Component;

/**
 * Created by linchengyu on 17/7/13.
 */
@Component
public class ConfigUtil {

    @ApolloConfig("DEV.BEITU_PUBLIC")
    private Config publicConfig; //inject config for namespace application

    @ApolloConfig("DEV.SYSTEM_PUBLIC_CONFIG")
    private Config systemPublicConfig; //inject config for namespace application

    @ApolloConfig
    private Config config; //inject config for namespace application

    public boolean isServerTest() {
        return systemPublicConfig.getBooleanProperty("server.test", false);
    }

    public boolean isServerPre() {
        return config.getBooleanProperty("server.pre", false);
    }

    public boolean shouldPreload() {
        return config.getBooleanProperty("server.preload.data", true);
    }

    public String getAccessKeyId() {
        return config.getProperty("oss.accessKeyId", "");
    }

    public String getAccessKeySecret() {
        return config.getProperty("oss.accessKeySecret", "");
    }

    public String getEndpoint() {
        return config.getProperty("oss.endpoint", "");
    }

    public String getBucketName() {
        return config.getProperty("oss.bucketName", "");
    }

    public String getAddressURLPrefix() {
        return config.getProperty("oss.addressURLPrefix", "");
    }

    public String getAppId() {
        return config.getProperty("zmxy.appId", "");
    }

    public String getPrivateKey() {
        return config.getProperty("zmxy.privateKey", "");
    }

    public String getPublicKey() {
        return config.getProperty("zmxy.publicKey", "");
    }

    public boolean getZmFaceSwitch() {
        return config.getBooleanProperty("zm.face.switch", false);
    }

    public boolean getZmScoreSwitch() {
        return config.getBooleanProperty("zm.score.switch", false);
    }

    public String getApiWebPath() {
        return config.getProperty("api.web.path", "");
    }

    public String getAESKey() {
        return config.getProperty("AES.key", "");
    }

    public String getAESIv() {
        return config.getProperty("AES.iv", "");
    }

    public String getVerifyCodeReviewMobile() {
        return config.getProperty("verify.code.review.mobile", "18958182526");
    }

    public String getVerifyCodeReviewCode() {
        return config.getProperty("verify.code.review.value", "8888");
    }

    public String getVerifyCodeTestCode() {
        return config.getProperty("verify.code.test.value", "1234");
    }

    public int getVerifyCodeLength() {
        return config.getIntProperty("verify.code.length", 4);
    }

    //    User Report View Config
    public String getEbUrl() {
        return config.getProperty("hessian.eb.crawler", "");
    }

    public String getPdfPath() {
        return config.getProperty("contract.pdf.path", "");
    }

    public String getDhbApiKey() {
        return config.getProperty("dhb.api.key", "");
    }

    public String getDhbAppSecret() {
        return config.getProperty("dhb.app.secret", "");
    }

    public String getZmxyAppId() {
        return config.getProperty("zmxy.appId", "");
    }

    public String getZmxyMerchantId() {
        return config.getProperty("zmxy.merchantId", "");
    }

    public String getZmxyPrivateKey() {
        return config.getProperty("zmxy.privateKey", "");
    }

    public String getZmxyZhimaPublicKey() {
        return config.getProperty("zmxy.publicKey", "");
    }

    public boolean getSwaggerEnable() {
        return config.getBooleanProperty("swagger.enable", false);
    }

    public String getESignProjectId() {
        return config.getProperty("esign.project.id", "");
    }

    public String getESignProjectSecret() {
        return config.getProperty("esign.project.secret", "");
    }

    public String getApisUrl() {
        return config.getProperty("esign.apis.url", "");
    }

    /**
     * sms
     *
     * @return
     */
    public int getMessageSwitchTime() {
        return config.getIntProperty("message.switch.time", 10 * 60);
    }

    /**
     * sms 测试白名单
     *
     * @return
     */
    public String getTestWhiteList() {
        return config.getProperty("test.white.list", "");
    }

    /************  risk begin **************/
    public String getCarrierH5TypeName() {
        return config.getProperty("carrier.carrierH5Type", "");
    }

    public String getTongdunUrl() {
        return config.getProperty("tongdun.url", "");
    }

    public String getTongdunQueryUrl() {
        return config.getProperty("tongdun.queryUrl", "");
    }

    public String getTongdunPartnerCode() {
        return config.getProperty("tongdun.partnerCode", "");
    }

    public String getTongdunPartnerKey() {
        return config.getProperty("tongdun.partnerKey", "");
    }

    public String getTongdunAppName() {
        return config.getProperty("tongdun.appName", "");
    }

    public String getJkzjZmxyAppId() {
        return config.getProperty("jkzjZmxy.appId", "1002307");
    }

    public String getJkzjZmxyPrivateKey() {
        return config.getProperty("jkzjZmxy.privateKey", "");
    }

    public String getJkzjZmxyZhimaPublicKey() {
        return config.getProperty("jkzjZmxy.zhimaPublicKey", "");
    }

    public String getZmxyFeedbackTypeId() {
        return config.getProperty("zmxy.feedbackTypeId", "");
    }

    public String getZmxyZhimaFilePath() {
        return config.getProperty("zmxy.zhimaFilePath", "");
    }

    public String getYouqianZmxyAppId() {
        return config.getProperty("youqianZmxy.appId", "");
    }

    public String getYouqianZmxyPrivateKey() {
        return config.getProperty("youqianZmxy.privateKey", "");
    }

    public String getYouqianZmxyZhimaPublicKey() {
        return config.getProperty("youqianZmxy.zhimaPublicKey", "");
    }

    /************* risk end *************/

    public String getH5AddressURL() {
        return config.getProperty("oss.h5.addressURL", "");
    }

    /**
     * 点券和短信日清算开关
     */
    public String getClearDayStatSwith() {
        return config.getProperty("clear.day.stat", "");
    }

    /**
     * 点券和短信日清算任务执行机器IP
     */
    public String getClearDayStatIpWhite() {
        return config.getProperty("clear.day.stat.ip.white", "");
    }

    /**
     * 第三方配置信息
     */
    public String getYoufenValidationApiUrl() {
        return config.getProperty("integration.youfen.validation.api.base.url", "https://api.acedata.com.cn:2443");
    }

    public String getYoufenValidationNameIdcardPath() {
        return config.getProperty("integration.youfen.validation.name.idcard.path", "/oreo/personal/validation/name/idcard");
    }

    public String getYoufenMultiDebitPath() {
        return config.getProperty("integration.youfen.verbose.union.multiDebit", "/oreo/verbose/union/multiDebit");
    }

    public String getYoufenAccount() {
        return config.getProperty("integration.youfen.account", "yangcongtest");
    }

    public String getJuXinLiApiUrl() {
        return config.getProperty("integration.juxinli.api.base.url", "https://yx.juxinli.com");
    }

    public String getJuXinLiCrawlingPath() {
        return config.getProperty("integration.juxinli.platform.crawling.path", "/#/apiui");
    }

    public String getJuXinLiQueryPath() {
        return config.getProperty("integration.juxinli.platform.query.path", "/api/user/data");
    }

    public String getJuXinLiOrgId() {
        return config.getProperty("integration.juxinli.org.id", "hsy2018");
    }

    public String getJuXinLiApiKey() {
        return config.getProperty("integration.juxinli.api.key", "1a63efd927e04f18a2e7a15b0391dc98");
    }

    public Boolean enableAddMerchant() {
        return config.getBooleanProperty("enable.add.merchant", false);

    }

    public int allowAccountNum() {
        return config.getIntProperty("allow.account.num", 20);
    }

    /**
     * 协议编号长度
     *
     * @return
     */
    public int getContractNumbLength() {
        return config.getIntProperty("contract.number.length", 10);
    }

    public int getRecommendFlowRatio() {
        return config.getIntProperty("recommend.flow.ratio", 2);
    }

    public int getShareNum() {
        return config.getIntProperty("recommend.share.num", 3);
    }

    /**
     * Open API相关
     */
    public String getOrderPushIpWhiteList() {
        return config.getProperty("open.order.push.ip.white.list", "");
    }

    public String getGXBAppId(){
        return config.getProperty("gxb.appid", "gxb576c4d54a76b8fd2");
    }
    public String getGXBAppSecret(){
        return config.getProperty("gxb.appSecret", "430009e5471f45de847c35965fe99c4a");
    }
    public String getGXBAuthPath(){
        return config.getProperty("gxb.auth.path", "https://prod.gxb.io/crawler/auth/v2/get_auth_token");
    }
    public String getGXBCrawlingPath(){
        return config.getProperty("gxb.h5.crawling.path", "https://prod.gxb.io/v2/auth");
    }

    public String getGXBPushIP(){
        return config.getProperty("gxb.push.ip", "139.196.95.4,139.224.12.200");
    }

}
