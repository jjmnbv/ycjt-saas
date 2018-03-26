package com.beitu.saas.auth.service.impl;

import com.beitu.saas.auth.dao.SaasSmsConfigDictionaryDao;
import com.beitu.saas.auth.entity.SaasSmsConfigDictionary;
import com.beitu.saas.auth.service.SaasSmsConfigDictionaryService;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
* User: xiaochong
* Date: 2018-03-26
* Time: 11:27:29.263
*/
@Module(value = "服务模块")
@NameSpace("com.beitu.saas.auth.dao.impl.SaasSmsConfigDictionaryDaoImpl")
@Service
public class SaasSmsConfigDictionaryServiceImpl extends AbstractBaseService implements SaasSmsConfigDictionaryService {


    @Autowired
    private SaasSmsConfigDictionaryDao saasSmsConfigDictionaryDao;

    @Override
    public List<SaasSmsConfigDictionary> getAllSmsConfig(){
       return this.selectByParams(new HashMap<String,Object>(2){{put("deleted",false);}});
    }
}


