package com.fqgj.youjie.auth.mapper;

import com.fqgj.common.mapper.BaseMapper;

import com.fqgj.youjie.auth.entity.AuthRolePermissionEntity;
import com.fqgj.youjie.auth.entity.AuthRolePermissionEntityConditions;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthRolePermissionEntityMapper extends BaseMapper<AuthRolePermissionEntity, Long, AuthRolePermissionEntityConditions> {
    int countByExample(AuthRolePermissionEntityConditions example);

    int deleteByExample(AuthRolePermissionEntityConditions example);

    int deleteByPrimaryKey(Long id);

    int insert(AuthRolePermissionEntity record);

    int insertSelective(AuthRolePermissionEntity record);

    List<AuthRolePermissionEntity> selectByExample(AuthRolePermissionEntityConditions example);

    AuthRolePermissionEntity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AuthRolePermissionEntity record, @Param("example") AuthRolePermissionEntityConditions example);

    int updateByExample(@Param("record") AuthRolePermissionEntity record, @Param("example") AuthRolePermissionEntityConditions example);

    int updateByPrimaryKeySelective(AuthRolePermissionEntity record);

    int updateByPrimaryKey(AuthRolePermissionEntity record);
}