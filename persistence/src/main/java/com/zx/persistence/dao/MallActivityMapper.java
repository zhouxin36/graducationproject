package com.zx.persistence.dao;

import com.zx.persistence.bean.MallActivity;
import com.zx.persistence.bean.MallActivityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MallActivityMapper {
    long countByExample(MallActivityExample example);

    int deleteByExample(MallActivityExample example);

    int deleteByPrimaryKey(String id);

    int insert(MallActivity record);

    int insertSelective(MallActivity record);

    List<MallActivity> selectByExample(MallActivityExample example);

    MallActivity selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") MallActivity record, @Param("example") MallActivityExample example);

    int updateByExample(@Param("record") MallActivity record, @Param("example") MallActivityExample example);

    int updateByPrimaryKeySelective(MallActivity record);

    int updateByPrimaryKey(MallActivity record);
}