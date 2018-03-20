package com.zx.persistence.service;

import com.zx.api.bean.EntityAndExample;
import com.zx.persistence.bean.Home;
import com.zx.persistence.bean.HomeExample;
import com.zx.persistence.dao.HomeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/HomeService")
public class HomeService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private HomeMapper homeMapper;

    @PostMapping("/countByExample")
    public long countByExample(@RequestBody HomeExample example) {
        return homeMapper.countByExample(example);
    }

    @PostMapping("/deleteByExample")
    public int deleteByExample(@RequestBody HomeExample example) {
        return homeMapper.deleteByExample(example);
    }

    @PostMapping("/deleteByPrimaryKey")
    public int deleteByPrimaryKey(String id) {
        return homeMapper.deleteByPrimaryKey(id);
    }

    @PostMapping("/insert")
    public int insert(@RequestBody Home record) {
        return homeMapper.insert(record);
    }

    @PostMapping("/insertSelective")
    public int insertSelective(@RequestBody Home record) {
        return homeMapper.insertSelective(record);
    }

    @PostMapping("/selectByExample")
    public List<Home> selectByExample(@RequestBody HomeExample example) {
        return homeMapper.selectByExample(example);
    }

    @PostMapping("/selectByPrimaryKey")
    public Home selectByPrimaryKey(String id) {
        return homeMapper.selectByPrimaryKey(id);
    }

    @PostMapping("/updateByExampleSelective")
    public int updateByExampleSelective(@RequestBody EntityAndExample<Home, HomeExample> exampleEntityAndExample) {
        return homeMapper.updateByExampleSelective(exampleEntityAndExample.getEntity(), exampleEntityAndExample.getExample());
    }

    @PostMapping("/updateByExample")
    public int updateByExample(@RequestBody EntityAndExample<Home, HomeExample> exampleEntityAndExample) {
        return homeMapper.updateByExample(exampleEntityAndExample.getEntity(), exampleEntityAndExample.getExample());
    }

    @PostMapping("/updateByPrimaryKeySelective")
    public int updateByPrimaryKeySelective(@RequestBody Home record) {
        return homeMapper.updateByPrimaryKeySelective(record);
    }

    @PostMapping("/updateByPrimaryKey")
    public int updateByPrimaryKey(@RequestBody Home record) {
        return homeMapper.updateByPrimaryKey(record);
    }
}
