package com.zx.backstage.controller;

import com.zx.api.bean.User;
import com.zx.api.bean.UserExample;
import com.zx.api.dto.R;
import com.zx.api.dto.ResultDTO;
import com.zx.api.utils.MyUtils;
import com.zx.api.utils.PageUtils;
import com.zx.api.utils.Query;
import com.zx.backstage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class UserController {

	@Autowired
	UserService userService;

	@ResponseBody
	@RequestMapping("/user_list")
	public R userList(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		UserExample userExample = new UserExample();
		userExample.setStartRow((Integer) query.get("offset"));
		userExample.setPageSize((Integer) query.get("limit"));
        UserExample.Criteria criteria =  userExample.createCriteria();
        if(!MyUtils.isBlank((String )query.get("name")))
       		criteria.andNameLike("%"+(String )query.get("name")+"%");
        List<User> users = userService.selectByExample(userExample);
        long total = userService.countByExample(userExample);
        PageUtils pageUtil = new PageUtils(users, (int)total, query.getLimit(), query.getPage());
		Map<String, PageUtils> map = new HashMap<>();
        return R.ok().put("page",pageUtil);
	}

	@ResponseBody
	@RequestMapping("user_disable")
	public ResultDTO userDisable(String id) {
		User user = userService.selectByPrimaryKey(id);
		user.setIsabled(0);
		int i = userService.updateByPrimaryKey(user);
		if (i == 1)
			return ResultDTO.ok();
		else
			return ResultDTO.error();
	}
	
	@ResponseBody
	@RequestMapping("user_able")
	public ResultDTO userAble(String id) {
		User user = userService.selectByPrimaryKey(id);
		user.setIsabled(1);
		int i = userService.updateByPrimaryKey(user);
		if (i == 1)
			return ResultDTO.ok();
		else
			return ResultDTO.error();
	}
	
	@ResponseBody
	@RequestMapping("user_delete")
	public ResultDTO userDelete(String id) {
		int i = userService.deleteByPrimaryKey(id);
		if (i == 1)
			return ResultDTO.ok();
		else
			return ResultDTO.error();
	}
	
	@ResponseBody
	@RequestMapping("find_userName")
	public ResultDTO<Map<String, String>> userNamebyId(String id) {
		User user = userService.selectByPrimaryKey(id);
		if(user != null) {
			Map<String, String> map = new HashMap<>();
			map.put("username", user.getName());
			return ResultDTO.buildSuccessData(map);
		}else {
			return ResultDTO.error();
		}
	}

    @ResponseBody
    @RequestMapping("user/{id}/query")
    public R querybyId(@PathVariable String id) {
        User user = userService.selectByPrimaryKey(id);
        if(user == null) {
            return R.error("user为空");
        }else {
            return R.ok().put("app",user);
        }
    }

    @ResponseBody
    @RequestMapping("update_password")
    public R updatePassword(@RequestBody User user) {
	    user.setPassword(MyUtils.md5Passwrod(user.getPassword()));
	    userService.updateByPrimaryKeySelective(user);
        if(user == null) {
            return R.error("修改密码失败");
        }else {
            return R.ok("修改成功");
        }
    }
}
