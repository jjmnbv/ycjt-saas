package com.beitu.saas.credit.client;
import com.beitu.saas.credit.domain.SaasGxbEbVo;
import com.beitu.saas.credit.entity.SaasGxbEb;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

/**
* User: xiaochong
* Date: 2018-04-17
* Time: 17:27:58.944
*/
public interface SaasGxbEbService<T extends BaseEntity> extends BaseService<T> {

    void saveGXBEbTop(SaasGxbEb saasGxbEb);

    SaasGxbEbVo getGxbEbTopByBorrowerCode(String borrowerCode);

    Boolean canAuthEbByBorrowerCode(String borrowerCode);
}