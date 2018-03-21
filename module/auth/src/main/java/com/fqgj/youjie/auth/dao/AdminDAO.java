package com.fqgj.youjie.auth.dao;

import com.fqgj.common.api.Page;
import com.fqgj.common.dao.BaseDAO;
import com.fqgj.youjie.auth.entity.AdminEntity;
import com.fqgj.youjie.auth.entity.AdminEntityConditions;


import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 2017/3/10
 * Time: 下午5:24
 */
public interface AdminDAO extends BaseDAO<AdminEntity, Long, AdminEntityConditions> {

    /**
     *
     * @param account
     * @return
     */
    AdminEntity getByAccount(String account);

    /**
     *
     * @param account
     * @param name
     * @param page
     * @return
     */
    List<AdminEntity> getListByAccountAndName(String account, String name, Page page);



    /**
     * 获取当前系统所有的信审人员
     *
     * @return
     */
    List<AdminEntity> getAllAuditor(List<Long> auditorIdList);
}
