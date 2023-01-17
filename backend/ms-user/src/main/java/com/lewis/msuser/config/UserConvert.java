package com.lewis.msuser.config;
import com.lewis.msuser.entities.domain.User;
import com.lewis.msuser.entities.dto.CategoryDTO;
import com.lewis.msuser.entities.dto.PlanDTO;
import com.lewis.msuser.entities.dto.UserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserConvert {

    public  List<UserDTO> toListUserDTO(List<User> usersEntity) {
        List<UserDTO> usersDTO = new ArrayList<>();
        for (User user : usersEntity) {
            UserDTO userDTO = new UserDTO();
            PlanDTO planDTO = new PlanDTO();
            CategoryDTO categoryDTO = new CategoryDTO();

            BeanUtils.copyProperties( user,userDTO);
            BeanUtils.copyProperties(user.getPlan(), planDTO);
            BeanUtils.copyProperties(user.getPlan().getCategory(), categoryDTO);
            planDTO.setCategory(categoryDTO);
            userDTO.setPlan(planDTO);
            usersDTO.add(userDTO);
        }
        return usersDTO;
    }
}
