package com.beitu.saas.common.handle.carrier.domain;

import java.io.Serializable;

public class CarriersBillVo implements Serializable{

	private static final long serialVersionUID = 6232154917840352099L;
	
	private String billDate;
	
	private String baseFee;
	
	private String totalFee;

	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

	public String getBaseFee() {
		return baseFee;
	}

	public void setBaseFee(String baseFee) {
		this.baseFee = baseFee;
	}

	public String getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}
}
