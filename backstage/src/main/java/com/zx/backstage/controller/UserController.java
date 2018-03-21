package com.zx.backstage.controller;

import com.zx.api.bean.User;
import com.zx.api.bean.UserExample;
import com.zx.api.dto.R;
import com.zx.api.dto.ResultDTO;
import com.zx.api.utils.MyUtils;
import com.zx.api.utils.PageUtils;
import com.zx.api.utils.Query;
import com.zx.backstage.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping("/user_list")
    public R userList(@RequestParam Map<String, Object> params) {
        PageUtils pageUtil = null;
        try {
            logger.info("---->UserController/userList; params:{}", params);
            Query query = new Query(params);
            UserExample userExample = new UserExample();
            userExample.setStartRow((Integer) query.get("offset"));
            userExample.setPageSize((Integer) query.get("limit"));
            UserExample.Criteria criteria = userExample.createCriteria();
            if (!MyUtils.isBlank((String) query.get("name")))
                criteria.andNameLike("%" + (String) query.get("name") + "%");
            List<User> users = userService.selectByExample(userExample);
            long total = userService.countByExample(userExample);
            pageUtil = new PageUtils(users, (int) total, query.getLimit(), query.getPage());
            Map<String, PageUtils> map = new HashMap<>();
            return R.ok().put("page", pageUtil);
        } catch (Exception e) {
            logger.error("UserController/userList; e:{},pageUtil:{}", e.getMessage(), pageUtil);
            return R.error("系统错误，请联系管理员！");

        }
    }

    @ResponseBody
    @RequestMapping("user_disable")
    public ResultDTO userDisable(String id) {
        try {
            logger.info("---->UserController/userDisable; id:{}", id);
            User user = userService.selectByPrimaryKey(id);
            user.setIsabled(0);
            int i = userService.updateByPrimaryKey(user);
            if (i == 1)
                return ResultDTO.ok();
            else
                return ResultDTO.error();
        } catch (Exception e) {
            logger.error("UserController/userDisable; e:{},id:{}", e.getMessage(), id);
            return ResultDTO.error("系统错误，请联系管理员！");
        }
    }

    @ResponseBody
    @RequestMapping("user_able")
    public ResultDTO userAble(String id) {
        try {
            logger.info("---->UserController/userAble; id:{}", id);
            User user = userService.selectByPrimaryKey(id);
            user.setIsabled(1);
            int i = userService.updateByPrimaryKey(user);
            if (i == 1)
                return ResultDTO.ok();
            else
                return ResultDTO.error();
        } catch (Exception e) {
            logger.error("UserController/userAble; e:{},id:{}", e.getMessage(), id);
            return ResultDTO.error("系统错误，请联系管理员！");
        }
    }

    @ResponseBody
    @RequestMapping("user_delete")
    public ResultDTO userDelete(String id) {
        try {
            logger.info("---->UserController/userDelete; id:{}", id);
            int i = userService.deleteByPrimaryKey(id);
            if (i == 1)
                return ResultDTO.ok();
            else
                return ResultDTO.error();
        } catch (Exception e) {
            logger.error("UserController/userDelete; e:{},id:{}", e.getMessage(), id);
            return ResultDTO.error("系统错误，请联系管理员！");
        }
    }

    @ResponseBody
    @RequestMapping("find_userName")
    public ResultDTO<Map<String, String>> userNamebyId(String id) {
        try {
            logger.info("---->UserController/userNamebyId; id:{}", id);
            User user = userService.selectByPrimaryKey(id);
            if (user != null) {
                Map<String, String> map = new HashMap<>();
                map.put("username", user.getName());
                return ResultDTO.buildSuccessData(map);
            } else {
                return ResultDTO.error();
            }
        } catch (Exception e) {
            logger.error("UserController/userNamebyId; e:{},id:{}", e.getMessage(), id);
            return ResultDTO.error("系统错误，请联系管理员！");
        }
    }

    @ResponseBody
    @RequestMapping("user/{id}/query")
    public R querybyId(@PathVariable String id) {
        try {
            logger.info("---->UserController/querybyId; id:{}", id);
            User user = userService.selectByPrimaryKey(id);
            if (user == null) {
                return R.error("user为空");
            } else {
                return R.ok().put("app", user);
            }
        } catch (Exception e) {
            logger.error("UserController/querybyId; e:{},id:{}", e.getMessage(), id);
            return R.error("系统错误，请联系管理员！");
        }
    }

    @ResponseBody
    @RequestMapping("update_password")
    public R updatePassword(@RequestBody User user) {
        try {
            logger.info("---->UserController/querybyId; user:{}", user);
            user.setPassword(MyUtils.md5Passwrod(user.getPassword()));
            userService.updateByPrimaryKeySelective(user);
            if (user == null) {
                return R.error("修改密码失败");
            } else {
                return R.ok("修改成功");
            }
        } catch (Exception e) {
            logger.error("UserController/updatePassword; e:{},user:{}", e.getMessage(), user);
            return R.error("系统错误，请联系管理员！");
        }
    }
}
