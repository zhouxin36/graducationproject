package com.zx.persistence.service;

import com.zx.api.bean.EntityAndExample;
import com.zx.persistence.bean.Admin;
import com.zx.persistence.bean.AdminExample;
import com.zx.persistence.dao.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/AdminService")
public class AdminService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private AdminMapper adminMapper;

    @PostMapping("/countByExample")
    public long countByExample(@RequestBody AdminExample example) {
        return adminMapper.countByExample(example);
    }

    @PostMapping("/deleteByExample")
    public int deleteByExample(@RequestBody AdminExample example) {
        return adminMapper.deleteByExample(example);
    }

    @PostMapping("/deleteByPrimaryKey")
    public int deleteByPrimaryKey(String id) {
        return adminMapper.deleteByPrimaryKey(id);
    }

    @PostMapping("/insert")
    public int insert(@RequestBody Admin record) {
        return adminMapper.insert(record);
    }

    @PostMapping("/insertSelective")
    public int insertSelective(@RequestBody Admin record) {
        return adminMapper.insertSelective(record);
    }

    @PostMapping("/selectByExample")
    public List<Admin> selectByExample(@RequestBody AdminExample example) {
        return adminMapper.selectByExample(example);
    }

    @PostMapping("/selectByPrimaryKey")
    public Admin selectByPrimaryKey(String id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    @PostMapping("/updateByExampleSelective")
    public int updateByExampleSelective(@RequestBody EntityAndExample<Admin, AdminExample> exampleEntityAndExample) {
        return adminMapper.updateByExampleSelective(exampleEntityAndExample.getEntity(), exampleEntityAndExample.getExample());
    }

    @PostMapping("/updateByExample")
    public int updateByExample(@RequestBody EntityAndExample<Admin, AdminExample> exampleEntityAndExample) {
        return adminMapper.updateByExample(exampleEntityAndExample.getEntity(), exampleEntityAndExample.getExample());
    }

    @PostMapping("/updateByPrimaryKeySelective")
    public int updateByPrimaryKeySelective(@RequestBody Admin record) {
        return adminMapper.updateByPrimaryKeySelective(record);
    }

    @PostMapping("/updateByPrimaryKey")
    public int updateByPrimaryKey(@RequestBody Admin record) {
        return adminMapper.updateByPrimaryKey(record);
    }
}
