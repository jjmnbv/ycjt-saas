package com.beitu.saas.risk.domain.carrier.vo;

import java.io.Serializable;

//通话记录
public class CarriersPhoneCallVo implements Serializable{
	private static final long serialVersionUID = -719075044542247879L;
	private String mobile;//通话号码
	private String type;//主叫、被叫

	private String createTime;//通话时间
	private String callTime;//时长

	private String location;//通话地址
	private String locationType;//通话类型

	public String getMobile() {
		return mobile;
	}

	public CarriersPhoneCallVo setMobile(String mobile) {
		this.mobile = mobile;
		return this;
	}

	public String getType() {
		return type;
	}

	public CarriersPhoneCallVo setType(String type) {
		this.type = type;
		return this;
	}

	public String getCreateTime() {
		return createTime;
	}

	public CarriersPhoneCallVo setCreateTime(String createTime) {
		this.createTime = createTime;
		return this;
	}

	public String getCallTime() {
		return callTime;
	}

	public CarriersPhoneCallVo setCallTime(String callTime) {
		this.callTime = callTime;
		return this;
	}

	public String getLocation() {
		return location;
	}

	public CarriersPhoneCallVo setLocation(String location) {
		this.location = location;
		return this;
	}

	public String getLocationType() {
		return locationType;
	}

	public CarriersPhoneCallVo setLocationType(String locationType) {
		this.locationType = locationType;
		return this;
	}
}
