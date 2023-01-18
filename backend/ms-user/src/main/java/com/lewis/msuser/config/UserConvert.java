package com.lewis.msuser.config;
import com.lewis.msuser.entities.domain.User;
import com.lewis.msuser.entities.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserConvert {

    @Autowired
    private ModelMapper modelMapper;

    public List<UserDTO> toUsersDTO(List<User> usersEntity) {
        List<UserDTO> usersDTO = new ArrayList<>();
        for(User user: usersEntity)
        {
            UserDTO userDTO = modelMapper.map(user, UserDTO.class);
            usersDTO.add(userDTO);
        }
        return usersDTO;
    }
}
