package com.lewis.msemployee;

import com.lewis.msemployee.entities.domain.Employee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class MsEmployeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsEmployeeApplication.class, args);
	}
}
