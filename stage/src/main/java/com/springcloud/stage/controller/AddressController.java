package com.springcloud.stage.controller;

import com.springcloud.stage.service.UserAddressService;
import com.zx.api.bean.EntityAndExample;
import com.zx.api.bean.User;
import com.zx.api.bean.UserAddress;
import com.zx.api.bean.UserAddressExample;
import com.zx.api.dto.ResultDTO;
import com.zx.api.utils.MyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
@Controller
@RequestMapping("/Address")
public class AddressController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserAddressService service;

	private String getUserId(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String user_id = -100+"";
		if (session.getAttribute("user_id") != null)
			user_id = (String) session.getAttribute("user_id");
		return user_id;
	}

	@ResponseBody
	@RequestMapping("/updateAddressById")
	public ResultDTO updateAddressById(UserAddress address) {
		if(MyUtils.isEquals(address.getAddress(),"--") || MyUtils.isEquals(address.getAddress(),"")){
		    address.setAddress(null);
        }
		int flag = service.updateByPrimaryKeySelective(address);
		if (flag == 0) {
			return ResultDTO.error();
		}
		return ResultDTO.ok();
	}

	@ResponseBody
	@RequestMapping("/insertAddress")
	public ResultDTO insertAddress(UserAddress address, HttpServletRequest request) {
		address.setId(MyUtils.getUUID());
		address.setSelected(0);
		address.setAddDate(LocalDateTime.now());
		address.setUserId(getUserId(request));
		int flag = service.insert(address);
		if (flag == 0) {
			return ResultDTO.error();
		}
		return ResultDTO.ok();
	}

	@ResponseBody
	@RequestMapping("/SelectAddressByAddressId")
	public ResultDTO SelectAddressByAddressId(String id) {
		UserAddress address = service.selectByPrimaryKey(id);
		Map<String,Object> map = new HashMap<>();
		if (address != null){
			map.put("address", address);
			return ResultDTO.buildSuccessData(map);
		}
		return ResultDTO.error();
	}

	@ResponseBody
	@RequestMapping("/deleteAddressById")
	public ResultDTO deleteAddressById(String id) {
		int flag = service.deleteByPrimaryKey(id);
		if (flag == 0) {
			return ResultDTO.error();
		}
		return ResultDTO.ok();
	}

	@ResponseBody
	@RequestMapping("/selectAddressById")
	public ResultDTO selectAddressById(HttpServletRequest request) {

		User user = new User();
		user.setId(getUserId(request));
		UserAddressExample userAddressExample = new UserAddressExample();
        UserAddressExample.Criteria criteria = userAddressExample.createCriteria();
        criteria.andUserIdEqualTo(user.getId());
        List<UserAddress> list = service.selectByExample(userAddressExample);
		if (list.size() == 0) {
			return ResultDTO.error();
		} else {
            Map<String,Object> map = new HashMap<>();
            map.put("Address", list);
			return ResultDTO.buildSuccessData(map);
		}
	}

	@ResponseBody
	@RequestMapping("/setDefaultAddress")
	public ResultDTO setDefaultAddress(String id, HttpServletRequest request) {
		User user = new User();
		user.setId(getUserId(request));
		UserAddressExample example=new UserAddressExample();
        UserAddressExample.Criteria criteria=example.createCriteria();
        criteria.andUserIdEqualTo(user.getId());
        UserAddress a=new UserAddress();
        a.setSelected(0);
        EntityAndExample entityAndExample = new EntityAndExample();
        entityAndExample.setExample(example);
        entityAndExample.setEntity(a);
        int count= service.updateByExampleSelective(entityAndExample);
        a.setId(id);
        a.setSelected(1);
        int i = service.updateByPrimaryKeySelective(a);
        if(i == 1) {
			return ResultDTO.ok();
		}
		return ResultDTO.error();
	}
}
