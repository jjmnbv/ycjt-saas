package com.beitu.saas.finance.service.impl;

import com.beitu.saas.common.utils.CalculateUtil;
import com.beitu.saas.common.utils.CryptUtil;
import com.beitu.saas.common.utils.DecimalUtils;
import com.beitu.saas.finance.client.SaasMerchantBalanceInfoService;
import com.beitu.saas.finance.client.enums.BalanceErrorCodeEnum;
import com.beitu.saas.finance.dao.SaasMerchantBalanceInfoDao;
import com.beitu.saas.finance.entity.SaasMerchantBalanceInfoEntity;
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
 * Date: 2018-03-28
 * Time: 11:41:55.210
 */
@Module(value = "机构账户余额表服务模块")
@NameSpace("com.beitu.saas.finance.dao.impl.SaasMerchantBalanceInfoDaoImpl")
@Service
public class SaasMerchantBalanceInfoServiceImpl extends AbstractBaseService implements SaasMerchantBalanceInfoService {


    @Autowired
    private SaasMerchantBalanceInfoDao saasMerchantBalanceInfoDao;

    @Override
    public SaasMerchantBalanceInfoEntity getMerchantBalanceInfoByMerchantCode(String merchantCode) {
        return saasMerchantBalanceInfoDao.selectMerchantBalanceInfoEntity(merchantCode);
    }

    @Override
    public Boolean increase(String merchantCode, BigDecimal value) {
        return balanceValueOp(merchantCode, value, Boolean.TRUE);
    }

    @Override
    public Boolean increase(String merchantCode, Double value) {
        BigDecimal addValue = DecimalUtils.round(value, 2);
        return increase(merchantCode, addValue);
    }

    @Override
    public Boolean decrease(String merchantCode, BigDecimal value) {
        return balanceValueOp(merchantCode, value, Boolean.FALSE);
    }

    @Override
    public Boolean decrease(String merchantCode, Double value) {
        BigDecimal subValue = DecimalUtils.round(value, 2);
        return decrease(merchantCode, subValue);
    }

    @Override
    public BigDecimal getBalanceByMerchantCode(String merchantCode) {
        return getDecryptValue(getOrInitUserBalanceByMerchantCode(merchantCode).getEncryptValue());
    }

    private Boolean balanceValueOp(String merchantCode, BigDecimal value, Boolean add) {
        SaasMerchantBalanceInfoEntity userBalanceInfo = getOrInitUserBalanceByMerchantCode(merchantCode);
        BigDecimal balanceValue = getDecryptValue(userBalanceInfo.getEncryptValue());
        if (add) {
            balanceValue = balanceValue.add(value);
        } else {
            if (balanceValue.compareTo(value) == -1) {
                throw new ApiErrorException(BalanceErrorCodeEnum.LACK_OF_BALANCE);
            }
            balanceValue = balanceValue.subtract(value);
        }
        userBalanceInfo.setValue(balanceValue);
        userBalanceInfo.setEncryptValue(CryptUtil.Encrypt(balanceValue + "_" + merchantCode));
        return saasMerchantBalanceInfoDao.updateByPrimaryKey(userBalanceInfo) > 0;
    }

    private SaasMerchantBalanceInfoEntity getOrInitUserBalanceByMerchantCode(String merchantCode) {
        SaasMerchantBalanceInfoEntity merchantBalanceInfo = this.getMerchantBalanceInfoByMerchantCode(merchantCode);
        if (merchantBalanceInfo != null) {
            if (!CalculateUtil.isIllegalAmount(merchantCode, merchantBalanceInfo.getEncryptValue())) {
                throw new ApiIllegalArgumentException(BalanceErrorCodeEnum.AMOUNT_ERROR);
            }
            return merchantBalanceInfo;
        }
        merchantBalanceInfo = new SaasMerchantBalanceInfoEntity();
        merchantBalanceInfo.setVersion(0L);
        merchantBalanceInfo.setMerchantCode(merchantCode);
        merchantBalanceInfo.setEncryptValue(CryptUtil.Encrypt(BigDecimal.ZERO + "_" + merchantCode));
        merchantBalanceInfo.setValue(BigDecimal.ZERO);
        return saasMerchantBalanceInfoDao.insert(merchantBalanceInfo);
    }

    private BigDecimal getDecryptValue(String encryptValue) {
        String encrypt = CryptUtil.Decrypt(encryptValue);
        String substring = encrypt.substring(0, encrypt.indexOf("_"));
        return BigDecimal.valueOf(Double.valueOf(substring));
    }

}


