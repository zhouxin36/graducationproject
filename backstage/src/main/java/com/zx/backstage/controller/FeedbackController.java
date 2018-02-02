package com.zx.backstage.controller;

import com.zx.api.bean.Feedback;
import com.zx.api.bean.FeedbackExample;
import com.zx.api.dto.R;
import com.zx.api.dto.ResultDTO;
import com.zx.api.utils.PageUtils;
import com.zx.api.utils.Query;
import com.zx.backstage.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class FeedbackController {

	@Autowired
	FeedbackService feedbackService;
	
	@RequestMapping("/feedback_request")
	@ResponseBody
	public R feedback_request(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        FeedbackExample feedbackExample = new FeedbackExample();
        feedbackExample.setStartRow((Integer) query.get("offset"));
        feedbackExample.setPageSize((Integer) query.get("limit"));
        List<Feedback> feedbacks = feedbackService.selectByExample(feedbackExample);
        long total = feedbackService.countByExample(feedbackExample);
        PageUtils pageUtil = new PageUtils(feedbacks, (int)total, query.getLimit(), query.getPage());
		Map<String, PageUtils> map = new HashMap<>();
		map.put("page",pageUtil);
		return R.ok().put("page",pageUtil);
	}
	
	@RequestMapping("/feedback_delete")
	@ResponseBody
	public ResultDTO feedback_delete(String id) {
		int i = feedbackService.deleteByPrimaryKey(id);
		if(i==0)
			return ResultDTO.error("删除失败");
		else
			return ResultDTO.ok("删除成功");
	}
}
