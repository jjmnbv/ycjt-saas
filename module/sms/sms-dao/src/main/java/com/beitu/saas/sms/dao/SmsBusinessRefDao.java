package com.beitu.saas.sms.dao;
import com.beitu.saas.sms.entity.SmsBusinessRef;
import com.beitu.saas.sms.model.BusinessRefInfo;
import com.fqgj.common.base.BaseMapper;

import java.util.List;

/**
* User: jungle
* Date: 2018-02-27
* Time: 17:09:17.439
*/

public interface SmsBusinessRefDao  extends BaseMapper<SmsBusinessRef> {

    /**
     * 获取所有节点信息
     */
    List<BusinessRefInfo> getAll();

}