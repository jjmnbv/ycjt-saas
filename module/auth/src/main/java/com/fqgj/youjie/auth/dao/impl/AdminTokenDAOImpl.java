package com.fqgj.youjie.auth.dao.impl;


import com.beitu.saas.common.consts.RedisKeyConsts;
import com.beitu.saas.common.consts.TimeConsts;
import com.fqgj.base.services.redis.RedisClient;
import com.fqgj.common.api.Page;
import com.fqgj.common.dao.BaseDAOImpl;
import com.fqgj.common.utils.CollectionUtils;

import com.fqgj.common.utils.TimeUtils;
import com.fqgj.youjie.auth.dao.AdminTokenDAO;
import com.fqgj.youjie.auth.entity.AdminTokenEntity;
import com.fqgj.youjie.auth.entity.AdminTokenEntityConditions;
import com.fqgj.youjie.auth.mapper.AdminTokenEntityMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (mr.vencnet@gmail.com)
 * Date: 2017/3/10
 * Time: 下午5:27
 */
@Repository
public class AdminTokenDAOImpl extends BaseDAOImpl<AdminTokenEntity, Long, AdminTokenEntityConditions> implements AdminTokenDAO {

    @Autowired
    private AdminTokenEntityMapper adminTokenEntityMapper;

    @Autowired
    private RedisClient redisClient;

    @Autowired
    @Override
    public void setBaseMapper() {
        super.setBaseMapper(adminTokenEntityMapper);
    }


    @Override
    public AdminTokenEntity getByAdminId(Long adminId) {
        AdminTokenEntityConditions conditions = new AdminTokenEntityConditions();
        AdminTokenEntityConditions.Criteria criteria = conditions.createCriteria();

        criteria.andAdminIdEqualTo(adminId);

        List<AdminTokenEntity> adminTokenEntityList = adminTokenEntityMapper.selectByExample(conditions);
        if (CollectionUtils.isNotEmpty(adminTokenEntityList)) {
            return adminTokenEntityList.get(0);
        }

        return null;
    }

    @Override
    public AdminTokenEntity getByToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }

        AdminTokenEntityConditions conditions = new AdminTokenEntityConditions();
        AdminTokenEntityConditions.Criteria criteria = conditions.createCriteria();
        criteria.andAccessTokenEqualTo(token);
        criteria.andExpireDateGreaterThanOrEqualTo(new Date());
        conditions.setPage(new Page(1));
        List<AdminTokenEntity> adminTokenEntityList = adminTokenEntityMapper.selectByExample(conditions);
        if (CollectionUtils.isNotEmpty(adminTokenEntityList)) {
            AdminTokenEntity adminTokenEntity = adminTokenEntityList.get(0);
            if (redisClient.get(RedisKeyConsts.ADMIN_TOKEN_REFRESH_LOCK, adminTokenEntity.getAccessToken()) == null) {
                refreshTokenExpireDate(adminTokenEntity);
            }
            return adminTokenEntity;
        }
        return null;
    }

    @Override
    public String refreshToken(AdminTokenEntity adminTokenEntity) {
        adminTokenEntity.setAccessToken();
        refreshTokenExpireDate(adminTokenEntity);
        return adminTokenEntity.getAccessToken();
    }

    @Override
    public Boolean refreshTokenExpireDate(AdminTokenEntity adminTokenEntity) {
        if (adminTokenEntity == null || adminTokenEntity.getId() == null) {
            return null;
        }
        adminTokenEntity.setExpireDate(new Date(System.currentTimeMillis() + TimeConsts.AN_HOUR * 1000));
        redisClient.set(RedisKeyConsts.ADMIN_TOKEN_REFRESH_LOCK, adminTokenEntity.getExpireDate(), TimeConsts.TEN_MINUTES, adminTokenEntity.getAccessToken());
        return adminTokenEntityMapper.updateByPrimaryKey(adminTokenEntity) > 0;
    }

}
