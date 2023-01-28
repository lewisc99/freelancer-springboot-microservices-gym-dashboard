package com.lewis.msuser;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {MsUserApplication.class}, properties = {"spring.jpa.defer-datasource-initialization=false",
		"spring.profiles.active=test"})
class MsUserApplicationTests {

	@Test
	void contextLoads() {
	}

}
