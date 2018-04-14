package com.beitu.saas.app.application.credit.vo;

import com.beitu.saas.intergration.risk.pojo.LoanPlatformBankCardPojo;
import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;

@ApiModel(value = "借贷平台银行卡信息")
public class LoanPlatformBankCardVo implements ResponseData {
    
    @ApiModelProperty(value = "名称")
    private String cardName;
    
    @ApiModelProperty(value = "卡号")
    private String cardNum;
    
    @ApiModelProperty(value = "类型")
    private String cardType;
    
    @ApiModelProperty(value = "预留手机号")
    private String reservePhone;
    
    public String getCardName() {
        return cardName;
    }
    
    public void setCardName(String cardName) {
        this.cardName = cardName;
    }
    
    public String getCardNum() {
        return cardNum;
    }
    
    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }
    
    public String getCardType() {
        return cardType;
    }
    
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
    
    public String getReservePhone() {
        return reservePhone;
    }
    
    public void setReservePhone(String reservePhone) {
        this.reservePhone = reservePhone;
    }
    
    public static LoanPlatformBankCardVo convertPojoToVo(LoanPlatformBankCardPojo pojo) {
        if (pojo == null) {
            return null;
        }
        LoanPlatformBankCardVo vo = new LoanPlatformBankCardVo();
        BeanUtils.copyProperties(pojo, vo);
        return vo;
    }
}
