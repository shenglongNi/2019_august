package com.nsl.persistence.mapper;

import com.nsl.persistence.model.LitemallRole;
import com.nsl.persistence.model.LitemallRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LitemallRoleMapper {
    long countByExample(LitemallRoleExample example);

    int deleteByExample(LitemallRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LitemallRole record);

    int insertSelective(LitemallRole record);

    List<LitemallRole> selectByExample(LitemallRoleExample example);

    LitemallRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LitemallRole record, @Param("example") LitemallRoleExample example);

    int updateByExample(@Param("record") LitemallRole record, @Param("example") LitemallRoleExample example);

    int updateByPrimaryKeySelective(LitemallRole record);

    int updateByPrimaryKey(LitemallRole record);
}