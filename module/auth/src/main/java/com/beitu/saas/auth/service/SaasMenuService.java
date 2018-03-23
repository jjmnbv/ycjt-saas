package com.beitu.saas.auth.service;
import com.beitu.saas.auth.entity.SaasMenu;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

import java.util.List;

/**
* User: xiaochong
* Date: 2018-03-22
* Time: 15:36:13.648
*/
public interface SaasMenuService<T extends BaseEntity> extends BaseService<T> {

    List<SaasMenu> getAllMenu();

    List<SaasMenu> getListByIds(List<Integer> ids);
}