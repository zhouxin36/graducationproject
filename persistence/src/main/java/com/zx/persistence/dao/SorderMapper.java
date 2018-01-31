package com.zx.persistence.dao;

import com.zx.persistence.bean.Sorder;
import com.zx.persistence.bean.SorderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SorderMapper {
    long countByExample(SorderExample example);

    int deleteByExample(SorderExample example);

    int deleteByPrimaryKey(String id);

    int insert(Sorder record);

    int insertSelective(Sorder record);

    List<Sorder> selectByExample(SorderExample example);

    Sorder selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Sorder record, @Param("example") SorderExample example);

    int updateByExample(@Param("record") Sorder record, @Param("example") SorderExample example);

    int updateByPrimaryKeySelective(Sorder record);

    int updateByPrimaryKey(Sorder record);
}