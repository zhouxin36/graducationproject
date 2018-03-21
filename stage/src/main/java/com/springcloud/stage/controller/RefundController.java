package com.springcloud.stage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.springcloud.stage.service.RefundService;
import com.zx.api.bean.Refund;
import com.zx.api.bean.RefundExample;
import com.zx.api.dto.ResultDTO;
import com.zx.api.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@RequestMapping("/Refund")
@Controller
public class RefundController {

	@Autowired
	 private RefundService refundService;

	private String getUserId(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String user_id = -100+"";
		if (session.getAttribute("user_id") != null)
			user_id = (String) session.getAttribute("user_id");
		return user_id;
	}
	
	@ResponseBody
	@RequestMapping("/saveRefund")
	public ResultDTO saveRefund(Refund refund, HttpServletRequest request) {
		if(refund==null)
			return ResultDTO.error();
		refund.setUserid(getUserId(request));
		refund.setId(MyUtils.getUUID());
		RefundExample example = new RefundExample();
		RefundExample.Criteria criteria = example.createCriteria();
		criteria.andUseridEqualTo(refund.getUserid());
		criteria.andForderidEqualTo(refund.getForderid());
		List<Refund> list = refundService.selectByExample(example);

		if(list.size()!=0 && list.get(0)!=null){
			return ResultDTO.error();
		}
		refundService.insert(refund);
		return ResultDTO.ok();
	}
	
	@ResponseBody
    @RequestMapping("/userGetRefund")
    public ResultDTO userGetRefund(HttpServletRequest request) {
        RefundExample refundExample = new RefundExample();
        RefundExample.Criteria criteria = refundExample.createCriteria();
        criteria.andUseridEqualTo(getUserId(request));
        List<Refund> list = refundService.selectByExample(refundExample);
        if(list.size()==0)
            return ResultDTO.error();
        Map<String,Object> map = new HashMap<>();
        map.put("list", list);
        return ResultDTO.buildSuccessData(map);
    }

}
