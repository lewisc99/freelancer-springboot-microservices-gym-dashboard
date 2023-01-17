package com.lewis.msuser.mockbeans;


import com.lewis.msuser.entities.domain.Category;
import com.lewis.msuser.entities.domain.Plan;
import com.lewis.msuser.entities.domain.User;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.*;


@ComponentScan(basePackages = {"com.lewis.msuser"})
@TestConfiguration
public class ConfigBeans {

    @Bean
    @Scope(value = "prototype")
    public User getUser()
    {
        return new User();
    }


    @Bean
    @Scope(value="prototype")
    public Plan getPlan()
    {
        return new Plan();
    }
    @Bean
    @Scope(value="prototype")
    public Category getCategory()
    {
        return new Category();
    }

}
