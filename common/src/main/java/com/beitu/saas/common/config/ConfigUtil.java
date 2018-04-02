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

    public String getAppCode() {
        return config.getProperty("server.test", "");
    }

    public String getReviewUrl() {
        return config.getProperty("app.reviewUrl", "");
    }

    public String getAppVersion() {
        return config.getProperty("app.version", "");
    }

    public String getIosForceUpdateUrl() {
        return config.getProperty("app.forceUpdate.iosUrl", "");
    }

    public String getAndroidForceUpdateUrl() {
        return config.getProperty("app.forceUpdate.androidUrl", "");
    }

    public String getForceUpdateDesc() {
        return config.getProperty("app.forceUpdate.desc", "");
    }

    public int getIosForceUpdateMinVersionCode() {
        return config.getIntProperty("app.forceUpdate.iosMinVersionCode", 0);
    }

    public int getAndroidForceUpdateMinVersionCode() {
        return config.getIntProperty("app.forceUpdate.androidMinVersionCode", 0);
    }

    public String getAccessKeyId() {
        return config.getProperty("oss.accessKeyId", "");
    }

    public String getAccessKeySecret() {
        return config.getProperty("oss.accessKeySecret", "");
    }

    public String getRegion() {
        return config.getProperty("oss.region", "");
    }

    public String getEndpoint() {
        return config.getProperty("oss.endpoint", "");
    }

    public String getBucketName() {
        return config.getProperty("oss.bucketName", "");
    }

    public String getPictureURLPrefix() {
        return config.getProperty("oss.pictureURLPrefix", "");
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

    public boolean getCarrierH5Switch() {
        return config.getBooleanProperty("carrier.h5.switch", true);
    }

    public String getApiWebPath() {
        return config.getProperty("api.web.path", "");
    }

    public String getApiWebPathHttp() {
        return config.getProperty("api.web.path.http", "");
    }

    public String getPasswordSecret() {
        return config.getProperty("transaction.password.secret", "");
    }

    public String getRechargeBaseFee() {
        return config.getProperty("bank.order.recharge.base.fee", "");
    }

    public String getWithdrawBaseFee() {
        return config.getProperty("bank.order.withdraw.base.fee", "");
    }

    public String getWithdrawFeeRate() {
        return config.getProperty("bank.order.withdraw.fee.rate", "");
    }

    public String getRechargeMax() {
        return config.getProperty("pay.recharge.max.value", "");
    }

    public boolean getRechargeWithdrawMinimumSwitch() {
        return config.getBooleanProperty("pay.recharge.withdraw.minimum.switch", false);
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
    public long getUserReportInitCreditValue() {
        return config.getLongProperty("report.init.credit.value", 6L);
    }

    public int getUserReportConsumeCreditValue() {
        return config.getIntProperty("report.credit.consume", 1);
    }

    public double getReportGeneratePrice() {
        return config.getDoubleProperty("report.generate.price", 19.9D);
    }

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

    public String getMeiqiaGroupId() {
        return config.getProperty("meiqia.groupId", "");
    }

    public String getMeiqiaAgentId() {
        return config.getProperty("meiqia.agentId", "");
    }

    /**
     * add by linanjun 文件导出的格式
     * 值有两种选项：.xlsx OR .csv
     *
     * @return
     */
    public String getExportFileType() {
        return config.getProperty("export.file.type", "");
    }

    public String getInvitationCodes() {
        return config.getProperty("invitation.code", "");
    }

    public boolean getSwaggerEnable() {
        return config.getBooleanProperty("swagger.enable", false);
    }

    //crawl.taobao.url 淘宝的爬取地址
    public String getCrawlTaoBaoUrl() {
        return config.getProperty("crawl.taobao.url", "");
    }

    //crawl.jd.url 京东的爬取地址
    public String getCrawlJDUrl() {
        return config.getProperty("crawl.jd.url", "");
    }

    //crawl.query.url  查询
    public String getCrawlQueryUrl() {
        return config.getProperty("crawl.query.url", "");
    }

    //京东爬取次数
    public int getCrwalJDTimes() {
        return config.getIntProperty("crawl.jd.times", 6);
    }

    //淘宝爬取次数
    public int getCrwalTaoBaoTimes() {
        return config.getIntProperty("crawl.taoBao.times", 9);
    }

    //京东爬取间隔
    public int getCrwalJDInterval() {
        return config.getIntProperty("crawl.jd.interval", 10);
    }

    //淘宝爬取间隔
    public int getCrwalTaoBaoInterval() {
        return config.getIntProperty("crawl.taobao.interval", 5);
    }

    public String getFilterHotProvinces() {
        return config.getProperty("filter.hot.provinces", "");
    }

    public int getFilterZmFloor() {
        return config.getIntProperty("filter.zm.floor", 350);
    }

    public int getFilterZmCeil() {
        return config.getIntProperty("filter.zm.ceil", 950);
    }

    public int getFilterContactCountFloor() {
        return config.getIntProperty("filter.contact.count.floor", 0);
    }

    public int getFilterContactCountCeil() {
        return config.getIntProperty("filter.contact.count.ceil", 120);
    }

    public int getFilterTotalLimit() {
        return config.getIntProperty("fliter.total.limit", 1000);
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

    public String getTaskWithholdIp() {
        return config.getProperty("task.withhold.ip", "");
    }

    public String getTaskPushIp() {
        return config.getProperty("task.push.ip", "");
    }

    public String getRecommendFilterBlackList() {
        return config.getProperty("recommend.filter.black.list", "");
    }

    public String getRecommendFilterWhiteList() {
        return config.getProperty("recommend.filter.white.list", "all");
    }

    public int getFaceCjpMaxCount() {
        return config.getIntProperty("face.cjp.max.count", 6);
    }

    public String getLoginAlertTitle() {
        return config.getProperty("login.alert.title", "");
    }

    public String getLoginAlertMsg() {
        return config.getProperty("login.alert.message", "");
    }

    public int getLoginAlertCountDown() {
        return config.getIntProperty("login.alert.countdown", 0);
    }

    public String getPushInterval() {
        return config.getProperty("push.interval", "1,6,12");
    }

    public String getOrderLockPushInterval() {
        return config.getProperty("order.lock.push.interval", "1,6,12,24,72");
    }

    public int getOrderDistributeTime() {
        return config.getIntProperty("order.distribute.time", 24);
    }

    public int getOrderDistributeNum() {
        return config.getIntProperty("order.distribute.num", 50);
    }

    public String getBorrowAlertTitle() {
        return config.getProperty("borrow.alert.title", "");
    }

    public String getBorrowAlertMsg() {
        return config.getProperty("borrow.alert.message", "");
    }

    public int getBorrowAlertCountDown() {
        return config.getIntProperty("borrow.alert.countdown", 0);
    }

    public Long getBorrowMaxAmount() {
        return config.getLongProperty("borrow.max.amount", 200000L);
    }

    public String getReceivedAlertMsg() {
        return config.getProperty("received.alert.message", "");
    }

    public int getReceivedAlertCountDown() {
        return config.getIntProperty("received.alert.countdown", 0);
    }

    public int getMaxDailyReceiveCompensation() {
        return config.getIntProperty("received.max.daily.compensation", 10);
    }

    public int getMaxExtendNum() {
        return config.getIntProperty("max.extend.num", 1000);
    }

    public String getMerchantDetailUrl() {
        return config.getProperty("merchant.detail.h5.url", "");
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

    public String getH5TianjiCarrierNotifyUrl() {
        return config.getProperty("carrier.tianji.notify.url", "");
    }
    /************* risk end *************/

    public String getH5AddressURLPrefix() {
        return config.getProperty("oss.h5.addressURLPrefix", "");
    }

    /**
     * 点券和短信日清算开关
     */
    public String getClearDayStatSwith() {
        return config.getProperty("clear.day.stat", "");
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

    public Boolean enableAddMerchant(){
        return config.getBooleanProperty("enable.add.merchant",false);

    }

    public int allowAccountNum(){
        return config.getIntProperty("allow.account.num",20);
    }

}
