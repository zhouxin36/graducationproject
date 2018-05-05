package com.springcloud.stage.controller;

import com.springcloud.stage.service.MessageService;
import com.springcloud.stage.service.RefundService;
import com.zx.api.bean.Message;
import com.zx.api.bean.MessageExample;
import com.zx.api.bean.Refund;
import com.zx.api.bean.RefundExample;
import com.zx.api.dto.ResultDTO;
import com.zx.api.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RequestMapping("/Message")
@Controller
public class MessageController {

	@Autowired
	 private MessageService messageService;

	private String getUserId(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String user_id = -100+"";
		if (session.getAttribute("user_id") != null)
			user_id = (String) session.getAttribute("user_id");
		return user_id;
	}
	
	@ResponseBody
	@RequestMapping("/messageList")
	public ResultDTO messageList(Refund refund, HttpServletRequest request) {
		MessageExample messageExample = new MessageExample();
		MessageExample.Criteria criteria1 = messageExample.createCriteria();
		criteria1.andUserIdEqualTo(getUserId(request));
		List<Message> messages = messageService.selectByExample(messageExample);
		return ResultDTO.buildSuccessData(messages);
	}

    @ResponseBody
    @RequestMapping("/messageDelete")
    public ResultDTO messageDelete(String id, HttpServletRequest request) {
	    messageService.deleteByPrimaryKey(id);
        return ResultDTO.ok();
    }
	

}
