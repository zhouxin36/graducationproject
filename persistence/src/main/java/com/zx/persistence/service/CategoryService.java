package com.zx.persistence.service;

import com.zx.api.bean.EntityAndExample;
import com.zx.persistence.bean.Category;
import com.zx.persistence.bean.CategoryExample;
import com.zx.persistence.dao.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/CategoryService")
public class CategoryService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private CategoryMapper categoryMapper;

    @PostMapping("/countByExample")
    public long countByExample(@RequestBody CategoryExample example) {
        return categoryMapper.countByExample(example);
    }

    @PostMapping("/deleteByExample")
    public int deleteByExample(@RequestBody CategoryExample example) {
        return categoryMapper.deleteByExample(example);
    }

    @PostMapping("/deleteByPrimaryKey")
    public int deleteByPrimaryKey(String id) {
        return categoryMapper.deleteByPrimaryKey(id);
    }

    @PostMapping("/insert")
    public int insert(@RequestBody Category record) {
        return categoryMapper.insert(record);
    }

    @PostMapping("/insertSelective")
    public int insertSelective(@RequestBody Category record) {
        return categoryMapper.insertSelective(record);
    }

    @PostMapping("/selectByExample")
    public List<Category> selectByExample(@RequestBody CategoryExample example) {
        return categoryMapper.selectByExample(example);
    }

    @PostMapping("/selectByPrimaryKey")
    public Category selectByPrimaryKey(String id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    @PostMapping("/updateByExampleSelective")
    public int updateByExampleSelective(@RequestBody EntityAndExample<Category, CategoryExample> exampleEntityAndExample) {
        return categoryMapper.updateByExampleSelective(exampleEntityAndExample.getEntity(), exampleEntityAndExample.getExample());
    }

    @PostMapping("/updateByExample")
    public int updateByExample(@RequestBody EntityAndExample<Category, CategoryExample> exampleEntityAndExample) {
        return categoryMapper.updateByExample(exampleEntityAndExample.getEntity(), exampleEntityAndExample.getExample());
    }

    @PostMapping("/updateByPrimaryKeySelective")
    public int updateByPrimaryKeySelective(@RequestBody Category record) {
        return categoryMapper.updateByPrimaryKeySelective(record);
    }

    @PostMapping("/updateByPrimaryKey")
    public int updateByPrimaryKey(@RequestBody Category record) {
        return categoryMapper.updateByPrimaryKey(record);
    }
}
