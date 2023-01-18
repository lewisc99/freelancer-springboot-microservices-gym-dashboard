package com.lewis.msuser.services.contracts;
import com.lewis.msuser.entities.domain.User;
import com.lewis.msuser.entities.dto.UsersDTO;
import com.lewis.msuser.entities.models.UserModel;
import com.lewis.msuser.entities.models.pageModel;
import java.util.UUID;

public interface UserService {
     UsersDTO findAll(pageModel page);
     User findById(UUID id);
     void  create(User user);
     void update(UserModel user);
     void delete(UUID id);
}
