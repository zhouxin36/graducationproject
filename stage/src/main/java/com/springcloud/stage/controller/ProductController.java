package com.springcloud.stage.controller;

import com.springcloud.stage.service.CategoryService;
import com.springcloud.stage.service.HomeService;
import com.springcloud.stage.service.MallActivityService;
import com.springcloud.stage.service.ProductService;
import com.zx.api.bean.*;
import com.zx.api.dto.R;
import com.zx.api.dto.ResultDTO;
import com.zx.api.utils.MyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Controller
@RequestMapping("/Product")
public class ProductController {

    private Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	ProductService productService;

    @Autowired
    MallActivityService mallActivityService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    HomeService homeService;

    @Autowired
    RedisTemplate redisTemplate;


    @ResponseBody
	@RequestMapping("/selectProductDes")
	private ResultDTO selectProductDes() {
        try {
            logger.info("---->ProductController/selectProductDes");
            ProductExample example = new ProductExample();
            example.setOrderByClause("sale desc");
            ProductExample.Criteria criteria = example.createCriteria();
            criteria.andOpenEqualTo(1);
            List<Product> list = productService.selectByExample(example);

            if (list.size() != 0) {
                Map<String, Object> map = new HashMap<>();
                map.put("list", list);
                return ResultDTO.buildSuccessData(map);
            }else {
                return ResultDTO.error();
            }
        }catch (Exception e){
            logger.error("ProductController/selectProductDes; Exception:{}", e);
            return ResultDTO.error("系统错误，请联系管理员！");
        }
	}

	@ResponseBody
	@RequestMapping("/SelectCategory")
	public ResultDTO SelectCategory(@RequestParam(value = "name") String name) {
        Map<String, Object> map = new HashMap<>();
        try {
            logger.info("---->ProductController/selectProductDes,name:{}",name);
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
                map.put("list", list_);
                return ResultDTO.buildSuccessData(map);
            }else {
                return ResultDTO.error();
            }
        }catch (Exception e){
            logger.error("ProductController/SelectCategory; Exception:{},map:{}", e,map);
            return ResultDTO.error("系统错误，请联系管理员！");
        }
	}


	@ResponseBody
	@RequestMapping("/SelectProductById")
	public ResultDTO SelectProductById(@RequestParam(value = "id", defaultValue = "1") String id) {
        Map<String, Object> map = new HashMap<>();
        try {
            logger.info("---->ProductController/SelectProductById,id:{}",id);
            Product product = productService.selectByPrimaryKey(id);
            if (product != null) {
                map.put("product", product);
                return ResultDTO.buildSuccessData(map);
            }
        }catch (Exception e){
            logger.error("ProductController/SelectProductById; Exception:{},map:{}", e,map);
            return ResultDTO.error("系统错误，请联系管理员！");
        }
		return ResultDTO.error();
	}

	@ResponseBody
	@RequestMapping("/SelectProductByName")
	public ResultDTO SelectProductByName(@RequestParam(value = "pn", defaultValue = "1") Integer pn, String name,
			String category, String brand, Double down, Double up , Integer sort) {
        Map<String,Object> map = new HashMap<>();
        try {
            logger.info("---->ProductController/SelectProductByName,"+"name=" + name + "&category=" + category + "&brand=" + brand
                    + "&down=" + down + "&up=" + up + "&sort1=" + sort + "&pn=" + pn);
            ProductExample example = new ProductExample();
            ProductExample.Criteria criteria=example.createCriteria();
            if(!MyUtils.isBlank(name)) {
                criteria.andNameLike("%" + name.trim() + "%");
            }
            criteria.andOpenEqualTo(1);
            if(!MyUtils.isBlank(brand)) {
                criteria.andNameLike("%"+brand.trim()+"%");
            }
            if(!MyUtils.isBlank(category)) {
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
            long total= productService.countByExample(example);
            example.setPageSize(10);
            example.setStartRow((pn-1)*10);
            List<Product> list=productService.selectByExample(example);

            if(!list.isEmpty()) {
                ProductExample example_ = new ProductExample();
                ProductExample.Criteria criteria_=example_.createCriteria();
                criteria_.andNameLike("%"+name.trim()+"%");
                criteria_.andOpenEqualTo(1);
                example_.setOrderByClause("price asc");
                BigDecimal min=productService.selectByExample(example_).get(0).getPrice();
                example_.setOrderByClause("price desc");
                BigDecimal max=productService.selectByExample(example_).get(0).getPrice();
                map.put("list", list);
                map.put("max", max);
                map.put("min", min);
                map.put("total", total);
                map.put("totalPage", (total+9)/10);
                map.put("pn", pn);
                return ResultDTO.buildSuccessData(map);
            }else {
                return ResultDTO.error();
            }
        }catch (Exception e){
            logger.error("ProductController/SelectProductByName; Exception:{},map:{}", e,map);
            return ResultDTO.error("系统错误，请联系管理员！");
        }
	}


	@ResponseBody
	@RequestMapping("/home_message")
	public ResultDTO home_message() {
        Map<String, Object> map = new HashMap<>();
        try {
            logger.info("---->ProductController/home_message");
            map = (Map<String, Object>) redisTemplate.opsForValue().get("home_message");
            if(map == null) {
                logger.info("---->ProductController/home_message,from mysql");
                map = new HashMap<>();
                List<MallActivity> list = mallActivityService.selectByExample(new MallActivityExample());
                List<Category> list2 = categoryService.selectByExample(new CategoryExample());
                List<Home> list1 = homeService.selectByExample(new HomeExample());
                List<List<Product>> lists = new ArrayList<>();

                for (int i = 0; i < list1.size(); i++) {
                    Home home = list1.get(i);
                    ProductExample productExample = new ProductExample();
                    productExample.setStartRow(0);
                    productExample.setPageSize(6);
                    if (home.getSort() == 0) {
                        productExample.setOrderByClause("add_date desc");
                    }
                    if (home.getSort() == 1) {
                        productExample.setOrderByClause("add_date asc");
                    }
                    if (home.getSort() == 2) {
                        productExample.setOrderByClause("sale asc");
                    }
                    if (home.getSort() == 3) {
                        productExample.setOrderByClause("sale desc");
                    }
                    if (home.getSort() == 4) {
                        productExample.setOrderByClause("price asc");
                    }
                    if (home.getSort() == 5) {
                        productExample.setOrderByClause("price desc");
                    }
                    ProductExample.Criteria criteria = productExample.createCriteria();
                    criteria.andCategoryIdEqualTo(home.getCategoryId());
                    List<Product> products = productService.selectByExample(productExample);
                    lists.add(products);
                    map.put("home", lists);
                }
                map.put("activity_list", list);
                map.put("category_list", list2);
                map.put("home_list", list1);
                redisTemplate.opsForValue().set("home_message",map,10, TimeUnit.MINUTES);
            }
            return ResultDTO.buildSuccessData(map);
        }catch (Exception e){
            logger.error("ProductController/home_message; Exception:{},map:{}", e,map);
            return ResultDTO.error("系统错误，请联系管理员！");
        }
	}

	@ResponseBody
	@RequestMapping("/selectProductByCategoryId")
	public ResultDTO selectProductByCategoryId(String id) {
        List<Product> list = null;
        try {
            logger.info("---->ProductController/selectProductByCategoryId,id:{}",id);
            ProductExample example = new ProductExample();
            ProductExample.Criteria criteria = example.createCriteria();
            example.setOrderByClause("sale desc");
            criteria.andCategoryIdEqualTo(id);
            criteria.andOpenEqualTo(1);
            example.setPageSize(6);
            example.setStartRow(0);
            list = productService.selectByExample(example);
            if (list == null)
                return ResultDTO.error();
            return ResultDTO.buildSuccessData(list);
        }catch (Exception e){
            logger.error("ProductController/selectProductByCategoryId; Exception:{},list:{}", e,list);
            return ResultDTO.error("系统错误，请联系管理员！");
        }
	}

	@ResponseBody
	@RequestMapping("/guessyoulike")
	public ResultDTO guessyoulike(String type) {
        Map<String, Object> map = new HashMap<>();
        try {
            logger.info("---->ProductController/guessyoulike,type:{}",type);
            ProductExample productExample = new ProductExample();
            ProductExample.Criteria criteria = productExample.createCriteria();
            criteria.andCategoryIdEqualTo(type);
            productExample.setPageSize(6);
            productExample.setStartRow(1);
            List<Product> products = productService.selectByExample(productExample);
            productExample.setStartRow(1);
            List<Product> products2 = productService.selectByExample(productExample);
            map.put("msg1", products);
            map.put("msg2", products2);
            return ResultDTO.buildSuccessData(map);
        }catch (Exception e){
            logger.error("ProductController/guessyoulike; Exception:{},map:{}", e,map);
            return ResultDTO.error("系统错误，请联系管理员！");
        }
	}

	@ResponseBody
	@RequestMapping("/select_category")
	public ResultDTO select_category() {
        Map<String, Object> map = new HashMap<>();
        try {
            logger.info("---->ProductController/select_category");
            map.put("category", categoryService.selectByExample(new CategoryExample()));
            return ResultDTO.buildSuccessData(map);
        }catch (Exception e){
            logger.error("ProductController/select_category; Exception:{},map:{}", e,map);
            return ResultDTO.error("系统错误，请联系管理员！");
        }
	}
}
