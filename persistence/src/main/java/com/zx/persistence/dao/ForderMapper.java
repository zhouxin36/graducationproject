package com.zx.persistence.dao;

import com.zx.persistence.bean.Forder;
import com.zx.persistence.bean.ForderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ForderMapper {
    long countByExample(ForderExample example);

    int deleteByExample(ForderExample example);

    int deleteByPrimaryKey(String id);

    int insert(Forder record);

    int insertSelective(Forder record);

    List<Forder> selectByExample(ForderExample example);

    Forder selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Forder record, @Param("example") ForderExample example);

    int updateByExample(@Param("record") Forder record, @Param("example") ForderExample example);

    int updateByPrimaryKeySelective(Forder record);

    int updateByPrimaryKey(Forder record);
}