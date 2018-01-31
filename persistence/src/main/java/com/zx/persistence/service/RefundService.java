package com.zx.persistence.service;

import com.zx.api.bean.EntityAndExample;
import com.zx.persistence.bean.Refund;
import com.zx.persistence.bean.RefundExample;
import com.zx.persistence.dao.RefundMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/RefundService")
public class RefundService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private RefundMapper refundMapper;

    @PostMapping("/countByExample")
    public long countByExample(@RequestBody RefundExample example) {
        return refundMapper.countByExample(example);
    }

    @PostMapping("/deleteByExample")
    public int deleteByExample(@RequestBody RefundExample example) {
        return refundMapper.deleteByExample(example);
    }

    @PostMapping("/deleteByPrimaryKey")
    public int deleteByPrimaryKey(String id) {
        return refundMapper.deleteByPrimaryKey(id);
    }

    @PostMapping("/insert")
    public int insert(@RequestBody Refund record) {
        return refundMapper.insert(record);
    }

    @PostMapping("/insertSelective")
    public int insertSelective(@RequestBody Refund record) {
        return refundMapper.insertSelective(record);
    }

    @PostMapping("/selectByExample")
    public List<Refund> selectByExample(@RequestBody RefundExample example) {
        return refundMapper.selectByExample(example);
    }

    @PostMapping("/selectByPrimaryKey")
    public Refund selectByPrimaryKey(String id) {
        return refundMapper.selectByPrimaryKey(id);
    }

    @PostMapping("/updateByExampleSelective")
    public int updateByExampleSelective(@RequestBody EntityAndExample<Refund, RefundExample> exampleEntityAndExample) {
        return refundMapper.updateByExampleSelective(exampleEntityAndExample.getEntity(), exampleEntityAndExample.getExample());
    }

    @PostMapping("/updateByExample")
    public int updateByExample(@RequestBody EntityAndExample<Refund, RefundExample> exampleEntityAndExample) {
        return refundMapper.updateByExample(exampleEntityAndExample.getEntity(), exampleEntityAndExample.getExample());
    }

    @PostMapping("/updateByPrimaryKeySelective")
    public int updateByPrimaryKeySelective(@RequestBody Refund record) {
        return refundMapper.updateByPrimaryKeySelective(record);
    }

    @PostMapping("/updateByPrimaryKey")
    public int updateByPrimaryKey(@RequestBody Refund record) {
        return refundMapper.updateByPrimaryKey(record);
    }
}
