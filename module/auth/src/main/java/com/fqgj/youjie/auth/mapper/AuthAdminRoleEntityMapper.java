package com.fqgj.youjie.auth.mapper;


import com.fqgj.common.mapper.BaseMapper;
import com.fqgj.youjie.auth.entity.AuthAdminRoleEntity;
import com.fqgj.youjie.auth.entity.AuthAdminRoleEntityConditions;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthAdminRoleEntityMapper extends BaseMapper<AuthAdminRoleEntity,Long,AuthAdminRoleEntityConditions> {
    int countByExample(AuthAdminRoleEntityConditions example);

    int deleteByExample(AuthAdminRoleEntityConditions example);

    int deleteByPrimaryKey(Long id);

    int insert(AuthAdminRoleEntity record);

    int insertSelective(AuthAdminRoleEntity record);

    List<AuthAdminRoleEntity> selectByExample(AuthAdminRoleEntityConditions example);

    AuthAdminRoleEntity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AuthAdminRoleEntity record, @Param("example") AuthAdminRoleEntityConditions example);

    int updateByExample(@Param("record") AuthAdminRoleEntity record, @Param("example") AuthAdminRoleEntityConditions example);

    int updateByPrimaryKeySelective(AuthAdminRoleEntity record);

    int updateByPrimaryKey(AuthAdminRoleEntity record);
}