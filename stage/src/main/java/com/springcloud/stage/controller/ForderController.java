package com.springcloud.stage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.springcloud.stage.service.ForderService;
import com.springcloud.stage.service.SorderService;
import com.springcloud.stage.service.UserAddressService;
import com.springcloud.stage.service.UserService;
import com.zx.api.bean.*;
import com.zx.api.dto.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/Forder")
public class ForderController {

	@Autowired
	ForderService forderService;


	@Autowired
	UserAddressService userAddressService;

	@Autowired
	SorderService sorderService;

	@Autowired
	UserService userService;

	
	@ResponseBody
	@RequestMapping("/request_forder")
	public ResultDTO requestForder(String id) {
		Forder forder = forderService.selectByPrimaryKey(id);
        UserAddress userAddress = null;
		if(forder.getAddressId() != null) {
            userAddress = userAddressService.selectByPrimaryKey(forder.getAddressId());
        }
		SorderExample sorderExample= new SorderExample();
        SorderExample.Criteria criteria = sorderExample.createCriteria();
        criteria.andForderIdEqualTo(forder.getId());
        List<Sorder> listSorder = sorderService.selectByExample(sorderExample);
		User user = userService.selectByPrimaryKey(forder.getUserId());
		Map<String,Object> map = new HashMap<>();
		map.put("forder", forder);
		map.put("userAddress", userAddress);
		map.put("listSorder", listSorder);
		map.put("user", user);
		return ResultDTO.buildSuccessData(map);
	}
	
	@ResponseBody
	@RequestMapping("/updatenum")
	public ResultDTO updatenum(String id,Integer num) {
		Sorder sorder = sorderService.selectByPrimaryKey(id);
		sorder.setNumber(num);
		sorderService.updateByPrimaryKeySelective(sorder);
		return ResultDTO.ok();
	}
	 
}
