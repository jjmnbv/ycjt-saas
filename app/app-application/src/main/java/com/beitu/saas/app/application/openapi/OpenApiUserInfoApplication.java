package com.beitu.saas.app.application.openapi;

import com.beitu.saas.app.application.openapi.vo.*;
import com.beitu.saas.app.enums.EducationMsgCodeEnum;
import com.beitu.saas.app.enums.MaritalStatusMsgCodeEnum;
import com.beitu.saas.borrower.client.*;
import com.beitu.saas.borrower.domain.SaasBorrowerRealInfoVo;
import com.beitu.saas.borrower.domain.SaasBorrowerVo;
import com.beitu.saas.borrower.entity.*;
import com.beitu.saas.common.utils.OrderNoUtil;
import com.beitu.saas.order.client.SaasOrderService;
import com.beitu.saas.order.entity.SaasOrder;
import com.beitu.saas.order.enums.OrderStatusEnum;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class OpenApiUserInfoApplication {
    
    private static final Log LOGGER = LogFactory.getLog(OpenApiUserInfoApplication.class);
    
    @Autowired
    private SaasBorrowerService saasBorrowerService;
    
    @Autowired
    private SaasBorrowerRealInfoService saasBorrowerRealInfoService;
    
    @Autowired
    private SaasBorrowerPersonalInfoService saasBorrowerPersonalInfoService;
    
    @Autowired
    private SaasBorrowerWorkInfoService saasBorrowerWorkInfoService;
    
    @Autowired
    private SaasBorrowerEmergentContactService saasBorrowerEmergentContactService;
    
    @Autowired
    private SaasBorrowerIdentityInfoService saasBorrowerIdentityInfoService;
    
    @Autowired
    private SaasOrderService saasOrderService;
    
    public String registerUser(String mobile, String merchantCode, String channelCode) {
        SaasBorrowerVo saasBorrowerVo = new SaasBorrowerVo();
        saasBorrowerVo.setMobile(mobile);
        saasBorrowerVo.setMerchantCode(merchantCode);
        saasBorrowerVo.setChannelCode(channelCode);
        SaasBorrower saasBorrower = saasBorrowerService.create(saasBorrowerVo);
        return saasBorrower.getBorrowerCode();
    }
    
    public String getBorrowerCodeByInfo(String mobile, String identityNo, String merchantCode) {
        SaasBorrowerVo borrowerVo = saasBorrowerService.getByMobileAndMerchantCode(mobile, merchantCode);
        if (borrowerVo != null) {
            return borrowerVo.getBorrowerCode();
        }
        SaasBorrowerRealInfoVo saasBorrowerRealInfoVo = saasBorrowerRealInfoService.getBorrowerRealInfoByIdentityCodeAndMerchantCode(identityNo, merchantCode);
        if (saasBorrowerRealInfoVo != null) {
            return saasBorrowerRealInfoVo.getBorrowerCode();
        }
        return null;
    }
    
    public void addUserPersonalInfo(OrderPushUserPersonalInfoVo personalInfo, OrderPushUserBasicInfoVo basicInfo, String borrowerCode, String orderNumb) {
        if (personalInfo == null) {
            return;
        }
        SaasBorrowerPersonalInfo saasBorrowerPersonalInfo = new SaasBorrowerPersonalInfo();
        saasBorrowerPersonalInfo.setBorrowerCode(borrowerCode);
        saasBorrowerPersonalInfo.setOrderNumb(orderNumb);
        saasBorrowerPersonalInfo.setWechatCode(personalInfo.getWechatCode());
        saasBorrowerPersonalInfo.setEducation(EducationMsgCodeEnum.getByDesc(personalInfo.getEducation()).getCode());
        saasBorrowerPersonalInfo.setAddress(personalInfo.getAddress());
        saasBorrowerPersonalInfo.setLiveDuration(personalInfo.getLiveDuration());
        saasBorrowerPersonalInfo.setMaritalStatus(MaritalStatusMsgCodeEnum.getByDesc(personalInfo.getMaritalStatus()).getCode());
        saasBorrowerPersonalInfo.setZmCreditScore(basicInfo.getZmScore());
        if (basicInfo == null || basicInfo.getZmScore() == null) {
            saasBorrowerPersonalInfo.setZmCreditScore(0);
        }
        saasBorrowerPersonalInfoService.create(saasBorrowerPersonalInfo);
    }
    
    public void addUserWorkInfo(OrderPushUserWorkInfoVo workInfo, String borrowerCode, String orderNumb) {
        if (workInfo == null) {
            return;
        }
        SaasBorrowerWorkInfo saasBorrowerWorkInfo = new SaasBorrowerWorkInfo();
        BeanUtils.copyProperties(workInfo, saasBorrowerWorkInfo);
        saasBorrowerWorkInfo.setBorrowerCode(borrowerCode);
        saasBorrowerWorkInfo.setOrderNumb(orderNumb);
        saasBorrowerWorkInfoService.create(saasBorrowerWorkInfo);
    }
    
    public void addUserEmergentContact(OrderPushUserEmergentContactVo emergentContact, String borrowerCode, String orderNumb) {
        if (emergentContact == null) {
            return;
        }
        SaasBorrowerEmergentContact saasBorrowerEmergentContact = new SaasBorrowerEmergentContact();
        BeanUtils.copyProperties(emergentContact, saasBorrowerEmergentContact);
        saasBorrowerEmergentContact.setBorrowerCode(borrowerCode);
        saasBorrowerEmergentContact.setOrderNumb(orderNumb);
        saasBorrowerEmergentContactService.create(saasBorrowerEmergentContact);
    }
    
    public void addUserIdentityInfo(OrderPushUserIdentityInfoVo identityInfo, String borrowerCode, String orderNumb) {
        if (identityInfo == null) {
            return;
        }
        SaasBorrowerIdentityInfo saasBorrowerIdentityInfo = new SaasBorrowerIdentityInfo();
        BeanUtils.copyProperties(identityInfo, saasBorrowerIdentityInfo);
        saasBorrowerIdentityInfo.setBorrowerCode(borrowerCode);
        saasBorrowerIdentityInfo.setOrderNumb(orderNumb);
        saasBorrowerIdentityInfoService.create(saasBorrowerIdentityInfo);
    }
    
    @Transactional
    public String userRealNameAndOrderCreate(SaasBorrowerRelatedDataVo borrowerRelatedDataVo,
                                             OrderPushUserBasicInfoVo basicInfo,
                                             OrderPushUserOrderInfoVo orderInfo) {
        
        String merchantCode = borrowerRelatedDataVo.getMerchantCode();
        String borrowerCode = borrowerRelatedDataVo.getBorrowerCode();
        String name = basicInfo.getName();
        saasBorrowerRealInfoService.create(merchantCode, borrowerCode, name, basicInfo.getIdentityNo());
        
        String channelCode = borrowerRelatedDataVo.getChannelCode();
        String orderNumb = OrderNoUtil.makeOrderNum();
        SaasOrder saasOrder = new SaasOrder();
        BeanUtils.copyProperties(orderInfo, saasOrder);
        saasOrder.setOrderNumb(orderNumb);
        saasOrder.setMerchantCode(merchantCode);
        saasOrder.setChannelCode(channelCode);
        saasOrder.setBorrowerCode(borrowerCode);
        saasOrder.setOrderStatus(OrderStatusEnum.SUBMIT_PRELIMINARY_REVIEW.getCode());
        saasOrder.setCreatedDt(null);
        saasOrderService.create(saasOrder);
        
        return orderNumb;
    }
}
