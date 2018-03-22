package com.beitu.saas.risk.handler.carrier.h5.tianji.vo;

/**
 * Created by zwh @yuntu-inc.com
 *
 * @description
 * @create 2017/10/31 0031 下午 4:47
 */
public class CarrierTianjiNotifyVo {
    /**
     * 传入的userId，传入为空时返回系统自动生成userId(回调)
     */
    private String userCode;
    /**
     * 调用方生成的会话唯一标识id(回调)
     */
    private String outUniqueId;
    /**
     * 状态 ‘login’(回调)
     */
    private String state;
    /**
     * 查询的账户名
     */
    private String account;
    /**
     * 查询ID
     */
    private String searchId;

    public String getUserCode() {
        return userCode;
    }

    public CarrierTianjiNotifyVo setUserCode(String userCode) {
        this.userCode = userCode;
        return this;
    }

    public String getOutUniqueId() {
        return outUniqueId;
    }

    public CarrierTianjiNotifyVo setOutUniqueId(String outUniqueId) {
        this.outUniqueId = outUniqueId;
        return this;
    }

    public String getState() {
        return state;
    }

    public CarrierTianjiNotifyVo setState(String state) {
        this.state = state;
        return this;
    }

    public String getAccount() {
        return account;
    }

    public CarrierTianjiNotifyVo setAccount(String account) {
        this.account = account;
        return this;
    }

    public String getSearchId() {
        return searchId;
    }

    public CarrierTianjiNotifyVo setSearchId(String searchId) {
        this.searchId = searchId;
        return this;
    }
}
