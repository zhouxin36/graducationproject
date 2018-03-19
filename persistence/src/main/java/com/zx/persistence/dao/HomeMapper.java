package com.zx.persistence.dao;

import com.zx.persistence.bean.Home;
import com.zx.persistence.bean.HomeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HomeMapper {
    long countByExample(HomeExample example);

    int deleteByExample(HomeExample example);

    int deleteByPrimaryKey(String id);

    int insert(Home record);

    int insertSelective(Home record);

    List<Home> selectByExample(HomeExample example);

    Home selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Home record, @Param("example") HomeExample example);

    int updateByExample(@Param("record") Home record, @Param("example") HomeExample example);

    int updateByPrimaryKeySelective(Home record);

    int updateByPrimaryKey(Home record);
}