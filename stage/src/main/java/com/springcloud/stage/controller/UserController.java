package com.springcloud.stage.controller;

import java.io.File;
import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.springcloud.stage.service.UserService;
import com.zx.api.bean.User;
import com.zx.api.bean.UserExample;
import com.zx.api.dto.ResultDTO;
import com.zx.api.utils.MD5Util;
import com.zx.api.utils.MyUtils;
import com.zx.api.utils.SendMailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


@RequestMapping("/User")
@Controller
public class UserController {

    private static final String SUCCESS = "success";
    private static final String Error = "error";

    @Autowired
    UserService service ;

    private String getUserId(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String user_id = -100+"";
        if (session.getAttribute("user_id") != null)
            user_id = (String) session.getAttribute("user_id");
        return user_id;
    }

    @ResponseBody
    @RequestMapping("/getMyInformtion")
    public ResultDTO getMyInformtion(HttpServletRequest request) {
        User user = service.selectByPrimaryKey(getUserId(request));
        if(user==null){
            return ResultDTO.error();
        }
        Map<String,Object> map = new HashMap<>();
        map.put("user", user);
        return ResultDTO.buildSuccessData(map);
    }

    @ResponseBody
    @RequestMapping("/updateUserById")
    public ResultDTO updateUserById (User user,@RequestParam(value = "file")MultipartFile file,HttpServletRequest request) throws ParseException {
        HttpSession  session = request.getSession();
        user.setEmail((String)session.getAttribute("email"));
        user.setId(getUserId(request));

        service.updateByPrimaryKeySelective(user);
        int flag = 1;

        if(file==null){
            System.out.println("file==null");
            return ResultDTO.ok();
        }
        if(file.getOriginalFilename()==""){
            System.out.println("file==??null");
            return ResultDTO.ok();
        }
        String path = request.getSession().getServletContext().getRealPath("uploads");

        System.out.println(path);
        java.util.Random r=new java.util.Random();
        String fileName = r.nextInt(100000)+".jpg";
        user.setAvatar(fileName);

        service.updateByPrimaryKeySelective(user);
        File targetFile = new File(path, fileName);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(flag==0){
            return ResultDTO.error();
        }return ResultDTO.ok();
    }


    @ResponseBody
    @RequestMapping("/logout")
    public ResultDTO logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("email");
        session.removeAttribute("name");
        session.removeAttribute("user_id");
        return ResultDTO.ok();
    }

    @RequestMapping("/login")
    @ResponseBody
    public ResultDTO login(User user,HttpServletRequest request) {
        UserExample example=new UserExample();
        UserExample.Criteria criteria=example.createCriteria();
        if(MyUtils.isEmail(user.getEmail()))
            criteria.andEmailEqualTo(user.getEmail());
        else if(MyUtils.isPhone(user.getEmail()))
            criteria.andPhoneEqualTo(user.getEmail());
        else
            return ResultDTO.error("用户不存在或密码错误");
        criteria.andPasswordEqualTo(MyUtils.md5Passwrod(user.getPassword()));
        System.out.println("email:"+user.getEmail());
        System.out.println("password:"+user.getPassword());
        List<User> list = service.selectByExample(example);
        System.out.println(list);
        if(list.size()==0){
            return ResultDTO.error("用户不存在或密码错误");
        }else {
            if(list.get(0).getIsabled() == 0) {
                return ResultDTO.error("用户被禁用");
            }
            HttpSession  session = request.getSession();
            session.setAttribute("email", list.get(0).getEmail());
            session.setAttribute("name", list.get(0).getName());
            session.setAttribute("user_id", list.get(0).getId());
            return ResultDTO.ok("welcome sir");
        }
    }

    @RequestMapping("/check_login")
    @ResponseBody
    public ResultDTO check_login(HttpServletRequest request){
        HttpSession  session = request.getSession();
        String name = (String)session.getAttribute("name");
        if(name == null){
            return ResultDTO.error();
        }
        Map<String,Object> map = new HashMap<>();
        map.put("name", name);
        return ResultDTO.buildSuccessData(map);
    }

    @ResponseBody
    @RequestMapping("/getUserMessage")
    public ResultDTO getUserMessage(HttpServletRequest request){
        HttpSession session = request.getSession();
        String	user_id =  (String) session.getAttribute("user_id");

        if(user_id == null){
            return ResultDTO.error();
        }else {
            User user = service.selectByPrimaryKey(user_id);
            Map<String,Object> map = new HashMap<>();
            map.put("user", user);
            return ResultDTO.buildSuccessData(map);
        }
    }

    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping("/validateEmail")
    public ResultDTO validateEmail(String email,HttpServletRequest request) {
        if (MyUtils.isEmail(email)) {
            UserExample userExample = new UserExample();
            UserExample.Criteria criteria = userExample.createCriteria();
            criteria.andEmailEqualTo(email);
            List<User> list = userService.selectByExample(userExample);
            if (list.size() == 0) {
                return ResultDTO.ok();
            } else {
                request.getSession().setAttribute("find_email_user", list.get(0));
//					System.out.println(list.get(0).getEmail()+list.get(0).getName());
                return ResultDTO.error("邮箱存在");
            }
        } else {
            return ResultDTO.error("邮箱格式错误");
        }
    }

    private static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }


    @ResponseBody
    @RequestMapping("/zhuce")
    public ResultDTO zhuce(User user) {
        String email = user.getEmail();
        user.setAccountSecurity(60);
        //Long balanceLong = (long) 0;
        user.setAccountBalance(new BigDecimal(0.00));
        user.setRegTime(LocalDateTime.now());
        user.setMemberLevel(0);
        user.setIsabled(0);
        user.setId(MyUtils.getUUID());
        String pString = user.getPassword();
        user.setPassword(MyUtils.md5Passwrod(pString));
        int i = userService.insert(user);

        UserExample example=new UserExample();
        UserExample.Criteria criteria=example.createCriteria();
        criteria.andEmailEqualTo(email);
        criteria.andPasswordEqualTo(MyUtils.md5Passwrod(pString));
        System.out.println("email:"+email);
        System.out.println("password:"+pString);
        List<User> list = userService.selectByExample(example);
        System.out.println(list);

        StringBuffer content = new StringBuffer("<h2>DGUT</h2>");
        content.append("欢迎注册天猫会员<br>点击<a href='http://localhost:8301/graducation/User/user_activity/"+list.get(0).getId()+"/"+MyUtils.md5Passwrod(user.getEmail())+"'>此处链接</a>激活账号");
        SendMailUtil.send(email, content.toString());
        if(i==1) {
            return ResultDTO.ok();
        }else {

            return ResultDTO.error();
        }

    }

    @ResponseBody
    @RequestMapping("/user_activity/{id}/{email}")
    public String user_activity(@PathVariable String id,@PathVariable String email) {
        User user = userService.selectByPrimaryKey(id);
        if(MyUtils.md5Passwrod(user.getEmail()).equals(email)) {
            user.setIsabled(1);
            userService.updateByPrimaryKeySelective(user);
            return "Activation success";
        }else {
            return "Activation failure";
        }
    }

    @ResponseBody
    @RequestMapping("/sendEmail")
    public ResultDTO sendEmail(String email,HttpServletRequest request) {
        StringBuffer content = new StringBuffer("<h2>five group</h2>");
        content.append("<a href = 'http://localhost:8301/graducation/views/change_password.html"+"'>点击此处修改密码</a>");
        SendMailUtil.send(email, content.toString());
        return null;

    }
    @ResponseBody
    @RequestMapping("/changePass")
    public ResultDTO changePass(String password,HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("find_email_user");
        if(null == user){
            return ResultDTO.error();
        }
        user.setPassword(MyUtils.md5Passwrod(password));
        int i = userService.updateByPrimaryKey(user);
        if(i==1) {
            return ResultDTO.ok();
        }else {
            return ResultDTO.error();
        }
    }

}
