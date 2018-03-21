package com.fqgj.youjie.auth.service.domain;

import com.fqgj.common.utils.MD5;
import com.fqgj.common.utils.TimeUtils;

import com.fqgj.youjie.auth.dao.AdminDAO;
import com.fqgj.youjie.auth.dao.AdminTokenDAO;
import com.fqgj.youjie.auth.domain.Admin;
import com.fqgj.youjie.auth.entity.AdminEntity;
import com.fqgj.youjie.auth.entity.AdminTokenEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 2017/3/11
 * Time: 下午3:59
 */
@Service
public class AdminRegistrationService {

    @Autowired
    private AdminDAO adminDAO;

    @Autowired
    private AdminTokenDAO adminTokenDAO;

    public Boolean registration(Admin admin) {
        Long adminId = addAdmin(admin);
        if (adminId == null) {
            return false;
        }
        admin.setId(adminId);
        return addTokenInfo(adminId);
    }

    private Long addAdmin(Admin admin) {
        AdminEntity adminEntity = new AdminEntity();
        adminEntity.setAccount(admin.getAccount().getAccount());
        adminEntity.setName(admin.getName());
        adminEntity.setPassword(admin.getPassword().getPassword());
        adminEntity.setMobile(admin.getMobile().getMobile());
        adminEntity.setPhone(admin.getPhone());
        if (admin.getAdminTypeEnum() != null) {
            adminEntity.setType(admin.getAdminTypeEnum().getType());
        }
        adminEntity.setDeleted(false);

        adminDAO.save(adminEntity);
        return adminEntity.getId();
    }

    private Boolean addTokenInfo(Long adminId) {
        AdminTokenEntity adminTokenEntity = new AdminTokenEntity();
        adminTokenEntity.setAdminId(adminId);
        adminTokenEntity.setDeleted(false);
        adminTokenEntity.setAccessToken();
        adminTokenEntity.setExpireDate(TimeUtils.appointed(2));

        return adminTokenDAO.save(adminTokenEntity) > 0;
    }

}
