package com.zx.persistence;

import com.zx.api.utils.MyUtils;
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

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersistenceApplicationTests {
    private final static Logger logger = LoggerFactory.getLogger(PersistenceApplicationTests.class);
    @Autowired
    IdGenerator idGenerator;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
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

    @Test
    public void test2(){
        User user = new User();
        user.setName("周鑫");
        user.setId(MyUtils.getId(user.getName()));
        user.setPassword("12345");
        user.setBirthday(LocalDateTime.now());
        user.setEmail("1561578781@qq.com");
        user.setIsabled(1);
        user.setSex(1);
        user.setRegTime(LocalDateTime.now());
        user.setNickname("zx");
        user.setAccountBalance(new BigDecimal(123));
        user.setPhone("17336030376");
        mapper.insert(user);
    }

    @Test
    public void test3(){
        User user = mapper.selectByPrimaryKey("3ff39b3fc51157fe7c68083f813da716");
        logger.info(user.getBirthday()+"");
    }
}
