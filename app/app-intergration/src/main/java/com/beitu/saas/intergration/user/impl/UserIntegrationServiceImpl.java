package com.beitu.saas.intergration.user.impl;

import com.beitu.saas.common.config.ConfigUtil;
import com.beitu.saas.common.utils.HttpClientUtil;
import com.beitu.saas.intergration.user.UserIntegrationService;
import com.beitu.saas.intergration.user.consts.YoufenApiResCodeConst;
import com.beitu.saas.intergration.user.consts.YoufenApiStatusCodeConst;
import com.beitu.saas.intergration.user.dto.UserNameIdNoValidationDto;
import com.beitu.saas.intergration.user.enums.UserNameIdNoValidationCodeEnum;
import com.beitu.saas.intergration.user.param.UserNameIdNoValidationParam;
import com.beitu.saas.intergration.user.pojo.UserNameIdNoValidationDataPojo;
import com.beitu.saas.intergration.user.pojo.UserNameIdNoValidationPojo;
import com.fqgj.common.utils.JSONUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public class UserIntegrationServiceImpl implements UserIntegrationService {
    
    @Autowired
    private ConfigUtil configUtil;
    
    @Override
    public UserNameIdNoValidationDto userNameMatchIdNo(UserNameIdNoValidationParam param) {
        if (param == null) {
            return new UserNameIdNoValidationDto(UserNameIdNoValidationCodeEnum.OTHER_ERROR, "校验输入参数为空");
        }
        String paramValidateResult = param.validate();
        if (StringUtils.isNotEmpty(paramValidateResult)) {
            return new UserNameIdNoValidationDto(UserNameIdNoValidationCodeEnum.OTHER_ERROR, paramValidateResult);
        }
    
        String url = configUtil.getYoufenValidationApiUrl() + configUtil.getYoufenValidationNameIdcardPath();
        String response = HttpClientUtil.doGet(url + "?account=yangcongtest&name=尉世豪&idcard=140221199701272151");
        if (response.contains("<")) {
            return new UserNameIdNoValidationDto(UserNameIdNoValidationCodeEnum.OTHER_ERROR, "接口返回异常");
        }
    
        UserNameIdNoValidationPojo pojo = null;
        try {
            pojo = JSONUtils.json2pojoAndOffUnknownField(response, UserNameIdNoValidationPojo.class);
        } catch (Exception e) {
        }
        if (pojo == null) {
            return new UserNameIdNoValidationDto(UserNameIdNoValidationCodeEnum.OTHER_ERROR, "接口返回数据异常");
        }
        
        if (!Objects.equals(pojo.getResCode(), YoufenApiResCodeConst.SUCCESS)) {
            return new UserNameIdNoValidationDto(UserNameIdNoValidationCodeEnum.RES_ERROR, pojo.getResMsg());
        }
    
        UserNameIdNoValidationDataPojo dataPojo = pojo.getData();
        if (dataPojo == null) {
            return new UserNameIdNoValidationDto(UserNameIdNoValidationCodeEnum.OTHER_ERROR, "接口返回数据异常");
        }
        
        String msg = dataPojo.getStatusMsg();
        if (Objects.equals(dataPojo.getStatusCode(), YoufenApiStatusCodeConst.FOUND_NO_RESULT) ||
                Objects.equals(dataPojo.getStatusCode(), YoufenApiStatusCodeConst.SYSTEM_ERROR)) {
            return new UserNameIdNoValidationDto(UserNameIdNoValidationCodeEnum.OTHER_ERROR, msg);
        }
    
        if (Objects.equals(dataPojo.getStatusCode(), YoufenApiStatusCodeConst.RESULT_MISMATCH)) {
            return new UserNameIdNoValidationDto(UserNameIdNoValidationCodeEnum.MISMATCH, msg);
        }
    
        if (Objects.equals(dataPojo.getStatusCode(), YoufenApiStatusCodeConst.RESULT_MATCH)) {
            return new UserNameIdNoValidationDto(UserNameIdNoValidationCodeEnum.MATCH, msg);
        }
        
        return new UserNameIdNoValidationDto(UserNameIdNoValidationCodeEnum.OTHER_ERROR, "接口未知错误");
    }
    
}
