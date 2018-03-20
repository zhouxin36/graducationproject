package com.zx.backstage.controller;

import com.zx.api.bean.*;
import com.zx.api.dto.R;
import com.zx.api.dto.ResultDTO;
import com.zx.api.utils.MyUtils;
import com.zx.api.utils.PageUtils;
import com.zx.api.utils.Query;
import com.zx.backstage.service.ForderService;
import com.zx.backstage.service.SorderService;
import com.zx.backstage.service.UserAddressService;
import com.zx.backstage.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Controller
public class ForderController {
    private Logger logger = LoggerFactory.getLogger(ForderController.class);

    @Autowired
    ForderService forderService;

    @Autowired
    UserAddressService userAddressService;

    @Autowired
    SorderService sorderService;

    @Autowired
    UserService userService;


    @ResponseBody
    @RequestMapping("/forder_list")
    public R productList(@RequestParam Map<String, Object> params) throws Exception {
        logger.info("---->ForderController/productList; params:{}", params);
        PageUtils pageUtil = null;
        try {
            Query query = new Query(params);
            ForderExample forderExample = new ForderExample();
            forderExample.setStartRow((Integer) query.get("offset"));
            forderExample.setPageSize((Integer) query.get("limit"));
            ForderExample.Criteria criteria = forderExample.createCriteria();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date start = null;
            Date end = null;
            if (!MyUtils.isBlank((String) query.get("logistics")))
                criteria.andLogisticsEqualTo(Integer.parseInt((String) query.get("logistics")));
            if (!MyUtils.isBlank((String) query.get("payment")))
                criteria.andPaymentEqualTo(Integer.parseInt((String) query.get("payment")));
            if (!MyUtils.isBlank((String) query.get("start"))) {
                start = simpleDateFormat.parse((String) query.get("start"));
                criteria.andAddDateGreaterThanOrEqualTo(start);
            }
            if (!MyUtils.isBlank((String) query.get("end"))) {
                end = simpleDateFormat.parse((String) query.get("end"));
                criteria.andAddDateLessThanOrEqualTo(end);
            }
            if (!MyUtils.isBlank((String) query.get("status")))
                criteria.andStatusEqualTo(Integer.parseInt((String) query.get("status")));
            List<Forder> feedbacks = forderService.selectByExample(forderExample);
            long total = forderService.countByExample(forderExample);
            pageUtil = new PageUtils(feedbacks, (int) total, query.getLimit(), query.getPage());
            return R.ok().put("page", pageUtil);
        } catch (Exception e) {
            logger.error("ForderController/productList; e:{},pageUtil:{}", e.getMessage(), pageUtil);
            return R.error("系统错误，请联系管理员！");

        }
    }

    @ResponseBody
    @RequestMapping("/report_chart")
    public ResultDTO<Map<String, Long>> report_chart(HttpServletRequest request, String year) throws ParseException {
        logger.info("---->ForderController/report_chart; year:{}", year);
        Map<String, Long> map = new HashMap<>();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
            SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
            ResultDTO msg = ResultDTO.ok();
            int i;
            ForderExample forderExample = new ForderExample();
            for (i = 1; i < 13; i++) {
                String start = year + "-" + i + "-01";
                String str = year + "-" + i;
                Date date = dateFormat.parse(str);
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(date);
                String end = year + "-" + i + "-" + calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
                Date d_start = dateFormat1.parse(start);
                Date d_end = dateFormat1.parse(end);
                forderExample.clear();
                ForderExample.Criteria criteria = forderExample.createCriteria();
                criteria.andSuccessTimeBetween(d_start, d_end);
                map.put("chart" + i, forderService.countByExample(forderExample));
            }
            msg.setData(map);
            if (i == 1) {
                return ResultDTO.error();
            } else {
                return msg;
            }
        } catch (Exception e) {
            logger.error("ForderController/report_chart; e:{},map:{}", e.getMessage(), map);
            return ResultDTO.error("系统错误，请联系管理员！");
        }
    }


    @ResponseBody
    @RequestMapping("/request_forder")
    public ResultDTO<Map<String, Object>> requestForder(HttpServletRequest request, String id) {
        logger.info("---->ForderController/requestForder; id:{}", id);
        Map<String, Object> map = new HashMap<>();
        try {
            Forder forder = forderService.selectByPrimaryKey(id);
            UserAddress userAddress = userAddressService.selectByPrimaryKey(forder.getAddressId());
            SorderExample sorderExample = new SorderExample();
            SorderExample.Criteria criteria = sorderExample.createCriteria();
            criteria.andForderIdEqualTo(forder.getId());
            List<Sorder> listSorder = sorderService.selectByExample(sorderExample);
            User user = userService.selectByPrimaryKey(forder.getUserId());
            ResultDTO<Map<String, Object>> ok = ResultDTO.ok();
            map.put("forder", forder);
            map.put("userAddress", userAddress);
            map.put("listSorder", listSorder);
            map.put("user", user);
            ok.setData(map);
            return ok;
        } catch (Exception e) {
            logger.error("ForderController/requestForder; e:{},map:{}", e.getMessage(), map);
            return ResultDTO.error("系统错误，请联系管理员！");
        }
    }

    @ResponseBody
    @RequestMapping("/forder_deliver")
    public ResultDTO forderDeliver(String id) {
        logger.info("---->ForderController/forderDeliver; id:{}", id);
        Forder forder = null;
        try {
            forder = forderService.selectByPrimaryKey(id);
            forder.setStatus(2);
            int i = forderService.updateByPrimaryKey(forder);
            if (i == 0) {
                return ResultDTO.error();
            } else
                return ResultDTO.ok();
        } catch (Exception e) {
            logger.error("ForderController/forderDeliver; e:{},forder:{}", e.getMessage(), forder);
            return ResultDTO.error("系统错误，请联系管理员！");
        }
    }
}
