package com.beitu.saas.rest.controller.borrow.response;

import com.fqgj.common.api.ResponseData;
import io.swagger.annotations.ApiModel;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WatsonQiu
 * Date: 2018/2/26
 * Time: 下午7:48
 */
@ApiModel(value = "借贷统计信息")
public class UserMultiDebitResponse implements ResponseData {
    private String multiDebitInfo;

    public UserMultiDebitResponse(String multiDebitInfo) {
        this.multiDebitInfo = multiDebitInfo;
    }

    public String getMultiDebitInfo() {
        return multiDebitInfo;
    }

    public UserMultiDebitResponse setMultiDebitInfo(String multiDebitInfo) {
        this.multiDebitInfo = multiDebitInfo;
        return this;
    }
}
