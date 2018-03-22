package com.beitu.saas.sms.ro;

import java.io.Serializable;
import java.util.List;

import com.beitu.saas.sms.enums.AppEnum;

/**
 * 批量推送消息
 */
public class BatchPushSendRequestRO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2399706914531218918L;
	/**
     * appCode
     */
    private AppEnum app;
    /**
     * 流水号
     */
    private String serialNum;
    /**
     * 推送消息体
     */
    private List<PushMsgInfoRO> msgInfoROs;
    
    public AppEnum getApp() {
        return app;
    }
    public void setApp(AppEnum app) {
        this.app = app;
    }
    public String getSerialNum() {
        return serialNum;
    }
    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }
    public List<PushMsgInfoRO> getMsgInfoROs() {
        return msgInfoROs;
    }
    public void setMsgInfoROs(List<PushMsgInfoRO> msgInfoROs) {
        this.msgInfoROs = msgInfoROs;
    }
    @Override
    public String toString() {
        return "BatchPushSendRequestRO [app=" + app + ", serialNum=" + serialNum + "]";
    }
    
}
