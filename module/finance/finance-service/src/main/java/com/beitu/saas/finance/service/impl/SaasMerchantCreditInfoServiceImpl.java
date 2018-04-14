package com.beitu.saas.finance.service.impl;

import com.beitu.saas.common.utils.CalculateUtil;
import com.beitu.saas.common.utils.CryptUtil;
import com.beitu.saas.common.utils.DecimalUtils;
import com.beitu.saas.finance.client.SaasMerchantCreditInfoService;
import com.beitu.saas.finance.client.enums.BalanceErrorCodeEnum;
import com.beitu.saas.finance.dao.SaasMerchantCreditInfoDao;
import com.beitu.saas.finance.entity.SaasMerchantBalanceInfoEntity;
import com.beitu.saas.finance.entity.SaasMerchantCreditInfoEntity;
import com.fqgj.common.api.exception.ApiErrorException;
import com.fqgj.common.api.exception.ApiIllegalArgumentException;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * User: fenqiguanjia
 * Date: 2018-03-23
 * Time: 20:33:11.056
 */
@Module(value = "机构点券表服务模块")
@NameSpace("com.fqgj.demo.dao.impl.SaasMerchantCreditInfoDaoImpl")
@Service
public class SaasMerchantCreditInfoServiceImpl extends AbstractBaseService implements SaasMerchantCreditInfoService {


    @Autowired
    private SaasMerchantCreditInfoDao saasMerchantCreditInfoDao;

    @Override
    public SaasMerchantCreditInfoEntity getCreditInfoByMerchantCode(String merchantCode) {
        return saasMerchantCreditInfoDao.selectCreditInfoByMerchantCode(merchantCode);
    }

    @Override
    public void update(SaasMerchantCreditInfoEntity saasMerchantCreditInfoEntity) {
        saasMerchantCreditInfoDao.updateByPrimaryKey(saasMerchantCreditInfoEntity);
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
    public Long getCreditByMerchantCode(String merchantCode) {
        return getDecryptValue(getOrInitUserCreditInfoByMerchantCode(merchantCode).getEncryptValue());
    }

    private Boolean creditValueOp(String merchantCode, Long value, Boolean add) {
        SaasMerchantCreditInfoEntity userBalanceInfo = getOrInitUserCreditInfoByMerchantCode(merchantCode);
        Long balanceValue = getDecryptValue(userBalanceInfo.getEncryptValue());
        if (add) {
            balanceValue = balanceValue + value;
        } else {
//            if (balanceValue.compareTo(value) == -1) {
//                throw new ApiErrorException(BalanceErrorCodeEnum.LACK_OF_BALANCE);
//            }
            balanceValue = balanceValue - value;
        }
        userBalanceInfo.setValue(balanceValue);
        userBalanceInfo.setEncryptValue(CryptUtil.Encrypt(balanceValue + "_" + merchantCode));
        return saasMerchantCreditInfoDao.updateByPrimaryKey(userBalanceInfo) > 0;
    }

    private SaasMerchantCreditInfoEntity getOrInitUserCreditInfoByMerchantCode(String merchantCode) {
        SaasMerchantCreditInfoEntity merchantCreditInfo = this.getCreditInfoByMerchantCode(merchantCode);
        if (merchantCreditInfo != null) {
            if (!CalculateUtil.isIllegalAmount(merchantCode, merchantCreditInfo.getEncryptValue())) {
                throw new ApiIllegalArgumentException(BalanceErrorCodeEnum.AMOUNT_ERROR);
            }
            return merchantCreditInfo;
        }
        merchantCreditInfo = new SaasMerchantCreditInfoEntity();
        merchantCreditInfo.setVersion(0L);
        merchantCreditInfo.setMerchantCode(merchantCode);
        merchantCreditInfo.setEncryptValue(CryptUtil.Encrypt(Long.valueOf(0) + "_" + merchantCode));
        merchantCreditInfo.setValue(0L);
        return saasMerchantCreditInfoDao.insert(merchantCreditInfo);
    }

    private Long getDecryptValue(String encryptValue) {
        String encrypt = CryptUtil.Decrypt(encryptValue);
        String substring = encrypt.substring(0, encrypt.indexOf("_"));
        return Long.valueOf(substring);
    }

}


