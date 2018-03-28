package com.beitu.saas.rest.controller.system;

import com.beitu.saas.app.api.DataApiResponse;
import com.beitu.saas.app.enums.EducationMsgCodeEnum;
import com.beitu.saas.app.enums.MaritalStatusMsgCodeEnum;
import com.beitu.saas.app.enums.QueryRepaymentDtEnum;
import com.beitu.saas.rest.controller.system.response.EnumResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author linanjun
 * @create 2018/3/25 下午5:13
 * @description
 */
@Controller
@RequestMapping("/system")
@Api(description = "系统参数模块")
public class SystemController {

    @RequestMapping(value = "/enum/education", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "学历枚举信息", response = EnumResponse.class)
    public DataApiResponse<EnumResponse> getEducationEnum() {
        return new DataApiResponse<>(new EnumResponse(EducationMsgCodeEnum.values()));
    }

    @RequestMapping(value = "/enum/marital", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "婚姻枚举信息", response = EnumResponse.class)
    public DataApiResponse<EnumResponse> getMaritalEnum() {
        return new DataApiResponse<>(new EnumResponse(MaritalStatusMsgCodeEnum.values()));
    }

    @RequestMapping(value = "/enum/query/repayment", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "婚姻枚举信息", response = EnumResponse.class)
    public DataApiResponse<EnumResponse> getQueryRepaymentEnum() {
        return new DataApiResponse<>(new EnumResponse(QueryRepaymentDtEnum.values()));
    }

}
