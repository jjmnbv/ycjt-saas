package com.beitu.saas.app.application.openapi;

import com.beitu.saas.app.application.openapi.vo.*;
import com.beitu.saas.app.application.order.OrderRecommendApplication;
import com.beitu.saas.app.enums.EducationMsgCodeEnum;
import com.beitu.saas.app.enums.MaritalStatusMsgCodeEnum;
import com.beitu.saas.app.enums.OpenApiOrderPushErrorCodeEnum;
import com.beitu.saas.borrower.client.*;
import com.beitu.saas.borrower.domain.SaasBorrowerRealInfoVo;
import com.beitu.saas.borrower.domain.SaasBorrowerVo;
import com.beitu.saas.borrower.entity.*;
import com.beitu.saas.channel.client.SaasChannelService;
import com.beitu.saas.channel.entity.SaasChannelEntity;
import com.beitu.saas.channel.enums.ChannelTypeEnum;
import com.beitu.saas.common.utils.OrderNoUtil;
import com.beitu.saas.credit.client.*;
import com.beitu.saas.credit.domain.*;
import com.beitu.saas.credit.entity.SaasCreditBmp;
import com.beitu.saas.credit.entity.SaasCreditCarrier;
import com.beitu.saas.credit.entity.SaasCreditDunning;
import com.beitu.saas.credit.entity.SaasCreditTongdun;
import com.beitu.saas.order.client.SaasOrderService;
import com.beitu.saas.order.entity.SaasOrder;
import com.beitu.saas.order.enums.OrderStatusEnum;
import com.fqgj.common.utils.CollectionUtils;
import com.fqgj.common.utils.JSONUtils;
import com.fqgj.exception.common.ApplicationException;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author linanjun
 * @create 2018/3/30 下午4:24
 * @description
 */
@Component
public class OpenApiOrderApplication {

    private static final Log LOGGER = LogFactory.getLog(OpenApiOrderApplication.class);
    
    @Autowired
    private OrderRecommendApplication orderRecommendApplication;
    
    @Autowired
    private SaasChannelService saasChannelService;
    
    @Autowired
    private SaasBorrowerService saasBorrowerService;
    
    @Autowired
    private SaasBorrowerRealInfoService saasBorrowerRealInfoService;
    
    @Autowired
    private SaasOrderService saasOrderService;
    
    @Autowired
    private SaasBorrowerPersonalInfoService saasBorrowerPersonalInfoService;
    
    @Autowired
    private SaasBorrowerWorkInfoService saasBorrowerWorkInfoService;
    
    @Autowired
    private SaasBorrowerEmergentContactService saasBorrowerEmergentContactService;
    
    @Autowired
    private SaasBorrowerIdentityInfoService saasBorrowerIdentityInfoService;
    
    @Autowired
    private SaasCreditCarrierService saasCreditCarrierService;
    
    @Autowired
    private SaasCreditCarrierBaseService saasCreditCarrierBaseService;
    
    @Autowired
    private SaasCreditCarrierExtService saasCreditCarrierExtService;
    
    @Autowired
    private SaasCreditCarrierBillService saasCreditCarrierBillService;
    
    @Autowired
    private SaasCreditCarrierRecordService saasCreditCarrierRecordService;
    
    @Autowired
    private SaasCreditDunningService saasCreditDunningService;
    
    @Autowired
    private SaasCreditDunningDetailService saasCreditDunningDetailService;
    
    @Autowired
    private SaasCreditBmpService saasCreditBmpService;
    
    @Autowired
    private SaasCreditBmpDetailService saasCreditBmpDetailService;
    
    @Autowired
    private SaasCreditTongdunService saasCreditTongdunService;
    
    @Autowired
    private SaasCreditTongdunDetailService saasCreditTongdunDetailService;
    
    public Boolean ycjtOrderPushProcess(String requestString) {
        OrderPushToSaasDataVo pushData;
        try {
            pushData = JSONUtils.json2pojoAndOffUnknownField(requestString, OrderPushToSaasDataVo.class);
        } catch (Exception e) {
            LOGGER.warn("************************* 洋葱借条推单处理失败:{} *************************", e);
            throw new ApplicationException(OpenApiOrderPushErrorCodeEnum.DATA_PARSE_ERROR);
        }
        if (pushData == null) {
            OpenApiOrderPushErrorCodeEnum errorCodeEnum = OpenApiOrderPushErrorCodeEnum.DATA_PARSE_ERROR;
            LOGGER.warn("************************* 洋葱借条推单处理失败:{} *************************", errorCodeEnum.getMsg());
            throw new ApplicationException(errorCodeEnum);
        }
        String mobile = pushData.getMobile();
        String identityNo = pushData.getIdentityNo();
        
        // TODO: 2018/4/11 根据推单记录数据判断是否继续进行推单操作
        
        Map merchantsInfo = orderRecommendApplication.getRecommendMerchantCode(Long.valueOf(pushData.getZmScore().toString()), identityNo);
        List<String> merchantCodes = (List<String>)merchantsInfo.get("list");
        if (CollectionUtils.isEmpty(merchantCodes)) {
            OpenApiOrderPushErrorCodeEnum errorCodeEnum = OpenApiOrderPushErrorCodeEnum.NO_MATCHED_MERCHANT;
            LOGGER.warn("************************* 洋葱借条推单处理失败:{} Mobile:{} IdentityNo:{} *************************", errorCodeEnum.getMsg(), mobile, identityNo);
            throw new ApplicationException(errorCodeEnum);
        }
        
        List<SaasBorrowerRelatedDataVo> borrowerRelatedDataVos = orderPushUserRegister(pushData, merchantCodes);
        if (CollectionUtils.isEmpty(borrowerRelatedDataVos)) {
            OpenApiOrderPushErrorCodeEnum errorCodeEnum = OpenApiOrderPushErrorCodeEnum.NO_MATCHED_MERCHANT;
            LOGGER.warn("************************* 洋葱借条推单处理失败:{} Mobile:{} IdentityNo:{} *************************", errorCodeEnum.getMsg(), mobile, identityNo);
            throw new ApplicationException(errorCodeEnum);
        }
        
        OrderPushToSaasVo userAndOrderData;
        try {
            userAndOrderData = JSONUtils.json2pojoAndOffUnknownField(pushData.getData(), OrderPushToSaasVo.class);
        } catch (Exception e) {
            LOGGER.warn("************************* 洋葱借条推单处理失败 Mobile:{} IdentityNo:{} CAUSE:{} *************************", mobile, identityNo, e);
            throw new ApplicationException(OpenApiOrderPushErrorCodeEnum.DATA_PARSE_ERROR);
        }
        if (userAndOrderData == null) {
            OpenApiOrderPushErrorCodeEnum errorCodeEnum = OpenApiOrderPushErrorCodeEnum.DATA_PARSE_ERROR;
            LOGGER.warn("************************* 洋葱借条推单处理失败:{} Mobile:{} IdentityNo:{} *************************", errorCodeEnum.getMsg(), mobile, identityNo);
            throw new ApplicationException(errorCodeEnum);
        }
        
        for (int i = 0; i < borrowerRelatedDataVos.size(); i++) {
            SaasBorrowerRelatedDataVo vo = borrowerRelatedDataVos.get(i);
            try {
                userInfoAndOrderInfoProcess(vo, userAndOrderData, mobile, identityNo);
            } catch (Exception e) {
                LOGGER.warn("************************* 洋葱借条推单处理失败 Mobile:{} IdentityNo:{} Merchant:{} Borrower:{} CAUSE:{} *************************", mobile, identityNo, vo.getMerchantCode(), vo.getBorrowerCode(), e);
            }
        }
        
        // TODO: 2018/4/11 根据商户吸量类型flowType记录推单数据
        Integer flowType = (Integer)merchantsInfo.get("flowType");
        
        return Boolean.TRUE;
    }
    
    @Transactional
    public void userInfoAndOrderInfoProcess(SaasBorrowerRelatedDataVo borrowerRelatedDataVo, OrderPushToSaasVo userAndOrderData, String mobile, String identityNo) {
        OrderPushUserBasicInfoVo basicInfo = userAndOrderData.getBasicInfo();
        if (basicInfo == null) {
            OpenApiOrderPushErrorCodeEnum errorCodeEnum = OpenApiOrderPushErrorCodeEnum.USER_BASIC_INFO_MISS;
            LOGGER.warn("************************* 洋葱借条推单处理失败:{} Mobile:{} IdentityNo:{} *************************", errorCodeEnum.getMsg(), mobile, identityNo);
        }
        OrderPushUserOrderInfoVo orderInfo = userAndOrderData.getOrderInfo();
        if (orderInfo == null) {
            OpenApiOrderPushErrorCodeEnum errorCodeEnum = OpenApiOrderPushErrorCodeEnum.USER_ORDER_INFO_MISS;
            LOGGER.warn("************************* 洋葱借条推单处理失败:{} Mobile:{} IdentityNo:{} *************************", errorCodeEnum.getMsg(), mobile, identityNo);
        }
        String merchantCode = borrowerRelatedDataVo.getMerchantCode();
        String borrowerCode = borrowerRelatedDataVo.getBorrowerCode();
        String name = basicInfo.getName();
        saasBorrowerRealInfoService.create(merchantCode, borrowerCode, name, identityNo);
        
        String orderNumb = OrderNoUtil.makeOrderNum();
        String channelCode = borrowerRelatedDataVo.getChannelCode();
        SaasOrder saasOrder = new SaasOrder();
        BeanUtils.copyProperties(orderInfo, saasOrder);
        saasOrder.setOrderNumb(orderNumb);
        saasOrder.setMerchantCode(merchantCode);
        saasOrder.setChannelCode(channelCode);
        saasOrder.setBorrowerCode(borrowerCode);
        saasOrder.setOrderStatus(OrderStatusEnum.SUBMIT_PRELIMINARY_REVIEW.getCode());
        saasOrderService.create(saasOrder);
        
        OrderPushUserPersonalInfoVo personalInfo = userAndOrderData.getPersonalInfo();
        if (personalInfo != null) {
            SaasBorrowerPersonalInfo saasBorrowerPersonalInfo = new SaasBorrowerPersonalInfo();
            saasBorrowerPersonalInfo.setBorrowerCode(borrowerCode);
            saasBorrowerPersonalInfo.setOrderNumb(orderNumb);
            saasBorrowerPersonalInfo.setWechatCode(personalInfo.getWechatCode());
            saasBorrowerPersonalInfo.setEducation(EducationMsgCodeEnum.getByDesc(personalInfo.getEducation()).getCode());
            saasBorrowerPersonalInfo.setAddress(personalInfo.getAddress());
            saasBorrowerPersonalInfo.setLiveDuration(personalInfo.getLiveDuration());
            saasBorrowerPersonalInfo.setMaritalStatus(MaritalStatusMsgCodeEnum.getByDesc(personalInfo.getMaritalStatus()).getCode());
            saasBorrowerPersonalInfo.setZmCreditScore(basicInfo.getZmScore());
            saasBorrowerPersonalInfoService.create(saasBorrowerPersonalInfo);
        }
        OrderPushUserWorkInfoVo workInfo = userAndOrderData.getWorkInfo();
        if (workInfo != null) {
            SaasBorrowerWorkInfo saasBorrowerWorkInfo = new SaasBorrowerWorkInfo();
            BeanUtils.copyProperties(workInfo, saasBorrowerWorkInfo);
            saasBorrowerWorkInfo.setBorrowerCode(borrowerCode);
            saasBorrowerWorkInfo.setOrderNumb(orderNumb);
            saasBorrowerWorkInfoService.create(saasBorrowerWorkInfo);
        }
        OrderPushUserEmergentContactVo emergentContact = userAndOrderData.getEmergentContact();
        if (emergentContact != null) {
            SaasBorrowerEmergentContact saasBorrowerEmergentContact = new SaasBorrowerEmergentContact();
            BeanUtils.copyProperties(emergentContact, saasBorrowerEmergentContact);
            saasBorrowerEmergentContact.setBorrowerCode(borrowerCode);
            saasBorrowerEmergentContact.setOrderNumb(orderNumb);
            saasBorrowerEmergentContactService.create(saasBorrowerEmergentContact);
        }
        OrderPushUserIdentityInfoVo identityInfo = userAndOrderData.getIdentityInfo();
        if (identityInfo != null) {
            SaasBorrowerIdentityInfo saasBorrowerIdentityInfo = new SaasBorrowerIdentityInfo();
            BeanUtils.copyProperties(identityInfo, saasBorrowerIdentityInfo);
            saasBorrowerIdentityInfo.setBorrowerCode(borrowerCode);
            saasBorrowerIdentityInfo.setOrderNumb(orderNumb);
            saasBorrowerIdentityInfoService.create(saasBorrowerIdentityInfo);
        }
        
        OrderPushUserCarrierInfoVo carrierInfo = userAndOrderData.getCarrierInfo();
        Long carrierId = null;
        if (carrierInfo != null) {
            SaasCreditCarrierVo saasCreditCarrierVo = new SaasCreditCarrierVo();
            BeanUtils.copyProperties(carrierInfo, saasCreditCarrierVo);
            saasCreditCarrierVo.setBorrowerCode(borrowerCode);
            saasCreditCarrierVo.setMerchantCode(merchantCode);
            saasCreditCarrierVo.setSuccess(Boolean.TRUE);
            SaasCreditCarrier saasCreditCarrier = saasCreditCarrierService.addSaasCreditCarrier(saasCreditCarrierVo);
            if (saasCreditCarrier != null) {
                carrierId = saasCreditCarrier.getId();
            }
        }
        OrderPushUserCarrierBaseVo carrierBase = carrierInfo.getBase();
        if (carrierId != null && carrierBase != null) {
            SaasCreditCarrierBaseVo creditCarrierBaseVo = new SaasCreditCarrierBaseVo();
            BeanUtils.copyProperties(carrierBase, creditCarrierBaseVo);
            creditCarrierBaseVo.setRecordId(carrierId);
            saasCreditCarrierBaseService.addSaasCreditCarrierBase(creditCarrierBaseVo);
        }
        OrderPushUserCarrierExtVo carrierExt = carrierInfo.getExt();
        if (carrierId != null && carrierExt != null) {
            SaasCreditCarrierExtVo saasCreditCarrierExtVo = new SaasCreditCarrierExtVo();
            BeanUtils.copyProperties(carrierExt, saasCreditCarrierExtVo);
            saasCreditCarrierExtVo.setRecordId(carrierId);
            saasCreditCarrierExtService.addSaasCreditCarrierExt(saasCreditCarrierExtVo);
        }
        List<OrderPushUserCarrierBillVo> carrierBills = carrierInfo.getBills();
        if (carrierId != null && CollectionUtils.isNotEmpty(carrierBills)) {
            List<SaasCreditCarrierBillVo> creditCarrierBillVos = new ArrayList<>(carrierBills.size());
            for (OrderPushUserCarrierBillVo carrierBill : carrierBills) {
                SaasCreditCarrierBillVo saasCreditCarrierBillVo = new SaasCreditCarrierBillVo();
                BeanUtils.copyProperties(carrierBill, saasCreditCarrierBillVo);
                saasCreditCarrierBillVo.setRecordId(carrierId);
                creditCarrierBillVos.add(saasCreditCarrierBillVo);
            }
            if (creditCarrierBillVos.size() > 1) {
                Collections.sort(creditCarrierBillVos, (o1, o2) -> (o2.getBillDate().compareTo(o1.getBillDate())));
            }
            saasCreditCarrierBillService.batchAddSaasCreditCarrierBill(creditCarrierBillVos);
        }
        List<OrderPushUserCarrierRecordVo> carrierRecords = carrierInfo.getRecords();
        if (carrierId != null && CollectionUtils.isNotEmpty(carrierRecords)) {
            List<SaasCreditCarrierRecordVo> creditCarrierRecordVos = new ArrayList<>(carrierRecords.size());
            for (OrderPushUserCarrierRecordVo carrierRecord : carrierRecords) {
                SaasCreditCarrierRecordVo saasCreditCarrierRecordVo = new SaasCreditCarrierRecordVo();
                BeanUtils.copyProperties(carrierRecord, saasCreditCarrierRecordVo);
                saasCreditCarrierRecordVo.setRecordId(carrierId);
                creditCarrierRecordVos.add(saasCreditCarrierRecordVo);
            }
            saasCreditCarrierRecordService.batchAddSaasCreditCarrierRecord(creditCarrierRecordVos);
        }
        
        OrderPushUserDunningInfoVo dunningInfo = userAndOrderData.getDunningInfo();
        Long dunningId = null;
        if (dunningInfo != null) {
            SaasCreditDunningVo saasCreditDunningVo = new SaasCreditDunningVo();
            BeanUtils.copyProperties(dunningInfo, saasCreditDunningVo);
            saasCreditDunningVo.setBorrowerCode(borrowerCode);
            saasCreditDunningVo.setMerchantCode(merchantCode);
            saasCreditDunningVo.setCarrierId(carrierId);
            saasCreditDunningVo.setSuccess(Boolean.TRUE);
            SaasCreditDunning saasCreditDunning = saasCreditDunningService.addSaasCreditDunning(saasCreditDunningVo);
            if (saasCreditDunning != null) {
                dunningId = saasCreditDunning.getId();
            }
        }
        List<OrderPushUserDunningDetailVo> dunningDetails = dunningInfo.getDetails();
        if (dunningId != null && CollectionUtils.isNotEmpty(dunningDetails)) {
            List<SaasCreditDunningDetailVo> creditDunningDetailVos = new ArrayList<>(dunningDetails.size());
            for (OrderPushUserDunningDetailVo dunningDetail : dunningDetails) {
                SaasCreditDunningDetailVo saasCreditDunningDetailVo = new SaasCreditDunningDetailVo();
                BeanUtils.copyProperties(dunningDetail, saasCreditDunningDetailVo);
                saasCreditDunningDetailVo.setRecordId(dunningId);
                creditDunningDetailVos.add(saasCreditDunningDetailVo);
            }
            saasCreditDunningDetailService.batchAddSaasCreditDunningDetail(creditDunningDetailVos);
        }
        
        OrderPushUserBmpInfoVo bmpInfo = userAndOrderData.getBmpInfo();
        Long bmpId = null;
        if (bmpInfo != null) {
            SaasCreditBmpVo saasCreditBmpVo = new SaasCreditBmpVo();
            BeanUtils.copyProperties(bmpInfo, saasCreditBmpVo);
            saasCreditBmpVo.setBorrowerCode(borrowerCode);
            saasCreditBmpVo.setMerchantCode(merchantCode);
            saasCreditBmpVo.setSuccess(Boolean.TRUE);
            SaasCreditBmp saasCreditBmp = saasCreditBmpService.addSaasCreditBmp(saasCreditBmpVo);
            if (saasCreditBmp != null) {
                bmpId = saasCreditBmp.getId();
            }
        }
        List<OrderPushUserBmpDetailVo> bmpDetails = bmpInfo.getDetails();
        if (bmpId != null && CollectionUtils.isNotEmpty(bmpDetails)) {
            List<SaasCreditBmpDetailVo> creditBmpDetailVos = new ArrayList<>(bmpDetails.size());
            for (OrderPushUserBmpDetailVo bmpDetail : bmpDetails) {
                SaasCreditBmpDetailVo saasCreditBmpDetailVo = new SaasCreditBmpDetailVo();
                BeanUtils.copyProperties(bmpDetail, saasCreditBmpDetailVo);
                saasCreditBmpDetailVo.setRecordId(bmpId);
                creditBmpDetailVos.add(saasCreditBmpDetailVo);
            }
            saasCreditBmpDetailService.batchAddSaasCreditBmpDetailVo(creditBmpDetailVos);
        }
        
        OrderPushUserTongdunInfoVo tongdunInfo = userAndOrderData.getTongdunInfo();
        Long tongdunId = null;
        if (tongdunInfo != null) {
            SaasCreditTongdunVo saasCreditTongdunVo = new SaasCreditTongdunVo();
            BeanUtils.copyProperties(tongdunInfo, saasCreditTongdunVo);
            saasCreditTongdunVo.setBorrowerCode(borrowerCode);
            saasCreditTongdunVo.setMerchantCode(merchantCode);
            saasCreditTongdunVo.setSuccess(Boolean.TRUE);
            SaasCreditTongdun saasCreditTongdun = saasCreditTongdunService.addSaasCreditTongdun(saasCreditTongdunVo);
            if (saasCreditTongdun != null) {
                tongdunId = saasCreditTongdun.getId();
            }
        }
        OrderPushUserTongdunDetailVo tongdunDetail = tongdunInfo.getDetail();
        if (tongdunId != null && tongdunDetail != null) {
            SaasCreditTongdunDetailVo saasCreditTongdunDetailVo = new SaasCreditTongdunDetailVo();
            BeanUtils.copyProperties(tongdunDetail, saasCreditTongdunDetailVo);
            saasCreditTongdunDetailVo.setRecordId(tongdunId);
            saasCreditTongdunDetailService.addSaasCreditTongdunDetail(saasCreditTongdunDetailVo);
        }
    }
    
    private List<SaasBorrowerRelatedDataVo> orderPushUserRegister(OrderPushToSaasDataVo data, List<String> merchantCodes) {
        List<SaasBorrowerRelatedDataVo> borrowerCodes = new ArrayList<>();
        for (int i = 0; i < merchantCodes.size(); i++) {
            String merchantCode = merchantCodes.get(i);
            String identityNo = data.getIdentityNo();
            if (!canMatchMerchant(identityNo, merchantCode)) {
                continue;
            }
            SaasChannelEntity channel = saasChannelService.getDefaultSaasChannelByMerchantCode(merchantCode, ChannelTypeEnum.RECOMMEND_DEFINED.getType());
            if (channel == null || StringUtils.isEmpty(channel.getChannelCode())) {
                continue;
            }
            String channelCode = channel.getChannelCode();
            String mobile = data.getMobile();
            String borrowerCode = getBorrowerCodeByInfo(mobile, identityNo, merchantCode);
            if (borrowerCode == null) {
                borrowerCode = registerUser(mobile, merchantCode, channelCode);
            }
            borrowerCodes.add(new SaasBorrowerRelatedDataVo(borrowerCode, merchantCode, channelCode));
        }
        return borrowerCodes;
    }
    
    public Boolean canMatchMerchant(String identityNo, String merchantCode) {
        SaasBorrowerRealInfoVo saasBorrowerRealInfoVo = saasBorrowerRealInfoService.getBorrowerRealInfoByIdentityCodeAndMerchantCode(identityNo, merchantCode);
        if (saasBorrowerRealInfoVo != null) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
    
    private String getBorrowerCodeByInfo(String mobile, String identityNo, String merchantCode) {
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
    
    private String registerUser(String mobile, String merchantCode, String channelCode) {
        SaasBorrowerVo saasBorrowerVo = new SaasBorrowerVo();
        saasBorrowerVo.setMobile(mobile);
        saasBorrowerVo.setMerchantCode(merchantCode);
        saasBorrowerVo.setChannelCode(channelCode);
        SaasBorrower saasBorrower = saasBorrowerService.create(saasBorrowerVo);
        return saasBorrower.getBorrowerCode();
    }

}
