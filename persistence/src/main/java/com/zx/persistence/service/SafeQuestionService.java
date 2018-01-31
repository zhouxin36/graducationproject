package com.zx.persistence.service;

import com.zx.api.bean.EntityAndExample;
import com.zx.persistence.bean.SafeQuestion;
import com.zx.persistence.bean.SafeQuestionExample;
import com.zx.persistence.dao.SafeQuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/SafeQuestionService")
public class SafeQuestionService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private SafeQuestionMapper safeQuestionMapper;

    @PostMapping("/countByExample")
    public long countByExample(@RequestBody SafeQuestionExample example) {
        return safeQuestionMapper.countByExample(example);
    }

    @PostMapping("/deleteByExample")
    public int deleteByExample(@RequestBody SafeQuestionExample example) {
        return safeQuestionMapper.deleteByExample(example);
    }

    @PostMapping("/deleteByPrimaryKey")
    public int deleteByPrimaryKey(String id) {
        return safeQuestionMapper.deleteByPrimaryKey(id);
    }

    @PostMapping("/insert")
    public int insert(@RequestBody SafeQuestion record) {
        return safeQuestionMapper.insert(record);
    }

    @PostMapping("/insertSelective")
    public int insertSelective(@RequestBody SafeQuestion record) {
        return safeQuestionMapper.insertSelective(record);
    }

    @PostMapping("/selectByExample")
    public List<SafeQuestion> selectByExample(@RequestBody SafeQuestionExample example) {
        return safeQuestionMapper.selectByExample(example);
    }

    @PostMapping("/selectByPrimaryKey")
    public SafeQuestion selectByPrimaryKey(String id) {
        return safeQuestionMapper.selectByPrimaryKey(id);
    }

    @PostMapping("/updateByExampleSelective")
    public int updateByExampleSelective(@RequestBody EntityAndExample<SafeQuestion, SafeQuestionExample> exampleEntityAndExample) {
        return safeQuestionMapper.updateByExampleSelective(exampleEntityAndExample.getEntity(), exampleEntityAndExample.getExample());
    }

    @PostMapping("/updateByExample")
    public int updateByExample(@RequestBody EntityAndExample<SafeQuestion, SafeQuestionExample> exampleEntityAndExample) {
        return safeQuestionMapper.updateByExample(exampleEntityAndExample.getEntity(), exampleEntityAndExample.getExample());
    }

    @PostMapping("/updateByPrimaryKeySelective")
    public int updateByPrimaryKeySelective(@RequestBody SafeQuestion record) {
        return safeQuestionMapper.updateByPrimaryKeySelective(record);
    }

    @PostMapping("/updateByPrimaryKey")
    public int updateByPrimaryKey(@RequestBody SafeQuestion record) {
        return safeQuestionMapper.updateByPrimaryKey(record);
    }
}
