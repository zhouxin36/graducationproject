package com.zx.backstage.controller;

import com.zx.api.bean.Admin;
import com.zx.api.bean.Category;
import com.zx.api.bean.CategoryExample;
import com.zx.api.dto.R;
import com.zx.api.utils.MyUtils;
import com.zx.api.utils.PageUtils;
import com.zx.backstage.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	@ResponseBody
	@RequestMapping("/category_add")
	public R categoryAdd(HttpServletRequest request, @RequestBody Category category) {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		category.setAdminId(admin.getId());
		category.setId(MyUtils.getUUID());
		int i = categoryService.insert(category);
		List<Category> categories = categoryService.selectByExample(new CategoryExample());
		request.getSession().setAttribute("category", categories);
		if(i==1) {
			return R.ok("添加成功");
			
		}else {
			return R.error("添加失败");
		}
		
	}
	
	@ResponseBody
	@RequestMapping("/category_list")
	public R categoryList() {
		List<Category> list= categoryService.selectByExample(new CategoryExample());
		PageUtils pageUtil = new PageUtils(list, list.size(), list.size(), 1);
		if(list.size()!=0) {
			return R.ok().put("page",pageUtil);
		}else {
			return R.error();
		}
		
	}
	
	@ResponseBody
	@RequestMapping("/category_delete")
	public R categoryDelete(HttpServletRequest request, String id) {
		int  i= categoryService.deleteByPrimaryKey(id);
		List<Category> categories = categoryService.selectByExample(new CategoryExample());
		request.getSession().setAttribute("category", categories);
		if(i!=0) {
			return R.ok("删除成功");
			
		}else {
			return R.error("删除失败");
		}
		
	}
	
	@ResponseBody
	@RequestMapping("/category_update")
	public R categoryUpdate(HttpServletRequest request, @RequestBody Category category) {
		int i = categoryService.updateByPrimaryKey(category);
		List<Category> categories = categoryService.selectByExample(new CategoryExample());
		request.getSession().setAttribute("category", categories);
		if(i!=0) {
			return R.ok("更新成功");
			
		}else {
			return R.error("更新失败");
		}
		
	}
	
	@ResponseBody
	@RequestMapping("/select_category")
	public R selectcategory(String id) {
		Category category = categoryService.selectByPrimaryKey(id);
		if(category!=null) {
			return R.ok().put("app",category);
			
		}else {
			return R.error("查找失败");
		}
	}
}
