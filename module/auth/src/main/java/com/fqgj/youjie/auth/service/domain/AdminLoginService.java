package com.fqgj.youjie.auth.service.domain;


import com.fqgj.common.utils.TimeUtils;
import com.fqgj.youjie.auth.dao.AdminDAO;
import com.fqgj.youjie.auth.dao.AdminTokenDAO;
import com.fqgj.youjie.auth.domain.Admin;
import com.fqgj.youjie.auth.entity.AdminEntity;
import com.fqgj.youjie.auth.entity.AdminTokenEntity;
import com.fqgj.youjie.auth.exceptions.AuthAdminException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 2017/3/11
 * Time: 下午5:06
 * 登陆服务
 */
@Service
public class AdminLoginService {

    @Autowired
    private AdminDAO adminDAO;

    @Autowired
    private AdminTokenDAO adminTokenDAO;

    /**
     * 登陆 成功则返回对应的用户标识token
     *
     * @param admin
     * @return
     */
    public String login(Admin admin) {
        AdminEntity adminEntity = adminDAO.getByAccount(admin.getAccount().getAccount());
        checkLoginInfo(admin, adminEntity);
        admin.setName(adminEntity.getName());
        admin.setId(adminEntity.getId());
        return getTokenInfo(adminEntity);
    }

    private void checkLoginInfo(Admin admin, AdminEntity adminEntity) {
        if (adminEntity == null) {
            throw new AuthAdminException("用户不存在");
        }

        if (!adminEntity.getPassword().equals(admin.getPassword().getPassword())) {
            throw new AuthAdminException("密码错误");
        }
    }

    private String getTokenInfo(AdminEntity adminEntity) {
        AdminTokenEntity adminTokenEntity = adminTokenDAO.getByAdminId(adminEntity.getId());
        if (adminTokenEntity == null) {
            throw new AuthAdminException("用户信息不完整");
        }
        return adminTokenDAO.refreshToken(adminTokenEntity);
    }

}
