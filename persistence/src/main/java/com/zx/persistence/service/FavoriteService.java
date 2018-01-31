package com.zx.persistence.service;

import com.zx.api.bean.EntityAndExample;
import com.zx.persistence.bean.Favorite;
import com.zx.persistence.bean.FavoriteExample;
import com.zx.persistence.dao.FavoriteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/FavoriteService")
public class FavoriteService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private FavoriteMapper favoriteMapper;

    @PostMapping("/countByExample")
    public long countByExample(@RequestBody FavoriteExample example) {
        return favoriteMapper.countByExample(example);
    }

    @PostMapping("/deleteByExample")
    public int deleteByExample(@RequestBody FavoriteExample example) {
        return favoriteMapper.deleteByExample(example);
    }

    @PostMapping("/deleteByPrimaryKey")
    public int deleteByPrimaryKey(String id) {
        return favoriteMapper.deleteByPrimaryKey(id);
    }

    @PostMapping("/insert")
    public int insert(@RequestBody Favorite record) {
        return favoriteMapper.insert(record);
    }

    @PostMapping("/insertSelective")
    public int insertSelective(@RequestBody Favorite record) {
        return favoriteMapper.insertSelective(record);
    }

    @PostMapping("/selectByExample")
    public List<Favorite> selectByExample(@RequestBody FavoriteExample example) {
        return favoriteMapper.selectByExample(example);
    }

    @PostMapping("/selectByPrimaryKey")
    public Favorite selectByPrimaryKey(String id) {
        return favoriteMapper.selectByPrimaryKey(id);
    }

    @PostMapping("/updateByExampleSelective")
    public int updateByExampleSelective(@RequestBody EntityAndExample<Favorite, FavoriteExample> exampleEntityAndExample) {
        return favoriteMapper.updateByExampleSelective(exampleEntityAndExample.getEntity(), exampleEntityAndExample.getExample());
    }

    @PostMapping("/updateByExample")
    public int updateByExample(@RequestBody EntityAndExample<Favorite, FavoriteExample> exampleEntityAndExample) {
        return favoriteMapper.updateByExample(exampleEntityAndExample.getEntity(), exampleEntityAndExample.getExample());
    }

    @PostMapping("/updateByPrimaryKeySelective")
    public int updateByPrimaryKeySelective(@RequestBody Favorite record) {
        return favoriteMapper.updateByPrimaryKeySelective(record);
    }

    @PostMapping("/updateByPrimaryKey")
    public int updateByPrimaryKey(@RequestBody Favorite record) {
        return favoriteMapper.updateByPrimaryKey(record);
    }
}
