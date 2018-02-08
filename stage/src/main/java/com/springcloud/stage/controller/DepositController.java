package com.springcloud.stage.controller;

import com.springcloud.stage.service.DepositService;
import com.springcloud.stage.service.UserService;
import com.zx.api.bean.Deposit;
import com.zx.api.bean.User;
import com.zx.api.dto.ResultDTO;
import com.zx.api.utils.MyUtils;
import com.zx.api.utils.PaymentUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;


@RequestMapping("deposit")
@Controller
public class DepositController {
	
	@Autowired
	DepositService depositService;
	
	@Autowired
	UserService userService;
	/**
	 *
	 * @throws IOException
	 */
	@RequestMapping("payOrder")
	public String payOrder(String pd_FrpIds, HttpServletResponse response) throws IOException {
		String p0_Cmd = "Buy";
		String p1_MerId = "10001126856";
		String p2_Order = MyUtils.getUUID();
		String p3_Amt = "0.01";
		String p4_Cur = "CNY";
		String p5_Pid = "";
		String p6_Pcat = "";
		String p7_Pdesc = "";
		String p8_Url = "http://localhost:8080/ECC/deposit/callBack";
		String p9_SAF = "";
		String pa_MP = "";
		String pd_FrpId = pd_FrpIds;
		String pr_NeedResponse = "1";
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc,
				p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue); // hmac

		// 鍚戞槗瀹濆彂閫佽姹�:
		StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		sb.append("p0_Cmd=").append(p0_Cmd).append("&");
		sb.append("p1_MerId=").append(p1_MerId).append("&");
		sb.append("p2_Order=").append(p2_Order).append("&");
		sb.append("p3_Amt=").append(p3_Amt).append("&");
		sb.append("p4_Cur=").append(p4_Cur).append("&");
		sb.append("p5_Pid=").append(p5_Pid).append("&");
		sb.append("p6_Pcat=").append(p6_Pcat).append("&");
		sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		sb.append("p8_Url=").append(p8_Url).append("&");
		sb.append("p9_SAF=").append(p9_SAF).append("&");
		sb.append("pa_MP=").append(pa_MP).append("&");
		sb.append("pd_FrpId=").append(pd_FrpId).append("&");
		sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		sb.append("hmac=").append(hmac);

		response.sendRedirect(sb.toString());
		return null;
	}

	@RequestMapping("callBack")
	public String callBack(HttpSession session) {
		Deposit deposit=new Deposit();
		String user_id =(String) session.getAttribute("user_id");
		deposit.setUserId(user_id);
		deposit.setIssuccess(1);
		deposit.setRechargeDate(LocalDateTime.now());
		deposit.setRechargeMoney(new BigDecimal(100000));
		deposit.setId(MyUtils.getUUID());
		depositService.insert(deposit);
		User user=userService.selectByPrimaryKey(user_id);
		System.out.println(user);
		user.setAccountBalance(user.getAccountBalance().add(new BigDecimal(100000)));
		userService.updateByPrimaryKeySelective(user);
		return "success";
	}
}
