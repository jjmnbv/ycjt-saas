package com.fqgj.youjie.auth.service;

import com.fqgj.common.api.Page;
import com.fqgj.youjie.auth.domain.Admin;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 2017/3/11
 * Time: 上午11:28
 */
public interface AdminService {

    /**
     * 注册管理员
     *
     * @param admin
     * @return
     */
    Boolean registration(Admin admin);

    /**
     * @param admin
     * @return
     */
    String login(Admin admin);

    /**
     * @param admin
     * @return
     */
    Boolean add(Admin admin, List<Long> roleIds);

    /**
     * @param account
     * @param name
     * @param page
     * @return
     */
    List<Admin> searchAdmin(String account, String name, Page page);

    /**
     * @param admin
     * @return
     */
    Boolean update(Admin admin);

    /**
     * @param accessToken
     * @return
     */
    Admin getByAccessToken(String accessToken);


    /**
     * 获取管理员信息
     * @return
     */
    List<Admin> getAdminListByIdList(List<Long> adminIdList);

    /**
     * 删除 管理员信息
     * @param id 用户id
     * @return
     * @author linanjun
     */
    boolean deleteById(Long id);

}
