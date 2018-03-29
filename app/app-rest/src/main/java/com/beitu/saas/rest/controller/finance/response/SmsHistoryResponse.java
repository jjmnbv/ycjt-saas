package com.beitu.saas.rest.controller.finance.response;

import com.beitu.saas.finance.entity.SaasSmsHistoryEntity;
import com.fqgj.common.api.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author xiaochong
 * @create 2018/3/28 下午11:10
 * @description
 */
@ApiModel
public class SmsHistoryResponse {

    private List<SmsHistoryItem> list;

    private Page page;

    public SmsHistoryResponse(List<SaasSmsHistoryEntity> list, Page page) {
        this.list = new ArrayList<>();
        list.forEach(obj -> {
            SmsHistoryItem smsHistoryItem = new SmsHistoryItem();
            BeanUtils.copyProperties(obj, smsHistoryItem);
            this.list.add(smsHistoryItem);
        });
        this.page = page;
    }

    public List<SmsHistoryItem> getList() {
        return list;
    }

    public void setList(List<SmsHistoryItem> list) {
        this.list = list;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    class SmsHistoryItem {

        @ApiModelProperty("0：支出，1：收入")
        private Integer opType;

        @ApiModelProperty("短信条数")
        private Long sms;

        @ApiModelProperty("备注信息")
        private String comment;

        @ApiModelProperty("操作人")
        private String opName;

        @ApiModelProperty("时间")
        private Date gmtCreate;

        @ApiModelProperty("号码")
        private String sendPhone;

        public Integer getOpType() {
            return opType;
        }

        public void setOpType(Integer opType) {
            this.opType = opType;
        }

        public Long getSms() {
            return sms;
        }

        public void setSms(Long sms) {
            this.sms = sms;
        }

        public String getSendPhone() {
            return sendPhone;
        }

        public void setSendPhone(String sendPhone) {
            this.sendPhone = sendPhone;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getOpName() {
            return opName;
        }

        public void setOpName(String opName) {
            this.opName = opName;
        }

        public Date getGmtCreate() {
            return gmtCreate;
        }

        public void setGmtCreate(Date gmtCreate) {
            this.gmtCreate = gmtCreate;
        }
    }


}
