package com.springcloud.stage.service;

import com.zx.api.bean.EntityAndExample;
import com.zx.api.bean.Feedback;
import com.zx.api.bean.FeedbackExample;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/graducation/FeedbackService")
@FeignClient(name = "persistence")
public interface FeedbackService {

    @PostMapping("/countByExample")
    long countByExample(FeedbackExample example);

    @PostMapping("/deleteByExample")
    int deleteByExample(FeedbackExample example);

    @PostMapping("/deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("id") String id);

    @PostMapping(value = "/insert")
    int insert(Feedback record);

    @PostMapping(value = "/insertSelective")
    int insertSelective(Feedback record);

    @PostMapping(value = "/selectByExample")
    List<Feedback> selectByExample(FeedbackExample example);

    @PostMapping("/selectByPrimaryKey")
    Feedback selectByPrimaryKey(@RequestParam("id") String id);

    @PostMapping("/updateByExampleSelective")
    int updateByExampleSelective(EntityAndExample<Feedback, FeedbackExample> exampleEntityAndExample);

    @PostMapping("/updateByExample")
    int updateByExample(EntityAndExample<Feedback, FeedbackExample> exampleEntityAndExample);

    @PostMapping("/updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Feedback record);

    @PostMapping("/updateByPrimaryKey")
    int updateByPrimaryKey(Feedback record);
}

