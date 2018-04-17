package com.beitu.saas.user.domain;

import com.beitu.saas.user.entity.SaasEsignUserAuthorization;
import com.fqgj.common.api.ResponseData;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * User: jungle
 * Date: 2018-04-17
 * Time: 11:27:43.193
 */
public class SaasEsignUserAuthorizationVo implements ResponseData, Serializable {

    private Long saasEsignUserAuthorizationId;

    /**
     * 用户码
     */
    private String userCode;
    /**
     * SAAS账户信息CODE
     */
    private String saasEsignCode;
    /**
     * 签章授权协议URL地址
     */
    private String authorizationUrl;

    public Long getSaasEsignUserAuthorizationId() {
        return saasEsignUserAuthorizationId;
    }

    public void setSaasEsignUserAuthorizationId(Long saasEsignUserAuthorizationId) {
        this.saasEsignUserAuthorizationId = saasEsignUserAuthorizationId;
    }


    public String getUserCode() {
        return this.userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getSaasEsignCode() {
        return this.saasEsignCode;
    }

    public void setSaasEsignCode(String saasEsignCode) {
        this.saasEsignCode = saasEsignCode;
    }

    public String getAuthorizationUrl() {
        return this.authorizationUrl;
    }

    public void setAuthorizationUrl(String authorizationUrl) {
        this.authorizationUrl = authorizationUrl;
    }

    public static SaasEsignUserAuthorizationVo convertEntityToVO(SaasEsignUserAuthorization saasEsignUserAuthorization) {
        if (saasEsignUserAuthorization == null) {
            return null;
        }
        SaasEsignUserAuthorizationVo saasEsignUserAuthorizationVo = new SaasEsignUserAuthorizationVo();
        BeanUtils.copyProperties(saasEsignUserAuthorization, saasEsignUserAuthorizationVo);
        saasEsignUserAuthorizationVo.setSaasEsignUserAuthorizationId(saasEsignUserAuthorization.getId());
        return saasEsignUserAuthorizationVo;
    }

    public static SaasEsignUserAuthorization convertVOToEntity(SaasEsignUserAuthorizationVo saasEsignUserAuthorizationVo) {
        if (saasEsignUserAuthorizationVo == null) {
            return null;
        }
        SaasEsignUserAuthorization saasEsignUserAuthorization = new SaasEsignUserAuthorization();
        BeanUtils.copyProperties(saasEsignUserAuthorizationVo, saasEsignUserAuthorization);
        saasEsignUserAuthorization.setId(saasEsignUserAuthorizationVo.getSaasEsignUserAuthorizationId());
        return saasEsignUserAuthorization;
    }

}