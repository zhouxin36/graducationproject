package com.springcloud.stage.controller;

import com.alipay.api.internal.util.AlipaySignature;
import com.springcloud.stage.config.AlipayConfig;
import com.springcloud.stage.service.*;
import com.zx.api.bean.*;
import com.zx.api.dto.ResultDTO;
import com.zx.api.utils.MyUtils;
import com.zx.api.utils.PaymentUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;


@RequestMapping("deposit")
@Controller
public class DepositController {
	Logger logger = LoggerFactory.getLogger(DepositController.class);
	@Autowired
	DepositService depositService;
    @Autowired
    UserAddressService addressService;
    @Autowired
    ForderService forderService;
    @Autowired
    SorderService sorderService;
    @Autowired
    CouponService couponService;

	
	@Autowired
	UserService userService;

	@Value("${bank_url}")
	String BANK_URL;

    @Value("${notify_url}")
    String NOTIFY_URL;

    @Value("${return_url}")
    String RETURN_URL;

	@Autowired
	AlipayService alipayService;

    private String getUserId(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String user_id = -100+"";
        if (session.getAttribute("user_id") != null)
            user_id = (String) session.getAttribute("user_id");
        return user_id;
    }
	/**
	 *
	 * @throws IOException
	 */
	@RequestMapping("payOrder")
	public String payOrder(String pd_FrpIds, Integer amount, HttpServletResponse response, HttpServletRequest request) throws IOException {
	    if(MyUtils.isEquals(pd_FrpIds,"zhifubao")){
	        try {
                alipayService.pay(NOTIFY_URL,getUserId(request)+"_"+ Math.random() * (Math.pow(10, 10)), amount.toString(), "Recharge", "", request, response);
            }catch (Exception e){
                logger.error("DepositController/payOrder is error:pd_FrpIds:{},e:{}",pd_FrpIds,e.getStackTrace());
            }
        }else {
            String p0_Cmd = "Buy";
            String p1_MerId = "10001126856";
            String p2_Order = MyUtils.getUUID();
            String p3_Amt = "0.01";
            String p4_Cur = "CNY";
            String p5_Pid = "";
            String p6_Pcat = "";
            String p7_Pdesc = "";
            String p8_Url = BANK_URL;
            String p9_SAF = "";
            String pa_MP = "";
            String pd_FrpId = pd_FrpIds;
            String pr_NeedResponse = "1";
            String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
            String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc,
                    p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue); // hmac

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
        }
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
		user.setAccountBalance(user.getAccountBalance().add(new BigDecimal(100000)));
		userService.updateByPrimaryKeySelective(user);
		return "success";
	}

    @GetMapping("/return_url")
    public String return_url(HttpServletRequest request, HttpServletResponse response)throws Exception{
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);
        //调用SDK验证签名

        PrintWriter out = response.getWriter();
        //——请在这里编写您的程序（以下代码仅作参考）——
        if(signVerified) {
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");

            String[] userId = out_trade_no.split("_");
            Deposit deposit=new Deposit();
            deposit.setUserId(userId[0]);
            deposit.setIssuccess(1);
            deposit.setRechargeDate(LocalDateTime.now());
            deposit.setRechargeMoney(new BigDecimal(total_amount));
            deposit.setId(MyUtils.getUUID());
            depositService.insert(deposit);
            User user=userService.selectByPrimaryKey(userId[0]);
            user.setAccountBalance(user.getAccountBalance().add(new BigDecimal(total_amount)));
            userService.updateByPrimaryKeySelective(user);
            out.println("trade_no:"+trade_no+"<br/>out_trade_no:"+out_trade_no+"<br/>total_amount:"+total_amount);
        }else {
            out.println("验签失败");
        }
        return "redirect:../views/test_main.html";
    }

    @GetMapping("/return_url2")
    public String return_url2(HttpServletRequest request, HttpServletResponse response)throws Exception{
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);
        //调用SDK验证签名

        PrintWriter out = response.getWriter();
        //——请在这里编写您的程序（以下代码仅作参考）——
        if(signVerified) {
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");

            String[] userId = out_trade_no.split("_");
            if(Double.valueOf(total_amount)>=5000){
                Coupon coupon = new Coupon();
                coupon.setStatus(0);
                coupon.setUserId(getUserId(request));
                coupon.setId(MyUtils.getUUID());
                couponService.insert(coupon);
            }
            Forder forder = forderService.selectByPrimaryKey(userId[0]);
            forder.setStatus(1);
            forder.setPayment(2);
            forderService.updateByPrimaryKeySelective(forder);
            return "redirect:../views/success.html"+"?addressId="+ forder.getAddressId();
        }else {
            out.println("验签失败");
        }
        return null;
    }


    @RequestMapping(value = "/finishForder",method = RequestMethod.POST)
    public void finishForder(String money, String forderId, @RequestParam(value="logistics" ,defaultValue="1") int logistics
            , HttpServletRequest request,HttpServletResponse response){
	    logger.info("-------->money:"+money+",forderId:"+forderId+",logistics:"+logistics);
        Forder forder = forderService.selectByPrimaryKey(forderId);
        forder.setLogistics(logistics);
        BigDecimal total = new BigDecimal(money);
        forder.setTotal(total);
        forderService.updateByPrimaryKeySelective(forder);
        try {
            alipayService.pay(RETURN_URL,forderId+"_"+ Math.random() * (Math.pow(10, 10)), money, forderId, "", request, response);
        }catch (Exception e){
            logger.error("DepositController/finishForder is error:pd_FrpIds:{},e:{}",forderId,e.getStackTrace());
        }
    }

    @RequestMapping(value = "/test2",method = RequestMethod.GET)
    public String test(){
	    logger.error("----------------------->");
	    return "redirect:../views/success.html";
    }

}
