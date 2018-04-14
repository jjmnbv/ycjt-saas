package com.beitu.saas.auth.dao.impl;

import com.beitu.saas.auth.dao.SaasOperationButtonDao;
import com.beitu.saas.auth.entity.SaasMenu;
import com.beitu.saas.auth.entity.SaasOperationButton;
import com.fqgj.common.base.AbstractBaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* User: xiaochong
* Date: 2018-03-22
* Time: 15:36:13.673
*/

@Repository
public class SaasOperationButtonDaoImpl extends AbstractBaseMapper<SaasOperationButton> implements SaasOperationButtonDao {
    @Override
    public List<SaasOperationButton> selectListByIds(List<Integer> ids){
        return this.getSqlSession().selectList(this.getStatement("selectListByIds"),ids);
    }

}