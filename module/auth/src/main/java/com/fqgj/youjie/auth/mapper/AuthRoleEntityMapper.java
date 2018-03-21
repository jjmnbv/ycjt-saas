package com.fqgj.youjie.auth.mapper;

import com.fqgj.common.mapper.BaseMapper;

import com.fqgj.youjie.auth.entity.AuthRoleEntity;
import com.fqgj.youjie.auth.entity.AuthRoleEntityConditions;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthRoleEntityMapper  extends BaseMapper<AuthRoleEntity,Long,AuthRoleEntityConditions>{
    int countByExample(AuthRoleEntityConditions example);

    int deleteByExample(AuthRoleEntityConditions example);

    int deleteByPrimaryKey(Long id);

    int insert(AuthRoleEntity record);

    int insertSelective(AuthRoleEntity record);

    List<AuthRoleEntity> selectByExample(AuthRoleEntityConditions example);

    AuthRoleEntity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AuthRoleEntity record, @Param("example") AuthRoleEntityConditions example);

    int updateByExample(@Param("record") AuthRoleEntity record, @Param("example") AuthRoleEntityConditions example);

    int updateByPrimaryKeySelective(AuthRoleEntity record);

    int updateByPrimaryKey(AuthRoleEntity record);
}