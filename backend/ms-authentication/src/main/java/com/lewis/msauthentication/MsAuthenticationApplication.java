package com.lewis.msauthentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableFeignClients
@EnableWebMvc
@EnableEurekaClient
public class MsAuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsAuthenticationApplication.class, args);
	}

}
