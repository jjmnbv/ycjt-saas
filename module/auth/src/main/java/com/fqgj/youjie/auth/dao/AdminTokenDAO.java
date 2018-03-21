package com.fqgj.youjie.auth.dao;


import com.fqgj.common.dao.BaseDAO;
import com.fqgj.youjie.auth.entity.AdminTokenEntity;
import com.fqgj.youjie.auth.entity.AdminTokenEntityConditions;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 2017/3/10
 * Time: 下午5:27
 */
public interface AdminTokenDAO extends BaseDAO<AdminTokenEntity, Long, AdminTokenEntityConditions> {

    /**
     * @param adminId
     * @return
     */
    AdminTokenEntity getByAdminId(Long adminId);


    /**
     * @param token
     * @return
     */
    AdminTokenEntity getByToken(String token);

    String refreshToken(AdminTokenEntity adminTokenEntity);

    Boolean refreshTokenExpireDate(AdminTokenEntity adminTokenEntity);

}
