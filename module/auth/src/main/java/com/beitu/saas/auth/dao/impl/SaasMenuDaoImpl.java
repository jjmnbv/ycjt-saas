package com.beitu.saas.auth.dao.impl;

import com.beitu.saas.auth.dao.SaasMenuDao;
import com.beitu.saas.auth.entity.SaasMenu;
import com.fqgj.common.base.AbstractBaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* User: xiaochong
* Date: 2018-03-22
* Time: 15:36:13.647
*/

@Repository
public class SaasMenuDaoImpl extends AbstractBaseMapper<SaasMenu> implements SaasMenuDao {

    @Override
    public List<SaasMenu> selectListByIds(List<Integer> ids){
       return this.getSqlSession().selectList(this.getStatement("selectListByIds"),ids);
    }

}