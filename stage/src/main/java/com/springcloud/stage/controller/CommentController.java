package com.springcloud.stage.controller;

import com.springcloud.stage.service.ForderService;
import com.springcloud.stage.service.ProductCommentService;
import com.springcloud.stage.service.UserService;
import com.zx.api.bean.Forder;
import com.zx.api.bean.ProductComment;
import com.zx.api.bean.ProductCommentExample;
import com.zx.api.bean.User;
import com.zx.api.dto.ResultDTO;
import com.zx.api.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.*;


@Controller
@RequestMapping("/Comment")
public class CommentController {

	@Autowired
	private ProductCommentService service;

	private String getUserId(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String user_id = -100+"";
		if (session.getAttribute("user_id") != null)
			user_id = (String) session.getAttribute("user_id");
		return user_id;
	}
	
	@ResponseBody
	@RequestMapping("/getMyCommont")
	 public ResultDTO getMyCommont(HttpServletRequest request) {
        ProductCommentExample productCommentExample = new ProductCommentExample();
        ProductCommentExample.Criteria criteria = productCommentExample.createCriteria();
        criteria.andUserIdEqualTo(getUserId(request));
        List<ProductComment> list = service.selectByExample(productCommentExample);
	           if(list.size()!=0) {
				   Map<String,Object> map = new HashMap<>();
				   map.put("list", list);
				   return ResultDTO.buildSuccessData(map);
			   }
	           return ResultDTO.error();
	}
	
	@ResponseBody
	@RequestMapping("/getMyGoodComment")
	public ResultDTO getMyGoodComment(HttpServletRequest request) {
        ProductCommentExample productCommentExample = new ProductCommentExample();
        ProductCommentExample.Criteria criteria = productCommentExample.createCriteria();
        criteria.andUserIdEqualTo(getUserId(request));
        criteria.andFavourCommentEqualTo(3);
        List<ProductComment> list = service.selectByExample(productCommentExample);
        if(list.size()!=0)
        {
            List<ProductComment> list2 = new ArrayList<>();
            int flag = 0 ;
            for( int i = 0 ; i<list.size() ; i++){
                if(list.get(i).getUpvote()>=10)
                {
                    flag = 1;
                    list2.add(list.get(i));
                }
            }
            if( flag == 1 ) {
                Map<String,Object> map = new HashMap<>();
                map.put("list", list2);
                return ResultDTO.buildSuccessData(map);
            }
        }return ResultDTO.error();
	}
	


	@RequestMapping("/request_comment")
	@ResponseBody
	public ResultDTO request_comment(String id, int favour, int pagenum) {
	    ProductCommentExample productCommentExample_ = new ProductCommentExample();
        ProductCommentExample.Criteria criteria_ = productCommentExample_.createCriteria();
        productCommentExample_.setStartRow(pagenum);
        productCommentExample_.setPageSize(10);
        criteria_.andProductIdEqualTo(id);
        if (favour != 0)
            criteria_.andFavourCommentEqualTo(favour);
        List<ProductComment> list1 = service.selectByExample(productCommentExample_);
        List<Integer> list=new ArrayList<>();
        {
            ProductCommentExample example=new ProductCommentExample();
            ProductCommentExample.Criteria criteria=example.createCriteria();
            criteria.andProductIdEqualTo(id);
            criteria.andFavourCommentEqualTo(1);
            int num1= (int) service.countByExample(example);
            ProductCommentExample example1=new ProductCommentExample();
            ProductCommentExample.Criteria criteria1=example1.createCriteria();
            criteria1.andProductIdEqualTo(id);
            criteria1.andFavourCommentEqualTo(2);
            int num2= (int) service.countByExample(example1);
            ProductCommentExample example2=new ProductCommentExample();
            ProductCommentExample.Criteria criteria2=example2.createCriteria();
            criteria2.andProductIdEqualTo(id);
            criteria2.andFavourCommentEqualTo(3);
            int num3= (int) service.countByExample(example2);
            list.add(num1);
            list.add(num2);
            list.add(num3);

        }
        Map<String,Object> map = new HashMap<>();
        map.put("list", list);
        map.put("list1",list1);
        map.put("favour",favour);
		if(list.size() == 0)
			return ResultDTO.error();
		else
			return ResultDTO.buildSuccessData(map);
	}
	@Autowired
    UserService userService;
	
	@RequestMapping("/request_product")
	@ResponseBody
	public ResultDTO request_product(String id) {
        Map<String,Object> map = new HashMap<>();
		User user = userService.selectByPrimaryKey(id);
		map.put("user", user);
		return ResultDTO.buildSuccessData(map);
		
	}
	@Autowired
	ProductCommentService productCommentService;
	
	@Autowired
    ForderService forderService;
	
	@RequestMapping("/comment")
	@ResponseBody
	public ResultDTO comment(HttpServletRequest request) {
		String id = request.getParameter("id");
		for(int i=0;request.getParameter("product"+i)!=null;i++){
		        Integer favourComment =Integer.parseInt(request.getParameter("comment"+i));
		        String productId = request.getParameter("product"+i);
		        Integer anonymous = Integer.parseInt(request.getParameter("anonymous"));
		        String content = request.getParameter("content"+i);
		        ProductComment productComment = new ProductComment();
		        productComment.setFavourComment(favourComment);
		        productComment.setCommentTime(LocalDateTime.now());
		        productComment.setProductId(productId);
		        productComment.setComment(content);
		        productComment.setUserId(getUserId(request));
		        productComment.setAnonymous(anonymous);
		        productComment.setId(MyUtils.getUUID());
		        productCommentService.insert(productComment);
		        
		 }
		Forder forder = forderService.selectByPrimaryKey(id);
		forder.setStatus(forder.getStatus()+1);
		forderService.updateByPrimaryKeySelective(forder);
		return ResultDTO.ok();
		
	}
	
}
