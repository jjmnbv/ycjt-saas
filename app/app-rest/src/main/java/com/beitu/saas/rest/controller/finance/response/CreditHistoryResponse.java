package com.beitu.saas.rest.controller.finance.response;

import com.beitu.saas.finance.entity.SaasCreditHistoryEntity;
import com.fqgj.common.api.Page;
import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author xiaochong
 * @create 2018/3/28 下午10:35
 * @description
 */
@ApiModel
public class CreditHistoryResponse implements ResponseData {

    private List<CreditHistoryItem> list;

    private Page page;

    public CreditHistoryResponse(List<SaasCreditHistoryEntity> list, Page page) {
        this.list = new ArrayList<>();
        list.forEach(obj -> {
            CreditHistoryItem creditHistoryItem = new CreditHistoryItem();
            BeanUtils.copyProperties(obj, creditHistoryItem);
            this.list.add(creditHistoryItem);
        });
        this.page = page;
    }

    public List<CreditHistoryItem> getList() {
        return list;
    }

    public void setList(List<CreditHistoryItem> list) {
        this.list = list;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    class CreditHistoryItem {

        private Long id;

        @ApiModelProperty("0：支出，1：收入")
        private Integer opType;

        @ApiModelProperty("点券使用量")
        private Long credit;

        @ApiModelProperty("备注信息")
        private String comment;

        @ApiModelProperty("操作人")
        private String opName;

        @ApiModelProperty("时间")
        private Date gmtCreate;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Integer getOpType() {
            return opType;
        }

        public void setOpType(Integer opType) {
            this.opType = opType;
        }

        public Long getCredit() {
            return credit;
        }

        public void setCredit(Long credit) {
            this.credit = credit;
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
