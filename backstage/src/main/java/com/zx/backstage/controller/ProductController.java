package com.zx.backstage.controller;

import com.zx.api.bean.*;
import com.zx.api.dto.R;
import com.zx.api.dto.ResultDTO;
import com.zx.api.utils.DeleteFileUtil;
import com.zx.api.utils.MyUtils;
import com.zx.api.utils.PageUtils;
import com.zx.api.utils.Query;
import com.zx.backstage.service.AdminService;
import com.zx.backstage.service.CategoryService;
import com.zx.backstage.service.PicService;
import com.zx.backstage.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProductController {

	Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	PicService picService;

	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;

	@Autowired
	AdminService adminService;

	@Value("${file_base_url}")
	String FILE_BASE_URL;


	@ResponseBody
	@RequestMapping("/product_list")
	public R productList(HttpServletRequest request, @RequestParam Map<String, Object> params) {
        logger.info("---->ProductController/productList; params:{}",params);
        PageUtils pageUtil = null;
        try {
            Query query = new Query(params);
            ProductExample productExample = new ProductExample();
            productExample.setStartRow((Integer) query.get("offset"));
            productExample.setPageSize((Integer) query.get("limit"));
            ProductExample.Criteria criteria = productExample.createCriteria();
            if (!MyUtils.isBlank((String) query.get("name")))
                criteria.andNameLike("%" + (String) query.get("name") + "%");
            List<Product> products = productService.selectByExample(productExample);
            long total = productService.countByExample(productExample);
            pageUtil = new PageUtils(products, (int) total, query.getLimit(), query.getPage());
            return R.ok().put("page", pageUtil);
        }catch (Exception e){
            logger.error("ProductController/login; e:{},pageUtil:{}",e.getMessage(),pageUtil);
            return R.error("系统错误，请联系管理员！");
        }
	}

	@ResponseBody
	@RequestMapping("/product_select_category")
	public R productSelectCategory(HttpServletRequest request) {
        logger.info("---->ProductController/productSelectCategory; ");
        Map<String, List<Category>> map = new HashMap<>();
        List<Category> categories = null;
        try {
            @SuppressWarnings("unchecked")
            List<Category> list = (List<Category>) request.getSession().getAttribute("category");
            if (list != null) {
                map.put("list", list);
                return R.ok().put("list", list);
            } else {
                categories = categoryService.selectByExample(new CategoryExample());
                request.getSession().setAttribute("category", categories);
                map.put("list", categories);
                return R.ok().put("list", categories);
            }
        }catch (Exception e){
            logger.error("ProductController/productSelectCategory; e:{},categories:{}",e.getMessage(),categories);
            return R.error("系统错误，请联系管理员！");
        }
	}

	@ResponseBody
	@RequestMapping("/product_add")
	public ResultDTO productAdd(HttpServletRequest request, @RequestBody Product product)
			throws IllegalStateException, IOException {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		product.setAddDate(LocalDateTime.now());
		product.setMonthSale(0);
		product.setSale(0);
		product.setOpen(2);
		product.setLastAdminId(admin.getId());
		product.setId(MyUtils.getUUID());
		int i = productService.insert(product);
//		int i = 1;
		if (i == 1) {
			return ResultDTO.ok("添加成功");

		} else {
			return ResultDTO.error("添加失败");
		}
	}

	@ResponseBody
	@RequestMapping("/request_img")
	public ResultDTO<Map<String,Object>> requestImg(HttpServletRequest request, String id) {
//		String path = (String) request.getSession().getAttribute("path2");
		String path = FILE_BASE_URL;
		PicExample picExample = new PicExample();
        PicExample.Criteria criteria = picExample.createCriteria();
        criteria.andProductIdEqualTo(id);
        List<Pic> list = picService.selectByExample(picExample);
		ResultDTO msg = ResultDTO.ok();
		Map<String,Object> map = new HashMap<>();
		List<Pic> list2 = new ArrayList<Pic>();
		List<Pic> list3 = new ArrayList<Pic>();
		for (Pic pic : list) {
			if (pic.getType() == 1) {
                map.put("type1", pic);
			}
			if (pic.getType() == 2) {
				list2.add(pic);
			}
			if (pic.getType() == 3) {
				list3.add(pic);
			}
		}
        map.put("type2", list2);
        map.put("type3", list3);
        map.put("p", path);
        msg.setData(map);
		return msg;
	}

	@RequestMapping(value="/upload")
    @ResponseBody
	public Object handleFileUpload(HttpServletRequest request,String id,Integer type){
		System.out.println("id="+id+"::type="+type);
		List<MultipartFile> files =((MultipartHttpServletRequest)request).getFiles("file");
		MultipartFile file = null;
		BufferedOutputStream stream = null;
		for (int i =0; i< files.size(); ++i) {
			file = files.get(i);
			if (!file.isEmpty()) {
				try {
					byte[] bytes = file.getBytes();
					String name = id +"_"+ file.getOriginalFilename();
                    if(type == 1){
                        PicExample picExample = new PicExample();
                        PicExample.Criteria criteria1 = picExample.createCriteria();
                        criteria1.andProductIdEqualTo(id);
                        criteria1.andTypeEqualTo(1);
                        List<Pic> list = picService.selectByExample(picExample);
                        Pic pic = new Pic();
                        pic.setType(1);
                        pic.setProductId(id);
                        pic.setPath(name);
                        pic.setId(MyUtils.getUUID());
                        if (list.size() != 0) {
                            picExample = new PicExample();
                            PicExample.Criteria criteria = picExample.createCriteria();
                            criteria.andTypeEqualTo(1);
                            criteria.andProductIdEqualTo(id);
                            picService.deleteByExample(picExample);
                            picService.insert(pic);
                            DeleteFileUtil.delete(FILE_BASE_URL, list.get(0).getPath());
                        }else{
                            picService.insert(pic);
                        }
                    }else {
                        Pic pic = new Pic();
                        pic.setType(type);
                        pic.setProductId(id);
                        pic.setPath(name);
                        pic.setId(MyUtils.getUUID());
                        picService.insert(pic);
                    }
					stream = new BufferedOutputStream(new FileOutputStream(new File(FILE_BASE_URL+name)));
					stream.write(bytes);
					stream.close();
				}catch (Exception e) {
					return "You failed to upload " + i + " =>" + e.getMessage();
				}
			} else {
				return "You failed to upload " + i + " becausethe file was empty.";
			}
		}
		return R.ok();

	}

	@ResponseBody
	@RequestMapping("/find_img")
	public R findImg(String id) {
		String path = FILE_BASE_URL;
		PicExample picExample = new PicExample();
        PicExample.Criteria criteria = picExample.createCriteria();
        criteria.andProductIdEqualTo(id);
        List<Pic> list = picService.selectByExample(picExample);
		if (list.size() != 0) {
            Map<String,Object> map = new HashMap<>();
            map.put("list", list);
            List<Pic> list1 = new ArrayList<>();
            List<Pic> list2 = new ArrayList<>();
            List<Pic> list3 = new ArrayList<>();
            for (Pic pic1:
                 list) {
                if(pic1.getType() == 1){
                    list1.add(pic1);
                }
                if(pic1.getType() == 2){
                    list2.add(pic1);
                }
                if(pic1.getType() == 3){
                    list3.add(pic1);
                }
            }
            return R.ok().put("list1",list1).put("list2",list2).put("list3",list3);
		} else {
			return R.error("图片为空");
		}
	}

	@ResponseBody
	@RequestMapping("/delete_pic")
	public R deletePic(String key) {
		String path = FILE_BASE_URL;
        PicExample picExample = new PicExample();
        PicExample.Criteria criteria = picExample.createCriteria();
        criteria.andPathEqualTo(key);
        picService.deleteByExample(picExample);
        DeleteFileUtil.delete(FILE_BASE_URL, key);
		return R.ok();
	}
	
	@ResponseBody
	@RequestMapping("/update_product")
	public ResultDTO updateProduct(HttpServletRequest request,@RequestBody Product product) {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		product.setLastAdminId(admin.getId());
		int i = productService.updateByPrimaryKeySelective(product);
		if (i == 1) {
			return ResultDTO.ok("修改成功");
		} else {
			return ResultDTO.error("修改失败");
		}
	}


	@ResponseBody
	@RequestMapping("/select_product")
	public R selectProduct(String id) {
        Product product = productService.selectByPrimaryKey(id);
        Category category = categoryService.selectByPrimaryKey(product.getCategoryId());
        Admin admin = adminService.selectByPrimaryKey(product.getLastAdminId());
        product.setLastAdminId(admin.getLogin());
        if (product != null) {
			return R.ok().put("app",product).put("cateType",category.getType());
		} else {
			return R.error("此商品不存在");
		}
	}
	
	@ResponseBody
	@RequestMapping("/product_open")
	public ResultDTO productOpen(HttpServletRequest request, String id) {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		Product product = productService.selectByPrimaryKey(id);
		product.setOpen(1);
		product.setLastAdminId(admin.getId());
		int i = productService.updateByPrimaryKey(product);
		if (i == 1) {
			return ResultDTO.ok();
		} else {
			return ResultDTO.error();
		}
	}
	
	
	@ResponseBody
	@RequestMapping("/product_down")
	public ResultDTO productDown(HttpServletRequest request, String id) {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		Product product = productService.selectByPrimaryKey(id);
		product.setOpen(2);
		product.setLastAdminId(admin.getId());
		int i = productService.updateByPrimaryKey(product);
		if (i == 1) {
			return ResultDTO.ok();
		} else {
			return ResultDTO.error();
		}
	}
	
	@ResponseBody
	@RequestMapping("/product_delete")
	public ResultDTO productDelete(String id) {
        PicExample picExample = new PicExample();
        PicExample.Criteria criteria = picExample.createCriteria();
        criteria.andProductIdEqualTo(id);
        List<Pic> list = picService.selectByExample(picExample);
//		String path = (String) request.getSession().getAttribute("path");
		String path = FILE_BASE_URL;
		for (Pic pic : list) {
			DeleteFileUtil.delete(path, pic.getPath());
			picService.deleteByPrimaryKey(pic.getId());
		}
		int i = productService.deleteByPrimaryKey(id);
		if (i == 1) {
			return ResultDTO.ok("删除成功");
		} else {
			return ResultDTO.error("删除失败");
		}
	}
}
