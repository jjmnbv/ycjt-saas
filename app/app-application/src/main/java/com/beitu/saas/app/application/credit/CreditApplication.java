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
import com.beitu.saas.intergration.user.UserIntegrationService;
import com.beitu.saas.intergration.user.dto.UserNameIdNoValidationDto;
import com.beitu.saas.intergration.user.enums.UserNameIdNoValidationCodeEnum;
import com.beitu.saas.intergration.user.param.UserNameIdNoValidationParam;
import com.beitu.saas.order.client.SaasOrderApplicationService;
import com.beitu.saas.order.domain.SaasOrderApplicationVo;
import com.fqgj.base.services.redis.RedisClient;
import com.fqgj.common.utils.CollectionUtils;
import com.fqgj.exception.common.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author linanjun
 * @create 2018/3/23 下午12:14
 * @description
 */
@Service
public class CreditApplication {

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
    private SaasBorrowerCarrierService saasBorrowerCarrierService;

    @Autowired
    private OrderApplication orderApplication;

    @Autowired
    private UserIntegrationService userIntegrationService;

    public List<CreditModuleListVo> listCreditModule(String channelCode, String borrowerCode) {
        List<SaasChannelRiskSettingsVo> saasChannelRiskSettingsVoList = saasChannelApplication.getSaasChannelRiskSettingsByChannelCode(channelCode);
        if (CollectionUtils.isEmpty(saasChannelRiskSettingsVoList)) {
            throw new ApplicationException(ChannelErrorCodeEnum.CHANNEL_MODULE);
        }
        List<CreditModuleListVo> creditModuleListVoList = new ArrayList<>(saasChannelRiskSettingsVoList.size());
        saasChannelRiskSettingsVoList.forEach(saasChannelRiskSettingsVo -> {
            CreditModuleListVo creditModuleListVo = new CreditModuleListVo();
            creditModuleListVo.setModuleCode(saasChannelRiskSettingsVo.getModuleCode());
            creditModuleListVo.setRequired(saasChannelRiskSettingsVo.getRequired() == 1);
            creditModuleListVo.setApplyStatus(getInfoApplyStatus(borrowerCode, saasChannelRiskSettingsVo.getModuleCode()).getCode());
            creditModuleListVoList.add(creditModuleListVo);
        });
        return creditModuleListVoList;
    }

    private BorrowerInfoApplyStatusEnum getInfoApplyStatus(String borrowerCode, String moduleCode) {
        RiskModuleEnum riskModuleEnum = RiskModuleEnum.getRiskModuleEnumByModuleCode(moduleCode);
        switch (riskModuleEnum) {
            case APPLICATION:
                if (saasOrderApplicationService.getByBorrowerCode(borrowerCode) == null) {
                    return BorrowerInfoApplyStatusEnum.INCOMPLETE;
                }
                return BorrowerInfoApplyStatusEnum.FINISHED;
            case PERSONAL_INFO:
                if (saasBorrowerPersonalInfoService.countByBorrowerCode(borrowerCode) == 0) {
                    return BorrowerInfoApplyStatusEnum.INCOMPLETE;
                }
                return BorrowerInfoApplyStatusEnum.FINISHED;
            case EMERGENT_CONTACT:
                if (saasBorrowerEmergentContactService.countByBorrowerCode(borrowerCode) == 0) {
                    return BorrowerInfoApplyStatusEnum.INCOMPLETE;
                }
                return BorrowerInfoApplyStatusEnum.FINISHED;
            case WORK_INFO:
                if (saasBorrowerWorkInfoService.countByBorrowerCode(borrowerCode) == 0) {
                    return BorrowerInfoApplyStatusEnum.INCOMPLETE;
                }
                return BorrowerInfoApplyStatusEnum.FINISHED;
            case IDENTITY_INFO:
                if (saasBorrowerIdentityInfoService.countByBorrowerCode(borrowerCode) == 0) {
                    return BorrowerInfoApplyStatusEnum.INCOMPLETE;
                }
                return BorrowerInfoApplyStatusEnum.FINISHED;
            case CARRIER_AUTHENTIC:
                if (saasBorrowerCarrierService.countByBorrowerCode(borrowerCode) == 0) {
                    return BorrowerInfoApplyStatusEnum.INCOMPLETE;
                }
                String value = redisClient.get(RedisKeyConsts.H5_CARRIER_CRAWLING, borrowerCode);
                if (value != null) {
                    return BorrowerInfoApplyStatusEnum.AUTHENTICATING;
                }
                return BorrowerInfoApplyStatusEnum.FINISHED;
            case ZM_CREDIT:
                return BorrowerInfoApplyStatusEnum.INCOMPLETE;
            case EB_INFO:
                return BorrowerInfoApplyStatusEnum.INCOMPLETE;
            case PLATFORM_BORROW_CREDIT:
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
    public Boolean userRealNameAuth(String borrowerCode, String name, String identityCode) {
        if (!realNameAuth(name, identityCode)) {
            return Boolean.FALSE;
        }
        saasBorrowerRealInfoService.create(borrowerCode, name, identityCode);
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
    public ApiResponse submitCreditInfo(String borrowerCode, String channelCode) {
        List<SaasChannelRiskSettingsVo> saasChannelRiskSettingsVoList = saasChannelApplication.getSaasChannelRiskSettingsByChannelCode(channelCode);
        if (CollectionUtils.isEmpty(saasChannelRiskSettingsVoList)) {
            return new ApiResponse("提交手机号码成功");
        }
        final String orderNumb = OrderNoUtil.makeOrderNum();
        saasChannelRiskSettingsVoList.forEach(saasChannelRiskSettingsVo -> {
            RiskModuleEnum riskModuleEnum = RiskModuleEnum.getRiskModuleEnumByModuleCode(saasChannelRiskSettingsVo.getModuleCode());
            switch (riskModuleEnum) {
                case APPLICATION:
                    submitApplication(borrowerCode, orderNumb, channelCode, saasChannelRiskSettingsVo.getRequired());
                    break;
                case PERSONAL_INFO:
                    submitPersonalInfo(borrowerCode, orderNumb, saasChannelRiskSettingsVo.getRequired());
                    break;
                case EMERGENT_CONTACT:
                    submitEmergentContact(borrowerCode, orderNumb, saasChannelRiskSettingsVo.getRequired());
                    break;
                case WORK_INFO:
                    submitWorkInfo(borrowerCode, orderNumb, saasChannelRiskSettingsVo.getRequired());
                    break;
                case IDENTITY_INFO:
                    submitIdentityInfo(borrowerCode, orderNumb, saasChannelRiskSettingsVo.getRequired());
                    break;
                case CARRIER_AUTHENTIC:
                    submitCarrierAuthentic(borrowerCode, saasChannelRiskSettingsVo.getRequired());
                    break;
                case ZM_CREDIT:
                    break;
                case EB_INFO:
                    break;
                case PLATFORM_BORROW_CREDIT:
                    break;
            }
        });
        return new ApiResponse("提交成功");
    }

    private void submitApplication(String borrowerCode, String orderNumb, String channelCode, Integer required) {
        SaasOrderApplicationVo saasOrderApplicationVo = saasOrderApplicationService.getByBorrowerCode(borrowerCode);
        if (saasOrderApplicationVo == null && SaasChannelRiskSettingsVo.DEFAULT_NEED_REQUIRED_VALUE.equals(required)) {
            throw new ApplicationException(BorrowerErrorCodeEnum.USER_PROFILE_NEED_APPLICATION_INFO);
        }
        orderApplication.createOrder(saasOrderApplicationVo, orderNumb, channelCode);
    }

    private void submitPersonalInfo(String borrowerCode, String orderNumb, Integer required) {
        if (saasBorrowerPersonalInfoService.countByBorrowerCode(borrowerCode) == 0 && SaasChannelRiskSettingsVo.DEFAULT_NEED_REQUIRED_VALUE.equals(required)) {
            throw new ApplicationException(BorrowerErrorCodeEnum.USER_PROFILE_NEED_PERSONAL_INFO);
        }
        if (!saasBorrowerPersonalInfoService.updateOrderNumbByBorrowerCode(orderNumb, borrowerCode)) {
            throw new ApplicationException(BorrowerErrorCodeEnum.USER_PROFILE_NEED_PERSONAL_INFO);
        }
    }

    private void submitEmergentContact(String borrowerCode, String orderNumb, Integer required) {
        if (saasBorrowerEmergentContactService.countByBorrowerCode(borrowerCode) == 0 && SaasChannelRiskSettingsVo.DEFAULT_NEED_REQUIRED_VALUE.equals(required)) {
            throw new ApplicationException(BorrowerErrorCodeEnum.USER_PROFILE_NEED_EMERGENT_CONTACT);
        }
        if (!saasBorrowerEmergentContactService.updateOrderNumbByBorrowerCode(orderNumb, borrowerCode)) {
            throw new ApplicationException(BorrowerErrorCodeEnum.USER_PROFILE_NEED_EMERGENT_CONTACT);
        }
    }

    private void submitWorkInfo(String borrowerCode, String orderNumb, Integer required) {
        if (saasBorrowerWorkInfoService.countByBorrowerCode(borrowerCode) == 0 && SaasChannelRiskSettingsVo.DEFAULT_NEED_REQUIRED_VALUE.equals(required)) {
            throw new ApplicationException(BorrowerErrorCodeEnum.USER_PROFILE_NEED_WORK_INFO);
        }
        if (!saasBorrowerWorkInfoService.updateOrderNumbByBorrowerCode(orderNumb, borrowerCode)) {
            throw new ApplicationException(BorrowerErrorCodeEnum.USER_PROFILE_NEED_WORK_INFO);
        }
    }

    private void submitIdentityInfo(String borrowerCode, String orderNumb, Integer required) {
        if (saasBorrowerIdentityInfoService.countByBorrowerCode(borrowerCode) == 0 && SaasChannelRiskSettingsVo.DEFAULT_NEED_REQUIRED_VALUE.equals(required)) {
            throw new ApplicationException(BorrowerErrorCodeEnum.USER_PROFILE_NEED_IDENTITY_INFO);
        }
        if (!saasBorrowerIdentityInfoService.updateOrderNumbByBorrowerCode(orderNumb, borrowerCode)) {
            throw new ApplicationException(BorrowerErrorCodeEnum.USER_PROFILE_NEED_IDENTITY_INFO);
        }
    }

    private void submitCarrierAuthentic(String borrowerCode, Integer required) {
        if (saasBorrowerCarrierService.countByBorrowerCode(borrowerCode) == 0 && SaasChannelRiskSettingsVo.DEFAULT_NEED_REQUIRED_VALUE.equals(required)) {
            throw new ApplicationException(BorrowerErrorCodeEnum.USER_PROFILE_NEED_IDENTITY_INFO);
        }
    }

}
