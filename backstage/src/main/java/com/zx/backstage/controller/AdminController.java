package com.zx.backstage.controller;

import com.zx.api.bean.Admin;
import com.zx.api.bean.AdminExample;
import com.zx.api.dto.R;
import com.zx.api.dto.ResultDTO;
import com.zx.api.utils.MyUtils;
import com.zx.api.utils.PageUtils;
import com.zx.api.utils.Query;
import com.zx.backstage.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class AdminController {

	@Autowired
	AdminService adminService;

	@ResponseBody
	@RequestMapping("/exit")
	public ResultDTO exit(HttpServletRequest request) {
		request.getSession().invalidate();
		return ResultDTO.ok();

	}

	@ResponseBody
	@RequestMapping(value = "/check_login",method = RequestMethod.POST)
	public ResultDTO<Map<String,Admin>> checkLogin(HttpServletRequest request) {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if (admin == null) {
			return ResultDTO.error("请登录");
		} else {
            Map<String, Admin> map = new HashMap<>();
            map.put("admin", admin);
            return ResultDTO.buildSuccessData(map);

		}

	}

    @ResponseBody
    @RequestMapping(value = "/admin_query",method = RequestMethod.GET)
    public R queryAdmin(HttpServletRequest request,String id) {
        Admin admin = adminService.selectByPrimaryKey(id);
        if (admin == null) {
            return R.error("次账户不存在");
        } else {
            return R.ok().put("app",admin);

        }

    }

	@ResponseBody
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public ResultDTO login(HttpServletRequest request, Admin admin) {
	    String password = MyUtils.md5Passwrod(admin.getPassword());
		List<Admin> list = adminService.login(admin.getLogin(), password);
		if (list.size() == 0) {
			return ResultDTO.error();
		} else {
		    LocalDateTime localDateTime = list.get(0).getLastTime();
			list.get(0).setLastIp(request.getRemoteAddr());
            list.get(0).setLastTime(LocalDateTime.now());
            adminService.updateByPrimaryKey(list.get(0));
            list.get(0).setLastTime(localDateTime);
            request.getSession().setAttribute("admin", list.get(0));
            return ResultDTO.ok();
		}

	}

	@ResponseBody
	@RequestMapping("/admin_list")
	public R adminList(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        AdminExample adminExample = new AdminExample();
        AdminExample.Criteria criteria = adminExample.createCriteria();
        if(!MyUtils.isBlank((String )query.get("name")))
            criteria.andLoginLike("%"+(String )query.get("name")+"%");
		List<Admin> list = adminService.selectByExample(adminExample);
		PageUtils pageUtil = new PageUtils(list, list.size(), list.size(), 1);
		if (list.size() != 0) {
            return R.ok().put("page",pageUtil);
        }
		else
			return R.error("列表为空");

	}

	@ResponseBody
	@RequestMapping("/admin_add")
	public ResultDTO adminAdd(@RequestBody Admin admin) {
		admin.setRegTime(LocalDateTime.now());
		admin.setId(MyUtils.getUUID());
		admin.setPassword(MyUtils.md5Passwrod(admin.getPassword()));
		int i = adminService.insert(admin);
		if (i != 0)
			return ResultDTO.ok("添加成功");
		else
			return ResultDTO.error("添加失败");

	}

	@ResponseBody
	@RequestMapping("/admin_delete")
	public ResultDTO adminDelete(String id) {
		int i = adminService.deleteByPrimaryKey(id);
		if (i != 0)
			return ResultDTO.ok("删除成功");
		else
			return ResultDTO.error("删除失败");

	}

	@ResponseBody
	@RequestMapping("/update_admin")
	public ResultDTO updateAdminPassword(@RequestBody Admin admin) {
		admin.setPassword(MyUtils.md5Passwrod(admin.getPassword()));
		int i = adminService.updateByPrimaryKeySelective(admin);
		if (i != 0)
			return ResultDTO.ok("更新成功");
		else
			return ResultDTO.error("更新失败");

	}
}
