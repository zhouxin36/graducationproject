package com.zx.persistence.service;

import com.zx.api.bean.EntityAndExample;
import com.zx.persistence.bean.Deposit;
import com.zx.persistence.bean.DepositExample;
import com.zx.persistence.dao.DepositMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/DepositService")
public class DepositService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private DepositMapper userMapper;

    @PostMapping("/countByExample")
    public long countByExample(@RequestBody DepositExample example) {
        return userMapper.countByExample(example);
    }

    @PostMapping("/deleteByExample")
    public int deleteByExample(@RequestBody DepositExample example) {
        return userMapper.deleteByExample(example);
    }

    @PostMapping("/deleteByPrimaryKey")
    public int deleteByPrimaryKey(String id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @PostMapping("/insert")
    public int insert(@RequestBody Deposit record) {
        return userMapper.insert(record);
    }

    @PostMapping("/insertSelective")
    public int insertSelective(@RequestBody Deposit record) {
        return userMapper.insertSelective(record);
    }

    @PostMapping("/selectByExample")
    public List<Deposit> selectByExample(@RequestBody DepositExample example) {
        return userMapper.selectByExample(example);
    }

    @PostMapping("/selectByPrimaryKey")
    public Deposit selectByPrimaryKey(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @PostMapping("/updateByExampleSelective")
    public int updateByExampleSelective(@RequestBody EntityAndExample<Deposit, DepositExample> exampleEntityAndExample) {
        return userMapper.updateByExampleSelective(exampleEntityAndExample.getEntity(), exampleEntityAndExample.getExample());
    }

    @PostMapping("/updateByExample")
    public int updateByExample(@RequestBody EntityAndExample<Deposit, DepositExample> exampleEntityAndExample) {
        return userMapper.updateByExample(exampleEntityAndExample.getEntity(), exampleEntityAndExample.getExample());
    }

    @PostMapping("/updateByPrimaryKeySelective")
    public int updateByPrimaryKeySelective(@RequestBody Deposit record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @PostMapping("/updateByPrimaryKey")
    public int updateByPrimaryKey(@RequestBody Deposit record) {
        return userMapper.updateByPrimaryKey(record);
    }
}
