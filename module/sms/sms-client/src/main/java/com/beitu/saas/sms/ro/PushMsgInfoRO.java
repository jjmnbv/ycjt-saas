/**
 * yuntu-inc.com Inc.
 * Copyright (c) 2014-2017 All Rights Reserved.
 */
package com.beitu.saas.sms.ro;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * push消息体
 *
 * @name push 消息体
 * @author liting
 * @version $Id: PushMsgInfoRO.java, v 0.1 2017年9月14日 上午10:33:55 liting Exp $
 */
public class PushMsgInfoRO implements Serializable{
    /** TODO: detail description  */
    private static final long serialVersionUID = -6533398675302640642L;
    /**
     * 消息id
     */
    private Long msgId;
    /**
     * 1、打开唤起app  2、打开跳转h5  3、透传文案 4、打开后下载
     */
    private Integer pushType = 3;
    /**
     * 用户编码【必填】
     */
    private String userCode;
    /**
     * 客户端id【必填】
     */
    private String clientId;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容【必填】
     */
    private String content;
    /**
     * app路由地址
     * 
     */
    private String data;
    /**
     * 状态  0 未读  1 已读
     */
    private Integer status;
    /**
     * 拓展数据
     */
    private Map<String, String> extData;
    /**
     * 创建时间
     */
    private Date createTime;
    
    public Long getMsgId() {
        return msgId;
    }
    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }
    public Integer getPushType() {
        return pushType;
    }
    public void setPushType(Integer pushType) {
        this.pushType = pushType;
    }
    
    public String getUserCode() {
        return userCode;
    }
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    public String getClientId() {
        return clientId;
    }
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
     
    
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public Map<String, String> getExtData() {
        return extData;
    }
    public void setExtData(Map<String, String> extData) {
        this.extData = extData;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    @Override
    public String toString() {
        return "PushMsgInfoRO [msgId=" + msgId + ", pushType=" + pushType + ", userCode=" + userCode
               + ", clientId=" + clientId + ", title=" + title + ", content=" + content + ", data="
               + data + ", status=" + status + ", extData=" + extData + ", createTime=" + createTime
               + "]";
    }
     
}
