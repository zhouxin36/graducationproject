package com.zx.backstage.controller;


		import com.zx.api.bean.*;
		import com.zx.api.dto.ResultDTO;
		import com.zx.backstage.service.ForderService;
		import com.zx.backstage.service.RefundService;
		import com.zx.backstage.service.SorderService;
		import com.zx.backstage.service.UserService;
		import org.slf4j.Logger;
		import org.slf4j.LoggerFactory;
		import org.springframework.beans.factory.annotation.Autowired;
		import org.springframework.stereotype.Controller;
		import org.springframework.web.bind.annotation.RequestMapping;
		import org.springframework.web.bind.annotation.ResponseBody;

		import java.util.HashMap;
		import java.util.List;
		import java.util.Map;


@Controller
public class RefundController {

	private Logger logger = LoggerFactory.getLogger(RefundController.class);
	@Autowired
	UserService userService;

	@Autowired
	RefundService refundService;

	@Autowired
	ForderService forderService;

	@Autowired
	SorderService sorderService;

	@RequestMapping("/refund_request")
	@ResponseBody
	public ResultDTO<Map<String,List<Refund>>> refund_request() {
        Map<String, List<Refund>> map = new HashMap<>();
        try {
            logger.info("---->ProductController/refund_request");
            List<Refund> list = refundService.selectByExample(new RefundExample());
            if (list.size() == 0) {
                return ResultDTO.error();
            } else {
                map.put("list", list);
                return ResultDTO.ok().buildSuccessData(map);
            }
        }catch (Exception e){
            logger.error("RefundController/refund_request; e:{},map:{}",e.getMessage(),map);
            return ResultDTO.error("系统错误，请联系管理员！");

        }
	}

	@RequestMapping("/refund")
	@ResponseBody
	public ResultDTO refund(String id) {
	    try {
            logger.info("---->ProductController/refund_request,id:{}",id);
            Refund refund = refundService.selectByPrimaryKey(id);
            Forder forder = forderService.selectByPrimaryKey(refund.getForderid());

            SorderExample sorderExample = new SorderExample();
            SorderExample.Criteria criteria = sorderExample.createCriteria();
            criteria.andForderIdEqualTo(forder.getId());
            List<Sorder> list = sorderService.selectByExample(sorderExample);

            for (Sorder s : list) {
                sorderService.deleteByPrimaryKey(s.getId());
            }
            forderService.deleteByPrimaryKey(forder.getId());
            refundService.deleteByPrimaryKey(refund.getId());
            User user = userService.selectByPrimaryKey(refund.getUserid());
            user.setAccountBalance(user.getAccountBalance().add(forder.getTotal()));
            userService.updateByPrimaryKey(user);
            return ResultDTO.ok();
        }catch (Exception e){
            logger.error("RefundController/refund; e:{},id:{}",e.getMessage(),id);
            return ResultDTO.error("系统错误，请联系管理员！");
        }
	}
}
