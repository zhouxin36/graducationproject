package com.springcloud.stage.service;

import com.zx.api.bean.EntityAndExample;
import com.zx.api.bean.Favorite;
import com.zx.api.bean.FavoriteExample;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/graducation/FavoriteService")
@FeignClient(name = "persistence")
public interface FavoriteService {

    @PostMapping("/countByExample")
    long countByExample(FavoriteExample example);

    @PostMapping("/deleteByExample")
    int deleteByExample(FavoriteExample example);

    @PostMapping("/deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("id") String id);

    @PostMapping(value = "/insert")
    int insert(Favorite record);

    @PostMapping(value = "/insertSelective")
    int insertSelective(Favorite record);

    @PostMapping(value = "/selectByExample")
    List<Favorite> selectByExample(FavoriteExample example);

    @PostMapping("/selectByPrimaryKey")
    Favorite selectByPrimaryKey(@RequestParam("id") String id);

    @PostMapping("/updateByExampleSelective")
    int updateByExampleSelective(EntityAndExample<Favorite, FavoriteExample> exampleEntityAndExample);

    @PostMapping("/updateByExample")
    int updateByExample(EntityAndExample<Favorite, FavoriteExample> exampleEntityAndExample);

    @PostMapping("/updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Favorite record);

    @PostMapping("/updateByPrimaryKey")
    int updateByPrimaryKey(Favorite record);
}

