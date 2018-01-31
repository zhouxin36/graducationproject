package com.zx.persistence.service;

import com.zx.api.bean.EntityAndExample;
import com.zx.persistence.bean.Forder;
import com.zx.persistence.bean.ForderExample;
import com.zx.persistence.dao.ForderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ForderService")
public class ForderService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ForderMapper forderMapper;

    @PostMapping("/countByExample")
    public long countByExample(@RequestBody ForderExample example) {
        return forderMapper.countByExample(example);
    }

    @PostMapping("/deleteByExample")
    public int deleteByExample(@RequestBody ForderExample example) {
        return forderMapper.deleteByExample(example);
    }

    @PostMapping("/deleteByPrimaryKey")
    public int deleteByPrimaryKey(String id) {
        return forderMapper.deleteByPrimaryKey(id);
    }

    @PostMapping("/insert")
    public int insert(@RequestBody Forder record) {
        return forderMapper.insert(record);
    }

    @PostMapping("/insertSelective")
    public int insertSelective(@RequestBody Forder record) {
        return forderMapper.insertSelective(record);
    }

    @PostMapping("/selectByExample")
    public List<Forder> selectByExample(@RequestBody ForderExample example) {
        return forderMapper.selectByExample(example);
    }

    @PostMapping("/selectByPrimaryKey")
    public Forder selectByPrimaryKey(String id) {
        return forderMapper.selectByPrimaryKey(id);
    }

    @PostMapping("/updateByExampleSelective")
    public int updateByExampleSelective(@RequestBody EntityAndExample<Forder, ForderExample> exampleEntityAndExample) {
        return forderMapper.updateByExampleSelective(exampleEntityAndExample.getEntity(), exampleEntityAndExample.getExample());
    }

    @PostMapping("/updateByExample")
    public int updateByExample(@RequestBody EntityAndExample<Forder, ForderExample> exampleEntityAndExample) {
        return forderMapper.updateByExample(exampleEntityAndExample.getEntity(), exampleEntityAndExample.getExample());
    }

    @PostMapping("/updateByPrimaryKeySelective")
    public int updateByPrimaryKeySelective(@RequestBody Forder record) {
        return forderMapper.updateByPrimaryKeySelective(record);
    }

    @PostMapping("/updateByPrimaryKey")
    public int updateByPrimaryKey(@RequestBody Forder record) {
        return forderMapper.updateByPrimaryKey(record);
    }
}
