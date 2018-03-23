package com.beitu.saas.auth.service.impl;

import com.beitu.saas.auth.dao.SaasMenuDao;
import com.beitu.saas.auth.entity.SaasMenu;
import com.beitu.saas.auth.service.SaasMenuService;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
* User: xiaochong
* Date: 2018-03-22
* Time: 15:36:13.648
*/
@Module(value = "服务模块")
@NameSpace("com.beitu.saas.dao.impl.SaasMenuDaoImpl")
@Service
public class SaasMenuServiceImpl extends AbstractBaseService implements SaasMenuService {


    @Autowired
    private SaasMenuDao saasMenuDao;

    @Override
    public List<SaasMenu> getAllMenu(){
        List list = saasMenuDao.selectByParams(new HashMap<String, Object>(2) {{
            put("deleted", false);
        }});
        return list;
    }
    @Override
    public List<SaasMenu> getListByIds(List ids){
        return saasMenuDao.selectListByIds(ids);
    }
}


