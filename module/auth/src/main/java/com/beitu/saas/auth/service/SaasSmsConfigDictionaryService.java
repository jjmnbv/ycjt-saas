package com.beitu.saas.auth.service;
import com.beitu.saas.auth.entity.SaasSmsConfigDictionary;
import com.fqgj.common.base.BaseService;
import com.fqgj.common.entity.BaseEntity;

import java.util.List;

/**
* User: xiaochong
* Date: 2018-03-26
* Time: 11:27:29.255
*/
public interface SaasSmsConfigDictionaryService<T extends BaseEntity> extends BaseService<T> {

    List<SaasSmsConfigDictionary> getAllSmsConfig();
}