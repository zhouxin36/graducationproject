package com.zx.backstage.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.zx.api.bean.*;
import com.zx.api.dto.ResultDTO;
import com.zx.api.utils.*;
import com.zx.backstage.service.CategoryService;
import com.zx.backstage.service.PicService;
import com.zx.backstage.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ProductController {

	@Autowired
	PicService picService;

	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;

	@ResponseBody
	@RequestMapping("/product_list")
	public ResultDTO<Map<String, PageUtils>> productList(HttpServletRequest request, @RequestParam Map<String, Object> params) {
		request.getSession().setAttribute("category", categoryService.selectByExample(new CategoryExample()));
        Query query = new Query(params);
        ProductExample productExample = new ProductExample();
        productExample.setStartRow((Integer) query.get("offset"));
        productExample.setPageSize((Integer) query.get("limit"));
        ProductExample.Criteria criteria = productExample.createCriteria();
        if(!MyUtils.isBlank((String) query.get("name")))
            criteria.andNameEqualTo((String) query.get("name"));
        List<Product> products = productService.selectByExample(productExample);
        long total = productService.countByExample(productExample);
        PageUtils pageUtil = new PageUtils(products, (int)total, query.getLimit(), query.getPage());
		Map<String, PageUtils> map = new HashMap<>();
		map.put("page",pageUtil);
        return ResultDTO.buildSuccessData(map);
	}

	@ResponseBody
	@RequestMapping("/product_search")
	public ResultDTO productSearch(HttpServletRequest request, String str) {
		request.getSession().setAttribute("search", str);
		return ResultDTO.ok();
	}

	@ResponseBody
	@RequestMapping("/product_select_category")
	public ResultDTO<Map<String, List<Category>>> productSelectCategory(HttpServletRequest request) {
		@SuppressWarnings("unchecked")
		List<Category> list = (List<Category>) request.getSession().getAttribute("category");
		if (list != null) {
			Map<String, List<Category>> map = new HashMap<>();
			map.put("list", list);
			return ResultDTO.ok().buildSuccessData(map);
		} else {
			return ResultDTO.error();
		}
	}

	@ResponseBody
	@RequestMapping("/product_add")
	public ResultDTO productAdd(HttpServletRequest request, Product product)
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
			return ResultDTO.ok();

		} else {
			return ResultDTO.error();
		}
	}

	@ResponseBody
	@RequestMapping("/request_img")
	public ResultDTO<Map<String,Object>> requestImg(HttpServletRequest request, String id) {
//		String path = (String) request.getSession().getAttribute("path2");
		String path = ImgUrlUtils.img_x;
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

	@ResponseBody
	@RequestMapping("/update_img")
	public ResultDTO updateImg(HttpServletRequest request,
			@RequestParam(value = "file1", required = false) MultipartFile file1,
			@RequestParam(value = "file2", required = false) MultipartFile file2,
			@RequestParam(value = "file3", required = false) MultipartFile file3, String id)
			throws IllegalStateException, IOException {
//		String path = (String) request.getSession().getAttribute("path");
		String path = ImgUrlUtils.img_j;
		if (!file1.isEmpty()) {
			int random = (int) (Math.random() * 10000);
			String newFileName = System.currentTimeMillis() + random + ".jpg";
			File dir = new File(path, newFileName);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			file1.transferTo(dir);
			PicExample picExample = new PicExample();
            PicExample.Criteria criteria1 = picExample.createCriteria();
            criteria1.andProductIdEqualTo(id);
            criteria1.andTypeEqualTo(1);
            List<Pic> list = picService.selectByExample(picExample);
			if (list.size() != 0) {
				DeleteFileUtil.delete(path, list.get(0).getPath());
				picExample = new PicExample();
                PicExample.Criteria criteria = picExample.createCriteria();
                criteria.andTypeEqualTo(1);
                criteria.andProductIdEqualTo(id);
                picService.deleteByExample(picExample);
			}
			Pic pic = new Pic();
			pic.setType(1);
			pic.setProductId(id);
			pic.setPath(newFileName);
            pic.setId(MyUtils.getUUID());
			picService.insert(pic);
		}
		if (!file2.isEmpty()) {
			int random = (int) (Math.random() * 10000);
			String newFileName = System.currentTimeMillis() + random + ".jpg";
			File dir = new File(path, newFileName);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			file2.transferTo(dir);
			Pic pic = new Pic();
			pic.setType(2);
			pic.setProductId(id);
			pic.setPath(newFileName);
            pic.setId(MyUtils.getUUID());
			picService.insert(pic);
		}
		if (!file3.isEmpty()) {
			int random = (int) (Math.random() * 10000);
			String newFileName = System.currentTimeMillis() + random + ".jpg";
			File dir = new File(path, newFileName);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			file3.transferTo(dir);
			Pic pic = new Pic();
			pic.setType(3);
			pic.setProductId(id);
			pic.setPath(newFileName);
			pic.setId(MyUtils.getUUID());
			picService.insert(pic);
		}
		ResultDTO msg = ResultDTO.ok();
		return msg;
	}

	@ResponseBody
	@RequestMapping("/find_img")
	public ResultDTO<Map<String,Object>> findImg(HttpServletRequest request, String id) {
//		String path = (String) request.getSession().getAttribute("path2");
		String path = ImgUrlUtils.img_x;
		PicExample picExample = new PicExample();
        PicExample.Criteria criteria = picExample.createCriteria();
        criteria.andTypeEqualTo(1);
        criteria.andProductIdEqualTo(id);
        List<Pic> list = picService.selectByExample(picExample);
		if (list.size() != 0) {
            ResultDTO<Map<String,Object>> objectResultDTO = new ResultDTO<>();
            Map<String,Object> map = new HashMap<>();
            map.put("list", list);
            map.put("p", path);
            objectResultDTO.setOK();
            objectResultDTO.setData(map);
            return objectResultDTO;
		} else {
			Map<String,Object> map = new HashMap<>();
			map.put("p", "../static/img/");
			return ResultDTO.error().buildSuccessData(map);
		}
	}

	@ResponseBody
	@RequestMapping("/delete_pic")
	public ResultDTO<Map<String, String>> deletePic(HttpServletRequest request, String id) {
//		String path = (String) request.getSession().getAttribute("path");
		String path = ImgUrlUtils.img_j;
		Pic pic = picService.selectByPrimaryKey(id);
		DeleteFileUtil.delete(path, pic.getPath());
		int i = picService.deleteByPrimaryKey(id);
        Map<String, String> map = new HashMap<>();
        map.put("p", path);
		if (i == 1) {
			return ResultDTO.buildSuccessData(map);
		} else {
            ResultDTO error = ResultDTO.error();
            error.setData(map);
            return error;
		}
	}
	
	@ResponseBody
	@RequestMapping("/update_product")
	public ResultDTO updateProduct(HttpServletRequest request, Product product) {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		product.setLastAdminId(admin.getId());
		int i = productService.updateByPrimaryKey(product);
		if (i == 1) {
			return ResultDTO.ok();
		} else {
			return ResultDTO.error();
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
		String path = ImgUrlUtils.img_j;
		for (Pic pic : list) {
			DeleteFileUtil.delete(path, pic.getPath());
			picService.deleteByPrimaryKey(pic.getId());
		}
		int i = productService.deleteByPrimaryKey(id);
		if (i == 1) {
			return ResultDTO.ok();
		} else {
			return ResultDTO.error();
		}
	}
}
