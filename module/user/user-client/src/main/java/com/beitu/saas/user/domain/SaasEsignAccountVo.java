package com.beitu.saas.user.domain;

import com.beitu.saas.user.entity.SaasEsignAccount;
import com.fqgj.common.api.ResponseData;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * User: jungle
 * Date: 2018-04-17
 * Time: 11:27:43.178
 */
public class SaasEsignAccountVo implements ResponseData, Serializable {

    private Long saasEsignAccountId;

    /**
     * 账户信息CODE
     */
    private String saasEsignCode;
    /**
     * 印章名字
     */
    private String name;
    /**
     * 身份标识码
     */
    private String code;
    /**
     * e签宝账户标识
     */
    private String accountId;
    /**
     * e签宝生成印章URL地址
     */
    private String sealUrl;

    public Long getSaasEsignAccountId() {
        return saasEsignAccountId;
    }

    public void setSaasEsignAccountId(Long saasEsignAccountId) {
        this.saasEsignAccountId = saasEsignAccountId;
    }


    public String getSaasEsignCode() {
        return this.saasEsignCode;
    }

    public void setSaasEsignCode(String saasEsignCode) {
        this.saasEsignCode = saasEsignCode;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAccountId() {
        return this.accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getSealUrl() {
        return this.sealUrl;
    }

    public void setSealUrl(String sealUrl) {
        this.sealUrl = sealUrl;
    }

    public static SaasEsignAccountVo convertEntityToVO(SaasEsignAccount saasEsignAccount) {
        if (saasEsignAccount == null) {
            return null;
        }
        SaasEsignAccountVo saasEsignAccountVo = new SaasEsignAccountVo();
        BeanUtils.copyProperties(saasEsignAccount, saasEsignAccountVo);
        saasEsignAccountVo.setSaasEsignAccountId(saasEsignAccount.getId());
        return saasEsignAccountVo;
    }

    public static SaasEsignAccount convertVOToEntity(SaasEsignAccountVo saasEsignAccountVo) {
        if (saasEsignAccountVo == null) {
            return null;
        }
        SaasEsignAccount saasEsignAccount = new SaasEsignAccount();
        BeanUtils.copyProperties(saasEsignAccountVo, saasEsignAccount);
        saasEsignAccount.setId(saasEsignAccountVo.getSaasEsignAccountId());
        return saasEsignAccount;
    }

}
