package com.zx.backstage;

import com.zx.api.bean.Admin;
import com.zx.api.bean.EntityAndExample;
import com.zx.api.bean.User;
import com.zx.api.bean.UserExample;
import com.zx.api.utils.MyUtils;
import com.zx.backstage.service.AdminService;
import com.zx.backstage.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BackstageApplicationTests {

    private final static Logger logger = LoggerFactory.getLogger(BackstageApplicationTests.class);

//    @Autowired
//    RedisTemplate redisTemplate;
//
//    @Test
//    public void contextLoads() {
//        redisTemplate.opsForValue().set("k1", "a1");
//    }

    @Autowired
    UserService userService;

    @Test
    public void test() {
        User user = new User();
        user.setName("zzz");
        user.setPassword("66666");
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andNicknameEqualTo("cc");
        EntityAndExample<User,UserExample> exampleEntityAndExample = new EntityAndExample<>();
        exampleEntityAndExample.setEntity(user);
        exampleEntityAndExample.setExample(userExample);
        logger.info("-------->" + userService.updateByExampleSelective(exampleEntityAndExample));
    }

    @Test
    public void test1() {
        User user = new User();
        user.setPassword("123456");
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andNicknameEqualTo("cc");
        logger.info("-------->" + userService.selectByExample(userExample));
    }

    @Test
    public void test2() {
        User user = new User();
        user.setId(MyUtils.getId("zhouxin"));
        user.setPassword("123456");
        user.setName("zhouxin1");
        userService.insert(user);
    }

    @Autowired
    AdminService adminService;
    @Test
    public void test3() {
        Admin admin = new Admin();
        admin.setLogin("zhouxin");
        admin.setId(MyUtils.getId(admin.getLogin()));
        admin.setPassword("123456");
        admin.setRegTime(LocalDateTime.now());
        admin.setSystem(1);
        adminService.insert(admin);
    }

    @Test
    public void test4() {
        List<Admin> zx = adminService.login("zhouxin", "123456");
        Admin admin = zx.get(0);
        admin.setSystem(0);
        adminService.updateByPrimaryKey(admin);
        logger.info("------->"+zx.get(0).getId());
        logger.info("------->"+zx.get(0).getLastIp());
        logger.info("------->"+zx.get(0).getLogin());
    }


}
