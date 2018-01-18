package com.zx.persistence;

import com.zx.api.bean.User;
import com.zx.persistence.dao.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.IdGenerator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersistenceApplicationTests {

    @Autowired
    IdGenerator idGenerator;

	@Autowired
	UserMapper userMapper;
	@Test
	public void contextLoads() {
		for (int i = 0; i < 10; i++) {
			User user = new User();
			user.setAge(i);
			user.setUsername(idGenerator.generateId().toString());
			user.setPassword(i+"");
			userMapper.insert(user);
		}
	}

}
