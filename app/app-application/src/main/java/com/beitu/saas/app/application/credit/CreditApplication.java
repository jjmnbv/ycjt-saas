package com.beitu.saas.app.application.credit;

import com.beitu.saas.app.api.ApiResponse;
import com.beitu.saas.app.application.channel.SaasChannelApplication;
import com.beitu.saas.app.application.credit.vo.CreditModuleListVo;
import com.beitu.saas.app.application.order.OrderApplication;
import com.beitu.saas.app.enums.BorrowerInfoApplyStatusEnum;
import com.beitu.saas.borrower.client.*;
import com.beitu.saas.borrower.enums.BorrowerErrorCodeEnum;
import com.beitu.saas.channel.domain.SaasChannelRiskSettingsVo;
import com.beitu.saas.channel.enums.ChannelErrorCodeEnum;
import com.beitu.saas.channel.enums.RiskModuleEnum;
import com.beitu.saas.common.consts.RedisKeyConsts;
import com.beitu.saas.common.utils.OrderNoUtil;
import com.beitu.saas.credit.client.SaasCreditCarrierService;
import com.beitu.saas.credit.client.SaasCreditTongdunService;
import com.beitu.saas.finance.client.SaasCreditHistoryService;
import com.beitu.saas.finance.client.enums.CreditConsumeEnum;
import com.beitu.saas.intergration.user.UserIntegrationService;
import com.beitu.saas.intergration.user.dto.UserNameIdNoValidationDto;
import com.beitu.saas.intergration.user.enums.UserNameIdNoValidationCodeEnum;
import com.beitu.saas.intergration.user.param.UserNameIdNoValidationParam;
import com.beitu.saas.order.client.SaasOrderApplicationService;
import com.beitu.saas.order.client.SaasOrderService;
import com.beitu.saas.order.domain.SaasOrderApplicationVo;
import com.beitu.saas.order.enums.OrderErrorCodeEnum;
import com.beitu.saas.order.enums.OrderStatusEnum;
import com.fqgj.base.services.redis.RedisClient;
import com.fqgj.common.utils.CollectionUtils;
import com.fqgj.exception.common.ApplicationException;
import com.fqgj.log.factory.LogFactory;
import com.fqgj.log.interfaces.Log;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author linanjun
 * @create 2018/3/23 下午12:14
 * @description
 */
@Service
public class CreditApplication {

    private static final Log LOGGER = LogFactory.getLog(CreditApplication.class);

    @Autowired
    private RedisClient redisClient;

    @Autowired
    private SaasChannelApplication saasChannelApplication;

    @Autowired
    private SaasBorrowerRealInfoService saasBorrowerRealInfoService;

    @Autowired
    private SaasOrderApplicationService saasOrderApplicationService;

    @Autowired
    private SaasBorrowerPersonalInfoService saasBorrowerPersonalInfoService;

    @Autowired
    private SaasBorrowerEmergentContactService saasBorrowerEmergentContactService;

    @Autowired
    private SaasBorrowerWorkInfoService saasBorrowerWorkInfoService;

    @Autowired
    private SaasBorrowerIdentityInfoService saasBorrowerIdentityInfoService;

    @Autowired
    private SaasCreditCarrierService saasCreditCarrierService;

    @Autowired
    private OrderApplication orderApplication;

    @Autowired
    private UserIntegrationService userIntegrationService;

    @Autowired
    private SaasOrderService saasOrderService;

    @Autowired
    private SaasBorrowerLoanCrawlService saasBorrowerLoanCrawlService;

    @Autowired
    private SaasCreditTongdunService saasCreditTongdunService;

    @Autowired
    private TongdunReportApplication tongdunReportApplication;

    @Autowired
    private SaasCreditHistoryService saasCreditHistoryService;

    public List<CreditModuleListVo> listCreditModule(String merchantCode, String channelCode, String borrowerCode) {
        List<SaasChannelRiskSettingsVo> saasChannelRiskSettingsVoList = saasChannelApplication.getSaasChannelRiskSettingsByChannelCode(channelCode);
        if (CollectionUtils.isEmpty(saasChannelRiskSettingsVoList)) {
            throw new ApplicationException(ChannelErrorCodeEnum.CHANNEL_MODULE);
        }
        final String orderNumb = saasOrderService.getReviewerRefuseOrderNumb(borrowerCode, channelCode);
        List<CreditModuleListVo> creditModuleListVoList = new ArrayList<>(saasChannelRiskSettingsVoList.size());
        saasChannelRiskSettingsVoList.forEach(saasChannelRiskSettingsVo -> {
            CreditModuleListVo creditModuleListVo = new CreditModuleListVo();
            creditModuleListVo.setModuleCode(saasChannelRiskSettingsVo.getModuleCode());
            creditModuleListVo.setRequired(SaasChannelRiskSettingsVo.DEFAULT_NEED_REQUIRED_VALUE.equals(saasChannelRiskSettingsVo.getRequired()));
            creditModuleListVo.setApplyStatus(getInfoApplyStatus(merchantCode, borrowerCode, orderNumb, saasChannelRiskSettingsVo.getModuleCode()).getCode());
            creditModuleListVoList.add(creditModuleListVo);
        });

        LOGGER.info("渠道号: {} H5处理后风控项列表size:{}", channelCode, creditModuleListVoList.size());
        return creditModuleListVoList;
    }

    private BorrowerInfoApplyStatusEnum getInfoApplyStatus(String merchantCode, String borrowerCode, String orderNumb, String moduleCode) {
        RiskModuleEnum riskModuleEnum = RiskModuleEnum.getRiskModuleEnumByModuleCode(moduleCode);
        switch (riskModuleEnum) {
            case APPLICATION:
                SaasOrderApplicationVo saasOrderApplicationVo = saasOrderApplicationService.getByBorrowerCodeAndOrderNumb(borrowerCode, orderNumb);
                if (saasOrderApplicationVo != null
                        && (new Date()).compareTo(saasOrderApplicationVo.getRepaymentDt()) < 0
                        && saasOrderApplicationVo.getBorrowerAuthorizedSignLoan()) {
                    return BorrowerInfoApplyStatusEnum.FINISHED;
                }
                return BorrowerInfoApplyStatusEnum.INCOMPLETE;
            case PERSONAL_INFO:
                if (saasBorrowerPersonalInfoService.countByBorrowerCodeAndOrderNumb(borrowerCode, orderNumb) == 0) {
                    return BorrowerInfoApplyStatusEnum.INCOMPLETE;
                }
                return BorrowerInfoApplyStatusEnum.FINISHED;
            case EMERGENT_CONTACT:
                if (saasBorrowerEmergentContactService.countByBorrowerCodeAndOrderNumb(borrowerCode, orderNumb) == 0) {
                    return BorrowerInfoApplyStatusEnum.INCOMPLETE;
                }
                return BorrowerInfoApplyStatusEnum.FINISHED;
            case WORK_INFO:
                if (saasBorrowerWorkInfoService.countByBorrowerCodeAndOrderNumb(borrowerCode, orderNumb) == 0) {
                    return BorrowerInfoApplyStatusEnum.INCOMPLETE;
                }
                return BorrowerInfoApplyStatusEnum.FINISHED;
            case IDENTITY_INFO:
                if (saasBorrowerIdentityInfoService.countByBorrowerCodeAndOrderNumb(borrowerCode, orderNumb) == 0) {
                    return BorrowerInfoApplyStatusEnum.INCOMPLETE;
                }
                return BorrowerInfoApplyStatusEnum.FINISHED;
            case CARRIER_AUTHENTIC:
                if (saasCreditCarrierService.effectivenessCreditCarrier(borrowerCode)) {
                    return BorrowerInfoApplyStatusEnum.FINISHED;
                }
                String value = redisClient.get(RedisKeyConsts.H5_CARRIER_CRAWLING, borrowerCode);
                if (value != null) {
                    return BorrowerInfoApplyStatusEnum.AUTHENTICATING;
                }
                return BorrowerInfoApplyStatusEnum.INCOMPLETE;
            case ZM_CREDIT:
                return BorrowerInfoApplyStatusEnum.INCOMPLETE;
            case EB_INFO:
                return BorrowerInfoApplyStatusEnum.INCOMPLETE;
            case PLATFORM_BORROW_CREDIT:
                if (saasBorrowerLoanCrawlService.effectivenessLoanCrawl(borrowerCode, null)) {
                    return BorrowerInfoApplyStatusEnum.FINISHED;
                }
                Object platformValue = redisClient.get(RedisKeyConsts.H5_LOAN_PLATFORM_CRAWLING, borrowerCode);
                if (platformValue != null) {
                    return BorrowerInfoApplyStatusEnum.AUTHENTICATING;
                }
                return BorrowerInfoApplyStatusEnum.INCOMPLETE;
        }
        return BorrowerInfoApplyStatusEnum.INCOMPLETE;
    }

    /**
     * 用户实名认证
     *
     * @param borrowerCode 用户CODE
     * @param name         用户姓名
     * @param identityCode 用户身份证号码
     * @return
     */
    public Boolean userRealNameAuth(String merchantCode, String borrowerCode, String name, String identityCode) {
        if (saasBorrowerRealInfoService.getBorrowerRealInfoByIdentityCodeAndMerchantCode(identityCode, merchantCode) != null) {
            throw new ApplicationException(BorrowerErrorCodeEnum.IDENTITY_CODE_EXIST);
        }
        if (saasBorrowerRealInfoService.getBorrowerRealInfoByBorrowerCode(borrowerCode) != null) {
            throw new ApplicationException(BorrowerErrorCodeEnum.USER_PROFILE_HAS_REAL_NAME);
        }
        saasCreditHistoryService.addExpenditureCreditHistory(merchantCode, name, CreditConsumeEnum.RISK_REALNAME);
        if (!realNameAuth(name, identityCode)) {
            return Boolean.FALSE;
        }
        saasBorrowerRealInfoService.create(merchantCode, borrowerCode, name, identityCode);
        return Boolean.TRUE;
    }

    /**
     * 实名认证
     *
     * @param name         用户姓名
     * @param identityCode 用户身份证号码
     * @return
     */
    public Boolean realNameAuth(String name, String identityCode) {
        // TODO: 2018/3/29 数据库查询既有数据直接返回结果

        UserNameIdNoValidationParam param = new UserNameIdNoValidationParam(name, identityCode);
        UserNameIdNoValidationDto dto = userIntegrationService.userNameMatchIdNo(param);
        if (Objects.equals(dto.getCode(), UserNameIdNoValidationCodeEnum.MATCH.getCode())) {
            // TODO: 2018/3/29 数据库插入一致数据

            return Boolean.TRUE;
        }
        if (Objects.equals(dto.getCode(), UserNameIdNoValidationCodeEnum.MISMATCH.getCode())) {
            // TODO: 2018/3/29 数据库插入不一致数据

        }
        LOGGER.error("--------实名认证------request:{}-------response:{}", param, dto);
        return Boolean.FALSE;
    }

    /**
     * 借款人提交资料
     *
     * @param borrowerCode 借款人CODE
     * @param channelCode  渠道CODE
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public ApiResponse submitCreditInfo(String merchantCode, String borrowerCode, String channelCode, String orderNumb) {
        List<SaasChannelRiskSettingsVo> saasChannelRiskSettingsVoList = saasChannelApplication.getSaasChannelRiskSettingsByChannelCode(channelCode);
        if (CollectionUtils.isEmpty(saasChannelRiskSettingsVoList)) {
            throw new ApplicationException(ChannelErrorCodeEnum.CHANNEL_MODULE);
        }
        if (StringUtils.isNotEmpty(orderNumb)) {
            // 驳回订单再次进行提交操作
            submitRejectApplication(borrowerCode, orderNumb);
            OrderStatusEnum orderStatusEnum = saasOrderService.getOrderStatusByOrderNumb(orderNumb);
            if (orderStatusEnum.equals(OrderStatusEnum.PRELIMINARY_REVIEWER_REJECT)) {
                orderApplication.updateOrderStatus(merchantCode, borrowerCode, orderNumb, OrderStatusEnum.SUBMIT_PRELIMINARY_REVIEW, "初审驳回后再提交");
                return new ApiResponse("提交成功");
            } else if (orderStatusEnum.equals(OrderStatusEnum.FINAL_REVIEWER_REJECT)) {
                orderApplication.updateOrderStatus(merchantCode, borrowerCode, orderNumb, OrderStatusEnum.SUBMIT_FINAL_REVIEW, "复审驳回后再提交");
                return new ApiResponse("提交成功");
            }
            throw new ApplicationException(OrderErrorCodeEnum.ERROR_ORDER_NUMB);
        }
        final String newOrderNumb = OrderNoUtil.makeOrderNum();
        saasChannelRiskSettingsVoList.forEach(saasChannelRiskSettingsVo -> {
            RiskModuleEnum riskModuleEnum = RiskModuleEnum.getRiskModuleEnumByModuleCode(saasChannelRiskSettingsVo.getModuleCode());
            switch (riskModuleEnum) {
                case APPLICATION:
                    break;
                case PERSONAL_INFO:
                    submitPersonalInfo(borrowerCode, newOrderNumb, saasChannelRiskSettingsVo.getRequired());
                    break;
                case EMERGENT_CONTACT:
                    submitEmergentContact(borrowerCode, newOrderNumb, saasChannelRiskSettingsVo.getRequired());
                    break;
                case WORK_INFO:
                    submitWorkInfo(borrowerCode, newOrderNumb, saasChannelRiskSettingsVo.getRequired());
                    break;
                case IDENTITY_INFO:
                    submitIdentityInfo(borrowerCode, newOrderNumb, saasChannelRiskSettingsVo.getRequired());
                    break;
                case CARRIER_AUTHENTIC:
                    submitCarrierAuthentic(merchantCode, borrowerCode, saasChannelRiskSettingsVo.getRequired());
                    break;
                case ZM_CREDIT:
                    break;
                case EB_INFO:
                    break;
                case PLATFORM_BORROW_CREDIT:
                    submitPlatformCredit(borrowerCode, saasChannelRiskSettingsVo.getRequired());
                    break;
            }
        });
        submitApplication(borrowerCode, newOrderNumb, channelCode, SaasChannelRiskSettingsVo.DEFAULT_NEED_REQUIRED_VALUE);
        generateBlackData(merchantCode, borrowerCode);
        return new ApiResponse("提交成功");
    }

    private void submitRejectApplication(String borrowerCode, String orderNumb) {
        SaasOrderApplicationVo saasOrderApplicationVo = saasOrderApplicationService.getByBorrowerCodeAndOrderNumb(borrowerCode, orderNumb);
        if (saasOrderApplicationVo == null) {
            throw new ApplicationException(OrderErrorCodeEnum.NO_PERMISSION_OPERATE_ORDER);
        }
        orderApplication.updateOrderByOrderApplicationVo(saasOrderApplicationVo, orderNumb);
    }

    private void submitApplication(String borrowerCode, String orderNumb, String channelCode, Integer required) {
        SaasOrderApplicationVo saasOrderApplicationVo = saasOrderApplicationService.getByBorrowerCodeAndOrderNumb(borrowerCode, null);
        if (saasOrderApplicationVo == null) {
            if (SaasChannelRiskSettingsVo.DEFAULT_NEED_REQUIRED_VALUE.equals(required)) {
                throw new ApplicationException(BorrowerErrorCodeEnum.USER_PROFILE_NEED_APPLICATION_INFO);
            }
            return;
        }
        saasOrderApplicationService.updateOrderNumbByBorrowerCode(orderNumb, borrowerCode);
        orderApplication.createOrder(saasOrderApplicationVo, orderNumb, channelCode);
    }

    private void submitPersonalInfo(String borrowerCode, String orderNumb, Integer required) {
        if (saasBorrowerPersonalInfoService.countByBorrowerCodeAndOrderNumb(borrowerCode, null) == 0) {
            if (SaasChannelRiskSettingsVo.DEFAULT_NEED_REQUIRED_VALUE.equals(required)) {
                throw new ApplicationException(BorrowerErrorCodeEnum.USER_PROFILE_NEED_PERSONAL_INFO);
            }
            return;
        }
        if (!saasBorrowerPersonalInfoService.updateOrderNumbByBorrowerCode(orderNumb, borrowerCode)) {
            throw new ApplicationException(BorrowerErrorCodeEnum.USER_PROFILE_NEED_PERSONAL_INFO);
        }
    }

    private void submitEmergentContact(String borrowerCode, String orderNumb, Integer required) {
        if (saasBorrowerEmergentContactService.countByBorrowerCodeAndOrderNumb(borrowerCode, null) == 0) {
            if (SaasChannelRiskSettingsVo.DEFAULT_NEED_REQUIRED_VALUE.equals(required)) {
                throw new ApplicationException(BorrowerErrorCodeEnum.USER_PROFILE_NEED_EMERGENT_CONTACT);
            }
            return;
        }
        if (!saasBorrowerEmergentContactService.updateOrderNumbByBorrowerCode(orderNumb, borrowerCode)) {
            throw new ApplicationException(BorrowerErrorCodeEnum.USER_PROFILE_NEED_EMERGENT_CONTACT);
        }
    }

    private void submitWorkInfo(String borrowerCode, String orderNumb, Integer required) {
        if (saasBorrowerWorkInfoService.countByBorrowerCodeAndOrderNumb(borrowerCode, null) == 0) {
            if (SaasChannelRiskSettingsVo.DEFAULT_NEED_REQUIRED_VALUE.equals(required)) {
                throw new ApplicationException(BorrowerErrorCodeEnum.USER_PROFILE_NEED_WORK_INFO);
            }
            return;
        }
        if (!saasBorrowerWorkInfoService.updateOrderNumbByBorrowerCode(orderNumb, borrowerCode)) {
            throw new ApplicationException(BorrowerErrorCodeEnum.USER_PROFILE_NEED_WORK_INFO);
        }
    }

    private void submitIdentityInfo(String borrowerCode, String orderNumb, Integer required) {
        if (saasBorrowerIdentityInfoService.countByBorrowerCodeAndOrderNumb(borrowerCode, null) == 0) {
            if (SaasChannelRiskSettingsVo.DEFAULT_NEED_REQUIRED_VALUE.equals(required)) {
                throw new ApplicationException(BorrowerErrorCodeEnum.USER_PROFILE_NEED_IDENTITY_INFO);
            }
            return;
        }
        if (!saasBorrowerIdentityInfoService.updateOrderNumbByBorrowerCode(orderNumb, borrowerCode)) {
            throw new ApplicationException(BorrowerErrorCodeEnum.USER_PROFILE_NEED_IDENTITY_INFO);
        }
    }

    private void submitCarrierAuthentic(String merchantCode, String borrowerCode, Integer required) {
        if (!saasCreditCarrierService.effectivenessCreditCarrier(borrowerCode)) {
            if (SaasChannelRiskSettingsVo.DEFAULT_NEED_REQUIRED_VALUE.equals(required)) {
                throw new ApplicationException(BorrowerErrorCodeEnum.USER_PROFILE_NEED_CARRIER_AUTHENTIC);
            }
        }
    }

    private void submitPlatformCredit(String borrowerCode, Integer required) {
        if (!saasBorrowerLoanCrawlService.effectivenessLoanCrawl(borrowerCode, null)) {
            if (SaasChannelRiskSettingsVo.DEFAULT_NEED_REQUIRED_VALUE.equals(required)) {
                throw new ApplicationException(BorrowerErrorCodeEnum.USER_PROFILE_NEED_PLATFORM_BORROW_CREDIT);
            }
        }
    }

    private void generateBlackData(String merchantCode, String borrowerCode) {
        if (!saasCreditTongdunService.effectivenessCreditTongdun(borrowerCode)) {
            tongdunReportApplication.generateTongdunReport(merchantCode, borrowerCode);
        }
    }

}
