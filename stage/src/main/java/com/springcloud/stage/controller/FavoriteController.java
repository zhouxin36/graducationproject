package com.springcloud.stage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.springcloud.stage.service.FavoriteService;
import com.springcloud.stage.service.ProductService;
import com.zx.api.bean.Favorite;
import com.zx.api.bean.FavoriteExample;
import com.zx.api.bean.Product;
import com.zx.api.dto.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/Favorite")
public class FavoriteController {
	@Autowired
	FavoriteService service;

	@Autowired
	ProductService productService;

	private String getUserId(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String user_id = -100+"";
		if (session.getAttribute("user_id") != null)
			user_id = (String) session.getAttribute("user_id");
		return user_id;
	}

	@RequestMapping("/getMyFavorite")
	@ResponseBody
	public ResultDTO getMyFavorite(HttpServletRequest request) {
        FavoriteExample example=new FavoriteExample();
        FavoriteExample.Criteria criteria=example.createCriteria();
        criteria.andUserIdEqualTo(getUserId(request));
        List<Favorite> list = service.selectByExample(example);
        if (list.size()==0)
			return ResultDTO.error();
		List<Product> productList =new ArrayList<>();
		for(Favorite favorite : list){
			Product p = productService.selectByPrimaryKey(favorite.getProductId());
			if(p!=null)
				productList.add(p);
		}
        Map<String,Object> map = new HashMap<>();
		map.put("list", productList);
		return ResultDTO.buildSuccessData(map);
	}
}
