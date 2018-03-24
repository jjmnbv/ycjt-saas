package com.beitu.saas.borrower.service.impl;

import com.beitu.saas.borrower.client.SaasBorrowerCarrierService;
import com.beitu.saas.borrower.dao.SaasBorrowerCarrierDao;
import com.beitu.saas.borrower.domain.SaasBorrowerCarrierVo;
import com.beitu.saas.borrower.entity.SaasBorrowerCarrier;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.common.utils.CollectionUtils;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * User: jungle
 * Date: 2018-03-22
 * Time: 15:56:46.956
 */
@Module(value = "SAAS借款人运营商报告信息表服务模块")
@NameSpace("com.beitu.saas.borrower.dao.impl.SaasBorrowerCarrierDaoImpl")
@Service
public class SaasBorrowerCarrierServiceImpl extends AbstractBaseService implements SaasBorrowerCarrierService {

    @Autowired
    private SaasBorrowerCarrierDao saasBorrowerCarrierDao;

    @Override
    public SaasBorrowerCarrierVo getByBorrowerCode(String borrowerCode) {
        List<SaasBorrowerCarrier> saasBorrowerCarrierList = saasBorrowerCarrierDao.selectByParams(new HashMap<String, Object>(4) {{
            put("borrowerCode", borrowerCode);
            put("date", new Date());
            put("deleted", Boolean.FALSE);
        }});
        if (CollectionUtils.isEmpty(saasBorrowerCarrierList)) {
            return null;
        }
        return SaasBorrowerCarrierVo.convertEntityToVO(saasBorrowerCarrierList.get(0));
    }

    @Override
    public int countByBorrowerCode(String borrowerCode) {
        return saasBorrowerCarrierDao.queryTotal(new HashMap<String, Object>(4) {{
            put("borrowerCode", borrowerCode);
            put("date", new Date());
            put("deleted", Boolean.FALSE);
        }});
    }

}


