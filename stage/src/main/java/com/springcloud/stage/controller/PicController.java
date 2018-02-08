package com.springcloud.stage.controller;


import java.io.IOException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;

import com.springcloud.stage.service.PicService;
import com.zx.api.bean.Pic;
import com.zx.api.bean.PicExample;
import com.zx.api.dto.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/Pic")
@Controller
public class PicController {


	@Autowired
	PicService picService;

	@Value("${file_base_url}")
	String FILE_BASE_URL;
	

	@RequestMapping("/selectPicByProductId")
	@ResponseBody
	public ResultDTO selectPicByProductId(HttpServletRequest request, String id) throws IOException {
		String path2 = FILE_BASE_URL;
        PicExample picExample = new PicExample();
        PicExample.Criteria criteria = picExample.createCriteria();
        criteria.andProductIdEqualTo(id);
        List<Pic> list = picService.selectByExample(picExample);
		if(list.size() !=0) {
            Map<String,Object> map = new HashMap<>();
            map.put("p", path2);
            map.put("list_pic", list);
			return ResultDTO.buildSuccessData(map);
		}else {
            Map<String,Object> map = new HashMap<>();
            map.put("p", path2);
			return ResultDTO.buildSuccessDataError(map);
		}
		
	}
	
	@RequestMapping("/selectPicByProductType")
	@ResponseBody
	public ResultDTO selectPicByProductType(HttpServletRequest request,String id,int type) throws IOException {
		String path2 = FILE_BASE_URL;
        PicExample example=new PicExample();
        PicExample.Criteria criteria=example.createCriteria();
        criteria.andProductIdEqualTo(id);
        criteria.andTypeEqualTo(type);
        List<Pic> list = picService.selectByExample(example);

		if(list.size() == 0) {
            Map<String,Object> map = new HashMap<>();
            map.put("path", path2);
            return ResultDTO.buildSuccessDataError(map);
        }
		else {
            Map<String,Object> map = new HashMap<>();
            map.put("path", path2);
            map.put("pic_list", list);
            return ResultDTO.buildSuccessData(map);
        }
	}
}
