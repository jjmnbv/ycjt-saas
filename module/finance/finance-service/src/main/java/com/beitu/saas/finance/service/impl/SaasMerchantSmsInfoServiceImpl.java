package com.beitu.saas.finance.service.impl;

import com.beitu.saas.common.utils.CalculateUtil;
import com.beitu.saas.common.utils.CryptUtil;
import com.beitu.saas.finance.client.SaasMerchantSmsInfoService;
import com.beitu.saas.finance.client.enums.BalanceErrorCodeEnum;
import com.beitu.saas.finance.dao.SaasMerchantSmsInfoDao;
import com.beitu.saas.finance.entity.SaasMerchantCreditInfoEntity;
import com.beitu.saas.finance.entity.SaasMerchantSmsInfoEntity;
import com.fqgj.common.api.exception.ApiErrorException;
import com.fqgj.common.api.exception.ApiIllegalArgumentException;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: fenqiguanjia
 * Date: 2018-03-23
 * Time: 20:33:11.066
 */
@Module(value = "机构短信表服务模块")
@NameSpace("com.fqgj.demo.dao.impl.SaasMerchantSmsInfoDaoImpl")
@Service
public class SaasMerchantSmsInfoServiceImpl extends AbstractBaseService implements SaasMerchantSmsInfoService {


    @Autowired
    private SaasMerchantSmsInfoDao saasMerchantSmsInfoDao;

    @Override
    public SaasMerchantSmsInfoEntity getSmsInfoByMerchantCode(String merchantCode) {
        return saasMerchantSmsInfoDao.selectSmsByMerchantCode(merchantCode);
    }

    @Override
    public void update(SaasMerchantSmsInfoEntity saasMerchantSmsInfoEntity) {
        saasMerchantSmsInfoDao.updateByPrimaryKey(saasMerchantSmsInfoEntity);
    }

    @Override
    public Boolean increase(String merchantCode, Long value) {
        return creditValueOp(merchantCode, value, Boolean.TRUE);
    }

    @Override
    public Boolean decrease(String merchantCode, Long value) {
        return creditValueOp(merchantCode, value, Boolean.FALSE);
    }

    @Override
    public Long getSmsByMerchantCode(String merchantCode) {
        return getDecryptValue(getOrInitUserSmsInfoByMerchantCode(merchantCode).getEncryptValue());
    }

    private Boolean creditValueOp(String merchantCode, Long value, Boolean add) {
        SaasMerchantSmsInfoEntity userBalanceInfo = getOrInitUserSmsInfoByMerchantCode(merchantCode);
        Long balanceValue = getDecryptValue(userBalanceInfo.getEncryptValue());
        if (add) {
            balanceValue = balanceValue + value;
        } else {
            if (balanceValue.compareTo(value) == -1) {
                throw new ApiErrorException(BalanceErrorCodeEnum.LACK_OF_BALANCE.setMsg("短信条数不足"));
            }
            balanceValue = balanceValue - value;
        }
        userBalanceInfo.setValue(balanceValue);
        userBalanceInfo.setEncryptValue(CryptUtil.Encrypt(balanceValue + "_" + merchantCode));
        return saasMerchantSmsInfoDao.updateByPrimaryKey(userBalanceInfo) > 0;
    }

    private SaasMerchantSmsInfoEntity getOrInitUserSmsInfoByMerchantCode(String merchantCode) {
        SaasMerchantSmsInfoEntity merchantCreditInfo = this.getSmsInfoByMerchantCode(merchantCode);
        if (merchantCreditInfo != null) {
            if (!CalculateUtil.isIllegalAmount(merchantCode, merchantCreditInfo.getEncryptValue())) {
                throw new ApiIllegalArgumentException(BalanceErrorCodeEnum.AMOUNT_ERROR.setMsg("数据非法"));
            }
            return merchantCreditInfo;
        }
        merchantCreditInfo = new SaasMerchantSmsInfoEntity();
        merchantCreditInfo.setVersion(0L);
        merchantCreditInfo.setMerchantCode(merchantCode);
        merchantCreditInfo.setEncryptValue(CryptUtil.Encrypt(Long.valueOf(0) + "_" + merchantCode));
        merchantCreditInfo.setValue(0L);
        return saasMerchantSmsInfoDao.insert(merchantCreditInfo);
    }

    private Long getDecryptValue(String encryptValue) {
        String encrypt = CryptUtil.Decrypt(encryptValue);
        String substring = encrypt.substring(0, encrypt.indexOf("_"));
        return Long.valueOf(substring);
    }

}


