package com.zx.persistence.service;

import com.zx.api.bean.EntityAndExample;
import com.zx.persistence.bean.Feedback;
import com.zx.persistence.bean.FeedbackExample;
import com.zx.persistence.dao.FeedbackMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/FeedbackService")
public class FeedbackService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private FeedbackMapper feedbackMapper;

    @PostMapping("/countByExample")
    public long countByExample(@RequestBody FeedbackExample example) {
        return feedbackMapper.countByExample(example);
    }

    @PostMapping("/deleteByExample")
    public int deleteByExample(@RequestBody FeedbackExample example) {
        return feedbackMapper.deleteByExample(example);
    }

    @PostMapping("/deleteByPrimaryKey")
    public int deleteByPrimaryKey(String id) {
        return feedbackMapper.deleteByPrimaryKey(id);
    }

    @PostMapping("/insert")
    public int insert(@RequestBody Feedback record) {
        return feedbackMapper.insert(record);
    }

    @PostMapping("/insertSelective")
    public int insertSelective(@RequestBody Feedback record) {
        return feedbackMapper.insertSelective(record);
    }

    @PostMapping("/selectByExample")
    public List<Feedback> selectByExample(@RequestBody FeedbackExample example) {
        return feedbackMapper.selectByExample(example);
    }

    @PostMapping("/selectByPrimaryKey")
    public Feedback selectByPrimaryKey(String id) {
        return feedbackMapper.selectByPrimaryKey(id);
    }

    @PostMapping("/updateByExampleSelective")
    public int updateByExampleSelective(@RequestBody EntityAndExample<Feedback, FeedbackExample> exampleEntityAndExample) {
        return feedbackMapper.updateByExampleSelective(exampleEntityAndExample.getEntity(), exampleEntityAndExample.getExample());
    }

    @PostMapping("/updateByExample")
    public int updateByExample(@RequestBody EntityAndExample<Feedback, FeedbackExample> exampleEntityAndExample) {
        return feedbackMapper.updateByExample(exampleEntityAndExample.getEntity(), exampleEntityAndExample.getExample());
    }

    @PostMapping("/updateByPrimaryKeySelective")
    public int updateByPrimaryKeySelective(@RequestBody Feedback record) {
        return feedbackMapper.updateByPrimaryKeySelective(record);
    }

    @PostMapping("/updateByPrimaryKey")
    public int updateByPrimaryKey(@RequestBody Feedback record) {
        return feedbackMapper.updateByPrimaryKey(record);
    }
}
