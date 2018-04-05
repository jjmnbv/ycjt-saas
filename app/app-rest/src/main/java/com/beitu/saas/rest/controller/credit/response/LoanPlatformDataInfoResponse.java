package com.beitu.saas.rest.controller.credit.response;

import com.beitu.saas.app.application.credit.vo.*;
import com.beitu.saas.intergration.risk.pojo.*;
import com.fqgj.common.api.ResponseData;
import com.fqgj.common.utils.CollectionUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApiModel(value = "多平台借贷爬取结果返回数据")
public class LoanPlatformDataInfoResponse implements ResponseData {
    
    @ApiModelProperty(value = "基本信息")
    private LoanPlatformBaseInfoVo baseInfo;
    
    @ApiModelProperty(value = "借入汇总")
    private LoanPlatformBorrowedSummaryVo borrowedSummary;
    
    @ApiModelProperty(value = "借入详情")
    private List<LoanPlatformBorrowedDetailVo> borrowedDetail;
    
    @ApiModelProperty(value = "银行卡列表")
    private List<LoanPlatformBankCardVo> bankCards;
    
    @ApiModelProperty(value = "好友列表")
    private List<LoanPlatformFriendVo> friends;
    
    @ApiModelProperty(value = "数据获取时间")
    private String createdTime;
    
    public LoanPlatformDataInfoResponse(LoanPlatformQueryPojo pojo) {
        if (pojo == null)
            return;
        LoanPlatformQueryDetailPojo detail = pojo.getDetail();
        if (detail == null)
            return;
        LoanPlatformDataPojo dataPojo = detail.getData();
        if (dataPojo == null)
            return;
        this.baseInfo = LoanPlatformBaseInfoVo.convertPojoToVo(dataPojo.getBasicInfo());
        LoanPlatformBorrowedPojo borrowedPojo = dataPojo.getBorrowed();
        if (borrowedPojo != null) {
            this.borrowedSummary = new LoanPlatformBorrowedSummaryVo();
            this.borrowedSummary.setTotal(borrowedPojo.getTotal());
            this.borrowedSummary.setTimes(borrowedPojo.getTimes());
            
            List<LoanPlatformBorrowedLendDetailPojo> details = dataPojo.getBorrowed().getBorrowedDetails();
            if (CollectionUtils.isNotEmpty(details)) {
                this.borrowedDetail = new ArrayList<>(details.size());
                details.forEach(listPojo -> this.borrowedDetail.add(LoanPlatformBorrowedDetailVo.convertPojoToVo(listPojo)));
            }
        }
        List<LoanPlatformBankCardPojo> bankCards = dataPojo.getBankCards();
        if (CollectionUtils.isNotEmpty(bankCards)) {
            this.bankCards = new ArrayList<>(bankCards.size());
            bankCards.forEach(listPojo -> this.bankCards.add(LoanPlatformBankCardVo.convertPojoToVo(listPojo)));
        }
        List<LoanPlatformFriendPojo> friends = dataPojo.getFriends();
        if (CollectionUtils.isNotEmpty(friends)) {
            this.friends = new ArrayList<>(friends.size());
            friends.forEach(listPojo -> this.friends.add(LoanPlatformFriendVo.convertPojoToVo(listPojo)));
        }
        String updateTime = detail.getUpdateTime();
        if (StringUtils.isNotEmpty(updateTime)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            this.createdTime = dateFormat.format(new Date(Long.parseLong(updateTime)));
        }
    }
    
    public LoanPlatformBaseInfoVo getBaseInfo() {
        return baseInfo;
    }
    
    public void setBaseInfo(LoanPlatformBaseInfoVo baseInfo) {
        this.baseInfo = baseInfo;
    }
    
    public LoanPlatformBorrowedSummaryVo getBorrowedSummary() {
        return borrowedSummary;
    }
    
    public void setBorrowedSummary(LoanPlatformBorrowedSummaryVo borrowedSummary) {
        this.borrowedSummary = borrowedSummary;
    }
    
    public List<LoanPlatformBorrowedDetailVo> getBorrowedDetail() {
        return borrowedDetail;
    }
    
    public void setBorrowedDetail(List<LoanPlatformBorrowedDetailVo> borrowedDetail) {
        this.borrowedDetail = borrowedDetail;
    }
    
    public List<LoanPlatformBankCardVo> getBankCards() {
        return bankCards;
    }
    
    public void setBankCards(List<LoanPlatformBankCardVo> bankCards) {
        this.bankCards = bankCards;
    }
    
    public List<LoanPlatformFriendVo> getFriends() {
        return friends;
    }
    
    public void setFriends(List<LoanPlatformFriendVo> friends) {
        this.friends = friends;
    }
    
    public String getCreatedTime() {
        return createdTime;
    }
    
    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }
}
