package com.zx.backstage.controller;

import com.zx.api.bean.Home;
import com.zx.api.bean.HomeExample;
import com.zx.api.dto.R;
import com.zx.api.dto.ResultDTO;
import com.zx.api.utils.MyUtils;
import com.zx.api.utils.PageUtils;
import com.zx.api.utils.Query;
import com.zx.backstage.service.CategoryService;
import com.zx.backstage.service.HomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;


@Controller
public class HomeController {

    private Logger logger = LoggerFactory.getLogger(HomeController.class);
    @Autowired
    HomeService homeService;

    @Autowired
    CategoryService categoryService;


    @ResponseBody
    @RequestMapping("/home_list")
    public R homeList(@RequestParam Map<String, Object> params) {
        logger.info("---->HomeController/homeList; params:{}", params);
        PageUtils pageUtil = null;
        try {
            Query query = new Query(params);
            HomeExample homeExample = new HomeExample();
            List<Home> list = homeService.selectByExample(homeExample);
            pageUtil = new PageUtils(list, list.size(), list.size(), 1);
            if (list.size() != 0) {
                return R.ok().put("page", pageUtil);
            } else
                return R.error("列表为空");
        } catch (Exception e) {
            logger.error("HomeController/homeList; e:{},pageUtil:{}", e, pageUtil);
            return R.error("系统错误，请联系管理员！");
        }
    }

    @ResponseBody
    @RequestMapping("/home_add")
    public ResultDTO homeAdd(@RequestBody Home home) {
        logger.info("---->HomeController/homeAdd; home:{}", home);
        try {
            home.setId(MyUtils.getUUID());
            home.setCategoryName(categoryService.selectByPrimaryKey(home.getCategoryId()).getType());
            int i = homeService.insert(home);
            if (i != 0)
                return ResultDTO.ok("添加成功");
            else
                return ResultDTO.error("添加失败");
        } catch (Exception e) {
            logger.error("HomeController/homeAdd; e:{},home:{}", e, home);
            return ResultDTO.error("系统错误，请联系管理员！");
        }
    }

    @ResponseBody
    @RequestMapping("/home_delete")
    public ResultDTO homeDelete(String id) {
        logger.info("---->HomeController/homeAdd; id:{}", id);
        try {
            int i = homeService.deleteByPrimaryKey(id);
            if (i != 0)
                return ResultDTO.ok("删除成功");
            else
                return ResultDTO.error("删除失败");
        } catch (Exception e) {
            logger.error("HomeController/homeAdd; e:{},id:{}", e, id);
            return ResultDTO.error("系统错误，请联系管理员！");
        }
    }

}
