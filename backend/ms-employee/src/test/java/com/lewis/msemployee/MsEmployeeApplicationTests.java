package com.lewis.msemployee;

import com.lewis.msemployee.mockclasses.classesBeanConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(classesBeanConfig.class)
class MsEmployeeApplicationTests {

	@Test
	void contextLoads() {
	}

}
