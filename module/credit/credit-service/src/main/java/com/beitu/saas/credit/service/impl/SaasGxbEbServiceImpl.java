package com.beitu.saas.credit.service.impl;

import com.beitu.saas.common.utils.DateUtil;
import com.beitu.saas.credit.dao.SaasGxbEbDao;
import com.beitu.saas.credit.client.SaasGxbEbService;
import com.beitu.saas.credit.domain.SaasGxbEbVo;
import com.beitu.saas.credit.entity.SaasGxbEb;
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
 * Date: 2018-04-17
 * Time: 17:27:58.951
 */
@Module(value = "公信宝电商爬虫表服务模块")
@NameSpace("com.beitu.saas.credit.dao.impl.SaasGxbEbDaoImpl")
@Service
public class SaasGxbEbServiceImpl extends AbstractBaseService implements SaasGxbEbService {


    @Autowired
    private SaasGxbEbDao saasGxbEbDao;

    @Override
    public void saveGXBEbTop(SaasGxbEb saasGxbEb) {
        SaasGxbEbVo saasGxbEbVo = getGxbEbTopByBorrowerCode(saasGxbEb.getBorrowerCode());
        if (saasGxbEbVo != null) {
            saasGxbEbDao.deleteByPrimaryKey(saasGxbEbVo.getSaasGxbEbId());
        }
        saasGxbEbDao.insert(saasGxbEb);

    }

    @Override
    public SaasGxbEbVo getGxbEbTopByBorrowerCode(String borrowerCode) {
        List list = this.selectByParams(new HashMap(2) {{
            put("borrowerCode", borrowerCode);
            put("deleted", false);
        }});
        if (CollectionUtils.isNotEmpty(list)) {
            SaasGxbEb saasGxbEb = (SaasGxbEb) list.get(0);
            SaasGxbEbVo saasGxbEbVo = new SaasGxbEbVo();
            BeanUtils.copyProperties(saasGxbEb, saasGxbEbVo);
            saasGxbEbVo.setSaasGxbEbId(saasGxbEb.getId());
            return saasGxbEbVo;
        }
        return null;
    }

    @Override
    public Boolean canAuthEbByBorrowerCode(String borrowerCode) {
        SaasGxbEbVo saasGxbEbVo = getGxbEbTopByBorrowerCode(borrowerCode);
        if (saasGxbEbVo != null && DateUtil.isExceedOneMonth(saasGxbEbVo.getGmtCreate())) {
            return false;
        }
        return true;
    }
}


