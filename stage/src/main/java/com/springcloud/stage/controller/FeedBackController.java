package com.springcloud.stage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.springcloud.stage.service.FeedbackService;
import com.zx.api.bean.Feedback;
import com.zx.api.dto.ResultDTO;
import com.zx.api.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/FeedBack")
public class FeedBackController {

	@Autowired
	private FeedbackService service ;

	private String getUserId(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String user_id = -100+"";
		if (session.getAttribute("user_id") != null)
			user_id = (String) session.getAttribute("user_id");
		return user_id;
	}


	@ResponseBody
	@RequestMapping("/SubmitFeedBack")
	public ResultDTO SubmitFeedBack(Feedback feedback, HttpServletRequest request) {
		feedback.setUserId(getUserId(request));
		feedback.setId(MyUtils.getUUID());
		service.insert(feedback);
		return ResultDTO.ok();
	}
	
}
