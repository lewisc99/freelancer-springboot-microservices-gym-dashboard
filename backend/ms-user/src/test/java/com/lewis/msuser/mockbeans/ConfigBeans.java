package com.lewis.msuser.mockbeans;


import com.lewis.msuser.config.UserConvert;
import com.lewis.msuser.entities.domain.Category;
import com.lewis.msuser.entities.domain.Plan;
import com.lewis.msuser.entities.domain.User;
import com.lewis.msuser.entities.models.CategoryModel;
import com.lewis.msuser.entities.models.PlanModel;
import com.lewis.msuser.entities.models.UserModel;
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

    @Bean
    @Scope(value="prototype")
    public UserModel getUserModel()
    {
        return new UserModel();
    }

    @Bean
    @Scope(value="prototype")
    public PlanModel getPlanModel()
    {
        return new PlanModel();
    }

    @Bean
    @Scope(value="prototype")
    public CategoryModel getCategoryModel()
    {
        return new CategoryModel();
    }

}
