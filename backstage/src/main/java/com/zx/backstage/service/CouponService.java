package com.zx.backstage.service;

import com.zx.api.bean.EntityAndExample;
import com.zx.api.bean.Coupon;
import com.zx.api.bean.CouponExample;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/graducation/CouponService")
@FeignClient(name = "persistence")
public interface CouponService {

    @PostMapping("/countByExample")
    long countByExample(CouponExample example);

    @PostMapping("/deleteByExample")
    int deleteByExample(CouponExample example);

    @PostMapping("/deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("id") String id);

    @PostMapping(value = "/insert")
    int insert(Coupon record);

    @PostMapping(value = "/insertSelective")
    int insertSelective(Coupon record);

    @PostMapping(value = "/selectByExample")
    List<Coupon> selectByExample(CouponExample example);

    @PostMapping("/selectByPrimaryKey")
    Coupon selectByPrimaryKey(@RequestParam("id") String id);

    @PostMapping("/updateByExampleSelective")
    int updateByExampleSelective(EntityAndExample<Coupon, CouponExample> exampleEntityAndExample);

    @PostMapping("/updateByExample")
    int updateByExample(EntityAndExample<Coupon, CouponExample> exampleEntityAndExample);

    @PostMapping("/updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Coupon record);

    @PostMapping("/updateByPrimaryKey")
    int updateByPrimaryKey(Coupon record);
}

