package com.beitu.saas.auth.service.impl;

import com.beitu.saas.auth.dao.SaasMerchantConfigDao;
import com.beitu.saas.auth.entity.SaasMerchantConfig;
import com.beitu.saas.auth.enums.ContractConfigTypeEnum;
import com.beitu.saas.auth.enums.MerchantConfigTypeEnum;
import com.beitu.saas.auth.service.SaasMerchantConfigService;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.common.utils.CollectionUtils;
import com.fqgj.log.enhance.Module;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * User: xiaochong
 * Date: 2018-03-22
 * Time: 15:36:13.668
 */
@Module(value = "机构配置表服务模块")
@NameSpace("com.beitu.saas.auth.dao.impl.SaasMerchantConfigDaoImpl")
@Service
public class SaasMerchantConfigServiceImpl extends AbstractBaseService implements SaasMerchantConfigService {


    @Autowired
    private SaasMerchantConfigDao saasMerchantConfigDao;

    @Override
    public Boolean isCompanyContractByMerchantCode(String merchantCode) {
        List<SaasMerchantConfig> list = this.selectByParams(new HashMap<String, Object>(2) {{
            put("merchantCode", merchantCode);
            put("configType", MerchantConfigTypeEnum.CONTRACT_CONFIG.getKey());
            put("deleted", false);
        }});
        if (CollectionUtils.isNotEmpty(list)) {
            if (ContractConfigTypeEnum.COMPANY_CONTRACT.getKey().toString().equals(list.get(0).getConfigEnum())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<String> getSmsConfigByMerchantCode(String merchantCode) {
        List<SaasMerchantConfig> list = this.selectByParams(new HashMap<String, Object>(2) {{
            put("merchantCode", merchantCode);
            put("configType", MerchantConfigTypeEnum.SMS_CONFIG.getKey());
            put("deleted", false);
        }});
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<>();
        }
        return list.stream().map(SaasMerchantConfig::getConfigEnum).collect(toList());
    }

    @Override
    public Boolean updateByMerchantCode(SaasMerchantConfig record) {
        return saasMerchantConfigDao.updateByMerchantCode(record) > 0;
    }

    @Override
    public Boolean updateContractConfig(String merchantCode, Integer type) {
        SaasMerchantConfig saasMerchantConfig = new SaasMerchantConfig();
        saasMerchantConfig.setConfigEnum(type.toString());
        List<SaasMerchantConfig> list = this.selectByParams(new HashMap<String, Object>() {{
            put("merchantCode", merchantCode);
            put("configType", MerchantConfigTypeEnum.CONTRACT_CONFIG.getKey());

        }});
        if (CollectionUtils.isEmpty(list)) {
            saasMerchantConfig.setMerchantCode(merchantCode);
            saasMerchantConfig.setConfigType(MerchantConfigTypeEnum.CONTRACT_CONFIG.getKey().longValue());
            saasMerchantConfigDao.insert(saasMerchantConfig);
            return true;
        }
        saasMerchantConfig.setId(list.get(0).getId());
        return saasMerchantConfigDao.updateByPrimaryKey(saasMerchantConfig) > 0;
    }

    @Override
    public Boolean updateSmsConfig(String merchantCode, Boolean enable, String bizCode) {
        SaasMerchantConfig saasMerchantConfig = new SaasMerchantConfig();
        saasMerchantConfig.setDeleted(enable);
        List<SaasMerchantConfig> list = this.selectByParams(new HashMap<String, Object>(4) {{
            put("merchantCode", merchantCode);
            put("configEnum", bizCode);
            put("configType", MerchantConfigTypeEnum.SMS_CONFIG.getKey());
        }});
        if (CollectionUtils.isEmpty(list)) {
            saasMerchantConfig.setMerchantCode(merchantCode);
            saasMerchantConfig.setConfigEnum(bizCode);
            saasMerchantConfig.setConfigType(MerchantConfigTypeEnum.SMS_CONFIG.getKey().longValue());
            saasMerchantConfigDao.insert(saasMerchantConfig);
            return true;
        }
        saasMerchantConfig.setId(list.get(0).getId());
        return saasMerchantConfigDao.updateByPrimaryKey(saasMerchantConfig) > 0;
    }

    @Override
    public Boolean hasSmsConfig(String merchantCode, String bizCode) {
        List<SaasMerchantConfig> list = this.selectByParams(new HashMap<String, Object>(2) {{
            put("merchantCode", merchantCode);
            put("configEnum", bizCode);
            put("configType", MerchantConfigTypeEnum.SMS_CONFIG.getKey());
            put("deleted", false);
        }});
        if (CollectionUtils.isNotEmpty(list)) {
            return true;
        }
        return false;
    }

}


