package com.beitu.saas.app.enums;

/**
 * @author xiaochong
 * @create 2018/3/31 下午5:36
 * @description
 */
public enum SaasSmsTypeEnum {

    SAAS_0002("SAAS_0002", "#company#邀请您立即申请借款。点击申请 #channel_url#"),
    SAAS_0003("SAAS_0003", "恭喜您，您通过#company#审核最高可借#10000# 点击申请 #channel_url#"),
    SAAS_0004("SAAS_0004", "您的借款申请已提交，我们会尽快审核您的申请 "),
    SAAS_0005("SAAS_0005", "渠道#channel_name#有新的进件，申请金额#money#，客户姓名#name# ，手机号#phone#"),
    SAAS_0006("SAAS_0006", "【签名】尊敬的客户，很遗憾，您的申请的借款审核未通过！感谢您的支持"),
    SAAS_0007("SAAS_0007", "【签名】您的借款已驳回，尽快修改资料重新提交吧 #channel_url#"),
    SAAS_0008("SAAS_0008", "【签名】尊敬的客户，恭喜您忙您申请的借款审核通过啦！耐心等待放款哦"),
    SAAS_0009("SAAS_0009", "【签名】#name#申请的借款已通过审核，客户电话#phone# "),
    SAAS_0010("SAAS_0010", "【签名】尊敬的客户，您有借款需要确认 #channel_url#"),
    SAAS_0011("SAAS_0011", "【签名】尊敬的客户，您申请的#money# 借款 已成功生效，借款合同已生成"),
    SAAS_0012("SAAS_0012", "【签名】#name#申请的#money# 借款 已成功生效，借款合同已生成"),
    SAAS_0013("SAAS_0013", "【签名】您好，您在#company#的账单在#date#到期，请借款处理"),
    SAAS_0014("SAAS_0014", "【签名】您有账单需要展期确认，请及时操作，点击链接 #channel_url#"),
    SAAS_0015("SAAS_0015", "【签名】您借款#money#的借款已成功展期#day#，账单到期日为#date#"),
    SAAS_0016("SAAS_0016", "【签名】您借款#money#的借款已成功核销"),
    SAAS_0017("SAAS_0017", "【签名】您在#company#有账单需要处理，请致电#company_phone# 了解详情"),
    SAAS_0018("SAAS_0018", "【签名】您账号为#account_phone#的账号在ip为#ip# #city_name# 登入，请及时确认是否本人操作"),
    SAAS_0019("SAAS_0019", "【签名】您账号为#account_phone#的账号在另外一处登入，请及时确认是否本人操作"),
    SAAS_0020("SAAS_0020", "【签名】您#company#的账户点券剩余xx，为了不影响正常使用请及时充值"),
    SAAS_0021("SAAS_0021", "【【签名】您#company#的账户短信剩余xx条，为了不影响正常使用请及时充值");

    private String bizCode;

    private String desc;

    SaasSmsTypeEnum(String bizCode, String desc) {
        this.bizCode = bizCode;
        this.desc = desc;
    }

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
