package com.springcloud.stage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class StageApplicationTests {

    Logger logger= LoggerFactory.getLogger(StageApplicationTests.class);
	@Value("${file_base_url}")
	String FILE_BASE_URL;
	@Test
	public void contextLoads() {
        logger.info("---------->"+FILE_BASE_URL);
	}

}
