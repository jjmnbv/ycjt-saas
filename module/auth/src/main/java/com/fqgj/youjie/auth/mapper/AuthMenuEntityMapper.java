package com.fqgj.youjie.auth.mapper;


import com.fqgj.common.mapper.BaseMapper;
import com.fqgj.youjie.auth.entity.AuthMenuEntity;
import com.fqgj.youjie.auth.entity.AuthMenuEntityConditions;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthMenuEntityMapper extends BaseMapper<AuthMenuEntity,Long,AuthMenuEntityConditions> {
    int countByExample(AuthMenuEntityConditions example);

    int deleteByExample(AuthMenuEntityConditions example);

    int deleteByPrimaryKey(Long id);

    int insert(AuthMenuEntity record);

    int insertSelective(AuthMenuEntity record);

    List<AuthMenuEntity> selectByExample(AuthMenuEntityConditions example);

    AuthMenuEntity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AuthMenuEntity record, @Param("example") AuthMenuEntityConditions example);

    int updateByExample(@Param("record") AuthMenuEntity record, @Param("example") AuthMenuEntityConditions example);

    int updateByPrimaryKeySelective(AuthMenuEntity record);

    int updateByPrimaryKey(AuthMenuEntity record);
}