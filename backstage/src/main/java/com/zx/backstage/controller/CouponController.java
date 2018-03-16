package com.zx.backstage.controller;

import javax.servlet.http.HttpServletRequest;

import com.zx.api.bean.Coupon;
import com.zx.api.dto.ResultDTO;
import com.zx.api.utils.MyUtils;
import com.zx.backstage.service.CouponService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CouponController {

    Logger logger = LoggerFactory.getLogger(CouponController.class);

    @Autowired
    CouponService couponService;


    @RequestMapping("/send_coupon")
    @ResponseBody
    public ResultDTO sendCoupon(HttpServletRequest request, String id) {
        logger.info("---->CouponController/sendCoupon; id:{}", id);
        Coupon coupon = new Coupon();
        try {
            coupon.setStatus(0);
            coupon.setUserId(id);
            coupon.setId(MyUtils.getUUID());
            int i = couponService.insert(coupon);
            if (i == 0) {
                return ResultDTO.error();
            } else
                return ResultDTO.ok();
        } catch (Exception e) {
            logger.error("CouponController/sendCoupon; e:{},coupon:{}", e.getMessage(), coupon);
            return ResultDTO.error("系统错误，请联系管理员！");
        }
    }

}
