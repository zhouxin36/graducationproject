package com.zx.backstage.controller;

import com.zx.api.bean.MallActivity;
import com.zx.api.bean.MallActivityExample;
import com.zx.api.bean.Pic;
import com.zx.api.dto.R;
import com.zx.api.dto.ResultDTO;
import com.zx.api.utils.DeleteFileUtil;
import com.zx.api.utils.MyUtils;
import com.zx.api.utils.PageUtils;
import com.zx.backstage.service.MallActivityService;
import com.zx.backstage.service.PicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class MallActivityController {

    private Logger logger = LoggerFactory.getLogger(MallActivityController.class);
    @Autowired
    MallActivityService mallActivityService;

    @Autowired
    PicService picService;

    @Value("${file_base_url}")
    String FILE_BASE_URL;

    @RequestMapping(value = "/add_activity", method = RequestMethod.POST)
    @ResponseBody
    public ResultDTO add_activity(HttpServletRequest request, String remark,String url)
            throws IllegalStateException, IOException {
        logger.info("---->MallActivityController/add_activity; remark:{}", remark);
        MallActivity mallActivity = new MallActivity();
        Pic pic = new Pic();
        try {
            List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
            String path = FILE_BASE_URL;
            System.out.println(path);
            BufferedOutputStream stream = null;
            if (!files.get(0).isEmpty()) {
                String activityId = MyUtils.getUUID();
                String name = activityId + "_" + files.get(0).getOriginalFilename();
                pic.setPath(name);
                pic.setId(MyUtils.getUUID());
                picService.insert(pic);
                mallActivity.setRemark(remark);
                mallActivity.setUrl(url);
                mallActivity.setPicId(pic.getId());
                mallActivity.setId(activityId);
                mallActivityService.insert(mallActivity);
                stream = new BufferedOutputStream(new FileOutputStream(new File(FILE_BASE_URL + name)));
                stream.write(files.get(0).getBytes());
                stream.close();
                return ResultDTO.ok();
            }
            return ResultDTO.error();
        } catch (Exception e) {
            logger.error("MallActivityController/add_activity; e:{},mallActivity:{},pic:{}", e.getMessage(), mallActivity, pic);
            return ResultDTO.error("系统错误，请联系管理员！");
        }

    }

    @RequestMapping("/activity_list")
    @ResponseBody
    public R activity_list() {
        logger.info("---->MallActivityController/activity_list;");
        PageUtils pageUtil = null;
        try {
            List<MallActivity> list = mallActivityService.selectByExample(new MallActivityExample());
            if (list.size() == 0) {
                return R.error("列表为空");
            } else {
                Map<String, List<MallActivity>> map = new HashMap<>();
                map.put("list", list);
                pageUtil = new PageUtils(list, list.size(), list.size(), 1);
                return R.ok().put("page", pageUtil);
            }
        } catch (Exception e) {
            logger.error("MallActivityController/activity_list; e:{},list:{}", e.getMessage(), pageUtil);
            return R.error("系统错误，请联系管理员！");

        }
    }

    @ResponseBody
    @RequestMapping("/find_img_activity")
    public ResultDTO<Map<String, Object>> find_img_activity(String id) {
        logger.info("---->MallActivityController/find_img_activity; id:{}", id);
        Map<String, Object> map = new HashMap<>();
        try {
            Pic pic = picService.selectByPrimaryKey(id);
            if (pic != null) {
                ResultDTO<Map<String, Object>> ok = new ResultDTO<>();
                ok.setOK();
                map.put("pic", pic);
                ok.setData(map);
                return ok;
            } else {
                return ResultDTO.error();
            }
        } catch (Exception e) {
            logger.error("MallActivityController/find_img_activity; e:{},map:{}", e.getMessage(), map);
            return ResultDTO.error("系统错误，请联系管理员！");

        }
    }

    @ResponseBody
    @RequestMapping("/activity_delete")
    public ResultDTO activity_delete(String id) {
        logger.info("---->MallActivityController/activity_delete; id:{}", id);
        MallActivity mallActivity = null;
        try {
            String path = FILE_BASE_URL;
            mallActivity = mallActivityService.selectByPrimaryKey(id);
            mallActivityService.deleteByPrimaryKey(id);
            Pic pic = picService.selectByPrimaryKey(mallActivity.getPicId());
            if (pic != null)
                DeleteFileUtil.delete(path, pic.getPath());
            picService.deleteByPrimaryKey(mallActivity.getPicId());
            return ResultDTO.ok();
        } catch (Exception e) {
            logger.error("MallActivityController/activity_delete; e:{},mallActivity:{}", e.getMessage(), mallActivity);
            return ResultDTO.error("系统错误，请联系管理员！");
        }
    }
}
