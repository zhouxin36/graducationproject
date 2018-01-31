package com.zx.persistence.service;

import com.zx.api.bean.EntityAndExample;
import com.zx.persistence.bean.UserAddress;
import com.zx.persistence.bean.UserAddressExample;
import com.zx.persistence.dao.UserAddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/UserAddressService")
public class UserAddressService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private UserAddressMapper userAddressMapper;

    @PostMapping("/countByExample")
    public long countByExample(@RequestBody UserAddressExample example) {
        return userAddressMapper.countByExample(example);
    }

    @PostMapping("/deleteByExample")
    public int deleteByExample(@RequestBody UserAddressExample example) {
        return userAddressMapper.deleteByExample(example);
    }

    @PostMapping("/deleteByPrimaryKey")
    public int deleteByPrimaryKey(String id) {
        return userAddressMapper.deleteByPrimaryKey(id);
    }

    @PostMapping("/insert")
    public int insert(@RequestBody UserAddress record) {
        return userAddressMapper.insert(record);
    }

    @PostMapping("/insertSelective")
    public int insertSelective(@RequestBody UserAddress record) {
        return userAddressMapper.insertSelective(record);
    }

    @PostMapping("/selectByExample")
    public List<UserAddress> selectByExample(@RequestBody UserAddressExample example) {
        return userAddressMapper.selectByExample(example);
    }

    @PostMapping("/selectByPrimaryKey")
    public UserAddress selectByPrimaryKey(String id) {
        return userAddressMapper.selectByPrimaryKey(id);
    }

    @PostMapping("/updateByExampleSelective")
    public int updateByExampleSelective(@RequestBody EntityAndExample<UserAddress, UserAddressExample> exampleEntityAndExample) {
        return userAddressMapper.updateByExampleSelective(exampleEntityAndExample.getEntity(), exampleEntityAndExample.getExample());
    }

    @PostMapping("/updateByExample")
    public int updateByExample(@RequestBody EntityAndExample<UserAddress, UserAddressExample> exampleEntityAndExample) {
        return userAddressMapper.updateByExample(exampleEntityAndExample.getEntity(), exampleEntityAndExample.getExample());
    }

    @PostMapping("/updateByPrimaryKeySelective")
    public int updateByPrimaryKeySelective(@RequestBody UserAddress record) {
        return userAddressMapper.updateByPrimaryKeySelective(record);
    }

    @PostMapping("/updateByPrimaryKey")
    public int updateByPrimaryKey(@RequestBody UserAddress record) {
        return userAddressMapper.updateByPrimaryKey(record);
    }
}
