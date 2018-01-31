package com.zx.persistence.dao;

import com.zx.persistence.bean.Footmark;
import com.zx.persistence.bean.FootmarkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FootmarkMapper {
    long countByExample(FootmarkExample example);

    int deleteByExample(FootmarkExample example);

    int deleteByPrimaryKey(String id);

    int insert(Footmark record);

    int insertSelective(Footmark record);

    List<Footmark> selectByExample(FootmarkExample example);

    Footmark selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Footmark record, @Param("example") FootmarkExample example);

    int updateByExample(@Param("record") Footmark record, @Param("example") FootmarkExample example);

    int updateByPrimaryKeySelective(Footmark record);

    int updateByPrimaryKey(Footmark record);
}