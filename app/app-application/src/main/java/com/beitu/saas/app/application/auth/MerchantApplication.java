package com.beitu.saas.app.application.auth;

import com.beitu.saas.app.application.contract.ContractApplication;
import com.beitu.saas.auth.domain.MerchantContractInfoVo;
import com.beitu.saas.auth.domain.SaasMerchantVo;
import com.beitu.saas.auth.entity.*;
import com.beitu.saas.auth.enums.AdminErrorEnum;
import com.beitu.saas.auth.enums.ContractConfigTypeEnum;
import com.beitu.saas.auth.enums.MerchantConfigTypeEnum;
import com.beitu.saas.auth.service.*;
import com.beitu.saas.channel.client.SaasChannelRiskSettingsService;
import com.beitu.saas.channel.client.SaasChannelService;
import com.beitu.saas.common.config.ConfigUtil;
import com.beitu.saas.finance.client.SaasCreditHistoryService;
import com.beitu.saas.finance.client.SaasMerchantCreditInfoService;
import com.beitu.saas.finance.client.SaasMerchantSmsInfoService;
import com.beitu.saas.finance.client.SaasSmsHistoryService;
import com.beitu.saas.merchant.client.SaasMerchantFlowConfigService;
import com.beitu.saas.merchant.client.enums.MerchantFlowZMEnum;
import com.beitu.saas.merchant.entity.SaasMerchantFlowConfig;
import com.fqgj.common.utils.GenerOrderNoUtil;
import com.fqgj.common.utils.MD5;
import com.fqgj.common.utils.StringUtils;
import com.fqgj.exception.common.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * @author xiaochong
 * @create 2018/3/26 下午8:56
 * @description
 */
@Component
public class MerchantApplication {

    @Autowired
    private SaasMerchantService saasMerchantService;

    @Autowired
    private SaasAdminService saasAdminService;

    @Autowired
    private RoleApplication roleApplication;

    @Autowired
    private SaasAdminRoleService saasAdminRoleService;

    @Autowired
    private SaasMerchantConfigService saasMerchantConfigService;

    @Autowired
    private SaasSmsConfigDictionaryService saasSmsConfigDictionaryService;

    @Autowired
    private SaasChannelService saasChannelService;

    @Autowired
    private SaasChannelRiskSettingsService saasChannelRiskSettingsService;

    @Autowired
    private SaasMerchantSmsInfoService saasMerchantSmsInfoService;

    @Autowired
    private SaasMerchantCreditInfoService saasMerchantCreditInfoService;

    @Autowired
    private SaasSmsHistoryService saasSmsHistoryService;

    @Autowired
    private SaasCreditHistoryService saasCreditHistoryService;

    @Autowired
    private ConfigUtil configUtil;

    @Autowired
    private SaasMerchantFlowConfigService saasMerchantFlowConfigService;

    @Autowired
    private ContractApplication contractApplication;

    @Transactional(rollbackFor = Exception.class)
    public void addMerchant(SaasMerchant saasMerchant, String password, String accountPhone, String accountName) {

        //1.保存机构信息
        saasMerchant.setMerchantCode(GenerOrderNoUtil.generateOrderNo());
        saasMerchant.setAllowAccountNum(configUtil.allowAccountNum());
        saasMerchantService.create(saasMerchant);
        String merchantCode = saasMerchant.getMerchantCode();
        //2.添加登录用户
        SaasAdmin saasAdmin = new SaasAdmin();
        saasAdmin.setMerchantCode(merchantCode);
        saasAdmin.setCode(GenerOrderNoUtil.generateOrderNo());
        saasAdmin.setJob("系统超级管理员");
        saasAdmin.setName(accountName);
        saasAdmin.setMobile(accountPhone);
        saasAdmin.setPassword(MD5.md5(password));
        saasAdmin.setCreateName("system");
        saasAdmin.setDefault(true);
        saasAdmin.setEnable(true);
        if (saasAdminService.hasRegisteredMobile(saasAdmin.getMobile())) {
            throw new ApplicationException(AdminErrorEnum.MOBILE_EXIST);
        }
        saasAdminService.create(saasAdmin);
        //3.添加机构默认角色
        //超级管理员
        Long roleId = roleApplication.addRoleAndEmpower("超级管理员", "system", saasAdmin.getMerchantCode(),
                Arrays.asList(109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126),
                Arrays.asList(109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155)
        );
        //子管理员
        roleApplication.addRoleAndEmpower("子管理员", "system", saasAdmin.getMerchantCode(),
                Arrays.asList(109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126),
                Arrays.asList(109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152)
        );
        //审核
        roleApplication.addRoleAndEmpower("审核", "system", saasAdmin.getMerchantCode(),
                Arrays.asList(113, 114, 115, 116, 117),
                Arrays.asList(114, 115, 116, 117, 118, 119, 136, 137, 138, 139, 140, 141)
        );
        //财务
        roleApplication.addRoleAndEmpower("财务", "system", saasAdmin.getMerchantCode(),
                Arrays.asList(113, 118, 119, 120),
                Arrays.asList(142, 143, 144, 145, 146, 147, 148, 149, 150)
        );
        //催收
        roleApplication.addRoleAndEmpower("催收", "system", saasAdmin.getMerchantCode(),
                Arrays.asList(121),
                Arrays.asList(151, 152)
        );

        //4.给用户赋权
        SaasAdminRole saasAdminRole = new SaasAdminRole();
        saasAdminRole.setAdminCode(saasAdmin.getCode());
        saasAdminRole.setRoleId(roleId);
        saasAdminRoleService.create(saasAdminRole);
        //5.添加默认机构配置
        SaasMerchantConfig saasMerchantConfig = new SaasMerchantConfig();
        saasMerchantConfig.setMerchantCode(saasAdmin.getMerchantCode());
        saasMerchantConfig.setConfigEnum(ContractConfigTypeEnum.COMPANY_CONTRACT.getKey().toString());
        saasMerchantConfig.setConfigType(MerchantConfigTypeEnum.CONTRACT_CONFIG.getKey().longValue());
        saasMerchantConfigService.create(saasMerchantConfig);

        List<SaasSmsConfigDictionary> smsConfig = saasSmsConfigDictionaryService.getAllSmsConfig();
        smsConfig.forEach(saasSmsConfigDictionary -> {
            SaasMerchantConfig entity = new SaasMerchantConfig();
            entity.setMerchantCode(saasAdmin.getMerchantCode());
            entity.setConfigEnum(saasSmsConfigDictionary.getBizCode());
            entity.setConfigType(MerchantConfigTypeEnum.SMS_CONFIG.getKey().longValue());
            saasMerchantConfigService.create(entity);
        });

        //6.添加默认渠道
        saasChannelService.createMerchantDefaultChannel(merchantCode);
        saasChannelRiskSettingsService.createDefaultChannelRiskSettings(merchantCode);

        //7 初始化点券和短信余额
        saasMerchantSmsInfoService.increase(merchantCode, 100L);
        saasSmsHistoryService.addIncomeSmsHistory(merchantCode, 10L, null, "充值");
        saasMerchantCreditInfoService.increase(merchantCode, 100L);
        saasCreditHistoryService.addIncomeCreditHistory(merchantCode, 10L, "system", "充值");
        //8 默认流量推荐设置
        SaasMerchantFlowConfig config = new SaasMerchantFlowConfig();
        config.setMerchantCode(merchantCode);
        config.setFlowOpen(true);
        config.setFlowMaxNum(100);
        config.setZmScore(MerchantFlowZMEnum.ZM_610_UP.getKey().intValue());
        config.setFlowType(2);
        saasMerchantFlowConfigService.create(config);
        //9 电子签
        //contractApplication.lenderDoLicenseContractSign(merchantCode);
    }

    public MerchantContractInfoVo getMerchantContractInfo(String merchantCode) {
        MerchantContractInfoVo merchantContractInfoVo = new MerchantContractInfoVo();
        SaasMerchantVo saasMerchantVo = saasMerchantService.getByMerchantCode(merchantCode);
        merchantContractInfoVo.setName(saasMerchantVo.getLenderName());
        merchantContractInfoVo.setCode(saasMerchantVo.getLenderIdcard());
        merchantContractInfoVo.setContractType(ContractConfigTypeEnum.PERSONAL_CONTRACT.getKey());
        if (saasMerchantConfigService.isCompanyContractByMerchantCode(merchantCode)) {
            merchantContractInfoVo.setContractType(ContractConfigTypeEnum.COMPANY_CONTRACT.getKey());
            merchantContractInfoVo.setName(saasMerchantVo.getCompanyName());
            merchantContractInfoVo.setCode(saasMerchantVo.getJurisdicalPersonIdcard());
            merchantContractInfoVo.setContractUrl(saasMerchantVo.getContractSealUrl());
        }
        return merchantContractInfoVo;
    }

    public Boolean canAddAccount(String merchantCode) {
        Integer allowAccountNum = saasMerchantService.getMerchantAllowAccountNum(merchantCode);
        Integer accountNum = saasAdminService.countAdminByMerchantCode(merchantCode);
        if (allowAccountNum > accountNum) {
            return true;
        }
        return false;
    }


}
