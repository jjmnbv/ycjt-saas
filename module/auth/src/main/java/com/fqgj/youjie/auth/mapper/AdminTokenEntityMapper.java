package com.fqgj.youjie.auth.mapper;



import com.fqgj.common.mapper.BaseMapper;
import com.fqgj.youjie.auth.entity.AdminTokenEntity;
import com.fqgj.youjie.auth.entity.AdminTokenEntityConditions;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminTokenEntityMapper  extends BaseMapper<AdminTokenEntity,Long,AdminTokenEntityConditions> {
    int countByExample(AdminTokenEntityConditions example);

    int deleteByExample(AdminTokenEntityConditions example);

    int deleteByPrimaryKey(Long id);

    int insert(AdminTokenEntity record);

    int insertSelective(AdminTokenEntity record);

    List<AdminTokenEntity> selectByExample(AdminTokenEntityConditions example);

    AdminTokenEntity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AdminTokenEntity record, @Param("example") AdminTokenEntityConditions example);

    int updateByExample(@Param("record") AdminTokenEntity record, @Param("example") AdminTokenEntityConditions example);

    int updateByPrimaryKeySelective(AdminTokenEntity record);

    int updateByPrimaryKey(AdminTokenEntity record);
}