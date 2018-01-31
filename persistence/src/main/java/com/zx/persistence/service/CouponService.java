package com.zx.persistence.service;

import com.zx.api.bean.EntityAndExample;
import com.zx.persistence.bean.Coupon;
import com.zx.persistence.bean.CouponExample;
import com.zx.persistence.dao.CouponMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/CouponService")
public class CouponService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private CouponMapper couponMapper;

    @PostMapping("/countByExample")
    public long countByExample(@RequestBody CouponExample example) {
        return couponMapper.countByExample(example);
    }

    @PostMapping("/deleteByExample")
    public int deleteByExample(@RequestBody CouponExample example) {
        return couponMapper.deleteByExample(example);
    }

    @PostMapping("/deleteByPrimaryKey")
    public int deleteByPrimaryKey(String id) {
        return couponMapper.deleteByPrimaryKey(id);
    }

    @PostMapping("/insert")
    public int insert(@RequestBody Coupon record) {
        return couponMapper.insert(record);
    }

    @PostMapping("/insertSelective")
    public int insertSelective(@RequestBody Coupon record) {
        return couponMapper.insertSelective(record);
    }

    @PostMapping("/selectByExample")
    public List<Coupon> selectByExample(@RequestBody CouponExample example) {
        return couponMapper.selectByExample(example);
    }

    @PostMapping("/selectByPrimaryKey")
    public Coupon selectByPrimaryKey(String id) {
        return couponMapper.selectByPrimaryKey(id);
    }

    @PostMapping("/updateByExampleSelective")
    public int updateByExampleSelective(@RequestBody EntityAndExample<Coupon, CouponExample> exampleEntityAndExample) {
        return couponMapper.updateByExampleSelective(exampleEntityAndExample.getEntity(), exampleEntityAndExample.getExample());
    }

    @PostMapping("/updateByExample")
    public int updateByExample(@RequestBody EntityAndExample<Coupon, CouponExample> exampleEntityAndExample) {
        return couponMapper.updateByExample(exampleEntityAndExample.getEntity(), exampleEntityAndExample.getExample());
    }

    @PostMapping("/updateByPrimaryKeySelective")
    public int updateByPrimaryKeySelective(@RequestBody Coupon record) {
        return couponMapper.updateByPrimaryKeySelective(record);
    }

    @PostMapping("/updateByPrimaryKey")
    public int updateByPrimaryKey(@RequestBody Coupon record) {
        return couponMapper.updateByPrimaryKey(record);
    }
}
