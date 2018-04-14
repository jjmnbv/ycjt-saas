package com.beitu.saas.auth.service.impl;

import com.beitu.saas.auth.dao.SaasMerchantDao;
import com.beitu.saas.auth.domain.SaasMerchantVo;
import com.beitu.saas.auth.entity.SaasMerchant;
import com.beitu.saas.auth.service.SaasMerchantService;
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
 * Date: 2018-03-22
 * Time: 15:36:13.656
 */
@Module(value = "机构表服务模块")
@NameSpace("com.beitu.saas.auth.dao.impl.SaasMerchantDaoImpl")
@Service
public class SaasMerchantServiceImpl extends AbstractBaseService implements SaasMerchantService {


    @Autowired
    private SaasMerchantDao saasMerchantDao;

    @Override
    public List<String> getMerchantList() {
        return saasMerchantDao.selectAllMerchantCode();
    }

    @Override
    public SaasMerchantVo getByMerchantCode(String merchantCode) {
        List list = this.selectByParams(new HashMap<String, Object>(2) {{
            put("merchantCode", merchantCode);
            put("deleted", false);
        }});
        if (CollectionUtils.isNotEmpty(list)) {
            SaasMerchantVo saasMerchantVo = new SaasMerchantVo();
            BeanUtils.copyProperties(((SaasMerchant) list.get(0)), saasMerchantVo);
            return saasMerchantVo;
        }
        return null;
    }

    @Override
    public Integer getMerchantAllowAccountNum(String merchantCode) {
        SaasMerchantVo saasMerchantVo = getByMerchantCode(merchantCode);
        if (null != saasMerchantVo) {
            return saasMerchantVo.getAllowAccountNum();
        }
        return 0;
    }

}


