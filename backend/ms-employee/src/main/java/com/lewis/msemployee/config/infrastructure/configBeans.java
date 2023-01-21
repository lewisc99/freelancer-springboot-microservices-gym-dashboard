package com.lewis.msemployee.config.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class configBeans {

    @Bean
    @Autowired
    public BCryptPasswordEncoder passwordEncoder()
    {

        return new BCryptPasswordEncoder();

    }

}
