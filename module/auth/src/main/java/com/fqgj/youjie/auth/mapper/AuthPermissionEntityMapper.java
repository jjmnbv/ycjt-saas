package com.fqgj.youjie.auth.mapper;

import com.fqgj.common.mapper.BaseMapper;

import com.fqgj.youjie.auth.entity.AuthPermissionEntity;
import com.fqgj.youjie.auth.entity.AuthPermissionEntityConditions;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthPermissionEntityMapper  extends BaseMapper<AuthPermissionEntity,Long,AuthPermissionEntityConditions>{
    int countByExample(AuthPermissionEntityConditions example);

    int deleteByExample(AuthPermissionEntityConditions example);

    int deleteByPrimaryKey(Long id);

    int insert(AuthPermissionEntity record);

    int insertSelective(AuthPermissionEntity record);

    List<AuthPermissionEntity> selectByExample(AuthPermissionEntityConditions example);

    AuthPermissionEntity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AuthPermissionEntity record, @Param("example") AuthPermissionEntityConditions example);

    int updateByExample(@Param("record") AuthPermissionEntity record, @Param("example") AuthPermissionEntityConditions example);

    int updateByPrimaryKeySelective(AuthPermissionEntity record);

    int updateByPrimaryKey(AuthPermissionEntity record);
}