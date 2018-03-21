package com.fqgj.youjie.auth.mapper;

import com.fqgj.common.mapper.BaseMapper;

import com.fqgj.youjie.auth.entity.AuthRoleMenuEntity;
import com.fqgj.youjie.auth.entity.AuthRoleMenuEntityConditions;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthRoleMenuEntityMapper extends BaseMapper<AuthRoleMenuEntity,Long,AuthRoleMenuEntityConditions>{
    int countByExample(AuthRoleMenuEntityConditions example);

    int deleteByExample(AuthRoleMenuEntityConditions example);

    int deleteByPrimaryKey(Long id);

    int insert(AuthRoleMenuEntity record);

    int insertSelective(AuthRoleMenuEntity record);

    List<AuthRoleMenuEntity> selectByExample(AuthRoleMenuEntityConditions example);

    AuthRoleMenuEntity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AuthRoleMenuEntity record, @Param("example") AuthRoleMenuEntityConditions example);

    int updateByExample(@Param("record") AuthRoleMenuEntity record, @Param("example") AuthRoleMenuEntityConditions example);

    int updateByPrimaryKeySelective(AuthRoleMenuEntity record);

    int updateByPrimaryKey(AuthRoleMenuEntity record);
}