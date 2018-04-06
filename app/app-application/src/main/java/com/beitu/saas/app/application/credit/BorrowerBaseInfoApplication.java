package com.beitu.saas.app.application.credit;

import com.beitu.saas.app.application.credit.vo.*;
import com.beitu.saas.app.enums.EducationMsgCodeEnum;
import com.beitu.saas.app.enums.MaritalStatusMsgCodeEnum;
import com.beitu.saas.borrower.client.*;
import com.beitu.saas.borrower.domain.*;
import com.beitu.saas.borrower.entity.SaasBorrowerEmergentContact;
import com.beitu.saas.borrower.entity.SaasBorrowerRealInfo;
import com.beitu.saas.borrower.entity.SaasBorrowerWorkInfo;
import com.beitu.saas.common.config.ConfigUtil;
import com.beitu.saas.common.utils.identityNumber.vo.IdcardInfoExtractor;
import com.beitu.saas.order.client.SaasOrderApplicationService;
import com.beitu.saas.order.domain.SaasOrderApplicationVo;
import com.fqgj.common.utils.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author linanjun
 * @create 2018/3/21 下午4:02
 * @description
 */
@Service
public class BorrowerBaseInfoApplication {

    @Autowired
    private SaasOrderApplicationService saasOrderApplicationService;

    @Autowired
    private SaasBorrowerPersonalInfoService saasBorrowerPersonalInfoService;

    @Autowired
    private SaasBorrowerService saasBorrowerService;

    @Autowired
    private SaasBorrowerRealInfoService saasBorrowerRealInfoService;

    @Autowired
    private SaasBorrowerIdentityInfoService saasBorrowerIdentityInfoService;

    @Autowired
    private ConfigUtil configUtil;

    @Autowired
    private SaasBorrowerWorkInfoService saasBorrowerWorkInfoService;

    @Autowired
    private SaasBorrowerEmergentContactService saasBorrowerEmergentContactService;

    @Autowired
    private SaasBorrowerLoginLogService saasBorrowerLoginLogService;

    public BorrowerOrderApplicationVo getUserOrderApplicationVo(String borrowerCode, String orderNumb) {
        SaasOrderApplicationVo saasOrderApplicationVo = saasOrderApplicationService.getByBorrowerCodeAndOrderNumb(borrowerCode, orderNumb);
        if (saasOrderApplicationVo == null) {
            return null;
        }
        BorrowerOrderApplicationVo borrowerOrderApplicationVo = new BorrowerOrderApplicationVo();
        BeanUtils.copyProperties(saasOrderApplicationVo, borrowerOrderApplicationVo);
        return borrowerOrderApplicationVo;
    }

    public BorrowerPersonalInfoVo getUserPersonalInfoVo(String merchantCode, String borrowerCode, String orderNumb) {
        BorrowerPersonalInfoVo borrowerPersonalInfoVo = new BorrowerPersonalInfoVo();
        SaasBorrowerVo saasBorrowerVo = saasBorrowerService.getByBorrowerCodeAndMerchantCode(borrowerCode, merchantCode);
        if (saasBorrowerVo == null) {

        }
        borrowerPersonalInfoVo.setMobile(saasBorrowerVo.getMobile());

        SaasBorrowerRealInfoVo saasBorrowerRealInfoVo = saasBorrowerRealInfoService.getBorrowerRealInfoByBorrowerCode(borrowerCode);
        if (saasBorrowerRealInfoVo != null) {
            borrowerPersonalInfoVo.setUserName(saasBorrowerRealInfoVo.getName());
            borrowerPersonalInfoVo.setIdentityCode(saasBorrowerRealInfoVo.getIdentityCode());
            borrowerPersonalInfoVo.setNativePlace(saasBorrowerRealInfoVo.getNativePlace());
            IdcardInfoExtractor idcardInfoExtractor = new IdcardInfoExtractor(saasBorrowerRealInfoVo.getIdentityCode());
            borrowerPersonalInfoVo.setGender(idcardInfoExtractor.getGender());
            borrowerPersonalInfoVo.setAge(idcardInfoExtractor.getAge());
        }

        SaasBorrowerPersonalInfoVo saasBorrowerPersonalInfoVo = saasBorrowerPersonalInfoService.getByBorrowerCodeAndOrderNumb(borrowerCode, orderNumb);
        if (saasBorrowerPersonalInfoVo != null) {
            borrowerPersonalInfoVo.setQq(saasBorrowerPersonalInfoVo.getQq());
            borrowerPersonalInfoVo.setEducation(EducationMsgCodeEnum.getByCode(saasBorrowerPersonalInfoVo.getEducation()).getMsg());
            borrowerPersonalInfoVo.setMaritalStatus(MaritalStatusMsgCodeEnum.getByCode(saasBorrowerPersonalInfoVo.getMaritalStatus()).getMsg());
            borrowerPersonalInfoVo.setZmCreditScore(saasBorrowerPersonalInfoVo.getZmCreditScore());
        }

        borrowerPersonalInfoVo.setPhoneSystem(saasBorrowerLoginLogService.getBorrowerPhoneSystem(borrowerCode));

        return borrowerPersonalInfoVo;
    }

    public BorrowerIdentityInfoVo getUserIdentityInfoVo(String borrowerCode, String orderNumb) {
        SaasBorrowerIdentityInfoVo saasBorrowerIdentityInfoVo = saasBorrowerIdentityInfoService.getByBorrowerCodeAndOrderNumb(borrowerCode, orderNumb);
        if (saasBorrowerIdentityInfoVo == null) {
            return null;
        }
        BorrowerIdentityInfoVo borrowerIdentityInfoVo = new BorrowerIdentityInfoVo();
        if (StringUtils.isNotEmpty(saasBorrowerIdentityInfoVo.getFrontUrl())) {
            borrowerIdentityInfoVo.setFrontUrl(configUtil.getAddressURLPrefix() + saasBorrowerIdentityInfoVo.getFrontUrl());
        }
        if (StringUtils.isNotEmpty(saasBorrowerIdentityInfoVo.getBackUrl())) {
            borrowerIdentityInfoVo.setBackUrl(configUtil.getAddressURLPrefix() + saasBorrowerIdentityInfoVo.getBackUrl());
        }
        if (StringUtils.isNotEmpty(saasBorrowerIdentityInfoVo.getHoldUrl())) {
            borrowerIdentityInfoVo.setHoldUrl(configUtil.getAddressURLPrefix() + saasBorrowerIdentityInfoVo.getHoldUrl());
        }
        return borrowerIdentityInfoVo;
    }

    public BorrowerWorkInfoVo getUserWorkInfoVo(String borrowerCode, String orderNumb) {
        SaasBorrowerWorkInfoVo saasBorrowerWorkInfoVo = saasBorrowerWorkInfoService.getByBorrowerCodeAndOrderNumb(borrowerCode, orderNumb);
        if (saasBorrowerWorkInfoVo == null) {
            return null;
        }
        BorrowerWorkInfoVo borrowerWorkInfoVo = new BorrowerWorkInfoVo();
        BeanUtils.copyProperties(saasBorrowerWorkInfoVo, borrowerWorkInfoVo);
        return borrowerWorkInfoVo;
    }

    public BorrowerEmergentContactVo getUserEmergentContactVo(String borrowerCode, String orderNumb) {
        SaasBorrowerEmergentContactVo saasBorrowerEmergentContactVo = saasBorrowerEmergentContactService.getByBorrowerCodeAndOrderNumb(borrowerCode, orderNumb);
        if (saasBorrowerEmergentContactVo == null) {
            return null;
        }
        BorrowerEmergentContactVo borrowerEmergentContactVo = new BorrowerEmergentContactVo();
        BeanUtils.copyProperties(saasBorrowerEmergentContactVo, borrowerEmergentContactVo);
        return borrowerEmergentContactVo;
    }

    public BorrowerLivingAreaVo getUserLivingAreaVo(String borrowerCode, String orderNumb) {
        List<SaasBorrowerLoginLogVo> saasBorrowerLoginLogVoList = saasBorrowerLoginLogService.listBorrowerLivingArea(borrowerCode);
        if (CollectionUtils.isEmpty(saasBorrowerLoginLogVoList)) {
            return null;
        }
        List<BorrowerLivingAreaDetailVo> results = new ArrayList<>(saasBorrowerLoginLogVoList.size());
        saasBorrowerLoginLogVoList.forEach(saasBorrowerLoginLogVo -> {
            BorrowerLivingAreaDetailVo borrowerLivingAreaDetailVo = new BorrowerLivingAreaDetailVo();
            borrowerLivingAreaDetailVo.setAddress(saasBorrowerLoginLogVo.getLoginIpAddress());
            borrowerLivingAreaDetailVo.setCreatedDate(saasBorrowerLoginLogVo.getGmtCreate());
            results.add(borrowerLivingAreaDetailVo);
        });
        return new BorrowerLivingAreaVo(results);
    }

}
