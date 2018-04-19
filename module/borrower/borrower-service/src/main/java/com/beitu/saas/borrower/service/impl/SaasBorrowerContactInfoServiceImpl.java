package com.beitu.saas.borrower.service.impl;

import com.beitu.saas.borrower.client.SaasBorrowerContactInfoService;
import com.beitu.saas.borrower.dao.SaasBorrowerContactInfoDao;
import com.beitu.saas.borrower.domain.SaasBorrowerContactInfoVo;
import com.beitu.saas.borrower.entity.SaasBorrowerContactInfo;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.common.utils.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.fqgj.log.enhance.Module;

import java.util.List;

/**
 * User: linchengyu
 * Date: 2018-04-19
 * Time: 16:43:06.900
 */
@Module(value = "SAAS借款人设备通讯录表服务模块")
@NameSpace("com.beitu.saas.borrower.dao.impl.SaasBorrowerContactInfoDaoImpl")
@Service
public class SaasBorrowerContactInfoServiceImpl extends AbstractBaseService implements SaasBorrowerContactInfoService {
    
    @Autowired
    private SaasBorrowerContactInfoDao saasBorrowerContactInfoDao;
    
    @Override
    public SaasBorrowerContactInfoVo getContactInfoByBorrowerCode(String merchantCode, String borrowerCode) {
        SaasBorrowerContactInfo entity = saasBorrowerContactInfoDao.getByBorrowerCode(merchantCode, borrowerCode);
        if (entity == null) {
            return null;
        }
        SaasBorrowerContactInfoVo vo = new SaasBorrowerContactInfoVo();
        BeanUtils.copyProperties(entity, vo);
        vo.setSaasBorrowerContactInfoId(entity.getId());
        return vo;
    }
    
    @Override
    public Boolean addContactInfo(SaasBorrowerContactInfoVo saasBorrowerContactInfoVo) {
        if (saasBorrowerContactInfoVo == null) {
            return Boolean.FALSE;
        }
        String merchantCode = saasBorrowerContactInfoVo.getMerchantCode();
        String borrowerCode = saasBorrowerContactInfoVo.getBorrowerCode();
        List<SaasBorrowerContactInfo> list = saasBorrowerContactInfoDao.getListByBorrowerCode(merchantCode, borrowerCode);
        if (CollectionUtils.isNotEmpty(list)) {
            for (SaasBorrowerContactInfo contactInfo : list) {
                saasBorrowerContactInfoDao.deleteByPrimaryKey(contactInfo.getId());
            }
        }
        SaasBorrowerContactInfo entity = new SaasBorrowerContactInfo();
        BeanUtils.copyProperties(saasBorrowerContactInfoVo, entity);
        return saasBorrowerContactInfoDao.insert(entity) != null;
    }
    
}


