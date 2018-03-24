package com.beitu.saas.app.application.credit;

import com.beitu.saas.app.api.ApiResponse;
import com.beitu.saas.app.application.channel.SaasChannelApplication;
import com.beitu.saas.app.application.credit.vo.CreditModuleListVo;
import com.beitu.saas.app.enums.BorrowerInfoApplyStatusEnum;
import com.beitu.saas.borrower.client.SaasBorrowerRealInfoService;
import com.beitu.saas.borrower.enums.BorrowerErrorCodeEnum;
import com.beitu.saas.channel.domain.SaasChannelRiskSettingsVo;
import com.beitu.saas.channel.enums.ChannelErrorCodeEnum;
import com.beitu.saas.channel.enums.RiskModuleEnum;
import com.beitu.saas.common.utils.OrderNoUtil;
import com.beitu.saas.order.client.SaasOrderApplicationService;
import com.fqgj.common.utils.CollectionUtils;
import com.fqgj.common.utils.StringUtils;
import com.fqgj.exception.common.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author linanjun
 * @create 2018/3/23 下午12:14
 * @description
 */
@Service
public class CreditApplication {

    @Autowired
    private SaasChannelApplication saasChannelApplication;

    @Autowired
    private SaasBorrowerRealInfoService saasBorrowerRealInfoService;

    @Autowired
    private SaasOrderApplicationService saasOrderApplicationService;

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
                return BorrowerInfoApplyStatusEnum.INCOMPLETE;
            case PERSONAL_INFO:
                return BorrowerInfoApplyStatusEnum.INCOMPLETE;
            case EMERGENT_CONTACT:
                return BorrowerInfoApplyStatusEnum.INCOMPLETE;
            case WORK_INFO:
                return BorrowerInfoApplyStatusEnum.INCOMPLETE;
            case CARRIER_AUTHENTIC:
                return BorrowerInfoApplyStatusEnum.INCOMPLETE;
            case ZM_CREDIT:
                return BorrowerInfoApplyStatusEnum.INCOMPLETE;
            case EB_INFO:
                return BorrowerInfoApplyStatusEnum.INCOMPLETE;
            case PLATFORM_BORROW_CREDIT:
                return BorrowerInfoApplyStatusEnum.INCOMPLETE;
            case IDENTITY_INFO:
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
        // TODO
        return Boolean.TRUE;
    }

    /**
     * 借款人提交资料
     *
     * @param borrowerCode 借款人CODE
     * @param merchantCode 机构CODE
     * @param channelCode  渠道CODE
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public ApiResponse submitCreditInfo(String borrowerCode, String merchantCode, String channelCode) {
        List<SaasChannelRiskSettingsVo> saasChannelRiskSettingsVoList = saasChannelApplication.getSaasChannelRiskSettingsByChannelCode(channelCode);
        if (CollectionUtils.isEmpty(saasChannelRiskSettingsVoList)) {
            return new ApiResponse("提交手机号码成功");
        }
        String orderNumb = OrderNoUtil.makeOrderNum();
        saasChannelRiskSettingsVoList.forEach(saasChannelRiskSettingsVo -> {
            RiskModuleEnum riskModuleEnum = RiskModuleEnum.getRiskModuleEnumByModuleCode(saasChannelRiskSettingsVo.getModuleCode());
            switch (riskModuleEnum) {
                case APPLICATION:
                    break;
                case PERSONAL_INFO:
                    break;
                case EMERGENT_CONTACT:
                    break;
                case WORK_INFO:
                    break;
                case CARRIER_AUTHENTIC:
                    break;
                case ZM_CREDIT:
                    break;
                case EB_INFO:
                    break;
                case PLATFORM_BORROW_CREDIT:
                    break;
                case IDENTITY_INFO:
                    break;
            }
        });
        return new ApiResponse("提交成功");
    }

    private void submitApplication() {

    }

}
