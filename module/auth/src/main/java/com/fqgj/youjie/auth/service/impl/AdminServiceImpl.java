package com.fqgj.youjie.auth.service.impl;

import com.fqgj.common.api.Page;
import com.fqgj.common.api.enums.BusinessErrorCodeEnum;

import com.fqgj.common.domain.Account;
import com.fqgj.common.domain.Mobile;
import com.fqgj.common.utils.CollectionUtils;

import com.fqgj.exception.common.ApplicationException;
import com.fqgj.youjie.auth.dao.AdminDAO;
import com.fqgj.youjie.auth.dao.AdminTokenDAO;
import com.fqgj.youjie.auth.dao.AuthAdminRoleDAO;
import com.fqgj.youjie.auth.domain.Admin;
import com.fqgj.youjie.auth.entity.AdminEntity;
import com.fqgj.youjie.auth.entity.AdminTokenEntity;
import com.fqgj.youjie.auth.entity.AuthAdminRoleEntity;
import com.fqgj.youjie.auth.service.AdminService;
import com.fqgj.youjie.auth.service.domain.AdminLoginService;
import com.fqgj.youjie.auth.service.domain.AdminRegistrationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 2017/3/11
 * Time: 上午11:35
 * <p>
 * 提供登陆、注册服务
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDAO adminDAO;

    @Autowired
    private AdminTokenDAO adminTokenDAO;

    @Autowired
    private AuthAdminRoleDAO authAdminRoleDAO;

    @Autowired
    private AdminRegistrationService adminRegistrationService;

    @Autowired
    private AdminLoginService adminLoginService;

    @Override
    public Boolean registration(Admin admin) {
        return adminRegistrationService.registration(admin);
    }

    @Override
    public String login(Admin admin) {
        return adminLoginService.login(admin);
    }

    @Override
    public Boolean add(Admin admin, List<Long> roleIds) {

        if (adminDAO.getByAccount(admin.getAccount().getAccount()) != null) {
            throw new ApplicationException("账号已存在");
        }

        if (!adminRegistrationService.registration(admin)) {
            throw new ApplicationException("添加用户失败");
        }

        if (CollectionUtils.isNotEmpty(roleIds)) {
            for (Long roleId : roleIds) {
                AuthAdminRoleEntity authAdminRoleEntity = new AuthAdminRoleEntity();
                authAdminRoleEntity.setAdminId(admin.getId());
                authAdminRoleEntity.setRoleId(roleId);

                authAdminRoleDAO.save(authAdminRoleEntity);
            }
        }

        return true;
    }

    @Override
    public List<Admin> searchAdmin(String account, String name, Page page) {
        List<AdminEntity> adminEntityList = adminDAO.getListByAccountAndName(account, name, page);
        if (CollectionUtils.isEmpty(adminEntityList)) {
            return null;
        }

        List<Admin> adminList = new ArrayList<>();
        for (AdminEntity adminEntity : adminEntityList) {
            adminList.add(new Admin()
                    .setId(adminEntity.getId())
                    .setAccount(new Account(adminEntity.getAccount()))
                    .setName(adminEntity.getName())
                    .setMobile(new Mobile(adminEntity.getMobile()))
                    .setPhone(adminEntity.getPhone())
            );
        }
        return adminList;
    }

    @Override
    public Boolean update(Admin admin) {
        AdminEntity adminEntity = adminDAO.selectById(admin.getId());
        if (adminEntity == null) {
            throw new ApplicationException("用户不存在");
        }

        if (StringUtils.isNotEmpty(admin.getName())) {
            adminEntity.setName(admin.getName());
        }

        if (admin.getAccount() != null) {
            adminEntity.setAccount(admin.getAccount().getAccount());
        }

        if (admin.getPassword().getPassword() != null) {
            adminEntity.setPassword(admin.getPassword().getPassword());
        }

        if (admin.getMobile() != null) {
            adminEntity.setMobile(admin.getMobile().getMobile());
        }

        if (StringUtils.isNotEmpty(admin.getPhone())) {
            adminEntity.setPhone(admin.getPhone());
        }

        return adminDAO.update(adminEntity) > 0;
    }

    @Override
    public Admin getByAccessToken(String accessToken) {
        AdminTokenEntity adminTokenEntity = adminTokenDAO.getByToken(accessToken);
        if (adminTokenEntity == null) {
            return null;
        }

        AdminEntity adminEntity = adminDAO.selectById(adminTokenEntity.getAdminId());
        if (adminEntity == null) {
            return null;
        }

        return new Admin(adminEntity.getId(), adminEntity.getName());
    }

    /**
     * 获取管理员信息
     *
     * @return
     */
    @Override
    public List<Admin> getAdminListByIdList(List<Long> adminIdList) {
        List<AdminEntity> adminEntityList = adminDAO.getAllAuditor(adminIdList);
        if (CollectionUtils.isEmpty(adminEntityList)) {
            throw new ApplicationException(BusinessErrorCodeEnum.AUDITOR_NOT_NULL);
        }
        List<Admin> adminList = new ArrayList<>();
        for (AdminEntity adminEntity : adminEntityList) {
            adminList.add(new Admin()
                    .setId(adminEntity.getId())
                    .setAccount(new Account(adminEntity.getAccount()))
                    .setName(adminEntity.getName())
                    .setMobile(new Mobile(adminEntity.getMobile()))
                    .setPhone(adminEntity.getPhone())
            );
        }
        return adminList;
    }

    @Override
    public boolean deleteById(Long id){
        if(adminDAO.deleteById(id) > 0){
            return adminTokenDAO.deleteById(id) > 0;
        }else{
            return false;
        }
    }
}
