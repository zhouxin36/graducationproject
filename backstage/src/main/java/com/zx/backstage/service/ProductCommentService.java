package com.zx.backstage.service;

import com.zx.api.bean.EntityAndExample;
import com.zx.api.bean.ProductComment;
import com.zx.api.bean.ProductCommentExample;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/graducation/ProductCommentService")
@FeignClient(name = "persistence")
public interface ProductCommentService {

    @PostMapping("/countByExample")
    long countByExample(ProductCommentExample example);

    @PostMapping("/deleteByExample")
    int deleteByExample(ProductCommentExample example);

    @PostMapping("/deleteByPrimaryKey")
    int deleteByPrimaryKey(@RequestParam("id") String id);

    @PostMapping(value = "/insert")
    int insert(ProductComment record);

    @PostMapping(value = "/insertSelective")
    int insertSelective(ProductComment record);

    @PostMapping(value = "/selectByExample")
    List<ProductComment> selectByExample(ProductCommentExample example);

    @PostMapping("/selectByPrimaryKey")
    ProductComment selectByPrimaryKey(@RequestParam("id") String id);

    @PostMapping("/updateByExampleSelective")
    int updateByExampleSelective(EntityAndExample<ProductComment, ProductCommentExample> exampleEntityAndExample);

    @PostMapping("/updateByExample")
    int updateByExample(EntityAndExample<ProductComment, ProductCommentExample> exampleEntityAndExample);

    @PostMapping("/updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ProductComment record);

    @PostMapping("/updateByPrimaryKey")
    int updateByPrimaryKey(ProductComment record);
}

