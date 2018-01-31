package com.zx.persistence.service;

import com.zx.api.bean.EntityAndExample;
import com.zx.persistence.bean.ProductComment;
import com.zx.persistence.bean.ProductCommentExample;
import com.zx.persistence.dao.ProductCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ProductCommentService")
public class ProductCommentService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ProductCommentMapper productCommentMapper;

    @PostMapping("/countByExample")
    public long countByExample(@RequestBody ProductCommentExample example) {
        return productCommentMapper.countByExample(example);
    }

    @PostMapping("/deleteByExample")
    public int deleteByExample(@RequestBody ProductCommentExample example) {
        return productCommentMapper.deleteByExample(example);
    }

    @PostMapping("/deleteByPrimaryKey")
    public int deleteByPrimaryKey(String id) {
        return productCommentMapper.deleteByPrimaryKey(id);
    }

    @PostMapping("/insert")
    public int insert(@RequestBody ProductComment record) {
        return productCommentMapper.insert(record);
    }

    @PostMapping("/insertSelective")
    public int insertSelective(@RequestBody ProductComment record) {
        return productCommentMapper.insertSelective(record);
    }

    @PostMapping("/selectByExample")
    public List<ProductComment> selectByExample(@RequestBody ProductCommentExample example) {
        return productCommentMapper.selectByExample(example);
    }

    @PostMapping("/selectByPrimaryKey")
    public ProductComment selectByPrimaryKey(String id) {
        return productCommentMapper.selectByPrimaryKey(id);
    }

    @PostMapping("/updateByExampleSelective")
    public int updateByExampleSelective(@RequestBody EntityAndExample<ProductComment, ProductCommentExample> exampleEntityAndExample) {
        return productCommentMapper.updateByExampleSelective(exampleEntityAndExample.getEntity(), exampleEntityAndExample.getExample());
    }

    @PostMapping("/updateByExample")
    public int updateByExample(@RequestBody EntityAndExample<ProductComment, ProductCommentExample> exampleEntityAndExample) {
        return productCommentMapper.updateByExample(exampleEntityAndExample.getEntity(), exampleEntityAndExample.getExample());
    }

    @PostMapping("/updateByPrimaryKeySelective")
    public int updateByPrimaryKeySelective(@RequestBody ProductComment record) {
        return productCommentMapper.updateByPrimaryKeySelective(record);
    }

    @PostMapping("/updateByPrimaryKey")
    public int updateByPrimaryKey(@RequestBody ProductComment record) {
        return productCommentMapper.updateByPrimaryKey(record);
    }
}
