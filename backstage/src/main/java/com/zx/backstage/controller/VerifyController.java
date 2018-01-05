package com.zx.backstage.controller;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.zx.backstage.config.ApiResponse;
import com.zx.backstage.config.CaptchaAPI;
import com.zx.backstage.dto.ResultDTO;
import com.zx.backstage.dto.VerifyDTO;
import com.zx.backstage.utils.HttpUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

@Controller
@RequestMapping("/verify")
public class VerifyController {

    @RequestMapping(value = "/verifyCode", method = RequestMethod.POST)
    @ResponseBody
    public boolean verify(HttpServletRequest request, HttpServletResponse response, VerifyDTO verifyDTO) throws Exception{
        boolean result =false;
        String domain = HttpUtil.getRootDomain(request);
        String ip = HttpUtil.getIpAddress(request);

        if (verifyDTO.getTicket()==null || verifyDTO.getTicket().length() == 0) {
            result = false;
        } else {
            SortedMap<String, String> args = new TreeMap<String, String>();
            args.put("captchaType", verifyDTO.getCaptchaType().toString());
            args.put("ticket", verifyDTO.getTicket());
            args.put("userIp", ip);
            CaptchaAPI api = CaptchaAPI.getInstance();
            try {
                ApiResponse resp = api.check(args);
                String content = (String)resp.getBody();
                Gson gson = new Gson();
                HashMap<String, String> map = gson.fromJson(content, new TypeToken<HashMap<String, String>>(){}.getType());
                if (Integer.parseInt(map.get("code")) == 0) {
                    result = true;
                } else {
                    result = false;
                }
            } catch (Exception e) {
                result = false;
                e.printStackTrace();
            }
        }
        return result;
    }


    @GetMapping("/index")
    @ResponseBody
    public ResultDTO<String> index(HttpServletRequest request, HttpServletResponse response, VerifyDTO verifyDTO) throws Exception{

        String domain = HttpUtil.getRootDomain(request);
        String ip = HttpUtil.getIpAddress(request);
        String jsUrl = "";
        CaptchaAPI api = CaptchaAPI.getInstance();
        try {
            /*
             * 补充用户、行为信息数据,方便我们做更准确的数据模型
             * 协议参考 https://www.qcloud.com/doc/api/254/2897
             */
            SortedMap<String, String> args = new TreeMap<>();
            args.put("captchaType", verifyDTO.getCaptchaType().toString());
            args.put("disturbLevel", "1");
            args.put("isHttps", "1");
            args.put("clientType", verifyDTO.getClientType().toString());
            args.put("userIp", ip);

            ApiResponse resp = api.getJsUrl(args);
            String content = (String)resp.getBody();

            Gson gson = new Gson();
            HashMap<String, String> map = gson.fromJson(content, new TypeToken<HashMap<String, String>>(){}.getType());
            if (Integer.parseInt(map.get("code")) == 0) {
                jsUrl = map.get("url");
            } else {
                System.out.println(resp.getBody());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        ResultDTO<String> stringResultDTO = new ResultDTO<>();
        return stringResultDTO.buildSuccessData(jsUrl);
    }


}
