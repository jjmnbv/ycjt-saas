package com.beitu.saas.app.application.credit;

import com.beitu.saas.app.application.channel.SaasChannelApplication;
import com.beitu.saas.app.application.credit.vo.CreditModuleListVo;
import com.beitu.saas.app.enums.BorrowerInfoApplyStatusEnum;
import com.beitu.saas.borrower.client.SaasBorrowerRealInfoService;
import com.beitu.saas.borrower.enums.BorrowerErrorCodeEnum;
import com.beitu.saas.channel.domain.SaasChannelRiskSettingsVo;
import com.beitu.saas.channel.enums.ChannelErrorCodeEnum;
import com.beitu.saas.channel.enums.RiskModuleEnum;
import com.fqgj.common.utils.CollectionUtils;
import com.fqgj.common.utils.StringUtils;
import com.fqgj.exception.common.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<CreditModuleListVo> listCreditModule(String channelCode, String borrowerCode, String orderNumb) {
        List<SaasChannelRiskSettingsVo> saasChannelRiskSettingsVoList = saasChannelApplication.getSaasChannelRiskSettingsByChannelCode(channelCode);
        if (CollectionUtils.isEmpty(saasChannelRiskSettingsVoList)) {
            throw new ApplicationException(ChannelErrorCodeEnum.CHANNEL_MODULE);
        }
        List<CreditModuleListVo> creditModuleListVoList = new ArrayList<>(saasChannelRiskSettingsVoList.size());
        saasChannelRiskSettingsVoList.forEach(saasChannelRiskSettingsVo -> {
            CreditModuleListVo creditModuleListVo = new CreditModuleListVo();
            creditModuleListVo.setModuleCode(saasChannelRiskSettingsVo.getModuleCode());
            creditModuleListVo.setRequired(saasChannelRiskSettingsVo.getRequired() == 1);
            creditModuleListVo.setApplyStatus(getInfoApplyStatus(borrowerCode, orderNumb, saasChannelRiskSettingsVo.getModuleCode()).getCode());
            creditModuleListVoList.add(creditModuleListVo);
        });
        return creditModuleListVoList;
    }

    private BorrowerInfoApplyStatusEnum getInfoApplyStatus(String borrowerCode, String orderNumb, String moduleCode) {
        if (StringUtils.isEmpty(orderNumb)) {
            return BorrowerInfoApplyStatusEnum.INCOMPLETE;
        }
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

}
