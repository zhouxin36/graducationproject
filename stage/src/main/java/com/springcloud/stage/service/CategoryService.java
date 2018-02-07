package com.springcloud.stage.service;

import com.zx.api.bean.Category;
import com.zx.api.bean.CategoryExample;
import com.zx.api.bean.EntityAndExample;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/graducation/CategoryService")
@FeignClient(name = "persistence")
public interface CategoryService {

    @PostMapping("/countByExample")
    long countByExample(CategoryExample example);

    @PostMapping("/deleteByExample")
    int deleteByExample(CategoryExample example);

    @PostMapping("/deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("id") String id);

    @PostMapping(value = "/insert")
    int insert(Category record);

    @PostMapping(value = "/insertSelective")
    int insertSelective(Category record);

    @PostMapping(value = "/selectByExample")
    List<Category> selectByExample(CategoryExample example);

    @PostMapping("/selectByPrimaryKey")
    Category selectByPrimaryKey(@RequestParam("id") String id);

    @PostMapping("/updateByExampleSelective")
    int updateByExampleSelective(EntityAndExample<Category, CategoryExample> exampleEntityAndExample);

    @PostMapping("/updateByExample")
    int updateByExample(EntityAndExample<Category, CategoryExample> exampleEntityAndExample);

    @PostMapping("/updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Category record);

    @PostMapping("/updateByPrimaryKey")
    int updateByPrimaryKey(Category record);
}

