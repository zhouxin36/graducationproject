package com.springcloud.stage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.springcloud.stage.service.MallActivityService;
import com.springcloud.stage.service.PicService;
import com.zx.api.bean.MallActivity;
import com.zx.api.bean.MallActivityExample;
import com.zx.api.bean.Pic;
import com.zx.api.dto.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/MallActivity")
public class MallActivityController {
	@Autowired
	MallActivityService mallActivityService;
	
	@Autowired
	PicService picService;

	@Value("${file_base_url}")
	String FILE_BASE_URL;
    
    @RequestMapping("/selectMallActivity")
    @ResponseBody
    public ResultDTO selectMallActivity() {
      List<MallActivity>	list =	mallActivityService.selectByExample(new MallActivityExample());
      if(list.size()!=0) {
		  Map<String,Object> map = new HashMap<>();
		  map.put("list", list);
		  return ResultDTO.buildSuccessData(map);
	  }
      return ResultDTO.error();
	}
    
    @ResponseBody
	@RequestMapping("/find_img_activity")
	public ResultDTO find_img_activity(HttpServletRequest request, String id) {
		String path = FILE_BASE_URL;
		Pic pic = picService.selectByPrimaryKey(id);
        Map<String,Object> map = new HashMap<>();
		if (pic != null) {
			System.out.println(pic.getPath());
			map.put("pic", pic);
			map.put("p", path);
			return ResultDTO.buildSuccessData(map);
		} else {
			System.out.println("error");
			map.put("p", path);
            ResultDTO resultDTO = ResultDTO.error();
            resultDTO.setData(map);
            return resultDTO;
		}
	}
    
}
