package com.beitu.saas.auth.service;
import com.beitu.saas.auth.entity.SaasOperationButton;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

import java.util.List;
import java.util.Map;

/**
* User: xiaochong
* Date: 2018-03-22
* Time: 15:36:13.674
*/
public interface SaasOperationButtonService<T extends BaseEntity> extends BaseService<T> {

    List<SaasOperationButton> getAllButton();

    List<SaasOperationButton> getListByIds(List ids);


    Map<String,Object> getParentButtonForMap();
}