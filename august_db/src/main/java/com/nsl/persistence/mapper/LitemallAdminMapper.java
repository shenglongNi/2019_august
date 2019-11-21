package com.nsl.persistence.mapper;

import com.nsl.persistence.model.LitemallAdmin;
import com.nsl.persistence.model.LitemallAdminExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LitemallAdminMapper {
    long countByExample(LitemallAdminExample example);

    int deleteByExample(LitemallAdminExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LitemallAdmin record);

    int insertSelective(LitemallAdmin record);

    List<LitemallAdmin> selectByExample(LitemallAdminExample example);

    LitemallAdmin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LitemallAdmin record, @Param("example") LitemallAdminExample example);

    int updateByExample(@Param("record") LitemallAdmin record, @Param("example") LitemallAdminExample example);

    int updateByPrimaryKeySelective(LitemallAdmin record);

    int updateByPrimaryKey(LitemallAdmin record);
}