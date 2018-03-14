package com.springcloud.stage.controller;


import com.springcloud.stage.service.*;
import com.zx.api.bean.*;
import com.zx.api.dto.ResultDTO;
import com.zx.api.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;


@Controller
@RequestMapping("/Order")
public class OrderController {

	@Autowired
	UserService userService;
	
	@Autowired
	CouponService couponService;
	
	@Autowired
	ForderService forderService;
	@Autowired
	SorderService sorderService;
	@Autowired
	ProductService productService;
	
	@Autowired
	UserAddressService addressService;





	private String getUserId(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String user_id = -100+"";
		if (session.getAttribute("user_id") != null)
			user_id = (String) session.getAttribute("user_id");
		return user_id;
	}
	
	@ResponseBody
	@RequestMapping("/selectForderStatusById")
	public ResultDTO selectForderStatusById(String id){
		 Forder forder =  forderService.selectByPrimaryKey(id);
	     if(forder!=null) {
			 Map<String,Object> map = new HashMap<>();
			 map.put("Forder", forder);
			 return ResultDTO.buildSuccessData(map);
		 }
	     return ResultDTO.error();
	}



	@ResponseBody
	@RequestMapping("/addSorderToForder")
	public ResultDTO addSorderToForder(HttpServletRequest request, String[] id,Integer [] setting){
        for (String i:
             id) {
            System.out.println(i);
        }
        String userId = getUserId(request);
        SorderExample sorderExample = new SorderExample();
        SorderExample.Criteria criteria = sorderExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andIsbalancedEqualTo(0);
        List<Sorder> sorders = sorderService.selectByExample(sorderExample);
        for(int i=0; i<sorders.size();i++){
            Sorder sorder = sorders.get(i);
            sorder.setNumber(setting[i]);
            sorderService.updateByPrimaryKeySelective(sorder);
        }
        Sorder sorder = null;
		String forderId = MyUtils.getUUID();
		Forder forder = new Forder();
		if(id==null){
			return ResultDTO.error();
		}else {
			BigDecimal money = new BigDecimal("0");
			for(int i = 0 ;i < id.length ; i++){
				if(id[i].equals(-100+""))
					continue;
				sorder = sorderService.selectByPrimaryKey(id[i]);
				BigDecimal mu = new BigDecimal(sorder.getNumber());
				money = money.add(sorder.getPrice().multiply(mu));
			}


			forder.setAddDate(LocalDateTime.now());
			forder.setTotal(money);
			forder.setStatus(0);
			forder.setUserId(sorder.getUserId());
			forder.setId(forderId);
			forderService.insert(forder);

			for(int i = 0 ;i < id.length ; i++){
				if(id[i].equals(-100+""))
					continue;
				sorder = sorderService.selectByPrimaryKey(id[i]);
				sorder.setIsbalanced(1);
				sorder.setForderId( forderId);
				sorderService.updateByPrimaryKeySelective(sorder);
			}
		}
		Map<String,Object> map = new HashMap<>();
		map.put("forderId", forderId);
		return ResultDTO.buildSuccessData(map);

	}



	@ResponseBody
	@RequestMapping("/addProductToSorder")
	public ResultDTO addProductToSorder(String productId,@RequestParam(value="number",defaultValue="1") int number,HttpServletRequest request) {
	    Sorder sorder = new Sorder();
	    sorder.setIsbalanced(0);
	    Product p = productService.selectByPrimaryKey(productId);
	   
		sorder.setName(p.getName());
		sorder.setNumber(number);
		sorder.setPrice(p.getPrice());
		sorder.setProductId(p.getId());
		sorder.setUserId(getUserId(request));
		sorder.setId(MyUtils.getUUID());
		User user = new User();
		user.setId(getUserId(request));

        SorderExample example=new SorderExample();
        SorderExample.Criteria criteria=example.createCriteria();
        criteria.andUserIdEqualTo(user.getId());
        criteria.andProductIdEqualTo(productId);
        criteria.andIsbalancedEqualTo(0);
        List<Sorder> list=sorderService.selectByExample(example);
        Sorder  s = null;
        if(list.size()!=0){
            s = list.get(0);
        }
        if(s!=null&&s.getIsbalanced()==0){

            SorderExample example1=new SorderExample();
            SorderExample.Criteria criteria1=example1.createCriteria();
            criteria1.andUserIdEqualTo(user.getId());
            criteria1.andProductIdEqualTo( p.getId());
            criteria1.andIsbalancedEqualTo(0);
            List<Sorder> list1=sorderService.selectByExample(example);
            Sorder sorder1=list1.get(0);
            sorder1.setNumber(s.getNumber()+1);
            sorderService.updateByPrimaryKeySelective(sorder1);

            return ResultDTO.ok();
        }
	    int flag = sorderService.insert(sorder);
       if(flag==0)
    	   return ResultDTO.error();
       return ResultDTO.ok();
	}
	
	@ResponseBody
	@RequestMapping("/deleteSorderById")
	public ResultDTO deleteSorderById(String id ) {
		int flag =  sorderService.deleteByPrimaryKey(id);
               if(flag==0)
            	   return ResultDTO.error();
               else {
				return ResultDTO.ok();
			}
	}
	
	
	
	@ResponseBody
	@RequestMapping("/finishForderAndSorderByForderId")
	public ResultDTO finishForderAndSorderByForderId(double money, String forderId,@RequestParam(value="logistics" ,defaultValue="1") int logistics, HttpServletRequest request){
		 Forder forder = forderService.selectByPrimaryKey(forderId);
		 User user = new User();
		 user.setId(getUserId(request));
		 UserAddressExample userAddressExample = new UserAddressExample();
        UserAddressExample.Criteria criteria = userAddressExample.createCriteria();
        criteria.andUserIdEqualTo(user.getId());
        List<UserAddress> list = addressService.selectByExample(userAddressExample);
	     if(list.size()!=0)
	    	 forder.setAddressId(list.get(0).getId());
	     else {
             Map<String,Object> map = new HashMap<>();
             map.put("status", 1);
			return ResultDTO.buildSuccessDataError(map);
		}
	     User user2 = userService.selectByPrimaryKey(user.getId());
	     if(forder.getTotal().compareTo(user2.getAccountBalance()) == 1) {
             Map<String,Object> map = new HashMap<>();
             map.put("status", 2);
	    	 return ResultDTO.buildSuccessDataError(map);
	     }
	     user2.setAccountBalance(user2.getAccountBalance().subtract(forder.getTotal()));
	     userService.updateByPrimaryKeySelective(user2);
	     forder.setLogistics(logistics);
	     BigDecimal total = new BigDecimal(money);
	     forder.setTotal(total);
	     forder.setStatus(1);
	     //forder.setSuccessTime(new Date());
	     forder.setPayment(1);
	     if(total.doubleValue()>=5000){
	    	 Coupon coupon = new Coupon();
	    	 coupon.setStatus(0);
	    	 coupon.setUserId(getUserId(request));
	    	 coupon.setId(MyUtils.getUUID());
	    	 couponService.insert(coupon);
	     }
	     forderService.updateByPrimaryKeySelective(forder);
        Map<String,Object> map = new HashMap<>();
        map.put("addressId", list.get(0).getId());
	     return ResultDTO.buildSuccessData(map);
	}


	
	@ResponseBody
	@RequestMapping("/CheckForderStatus")
	public ResultDTO CheckForderStatus(String id) {
		 Forder forder = forderService.selectByPrimaryKey(id);
        if(forder!=null) {
            Map<String,Object> map = new HashMap<>();
            map.put("statue", forder.getStatus());
            return ResultDTO.buildSuccessData(map);
        }
        return ResultDTO.error();
	}
	
	@ResponseBody
	@RequestMapping("/Confirmreceipt")
    public ResultDTO Confirmreceipt(String id) {
        Forder forder = forderService.selectByPrimaryKey(id);
        if(forder==null)
            return ResultDTO.error();
        forder.setStatus(3);
        forder.setSuccessTime(LocalDateTime.now());
        SorderExample sorderExample = new SorderExample();
        SorderExample.Criteria criteria = sorderExample.createCriteria();
        criteria.andForderIdEqualTo(forder.getId());
        List<Sorder> list = sorderService.selectByExample(sorderExample);
        for (Sorder sorder : list) {
            Product product = productService.selectByPrimaryKey(sorder.getProductId());
            product.setSale(product.getSale()+sorder.getNumber());
            product.setMonthSale(product.getMonthSale()+sorder.getNumber());
            productService.updateByPrimaryKey(product);
        }
        forderService.updateByPrimaryKeySelective(forder);
        return ResultDTO.ok();
    }
	
	@ResponseBody
	@RequestMapping("/selectSorderByForderId")
    public ResultDTO selectSorderByForderId(String id){
	    SorderExample sorderExample = new SorderExample();
        SorderExample.Criteria criteria = sorderExample.createCriteria();
        criteria.andForderIdEqualTo(id);
        List<Sorder> list = sorderService.selectByExample(sorderExample);
        if(list.size()!=0) {
            Map<String,Object> map = new HashMap<>();
            map.put("list", list);
            return ResultDTO.buildSuccessData(map);
        }
        return ResultDTO.error();
    }
	
	
	@ResponseBody
	@RequestMapping("/selectPaiedForder")
	public ResultDTO selectPaiedForder(HttpServletRequest request) {
		User user = new User();
		List<List<Sorder>> bigList = new ArrayList<List<Sorder>>();
		user.setId(getUserId(request));
        ForderExample example = new ForderExample();
        ForderExample.Criteria criteria = example.createCriteria();
        criteria.andStatusNotEqualTo(0);
        criteria.andUserIdEqualTo(getUserId(request));
        List<Forder>  list =  forderService.selectByExample(example);
		/*if(list.size()!=0){
	    	   for(int i = 0 ; i < list.size() ; i ++ )
	    	   {
	    		    bigList.add(sorderService.selectSorderByForder(list.get(i).getId()));
	    	   }
	    	   if(bigList.size()!=0)*/
        Map<String,Object> map = new HashMap<>();
        map.put("list", list);
        return ResultDTO.buildSuccessData(map);
	      // }	     
	       //return Msg.error();
	}
	
	
	@ResponseBody
	@RequestMapping("/getSorderById")
	public ResultDTO getSorderById(HttpServletRequest request) {
		User user = new User();
		user.setId(getUserId(request));
        SorderExample example=new SorderExample();
        SorderExample.Criteria criteria=example.createCriteria();
        criteria.andUserIdEqualTo(user.getId());
        criteria.andIsbalancedEqualTo(0);
        List<Sorder> list =  sorderService.selectByExample(example);
        if(list.size()!=0){
            Map<String,Object> map = new HashMap<>();
            map.put("list", list);
			return ResultDTO.buildSuccessData(map);
		}
		return ResultDTO.error();
	}
	
	@ResponseBody
	@RequestMapping("/getOrderById")
	public ResultDTO getOrderById(int number, HttpServletRequest request) {
		System.out.println("number"+number);
		List<Forder> listForders;
		
		String id = (String) request.getSession().getAttribute("user_id");
		User user =  new User();
		user.setId(id);
        ForderExample example = new ForderExample();
        ForderExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(user.getId());
        forderService.selectByExample(example);

        switch (number) {
		case -1:
			listForders = forderService.selectByExample(example);
		    break;
	
		default: {
            criteria.andStatusEqualTo(number);
            listForders = forderService.selectByExample(example);
        }
			break;
		}
		 if(listForders.size()==0)
			 return ResultDTO.error();
		 else {
             Map<String,Object> map = new HashMap<>();
             map.put("list", listForders);
             return ResultDTO.buildSuccessData(map);
         }
		/*List<List<Sorder>> bigList = new ArrayList<List<Sorder>>();
		     for(Forder forder : listForders){
		    	 List<Sorder>  list = sorderService.selectSorderByForder(forder.getId());
		          if(list.size()!=0)  
		    	 bigList.add(list);
		     }
		
		
		System.out.println("size:"+bigList.size());
		if(bigList.size()!=0)
         return Msg.success().add("order", bigList);
		else {
			return Msg.error();
		}*/
	}
}
