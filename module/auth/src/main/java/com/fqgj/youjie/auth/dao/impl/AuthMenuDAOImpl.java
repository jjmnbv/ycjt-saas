package com.fqgj.youjie.auth.dao.impl;

import com.fqgj.common.api.Page;
import com.fqgj.common.dao.BaseDAOImpl;
import com.fqgj.common.utils.CollectionUtils;

import com.fqgj.youjie.auth.dao.AuthMenuDAO;
import com.fqgj.youjie.auth.entity.AuthMenuEntity;
import com.fqgj.youjie.auth.entity.AuthMenuEntityConditions;
import com.fqgj.youjie.auth.mapper.AuthMenuEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 2017/3/11
 * Time: 上午10:09
 */
@Repository
public class AuthMenuDAOImpl extends BaseDAOImpl<AuthMenuEntity, Long, AuthMenuEntityConditions> implements AuthMenuDAO {

    @Autowired
    private AuthMenuEntityMapper authMenuEntityMapper;

    @Autowired
    @Override
    public void setBaseMapper() {
        super.setBaseMapper(authMenuEntityMapper);
    }


    @Override
    public List<AuthMenuEntity> getListByParentId(Long parentId, Page page) {
        AuthMenuEntityConditions conditions = new AuthMenuEntityConditions();
        AuthMenuEntityConditions.Criteria criteria = conditions.createCriteria();
        criteria.andPIdEqualTo(parentId);

        if (page != null) {
            conditions.setPage(page);
            if (page.getCurrentPage() == 1) {
                page.setTotalCount(authMenuEntityMapper.countByExample(conditions));
            }
        }

        return authMenuEntityMapper.selectByExample(conditions);
    }

    @Override
    public List<AuthMenuEntity> getListByIds(List<Long> menuIds) {
        if (CollectionUtils.isEmpty(menuIds)) {
            return null;
        }

        AuthMenuEntityConditions conditions = new AuthMenuEntityConditions();
        AuthMenuEntityConditions.Criteria criteria = conditions.createCriteria();

        criteria.andIdIn(menuIds);
        return authMenuEntityMapper.selectByExample(conditions);
    }
    @Override
    public AuthMenuEntity getMenuByPrimaryKey(Long id){
        return authMenuEntityMapper.selectByPrimaryKey(id);
    }

}
