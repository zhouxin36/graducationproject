package com.springcloud.stage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.springcloud.stage.service.CouponService;
import com.zx.api.bean.Coupon;
import com.zx.api.bean.CouponExample;
import com.zx.api.dto.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/Coupon")
public class CouponController {

	@Autowired
	CouponService service;

	private String getUserId(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String user_id = -100+"";
		if (session.getAttribute("user_id") != null)
			user_id = (String) session.getAttribute("user_id");
		return user_id;
	}
	
	
	@RequestMapping("/getMyCoupon")
	@ResponseBody
	public ResultDTO getMyCoupon(@RequestParam(value="status",defaultValue="0")Integer status, HttpServletRequest request) {
        CouponExample example=new CouponExample();
        CouponExample.Criteria criteria=example.createCriteria();
        criteria.andUserIdEqualTo(getUserId(request));
        criteria.andStatusEqualTo(status);
        List<Coupon> list = service.selectByExample(example);
        Map<String,Object> map = new HashMap<>();
		map.put("list", list);
		if(list.size()==0)
			return ResultDTO.error();
		return ResultDTO.buildSuccessData(map);
	}
	
}
