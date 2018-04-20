package com.beitu.saas.intergration.user.impl;

import com.alibaba.fastjson.JSONObject;
import com.beitu.saas.borrower.client.SaasBorrowerService;
import com.beitu.saas.borrower.domain.SaasBorrowerVo;
import com.beitu.saas.common.config.ConfigUtil;
import com.beitu.saas.common.handle.oss.OSSService;
import com.beitu.saas.common.utils.DateUtil;
import com.beitu.saas.common.utils.HttpClientUtil;
import com.beitu.saas.credit.client.SaasMultiDebitService;
import com.beitu.saas.credit.entity.SaasMultiDebitEntity;
import com.beitu.saas.intergration.user.UserIntegrationService;
import com.beitu.saas.intergration.user.consts.YoufenApiResCodeConst;
import com.beitu.saas.intergration.user.consts.YoufenApiStatusCodeConst;
import com.beitu.saas.intergration.user.dto.UserMultiDebitDto;
import com.beitu.saas.intergration.user.dto.UserNameIdNoValidationDto;
import com.beitu.saas.intergration.user.enums.UserMultiDebitCodeEnum;
import com.beitu.saas.intergration.user.enums.UserMultiDebitStatusEnum;
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

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    @Autowired
    private SaasBorrowerService saasBorrowerService;

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
    public UserMultiDebitDto userMultiDebit(UserMultiDebitParam param, String merchantCode) {
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
        urlSb.append("account=" + configUtil.getYoufenAccount() + "");
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
        String statusCode = dataJSONObject.getString("statusCode");
        String statusMsg = dataJSONObject.getString("statusMsg");
        if (!Objects.equals(statusCode, YoufenApiResCodeConst.QUERY_SUCCESS)) {
            return new UserMultiDebitDto(UserMultiDebitCodeEnum.QUERY_ERROR, statusMsg);
        }

        JSONObject result = dataJSONObject.getJSONObject("result");
        String resultJsonString = result.toJSONString();
        String multiDebitUrl = "multiDebitData/";
        if (configUtil.isServerTest()) {
            multiDebitUrl += "test/";
        }
        String userTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        multiDebitUrl += "youfenMultiDebit" + "_" + userTime + "_" + MD5.md5(userTime + System.currentTimeMillis()) + ".json";
        multiDebitUrl = ossService.uploadFile(multiDebitUrl, resultJsonString);

        this.createMultiDebit(param, merchantCode, result, multiDebitUrl);
        return new UserMultiDebitDto(UserMultiDebitCodeEnum.REQUEST_SUCCESS.getCode(), UserMultiDebitCodeEnum.REQUEST_SUCCESS.getMsg(), resultJsonString);
    }


    private void createMultiDebit(UserMultiDebitParam param, String merchantCode, JSONObject result, String multiDebitUrl) {
        SaasBorrowerVo saasBorrowerVo = saasBorrowerService.getByMobileAndMerchantCode(param.getMobile(), merchantCode);
        if (saasBorrowerVo == null) {
            return;
        }

        JSONObject evaluate = result.getJSONObject("evaluate");
        String score = evaluate.getString("score");
        String failRate = evaluate.getString("failRate");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, 1);//增加一个月

        SaasMultiDebitEntity entity = new SaasMultiDebitEntity()
                .setBorrowerCode(saasBorrowerVo.getBorrowerCode())
                .setMobile(param.getMobile())
                .setIdentityCode(param.getIdentityNo())
                .setScore(score == null ? 0 : Integer.valueOf(score))
                .setFailRate(failRate == null ? BigDecimal.ZERO : new BigDecimal(failRate))
                .setUrl(multiDebitUrl)
                .setExpiredDt(calendar.getTime())
                .setStatus(UserMultiDebitStatusEnum.EFFECTIVE.getType());

        saasMultiDebitService.create(entity);
    }
}
