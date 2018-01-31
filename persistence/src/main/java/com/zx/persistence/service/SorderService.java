package com.zx.persistence.service;

import com.zx.api.bean.EntityAndExample;
import com.zx.persistence.bean.Sorder;
import com.zx.persistence.bean.SorderExample;
import com.zx.persistence.dao.SorderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/SorderService")
public class SorderService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private SorderMapper sorderMapper;

    @PostMapping("/countByExample")
    public long countByExample(@RequestBody SorderExample example) {
        return sorderMapper.countByExample(example);
    }

    @PostMapping("/deleteByExample")
    public int deleteByExample(@RequestBody SorderExample example) {
        return sorderMapper.deleteByExample(example);
    }

    @PostMapping("/deleteByPrimaryKey")
    public int deleteByPrimaryKey(String id) {
        return sorderMapper.deleteByPrimaryKey(id);
    }

    @PostMapping("/insert")
    public int insert(@RequestBody Sorder record) {
        return sorderMapper.insert(record);
    }

    @PostMapping("/insertSelective")
    public int insertSelective(@RequestBody Sorder record) {
        return sorderMapper.insertSelective(record);
    }

    @PostMapping("/selectByExample")
    public List<Sorder> selectByExample(@RequestBody SorderExample example) {
        return sorderMapper.selectByExample(example);
    }

    @PostMapping("/selectByPrimaryKey")
    public Sorder selectByPrimaryKey(String id) {
        return sorderMapper.selectByPrimaryKey(id);
    }

    @PostMapping("/updateByExampleSelective")
    public int updateByExampleSelective(@RequestBody EntityAndExample<Sorder, SorderExample> exampleEntityAndExample) {
        return sorderMapper.updateByExampleSelective(exampleEntityAndExample.getEntity(), exampleEntityAndExample.getExample());
    }

    @PostMapping("/updateByExample")
    public int updateByExample(@RequestBody EntityAndExample<Sorder, SorderExample> exampleEntityAndExample) {
        return sorderMapper.updateByExample(exampleEntityAndExample.getEntity(), exampleEntityAndExample.getExample());
    }

    @PostMapping("/updateByPrimaryKeySelective")
    public int updateByPrimaryKeySelective(@RequestBody Sorder record) {
        return sorderMapper.updateByPrimaryKeySelective(record);
    }

    @PostMapping("/updateByPrimaryKey")
    public int updateByPrimaryKey(@RequestBody Sorder record) {
        return sorderMapper.updateByPrimaryKey(record);
    }
}
