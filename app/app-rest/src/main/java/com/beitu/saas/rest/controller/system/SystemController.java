package com.beitu.saas.rest.controller.system;

import com.beitu.saas.app.api.DataApiResponse;
import com.beitu.saas.app.common.RequestLocalInfo;
import com.beitu.saas.app.enums.*;
import com.beitu.saas.auth.entity.SaasAdmin;
import com.beitu.saas.channel.client.SaasChannelService;
import com.beitu.saas.channel.entity.SaasChannelEntity;
import com.beitu.saas.channel.param.SaasChannelParam;
import com.beitu.saas.rest.controller.system.response.EnumResponse;
import com.beitu.saas.risk.helpers.CollectionUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author linanjun
 * @create 2018/3/25 下午5:13
 * @description
 */
@Controller
@RequestMapping("/system")
@Api(description = "系统参数模块")
public class SystemController {

    @Autowired
    private SaasChannelService saasChannelService;

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
    @ApiOperation(value = "应还日期枚举信息", response = EnumResponse.class)
    public DataApiResponse<EnumResponse> getQueryRepaymentEnum() {
        return new DataApiResponse<>(new EnumResponse(QueryRepaymentDtEnum.values()));
    }

    @RequestMapping(value = "/enum/loan/platform", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "多借贷平台枚举信息", response = EnumResponse.class)
    public DataApiResponse<EnumResponse> getSaasLoanPlatformEnum() {
        return new DataApiResponse<>(new EnumResponse(SaasLoanPlatformEnum.values()));
    }

    @RequestMapping(value = "/enum/channel", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "渠道列表枚举信息", response = EnumResponse.class)
    public DataApiResponse<EnumResponse> listChannel() {
        SaasAdmin saasAdmin = RequestLocalInfo.getCurrentAdmin().getSaasAdmin();
        List<SaasChannelEntity> saasChannelEntityList = saasChannelService.getAllSaasChannelList(saasAdmin.getMerchantCode());
        if (CollectionUtils.isEmpty(saasChannelEntityList)) {
            return new DataApiResponse<>();
        }
        List<EnumResponse.EnumVo> enumVoList = new ArrayList<>(saasChannelEntityList.size());
        saasChannelEntityList.forEach(saasChannelEntity -> {
            EnumResponse.EnumVo enumVo = new EnumResponse.EnumVo();
            enumVo.setCode(saasChannelEntity.getChannelCode());
            enumVo.setMsg(saasChannelEntity.getChannelName());
            enumVoList.add(enumVo);
        });
        return new DataApiResponse<>(new EnumResponse(enumVoList));
    }

    @RequestMapping(value = "/enum/lend/remark", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "下款途径枚举信息", response = EnumResponse.class)
    public DataApiResponse<EnumResponse> getSaasLendRemarkEnum() {
        return new DataApiResponse<>(new EnumResponse(SaasLendRemarkEnum.values()));
    }

}
