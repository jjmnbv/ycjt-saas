package com.beitu.saas.intergration.user.impl;

import com.alibaba.fastjson.JSONObject;
import com.beitu.saas.common.config.ConfigUtil;
import com.beitu.saas.common.handle.oss.OSSService;
import com.beitu.saas.common.utils.HttpClientUtil;
import com.beitu.saas.credit.client.SaasMultiDebitService;
import com.beitu.saas.intergration.user.UserIntegrationService;
import com.beitu.saas.intergration.user.consts.YoufenApiResCodeConst;
import com.beitu.saas.intergration.user.consts.YoufenApiStatusCodeConst;
import com.beitu.saas.intergration.user.dto.UserMultiDebitDto;
import com.beitu.saas.intergration.user.dto.UserNameIdNoValidationDto;
import com.beitu.saas.intergration.user.enums.UserMultiDebitCodeEnum;
import com.beitu.saas.intergration.user.enums.UserNameIdNoValidationCodeEnum;
import com.beitu.saas.intergration.user.param.UserMultiDebitParam;
import com.beitu.saas.intergration.user.param.UserNameIdNoValidationParam;
import com.beitu.saas.intergration.user.pojo.*;
import com.fqgj.common.utils.JSONUtils;
import com.fqgj.common.utils.MD5;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Service
public class UserIntegrationServiceImpl implements UserIntegrationService {
    private static final Log LOGGER = LogFactory.getLog(UserIntegrationServiceImpl.class);

    @Autowired
    private ConfigUtil configUtil;
    @Autowired
    private OSSService ossService;
    @Autowired
    private SaasMultiDebitService saasMultiDebitService;

    @Override
    public UserNameIdNoValidationDto userNameMatchIdNo(UserNameIdNoValidationParam param) {
        if (param == null) {
            return new UserNameIdNoValidationDto(UserNameIdNoValidationCodeEnum.OTHER_ERROR, "输入参数为空");
        }
        String paramValidateResult = param.validate();
        if (StringUtils.isNotEmpty(paramValidateResult)) {
            return new UserNameIdNoValidationDto(UserNameIdNoValidationCodeEnum.OTHER_ERROR, paramValidateResult);
        }

        String url = configUtil.getYoufenValidationApiUrl() + configUtil.getYoufenValidationNameIdcardPath();
        StringBuilder urlSb = new StringBuilder();
        urlSb.append(url + "?");
        urlSb.append("account=" + configUtil.getYoufenAccount() + "&");
        urlSb.append("name=" + param.getName() + "&");
        urlSb.append("idcard=" + param.getIdentityNo() + "");
        String response = HttpClientUtil.doGet(urlSb.toString());
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

    @Override
    public UserMultiDebitDto userMultiDebit(UserMultiDebitParam param) {
        if (param == null) {
            return new UserMultiDebitDto(UserMultiDebitCodeEnum.OTHER_ERROR, "输入参数为空");
        }
        String paramValidateResult = param.validate();
        if (StringUtils.isNotEmpty(paramValidateResult)) {
            return new UserMultiDebitDto(UserMultiDebitCodeEnum.OTHER_ERROR, paramValidateResult);
        }

        String url = configUtil.getYoufenValidationApiUrl() + configUtil.getYoufenMultiDebitPath();
        StringBuilder urlSb = new StringBuilder();
        urlSb.append(url + "?");
        urlSb.append("name=" + param.getName() + "&");
        urlSb.append("cellphone=" + param.getMobile() + "&");
        urlSb.append("idcard=" + param.getIdentityNo() + "&");
        urlSb.append("account=" + "yangcong099" + "");
        String response = HttpClientUtil.doGet(urlSb.toString());
        if (response.contains("<")) {
            return new UserMultiDebitDto(UserMultiDebitCodeEnum.OTHER_ERROR, "接口返回异常");
        }

        JSONObject jsonObject = JSONObject.parseObject(response);
        String resCode = jsonObject.getString("resCode");
        String resMsg = jsonObject.getString("resMsg");
        if (!Objects.equals(resCode, YoufenApiResCodeConst.SUCCESS)) {
            return new UserMultiDebitDto(UserMultiDebitCodeEnum.COMMIT_ERROR, resMsg);
        }

        JSONObject dataJSONObject = jsonObject.getJSONObject("data");
        if (dataJSONObject.size() == 0) {
            return new UserMultiDebitDto(UserMultiDebitCodeEnum.OTHER_ERROR, "接口返回数据异常");
        }
        String statusCode = dataJSONObject.getString("statusCode");
        String statusMsg = dataJSONObject.getString("statusMsg");
        if (!Objects.equals(statusCode, YoufenApiResCodeConst.QUERY_SUCCESS)) {
            return new UserMultiDebitDto(UserMultiDebitCodeEnum.QUERY_ERROR, statusMsg);
        }

        JSONObject result = dataJSONObject.getJSONObject("result");
        JSONObject evaluate = result.getJSONObject("evaluate");
        String score = evaluate.getString("score");
        String failRate = evaluate.getString("failRate");
        String resultJsonString = result.toJSONString();

        //json字符串上传oss并且返回到dto
        String carrierUrl = "carrierData/";
        if (configUtil.isServerTest()) {
            carrierUrl += "test/";
        }
        String userTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        carrierUrl += "youfenCarrier" + "_" + userTime + "_" + MD5.md5(userTime + System.currentTimeMillis()) + ".json";
        carrierUrl = ossService.uploadFile(carrierUrl, resultJsonString);
        // TODO: 2018/4/16 入库操作 --- borrowerId 身份证 手机号 评分 ossurl 失效时间(到期后自动失效) 状态
        //saasMultiDebitService.create(null);
        return new UserMultiDebitDto(UserMultiDebitCodeEnum.QUERY_SUCCESS.getCode(), UserMultiDebitCodeEnum.QUERY_SUCCESS.getMsg(), resultJsonString);
    }
}
