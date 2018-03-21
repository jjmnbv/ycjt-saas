package com.fqgj.youjie.auth.dao;

import com.fqgj.common.api.Page;
import com.fqgj.common.dao.BaseDAO;
import com.fqgj.youjie.auth.entity.AuthMenuEntity;
import com.fqgj.youjie.auth.entity.AuthMenuEntityConditions;


import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 2017/3/11
 * Time: 上午10:07
 */
public interface AuthMenuDAO extends BaseDAO<AuthMenuEntity, Long, AuthMenuEntityConditions> {

    /**
     * @param parentId
     * @param page
     * @return
     */
    List<AuthMenuEntity> getListByParentId(Long parentId, Page page);


    /**
     *
     * @param menuIds
     * @return
     */
    List<AuthMenuEntity> getListByIds(List<Long> menuIds);

    AuthMenuEntity getMenuByPrimaryKey(Long id);
}
