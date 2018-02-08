package com.springcloud.stage.controller;

import java.time.LocalDateTime;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.springcloud.stage.service.FootmarkService;
import com.zx.api.bean.Footmark;
import com.zx.api.bean.FootmarkExample;
import com.zx.api.dto.ResultDTO;
import com.zx.api.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/FootMark")
public class FootMarkController {

	@Autowired
	private FootmarkService service;


	private String getUserId(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String user_id = -100+"";
		if (session.getAttribute("user_id") != null)
			user_id = (String) session.getAttribute("user_id");
		return user_id;
	}
	@ResponseBody
	@RequestMapping("/checkTodayFootMark")
	public ResultDTO checkTodayFootMark(String productId, HttpServletRequest request) {
		int flag;
		FootmarkExample example = new FootmarkExample();
		FootmarkExample.Criteria criteria = example.createCriteria();
		criteria.andProductIdEqualTo(productId);
		criteria.andVisitTimeEqualTo(new Date());
		criteria.andUserIdEqualTo(getUserId(request));
		List<Footmark> list = service.selectByExample(example);
		if (list.size() == 0)
			flag = 0;
		else
			flag = 1;


		if(flag==0){
			return ResultDTO.ok();
		}else{
			return ResultDTO.error();
		}
	}

	@ResponseBody
	@RequestMapping("/saveFootMark")
	public ResultDTO saveFootMark(String productId,HttpServletRequest request) {
		Footmark footmark = new Footmark();
		footmark.setProductId(productId);
		footmark.setUserId(getUserId(request));
		footmark.setVisitTime(LocalDateTime.now());
		footmark.setId(MyUtils.getUUID());
		service.insert(footmark);
		return ResultDTO.ok();
	}

	@ResponseBody
	@RequestMapping("/getPassWeekFootMark")
	public ResultDTO getPassWeekFootMark(HttpServletRequest request){
		Date dNow = new Date();
		Date dBefore;
		Date d_seven;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dNow);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		dBefore = calendar.getTime();
		calendar.add(Calendar.DAY_OF_MONTH, -7);
		d_seven = calendar.getTime();
        String userId = getUserId(request);
        FootmarkExample example = new FootmarkExample();
        FootmarkExample.Criteria criteria = example.createCriteria();
        criteria.andVisitTimeGreaterThanOrEqualTo(d_seven);
        criteria.andVisitTimeLessThanOrEqualTo(dBefore);
        if (userId != null) {
            criteria.andUserIdEqualTo(userId);
        }
        List<Footmark> list = service.selectByExample(example);
		List<Footmark> list2 = new ArrayList<Footmark>();
		if(list.size()!=0){
			for(int i = 0 ;i < 4 && i< list.size();i++)
				list2.add(list.get(i));
            Map<String,Object> map = new HashMap<>();
			map.put("list", list2);
			return ResultDTO.buildSuccessData(map);

		}
		return ResultDTO.error();
	}


	@ResponseBody
	@RequestMapping("/getTodayFootMark")
	public ResultDTO getTodayFootMark(HttpServletRequest request) {
        FootmarkExample example = new FootmarkExample();
        FootmarkExample.Criteria criteria = example.createCriteria();
        criteria.andVisitTimeGreaterThanOrEqualTo(new Date());
        criteria.andVisitTimeLessThanOrEqualTo(new Date());
        String userId = getUserId(request);
        if (userId != null) {
            criteria.andUserIdEqualTo(userId);
        }
        List<Footmark> list = service.selectByExample(example);
		List<Footmark> list2 = new ArrayList<Footmark>();
		if(list.size()!=0){
			for(int i = 0 ;i < 4 && i< list.size();i++)
				list2.add(list.get(i));
            Map<String,Object> map = new HashMap<>();
			map.put("list", list2);
			return ResultDTO.buildSuccessData(map);

		}
		return ResultDTO.error();
	}

}
