package com.lewis.msuser.services.contracts;
import com.lewis.msuser.entities.domain.User;
import java.util.List;
import java.util.UUID;

public interface UserService {
     List<User> findAll(int pagNumber, int pagSize, String sortBy);
     User findById(UUID id);
     void  create(User user);
  //   void update(User user);

     void delete(UUID id);
}
