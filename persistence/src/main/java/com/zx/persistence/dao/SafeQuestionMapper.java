package com.zx.persistence.dao;

import com.zx.persistence.bean.SafeQuestion;
import com.zx.persistence.bean.SafeQuestionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SafeQuestionMapper {
    long countByExample(SafeQuestionExample example);

    int deleteByExample(SafeQuestionExample example);

    int deleteByPrimaryKey(String id);

    int insert(SafeQuestion record);

    int insertSelective(SafeQuestion record);

    List<SafeQuestion> selectByExample(SafeQuestionExample example);

    SafeQuestion selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SafeQuestion record, @Param("example") SafeQuestionExample example);

    int updateByExample(@Param("record") SafeQuestion record, @Param("example") SafeQuestionExample example);

    int updateByPrimaryKeySelective(SafeQuestion record);

    int updateByPrimaryKey(SafeQuestion record);
}