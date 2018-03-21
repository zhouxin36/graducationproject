package com.springcloud.stage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.springcloud.stage.service.SafeQuestionService;
import com.zx.api.bean.SafeQuestion;
import com.zx.api.bean.SafeQuestionExample;
import com.zx.api.dto.ResultDTO;
import com.zx.api.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/question")
@Controller
public class QuestionController {

    @Autowired
    SafeQuestionService service;


    private String getUserId(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String user_id = -100+"";
        if (session.getAttribute("user_id") != null)
            user_id = (String) session.getAttribute("user_id");
        return user_id;
    }


    @ResponseBody
    @RequestMapping("/Check_Answer")
    public ResultDTO Check_Answer(String answer, HttpServletRequest request) {
        SafeQuestionExample safeQuestionExample = new SafeQuestionExample();
        SafeQuestionExample.Criteria criteria = safeQuestionExample.createCriteria();
        criteria.andUserIdEqualTo(getUserId(request));
        List<SafeQuestion> questions = service.selectByExample(safeQuestionExample);
        if(questions.size()==0)
            return ResultDTO.error();
        boolean flag = questions.get(0).getQuestionAnswer().equals(answer);
        if(flag==true)
            return ResultDTO.ok();
        return ResultDTO.error();
    }

    @ResponseBody
    @RequestMapping("/insertUserQuestion")
    public ResultDTO insertUserQuestion(SafeQuestion safeQuestion ,HttpServletRequest request) {
        safeQuestion.setUserId(getUserId(request));
        safeQuestion.setId(MyUtils.getUUID());
        int flag = service.insert(safeQuestion);
        if(flag==0){
            return ResultDTO.error();

        }return ResultDTO.ok();
    }

    @ResponseBody
    @RequestMapping("/updateUserQuestion")
    public ResultDTO updateUserQuestion(SafeQuestion safeQuestion, HttpServletRequest request) {
        safeQuestion.setUserId(getUserId(request));
        int flag  = service.updateByPrimaryKey(safeQuestion);
        if(flag == 0)
            return ResultDTO.error();
        else {
            return ResultDTO.ok();
        }


    }

    @RequestMapping("/getQuestionById")
    @ResponseBody
    public ResultDTO getQuestionById(HttpServletRequest request) {
        SafeQuestionExample safeQuestionExample = new SafeQuestionExample();
        SafeQuestionExample.Criteria criteria = safeQuestionExample.createCriteria();
        criteria.andUserIdEqualTo(getUserId(request));
        List<SafeQuestion> list   =  service.selectByExample(safeQuestionExample);
        if(list.size()==0)
            return ResultDTO.error();
        Map<String,Object> map = new HashMap<>();
        map.put("list", list);
        return ResultDTO.buildSuccessData(map);

    }


}
