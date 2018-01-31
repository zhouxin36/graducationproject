package com.zx.persistence;

import com.zx.api.utils.MD5Util;
import com.zx.api.utils.MyUtils;
import com.zx.persistence.bean.CategoryExample;
import com.zx.persistence.bean.Coupon;
import com.zx.persistence.bean.User;
import com.zx.persistence.bean.UserExample;
import com.zx.persistence.dao.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.IdGenerator;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersistenceApplicationTests {
    private final static Logger logger = LoggerFactory.getLogger(PersistenceApplicationTests.class);
    @Autowired
    IdGenerator idGenerator;

    @Autowired
    UserMapper mapper;


//	@Test
//	public void contextLoads() {
//        mapper.selectByExample(null)
//        .forEach((e)->{
//            String id = MyUtils.getId(new Date().toString());
//            Coupon user = new Coupon();
//            user.setId(id);
//            CategoryExample userExample = new CategoryExample();
//            CategoryExample.Criteria criteria = userExample.createCriteria();
//            criteria.andTypeEqualTo(new Date().toString());
//            mapper.updateByExampleSelective(user,null);
//        });
//    }
//
//    @Test
//    public  void test(){
//	    logger.info("-------->"+MyUtils.getId("周鑫"));
//	    logger.info("-------->"+ MD5Util.MD5("周鑫"));
//    }

    @Test
    public void test1() {
        User user = new User();
        user.setPassword("123456");
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andNicknameEqualTo("cc");
        logger.info("-------->" + mapper.updateByExampleSelective(user,userExample));
    }
}
