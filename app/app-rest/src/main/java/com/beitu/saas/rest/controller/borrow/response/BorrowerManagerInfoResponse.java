package com.beitu.saas.rest.controller.borrow.response;

import com.beitu.saas.app.application.borrower.vo.BorrowerManagerInfoVo;
import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * @author linanjun
 * @create 2018/3/21 下午3:08
 * @description
 */
@ApiModel(value = "客户信息")
public class BorrowerManagerInfoResponse implements ResponseData {

    private List<BorrowerManagerInfoVo> borrowerManagerInfoVos=new ArrayList<>();

    public BorrowerManagerInfoResponse(List<BorrowerManagerInfoVo> borrowerManagerInfoVos) {
        this.borrowerManagerInfoVos = borrowerManagerInfoVos;
    }

    public List<BorrowerManagerInfoVo> getBorrowerManagerInfoVos() {
        return borrowerManagerInfoVos;
    }

    public BorrowerManagerInfoResponse setBorrowerManagerInfoVos(List<BorrowerManagerInfoVo> borrowerManagerInfoVos) {
        this.borrowerManagerInfoVos = borrowerManagerInfoVos;
        return this;
    }
}