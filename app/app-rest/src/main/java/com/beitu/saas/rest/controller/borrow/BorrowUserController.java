package com.beitu.saas.rest.controller.borrow;

import com.beitu.saas.app.annotations.SignIgnore;
import com.beitu.saas.app.annotations.VisitorAccessible;
import com.beitu.saas.app.api.ApiResponse;
import com.beitu.saas.app.api.DataApiResponse;
import com.beitu.saas.app.application.borrower.BorrowerApplication;
import com.beitu.saas.app.application.borrower.vo.BorrowerManagerInfoVo;
import com.beitu.saas.app.common.RequestLocalInfo;
import com.beitu.saas.borrower.BorrowerInfoParam;
import com.beitu.saas.rest.controller.borrow.request.BorrowSaveUserInfoRequest;
import com.beitu.saas.rest.controller.borrow.request.BorrowUserLoginRequest;
import com.beitu.saas.rest.controller.borrow.request.BorrowerManagerInfoRequest;
import com.beitu.saas.rest.controller.borrow.response.BorrowUserLoginSuccessResponse;
import com.beitu.saas.rest.controller.borrow.response.BorrowerManagerInfoResponse;
import com.fqgj.common.api.Page;
import com.fqgj.common.response.ModuleResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

/**
 * @author linanjun
 * @create 2018/3/21 下午2:20
 * @description
 */
@Controller
@RequestMapping("/borrow/user")
@Api(description = "借款人用户模块")
public class BorrowUserController {

    @Autowired
    private BorrowerApplication borrowerApplication;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "借款人用户登录", response = BorrowUserLoginSuccessResponse.class)
    public DataApiResponse<BorrowUserLoginSuccessResponse> login(@RequestBody @Valid BorrowUserLoginRequest req) {
        // TODO
        return new DataApiResponse<>(new BorrowUserLoginSuccessResponse());
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "借款人用户信息保存", response = ApiResponse.class)
    public ApiResponse save(@RequestBody @Valid BorrowSaveUserInfoRequest req) {
        // TODO
        return new ApiResponse();
    }


    /**
     * 客户列表
     *
     * @param req
     * @param page
     * @return
     */
    @SignIgnore
    @VisitorAccessible
    @RequestMapping(value = "/borrowerInfoGet", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "客户管理类别", response = BorrowerManagerInfoResponse.class)
    public ModuleResponse getBorrowerInfoListByPage(@RequestBody BorrowerManagerInfoRequest req, Page page) {
//        String merchantCode = RequestLocalInfo.getCurrentAdmin().getSaasAdmin().getMerchantCode();
        String merchantCode = "2018040420DRUNS";
        BorrowerInfoParam borrowerInfoParam = new BorrowerInfoParam();
        BeanUtils.copyProperties(req, borrowerInfoParam);
        borrowerInfoParam.setMerchantCode(merchantCode);
        List<BorrowerManagerInfoVo> borrowerInfoList = borrowerApplication.getBorrowerInfoListByPage(borrowerInfoParam, page);
        return new ModuleResponse<>(new BorrowerManagerInfoResponse(borrowerInfoList), page);
    }

}