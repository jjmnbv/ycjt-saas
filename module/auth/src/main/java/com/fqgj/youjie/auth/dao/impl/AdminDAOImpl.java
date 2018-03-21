package com.fqgj.youjie.auth.dao.impl;


import com.fqgj.common.api.Page;
import com.fqgj.common.dao.BaseDAOImpl;
import com.fqgj.common.utils.CollectionUtils;
import com.fqgj.youjie.auth.dao.AdminDAO;
import com.fqgj.youjie.auth.entity.AdminEntity;
import com.fqgj.youjie.auth.entity.AdminEntityConditions;
import com.fqgj.youjie.auth.mapper.AdminEntityMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 2017/3/10
 * Time: 下午5:25
 */
@Repository
public class AdminDAOImpl extends BaseDAOImpl<AdminEntity, Long, AdminEntityConditions> implements AdminDAO {

    @Autowired
    protected AdminEntityMapper adminEntityMapper;

    @Autowired
    @Override
    public void setBaseMapper() {
        super.setBaseMapper(adminEntityMapper);
    }


    @Override
    public AdminEntity getByAccount(String account) {
        AdminEntityConditions conditions = new AdminEntityConditions();
        AdminEntityConditions.Criteria criteria = conditions.createCriteria();
        criteria.andAccountEqualTo(account);

        List<AdminEntity> adminEntityList = adminEntityMapper.selectByExample(conditions);
        if (CollectionUtils.isNotEmpty(adminEntityList)) {
            return adminEntityList.get(0);
        }

        return null;
    }

    @Override
    public List<AdminEntity> getListByAccountAndName(String account, String name, Page page) {
        AdminEntityConditions conditions = new AdminEntityConditions();
        AdminEntityConditions.Criteria criteria = conditions.createCriteria();
        if (StringUtils.isNotEmpty(account)) {
            criteria.andAccountLike("%" + account + "%");
        }

        if (StringUtils.isNotEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }

        conditions.setPage(page);
        page.setTotalCount(countByExample(conditions));
        return adminEntityMapper.selectByExample(conditions);
    }

    /**
     * 获取当前系统所有的信审人员
     *
     * @return
     */
    @Override
    public List<AdminEntity> getAllAuditor(List<Long> auditorIdList) {
        AdminEntityConditions conditions = new AdminEntityConditions();
        AdminEntityConditions.Criteria criteria = conditions.createCriteria();
        if (CollectionUtils.isNotEmpty(auditorIdList)){
            criteria.andIdIn(auditorIdList);
        }
        criteria.andDeletedEqualTo(Boolean.FALSE);
        return adminEntityMapper.selectByExample(conditions);
    }
}
