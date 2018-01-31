package com.zx.persistence.service;

import com.zx.api.bean.EntityAndExample;
import com.zx.persistence.bean.Footmark;
import com.zx.persistence.bean.FootmarkExample;
import com.zx.persistence.dao.FootmarkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/FootmarkService")
public class FootmarkService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private FootmarkMapper footmarkMapper;

    @PostMapping("/countByExample")
    public long countByExample(@RequestBody FootmarkExample example) {
        return footmarkMapper.countByExample(example);
    }

    @PostMapping("/deleteByExample")
    public int deleteByExample(@RequestBody FootmarkExample example) {
        return footmarkMapper.deleteByExample(example);
    }

    @PostMapping("/deleteByPrimaryKey")
    public int deleteByPrimaryKey(String id) {
        return footmarkMapper.deleteByPrimaryKey(id);
    }

    @PostMapping("/insert")
    public int insert(@RequestBody Footmark record) {
        return footmarkMapper.insert(record);
    }

    @PostMapping("/insertSelective")
    public int insertSelective(@RequestBody Footmark record) {
        return footmarkMapper.insertSelective(record);
    }

    @PostMapping("/selectByExample")
    public List<Footmark> selectByExample(@RequestBody FootmarkExample example) {
        return footmarkMapper.selectByExample(example);
    }

    @PostMapping("/selectByPrimaryKey")
    public Footmark selectByPrimaryKey(String id) {
        return footmarkMapper.selectByPrimaryKey(id);
    }

    @PostMapping("/updateByExampleSelective")
    public int updateByExampleSelective(@RequestBody EntityAndExample<Footmark, FootmarkExample> exampleEntityAndExample) {
        return footmarkMapper.updateByExampleSelective(exampleEntityAndExample.getEntity(), exampleEntityAndExample.getExample());
    }

    @PostMapping("/updateByExample")
    public int updateByExample(@RequestBody EntityAndExample<Footmark, FootmarkExample> exampleEntityAndExample) {
        return footmarkMapper.updateByExample(exampleEntityAndExample.getEntity(), exampleEntityAndExample.getExample());
    }

    @PostMapping("/updateByPrimaryKeySelective")
    public int updateByPrimaryKeySelective(@RequestBody Footmark record) {
        return footmarkMapper.updateByPrimaryKeySelective(record);
    }

    @PostMapping("/updateByPrimaryKey")
    public int updateByPrimaryKey(@RequestBody Footmark record) {
        return footmarkMapper.updateByPrimaryKey(record);
    }
}
