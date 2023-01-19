package com.lewis.msuser.services.contracts;
import com.lewis.msuser.entities.domain.User;
import com.lewis.msuser.entities.models.PageModel;
import com.lewis.msuser.entities.models.UserModel;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface UserService {
     Page<User> findAll(PageModel page);
     User findById(UUID id);
     void  create(User user);
     void update(UserModel user);
     void delete(UUID id);
}
