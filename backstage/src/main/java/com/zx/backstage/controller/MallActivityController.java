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

	@Autowired
	MallActivityService mallActivityService;

	@Autowired
	PicService picService;

    @Value("${file_base_url}")
	String FILE_BASE_URL;

	@RequestMapping(value = "/add_activity",method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO add_activity(HttpServletRequest request, String remark)
			throws IllegalStateException, IOException {
        List<MultipartFile> files =((MultipartHttpServletRequest)request).getFiles("file");
		String path = FILE_BASE_URL;
		System.out.println(path);
        BufferedOutputStream stream = null;
		if (!files.get(0).isEmpty()) {
		    String activityId = MyUtils.getUUID();
            String name = activityId +"_"+ files.get(0).getOriginalFilename();
			Pic pic = new Pic();
            pic.setPath(name);
            pic.setId(MyUtils.getUUID());
            picService.insert(pic);
            MallActivity mallActivity = new MallActivity();
            mallActivity.setRemark(remark);
            mallActivity.setPicId(pic.getId());
            mallActivity.setId(activityId);
            mallActivityService.insert(mallActivity);
            stream = new BufferedOutputStream(new FileOutputStream(new File(FILE_BASE_URL+name)));
            stream.write(files.get(0).getBytes());
            stream.close();
            return ResultDTO.ok();
		}
		return ResultDTO.error();

	}

	@RequestMapping("/activity_list")
	@ResponseBody
	public R activity_list(){
		List<MallActivity> list = mallActivityService.selectByExample(new MallActivityExample());
		if(list.size() == 0) {
			return R.error("列表为空");
		}else {
			Map<String, List<MallActivity>> map = new HashMap<>();
			map.put("list", list);
            PageUtils pageUtil = new PageUtils(list, list.size(), list.size(), 1);
			return R.ok().put("page",pageUtil);
		}
	}

	@ResponseBody
	@RequestMapping("/find_img_activity")
	public ResultDTO<Map<String,Object>> find_img_activity(String id) {
//		String path = (String) request.getSession().getAttribute("path2");
		Pic pic = picService.selectByPrimaryKey(id);
		if (pic != null) {
            ResultDTO<Map<String,Object>> ok = new ResultDTO<>();
            ok.setOK();
            Map<String,Object> map = new HashMap<>();
            map.put("pic", pic);
            ok.setData(map);
            return ok;
		} else {
            return ResultDTO.error();
		}
	}

	@ResponseBody
	@RequestMapping("/activity_delete")
	public ResultDTO activity_delete(String id) {
//		String path = (String) request.getSession().getAttribute("path");
		String path = FILE_BASE_URL;
		MallActivity mallActivity = mallActivityService.selectByPrimaryKey(id);
		mallActivityService.deleteByPrimaryKey(id);
		Pic pic = picService.selectByPrimaryKey(mallActivity.getPicId());
		if(pic != null)
			DeleteFileUtil.delete(path, pic.getPath());
		picService.deleteByPrimaryKey(mallActivity.getPicId());
		return ResultDTO.ok();
	}
}
