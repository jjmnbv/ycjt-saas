package com.beitu.saas.auth.service.impl;

import com.beitu.saas.auth.dao.SaasOperationButtonDao;
import com.beitu.saas.auth.entity.SaasMenu;
import com.beitu.saas.auth.entity.SaasOperationButton;
import com.beitu.saas.auth.service.SaasOperationButtonService;
import com.fqgj.common.base.AbstractBaseService;
import com.fqgj.common.base.NameSpace;
import com.fqgj.log.enhance.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* User: xiaochong
* Date: 2018-03-22
* Time: 15:36:13.674
*/
@Module(value = "服务模块")
@NameSpace("com.beitu.saas.dao.impl.SaasOperationButtonDaoImpl")
@Service
public class SaasOperationButtonServiceImpl extends AbstractBaseService implements SaasOperationButtonService {


    @Autowired
    private SaasOperationButtonDao saasOperationButtonDao;

    @Override
    public List<SaasOperationButton> getAllButton(){
        List list = saasOperationButtonDao.selectByParams(new HashMap<String, Object>(2) {{
            put("deleted", false);
        }});
        return list;
    }

    @Override
    public List<SaasOperationButton> getListByIds(List ids){
        return saasOperationButtonDao.selectListByIds(ids);
    }

    @Override
    public Map<String,Object> getParentButtonForMap(){
        Map map = new HashMap(8);
        List<SaasOperationButton> list = saasOperationButtonDao.selectByParams(new HashMap<String, Object>(2) {{
            put("isParent", true);
            put("deleted", false);
        }});
        list.parallelStream().forEach(saasOperationButton -> map.put(saasOperationButton.getId(),saasOperationButton.getName()));
        return map;
    }
}


