package com.springcloud.stage.service;

import com.zx.api.bean.EntityAndExample;
import com.zx.api.bean.Product;
import com.zx.api.bean.ProductExample;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/graducation/ProductService")
@FeignClient(name = "persistence")
public interface ProductService {

    @PostMapping("/countByExample")
    long countByExample(ProductExample example);

    @PostMapping("/deleteByExample")
    int deleteByExample(ProductExample example);

    @PostMapping("/deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("id") String id);

    @PostMapping(value = "/insert")
    int insert(Product record);

    @PostMapping(value = "/insertSelective")
    int insertSelective(Product record);

    @PostMapping(value = "/selectByExample")
    List<Product> selectByExample(ProductExample example);

    @PostMapping("/selectByPrimaryKey")
    Product selectByPrimaryKey(@RequestParam("id") String id);

    @PostMapping("/updateByExampleSelective")
    int updateByExampleSelective(EntityAndExample<Product, ProductExample> exampleEntityAndExample);

    @PostMapping("/updateByExample")
    int updateByExample(EntityAndExample<Product, ProductExample> exampleEntityAndExample);

    @PostMapping("/updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Product record);

    @PostMapping("/updateByPrimaryKey")
    int updateByPrimaryKey(Product record);
}

