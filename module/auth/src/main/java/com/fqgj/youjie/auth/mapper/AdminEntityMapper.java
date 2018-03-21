package com.fqgj.youjie.auth.mapper;


import com.fqgj.common.mapper.BaseMapper;
import com.fqgj.youjie.auth.entity.AdminEntity;
import com.fqgj.youjie.auth.entity.AdminEntityConditions;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminEntityMapper extends BaseMapper<AdminEntity,Long,AdminEntityConditions> {
    int countByExample(AdminEntityConditions example);

    int deleteByExample(AdminEntityConditions example);

    int deleteByPrimaryKey(Long id);

    int insert(AdminEntity record);

    int insertSelective(AdminEntity record);

    List<AdminEntity> selectByExample(AdminEntityConditions example);

    AdminEntity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AdminEntity record, @Param("example") AdminEntityConditions example);

    int updateByExample(@Param("record") AdminEntity record, @Param("example") AdminEntityConditions example);

    int updateByPrimaryKeySelective(AdminEntity record);

    int updateByPrimaryKey(AdminEntity record);
}