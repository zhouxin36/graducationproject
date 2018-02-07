package com.zx.backstage.controller;

import com.zx.api.bean.MallActivity;
import com.zx.api.bean.MallActivityExample;
import com.zx.api.bean.Pic;
import com.zx.api.dto.ResultDTO;
import com.zx.api.utils.DeleteFileUtil;
import com.zx.api.utils.MyUtils;
import com.zx.backstage.service.MallActivityService;
import com.zx.backstage.service.PicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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

	@RequestMapping("/add_activity")
	@ResponseBody
	public ResultDTO add_activity(@RequestParam(value = "file", required = false) MultipartFile file, String remark)
			throws IllegalStateException, IOException {
//		String path = (String) request.getSession().getAttribute("path");
		String path = FILE_BASE_URL;
		System.out.println(path);
		if (!file.isEmpty()) {
			int random = (int) (Math.random() * 10000);
			String newFileName = System.currentTimeMillis() + random + ".jpg";
			File dir = new File(path, newFileName);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			// MultipartFile自带的解析方法
			file.transferTo(dir);
			Pic pic = new Pic();
			pic.setPath(newFileName);
			pic.setId(MyUtils.getUUID());
			picService.insert(pic);
			MallActivity mallActivity = new MallActivity();
			mallActivity.setRemark(remark);
			mallActivity.setPicId(pic.getId());
			mallActivity.setId(MyUtils.getUUID());
			mallActivityService.insert(mallActivity);
			return ResultDTO.ok();
		}
		return ResultDTO.error();

	}

	@RequestMapping("/activity_list")
	@ResponseBody
	public ResultDTO<Map<String, List<MallActivity>>> activity_list(){
		List<MallActivity> list = mallActivityService.selectByExample(new MallActivityExample());
		if(list.size() == 0) {
			return ResultDTO.error();
		}else {
			Map<String, List<MallActivity>> map = new HashMap<>();
			map.put("list", list);
			return ResultDTO.buildSuccessData(map);
		}
	}

	@ResponseBody
	@RequestMapping("/find_img_activity")
	public ResultDTO<Map<String,Object>> find_img_activity(String id) {
//		String path = (String) request.getSession().getAttribute("path2");
		String path = FILE_BASE_URL;
		Pic pic = picService.selectByPrimaryKey(id);
		if (pic != null) {
            ResultDTO<Map<String,Object>> ok = new ResultDTO<>();
            ok.setOK();
            Map<String,Object> map = new HashMap<>();
            map.put("pic", pic);
            map.put("p", path);
            ok.setData(map);
            return ok;
		} else {
            ResultDTO<Map<String,Object>> error = new ResultDTO<>();
            error.setError();
            Map<String,Object> map = new HashMap<>();
            map.put("p", "../static/img/");
            error.setData(map);
            return error;
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
