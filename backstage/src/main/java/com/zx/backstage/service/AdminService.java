package com.zx.backstage.service;

import com.zx.api.bean.EntityAndExample;
import com.zx.api.bean.Admin;
import com.zx.api.bean.AdminExample;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/graducation/AdminService")
@FeignClient(name = "persistence")
public interface AdminService {

    @PostMapping("/countByExample")
    long countByExample(AdminExample example);

    @PostMapping("/deleteByExample")
    int deleteByExample(AdminExample example);

    @PostMapping("/deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("id") String id);

    @PostMapping(value = "/insert")
    int insert(Admin record);

    @PostMapping(value = "/insertSelective")
    int insertSelective(Admin record);

    @PostMapping(value = "/selectByExample")
    List<Admin> selectByExample(AdminExample example);

    @PostMapping("/selectByPrimaryKey")
    Admin selectByPrimaryKey(@RequestParam("id") String id);

    @PostMapping("/updateByExampleSelective")
    int updateByExampleSelective(EntityAndExample<Admin, AdminExample> exampleEntityAndExample);

    @PostMapping("/updateByExample")
    int updateByExample(EntityAndExample<Admin, AdminExample> exampleEntityAndExample);

    @PostMapping("/updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Admin record);

    @PostMapping("/updateByPrimaryKey")
    int updateByPrimaryKey(Admin record);
}

