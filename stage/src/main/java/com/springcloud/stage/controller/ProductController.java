package com.springcloud.stage.controller;

import com.springcloud.stage.service.CategoryService;
import com.springcloud.stage.service.MallActivityService;
import com.springcloud.stage.service.ProductService;
import com.zx.api.bean.*;
import com.zx.api.dto.R;
import com.zx.api.dto.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/Product")
public class ProductController {

	@Autowired
	ProductService productService;

	@ResponseBody
	@RequestMapping("/selectProductDes")
	private ResultDTO selectProductDes() {

        ProductExample example = new ProductExample();
        example.setOrderByClause("sale desc");
        ProductExample.Criteria criteria=example.createCriteria();
        criteria.andOpenEqualTo(1);
        List<Product> list = productService.selectByExample(example);

		if (list.size() != 0) {
            Map<String,Object> map = new HashMap<>();
            map.put("list", list);
			return ResultDTO.buildSuccessData(map);
		}
		return ResultDTO.error();
	}

	@ResponseBody
	@RequestMapping("/SelectCategory")
	public ResultDTO SelectCategory(@RequestParam(value = "name") String name) {
        ProductExample example = new ProductExample();
        ProductExample.Criteria criteria = example.createCriteria();
        criteria.andOpenEqualTo(1);
        criteria.andNameLike("%" + name.trim() + "%");
        List<Product> list = productService.selectByExample(example);
        List<Product> list_ = null;
        if (list.size() != 0) {
            ProductExample example2 = new ProductExample();
            ProductExample.Criteria criteria2 = example2.createCriteria();
            criteria2.andCategoryIdEqualTo(list.get(0).getCategoryId());
            list_ = productService.selectByExample(example2);
        }
		if (list_.size() != 0) {
            Map<String,Object> map = new HashMap<>();
            map.put("list", list_);
            return ResultDTO.buildSuccessData(map);
        }
		return ResultDTO.error();
	}


	@ResponseBody
	@RequestMapping("/SelectProductById")
	public ResultDTO SelectProductById(@RequestParam(value = "id", defaultValue = "1") String id) {
		Product product = productService.selectByPrimaryKey(id);
		if (product != null) {
            Map<String,Object> map = new HashMap<>();
            map.put("product", product);
            return ResultDTO.buildSuccessData(map);
        }
		return ResultDTO.error();
	}

	@ResponseBody
	@RequestMapping("/SelectProductByName")
	public ResultDTO SelectProductByName(@RequestParam(value = "pn", defaultValue = "1") Integer pn, String name,
			String category, String brand, Double down, Double up , Integer sort) {
		System.out.println("name=" + name + "&category=" + category + "&brand=" + brand
				+ "&down=" + down + "&up=" + up + "&sort1=" + sort + "&pn=" + pn);
		if(brand.equals("")) {
			brand = null;
		}

        {
            ProductExample example = new ProductExample();
            ProductExample.Criteria criteria=example.createCriteria();
            criteria.andNameLike("%"+name.trim()+"%");
            criteria.andOpenEqualTo(1);
            if(brand!=null) {
                criteria.andNameLike("%"+brand.trim()+"%");
            }
            if(category!=null) {
                criteria.andCategoryIdEqualTo(category);
            }
            if(down!=null) {
                criteria.andPriceGreaterThanOrEqualTo(new BigDecimal(down));
            }
            if(up!=null) {
                criteria.andPriceLessThanOrEqualTo(new BigDecimal(up));
            }
            if(sort!=null) {
                if(sort==1) {
                    example.setOrderByClause("price asc");
                }else if(sort==2) {
                    example.setOrderByClause("price desc");
                }else if(sort==3) {
                    example.setOrderByClause("month_sale asc");
                }else if(sort==4) {
                    example.setOrderByClause("month_sale desc");
                }
            }
            example.setPageSize(10);
            example.setStartRow(pn);
            List<Product> list=productService.selectByExample(example);
            for (Product product : list) {
                System.out.println(product);
            }

            if(!list.isEmpty()) {
                ProductExample example_ = new ProductExample();
                ProductExample.Criteria criteria_=example_.createCriteria();
                criteria_.andNameLike("%"+name.trim()+"%");
                criteria_.andOpenEqualTo(1);
                example_.setOrderByClause("price asc");
                BigDecimal max=productService.selectByExample(example_).get(0).getPrice();
                example_.setOrderByClause("price desc");
                BigDecimal min=productService.selectByExample(example_).get(0).getPrice();
                Map<String,Object> map = new HashMap<>();
                map.put("list", list);
                map.put("max", max);
                map.put("min", min);
                return ResultDTO.buildSuccessData(map);
            }

            return ResultDTO.error();
        }
	}

	@Autowired
    MallActivityService mallActivityService;

	@Autowired
    CategoryService categoryService;

	@ResponseBody
	@RequestMapping("/home_message")
	public ResultDTO home_message() {
		List<MallActivity> list = mallActivityService.selectByExample(new MallActivityExample());
		List<Category> list2 = categoryService.selectByExample(new CategoryExample());
//		Msg msg1_1 = productService.selectProductsByCategory(1, 1);
//		Msg msg1_2 = productService.selectProductsByCategory(1, 2);
//		Msg msg1_3 = productService.selectProductsByCategory(1, 3);
//		Msg msg2_1 = productService.selectProductsByCategory(2, 1);
//		Msg msg2_2 = productService.selectProductsByCategory(2, 2);
//		Msg msg2_3 = productService.selectProductsByCategory(2, 3);
        Map<String,Object> map = new HashMap<>();
        map.put("activity_list", list);
        map.put("category_list", list2);
        return ResultDTO.buildSuccessData(map);
//		return ResultDTO.success().add("activity_list", list).add("category_list", list2).add("msg1_1", msg1_1)
//				.add("msg1_2", msg1_2).add("msg1_3", msg1_3).add("msg2_1", msg2_1).add("msg2_2", msg2_2)
//				.add("msg2_3", msg2_3);
	}

	@ResponseBody
	@RequestMapping("/selectProductByCategoryId")
	public ResultDTO selectProductByCategoryId(String id) {
        ProductExample example = new ProductExample();
        ProductExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(id);
        criteria.andOpenEqualTo(1);
        example.setPageSize(6);
        example.setStartRow(1);
        List<Product> list = productService.selectByExample(example);
        return ResultDTO.buildSuccessData(list);
	}

	@ResponseBody
	@RequestMapping("/guessyoulike")
	public ResultDTO guessyoulike(String type) {
	    ProductExample productExample = new ProductExample();
        ProductExample.Criteria criteria = productExample.createCriteria();
        criteria.andCategoryIdEqualTo(type);
        productExample.setPageSize(6);
        productExample.setStartRow(1);
        List<Product> products = productService.selectByExample(productExample);
        productExample.setStartRow(1);
        List<Product> products2 = productService.selectByExample(productExample);
        Map<String,Object> map = new HashMap<>();
        map.put("msg1", products);
        map.put("msg2", products2);
		return ResultDTO.buildSuccessData(map);
	}

	@ResponseBody
	@RequestMapping("/select_category")
	public ResultDTO select_category() {
        Map<String,Object> map = new HashMap<>();
        map.put("category", categoryService.selectByExample(new CategoryExample()));
		return ResultDTO.buildSuccessData(map);
	}
}
