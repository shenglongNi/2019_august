package com.nsl.persistence.mapper;

import com.nsl.persistence.model.LitemallPermission;
import com.nsl.persistence.model.LitemallPermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LitemallPermissionMapper {
    long countByExample(LitemallPermissionExample example);

    int deleteByExample(LitemallPermissionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LitemallPermission record);

    int insertSelective(LitemallPermission record);

    List<LitemallPermission> selectByExample(LitemallPermissionExample example);

    LitemallPermission selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LitemallPermission record, @Param("example") LitemallPermissionExample example);

    int updateByExample(@Param("record") LitemallPermission record, @Param("example") LitemallPermissionExample example);

    int updateByPrimaryKeySelective(LitemallPermission record);

    int updateByPrimaryKey(LitemallPermission record);
}