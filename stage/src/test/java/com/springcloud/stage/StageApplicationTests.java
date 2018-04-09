package com.springcloud.stage;

import com.zx.api.bean.Message;
import com.zx.api.utils.MyUtils;
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

	@Test
	public void test(){
		String id = "CF31FBFB-03A0-43C8-8853-B8821398B33D";
		String id2 = "cf31fbfb-03a0-43c8-8853-b8821398b33d";
		logger.info("--------->"+id.toLowerCase().trim());
		logger.info("--------->"+ MyUtils.isEquals(id.toLowerCase().trim(),id2.toLowerCase().trim()));
	}

}
