package com.zx.backstage.controller;

import com.zx.api.bean.Feedback;
import com.zx.api.bean.FeedbackExample;
import com.zx.api.dto.R;
import com.zx.api.dto.ResultDTO;
import com.zx.api.utils.PageUtils;
import com.zx.api.utils.Query;
import com.zx.backstage.service.FeedbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class FeedbackController {

    private Logger logger = LoggerFactory.getLogger(FeedbackController.class);


    @Autowired
    FeedbackService feedbackService;

    @RequestMapping("/feedback_request")
    @ResponseBody
    public R feedback_request(@RequestParam Map<String, Object> params) {
        logger.info("---->FeedbackController/feedback_request; params:{}", params);
        PageUtils pageUtil = null;
        try {
            Query query = new Query(params);
            FeedbackExample feedbackExample = new FeedbackExample();
            feedbackExample.setStartRow((Integer) query.get("offset"));
            feedbackExample.setPageSize((Integer) query.get("limit"));
            List<Feedback> feedbacks = feedbackService.selectByExample(feedbackExample);
            long total = feedbackService.countByExample(feedbackExample);
            pageUtil = new PageUtils(feedbacks, (int) total, query.getLimit(), query.getPage());
            Map<String, PageUtils> map = new HashMap<>();
            map.put("page", pageUtil);
            return R.ok().put("page", pageUtil);
        } catch (Exception e) {
            logger.error("FeedbackController/feedback_request; e:{},pageUtil:{}", e, pageUtil);
            return R.error("系统错误，请联系管理员！");
        }
    }

    @RequestMapping("/feedback_delete")
    @ResponseBody
    public ResultDTO feedback_delete(String id) {
        logger.info("---->FeedbackController/feedback_delete; id:{}", id);
        try {
            int i = feedbackService.deleteByPrimaryKey(id);
            if (i == 0) {
                return ResultDTO.error("删除失败");
            } else
                return ResultDTO.ok("删除成功");
        } catch (Exception e) {
            logger.error("FeedbackController/feedback_delete; e:{},admin:{}", e, id);
            return ResultDTO.error("系统错误，请联系管理员！");
        }
    }
}
