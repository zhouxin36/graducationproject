package com.zx.persistence.service;

import com.zx.api.bean.EntityAndExample;
import com.zx.persistence.bean.MallActivity;
import com.zx.persistence.bean.MallActivityExample;
import com.zx.persistence.dao.MallActivityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/MallActivityService")
public class MallActivityService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MallActivityMapper mallActivityMapper;

    @PostMapping("/countByExample")
    public long countByExample(@RequestBody MallActivityExample example) {
        return mallActivityMapper.countByExample(example);
    }

    @PostMapping("/deleteByExample")
    public int deleteByExample(@RequestBody MallActivityExample example) {
        return mallActivityMapper.deleteByExample(example);
    }

    @PostMapping("/deleteByPrimaryKey")
    public int deleteByPrimaryKey(String id) {
        return mallActivityMapper.deleteByPrimaryKey(id);
    }

    @PostMapping("/insert")
    public int insert(@RequestBody MallActivity record) {
        return mallActivityMapper.insert(record);
    }

    @PostMapping("/insertSelective")
    public int insertSelective(@RequestBody MallActivity record) {
        return mallActivityMapper.insertSelective(record);
    }

    @PostMapping("/selectByExample")
    public List<MallActivity> selectByExample(@RequestBody MallActivityExample example) {
        return mallActivityMapper.selectByExample(example);
    }

    @PostMapping("/selectByPrimaryKey")
    public MallActivity selectByPrimaryKey(String id) {
        return mallActivityMapper.selectByPrimaryKey(id);
    }

    @PostMapping("/updateByExampleSelective")
    public int updateByExampleSelective(@RequestBody EntityAndExample<MallActivity, MallActivityExample> exampleEntityAndExample) {
        return mallActivityMapper.updateByExampleSelective(exampleEntityAndExample.getEntity(), exampleEntityAndExample.getExample());
    }

    @PostMapping("/updateByExample")
    public int updateByExample(@RequestBody EntityAndExample<MallActivity, MallActivityExample> exampleEntityAndExample) {
        return mallActivityMapper.updateByExample(exampleEntityAndExample.getEntity(), exampleEntityAndExample.getExample());
    }

    @PostMapping("/updateByPrimaryKeySelective")
    public int updateByPrimaryKeySelective(@RequestBody MallActivity record) {
        return mallActivityMapper.updateByPrimaryKeySelective(record);
    }

    @PostMapping("/updateByPrimaryKey")
    public int updateByPrimaryKey(@RequestBody MallActivity record) {
        return mallActivityMapper.updateByPrimaryKey(record);
    }
}
