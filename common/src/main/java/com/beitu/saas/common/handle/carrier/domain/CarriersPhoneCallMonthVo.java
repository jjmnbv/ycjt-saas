package com.beitu.saas.common.handle.carrier.domain;

import java.io.Serializable;
import java.util.List;

public class CarriersPhoneCallMonthVo implements Serializable{
	
	private static final long serialVersionUID = -8185515020299249918L;

	private String month;
	
	private Integer total=0;//记录数

	private Integer calling=0;//主叫次数
	private String callingTime;//主叫时长

	private Integer called=0;//被叫次数
	private String calledTime;//被叫时长

	public String getCallingTime() {
		return callingTime;
	}

	public CarriersPhoneCallMonthVo setCallingTime(String callingTime) {
		this.callingTime = callingTime;
		return this;
	}

	public String getCalledTime() {
		return calledTime;
	}

	public CarriersPhoneCallMonthVo setCalledTime(String calledTime) {
		this.calledTime = calledTime;
		return this;
	}

	private List<CarriersPhoneCallVo> carriersPhoneCallVoList;//通话记录

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<CarriersPhoneCallVo> getCarriersPhoneCallVoList() {
		return carriersPhoneCallVoList;
	}

	public void setCarriersPhoneCallVoList(
			List<CarriersPhoneCallVo> carriersPhoneCallVoList) {
		this.carriersPhoneCallVoList = carriersPhoneCallVoList;
	}

	public Integer getCalling() {
		return calling;
	}

	public void setCalling(Integer calling) {
		this.calling = calling;
	}

	public Integer getCalled() {
		return called;
	}

	public void setCalled(Integer called) {
		this.called = called;
	}
	
}
