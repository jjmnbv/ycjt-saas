package com.beitu.saas.merchant.service.impl;

import com.beitu.saas.merchant.client.domain.SaasMerchantFlowConfigVo;
import com.beitu.saas.merchant.dao.SaasMerchantFlowConfigDao;
import com.beitu.saas.merchant.client.SaasMerchantFlowConfigService;
import com.beitu.saas.merchant.entity.SaasMerchantFlowConfig;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.common.utils.CollectionUtils;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * User: xiaochong
 * Date: 2018-04-09
 * Time: 11:49:34.719
 */
@Module(value = "机构流量配置表服务模块")
@NameSpace("com.beitu.saas.merchant.dao.impl.SaasMerchantFlowConfigDaoImpl")
@Service
public class SaasMerchantFlowConfigServiceImpl extends AbstractBaseService implements SaasMerchantFlowConfigService {


    @Autowired
    private SaasMerchantFlowConfigDao saasMerchantFlowConfigDao;

    @Override
    public SaasMerchantFlowConfigVo getMerchantFlowConfigByMerchantCode(String merchantCode) {
        List list = saasMerchantFlowConfigDao.selectByParams(new HashMap(2) {{
            put("merchantCode", merchantCode);
            put("deleted", false);
        }});
        if (CollectionUtils.isNotEmpty(list)) {
            SaasMerchantFlowConfigVo saasMerchantFlowConfigVo = new SaasMerchantFlowConfigVo();
            BeanUtils.copyProperties(((SaasMerchantFlowConfig) list.get(0)), saasMerchantFlowConfigVo);
            saasMerchantFlowConfigVo.setSaasMerchantFlowConfigId(((SaasMerchantFlowConfig) list.get(0)).getId());
            return saasMerchantFlowConfigVo;
        }
        return null;
    }

    @Override
    public void replace(SaasMerchantFlowConfigVo record) {
        SaasMerchantFlowConfig saasMerchantFlowConfig = new SaasMerchantFlowConfig();
        BeanUtils.copyProperties(record,saasMerchantFlowConfig);
        saasMerchantFlowConfigDao.replace(saasMerchantFlowConfig);
    }
}


