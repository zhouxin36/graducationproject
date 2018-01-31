package com.zx.persistence.service;

import com.zx.api.bean.EntityAndExample;
import com.zx.persistence.bean.Pic;
import com.zx.persistence.bean.PicExample;
import com.zx.persistence.dao.PicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/PicService")
public class PicService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private PicMapper picMapper;

    @PostMapping("/countByExample")
    public long countByExample(@RequestBody PicExample example) {
        return picMapper.countByExample(example);
    }

    @PostMapping("/deleteByExample")
    public int deleteByExample(@RequestBody PicExample example) {
        return picMapper.deleteByExample(example);
    }

    @PostMapping("/deleteByPrimaryKey")
    public int deleteByPrimaryKey(String id) {
        return picMapper.deleteByPrimaryKey(id);
    }

    @PostMapping("/insert")
    public int insert(@RequestBody Pic record) {
        return picMapper.insert(record);
    }

    @PostMapping("/insertSelective")
    public int insertSelective(@RequestBody Pic record) {
        return picMapper.insertSelective(record);
    }

    @PostMapping("/selectByExample")
    public List<Pic> selectByExample(@RequestBody PicExample example) {
        return picMapper.selectByExample(example);
    }

    @PostMapping("/selectByPrimaryKey")
    public Pic selectByPrimaryKey(String id) {
        return picMapper.selectByPrimaryKey(id);
    }

    @PostMapping("/updateByExampleSelective")
    public int updateByExampleSelective(@RequestBody EntityAndExample<Pic, PicExample> exampleEntityAndExample) {
        return picMapper.updateByExampleSelective(exampleEntityAndExample.getEntity(), exampleEntityAndExample.getExample());
    }

    @PostMapping("/updateByExample")
    public int updateByExample(@RequestBody EntityAndExample<Pic, PicExample> exampleEntityAndExample) {
        return picMapper.updateByExample(exampleEntityAndExample.getEntity(), exampleEntityAndExample.getExample());
    }

    @PostMapping("/updateByPrimaryKeySelective")
    public int updateByPrimaryKeySelective(@RequestBody Pic record) {
        return picMapper.updateByPrimaryKeySelective(record);
    }

    @PostMapping("/updateByPrimaryKey")
    public int updateByPrimaryKey(@RequestBody Pic record) {
        return picMapper.updateByPrimaryKey(record);
    }
}
