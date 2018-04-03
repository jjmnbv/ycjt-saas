package com.beitu.saas.order.dao;
import com.fqgj.common.base.BaseMapper;
import com.beitu.saas.order.entity.SaasOrderApplication;

/**
* User: jungle
* Date: 2018-03-23
* Time: 15:18:54.758
*/

public interface SaasOrderApplicationDao  extends BaseMapper<SaasOrderApplication> {

    SaasOrderApplication selectByBorrowerCode(String borrowerCode);

}