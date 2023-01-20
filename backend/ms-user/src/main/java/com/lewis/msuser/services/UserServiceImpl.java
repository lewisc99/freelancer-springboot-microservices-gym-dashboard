package com.lewis.msuser.services;

import com.lewis.msuser.entities.domain.User;
import com.lewis.msuser.entities.models.PageModel;
import com.lewis.msuser.entities.models.UserModel;
import com.lewis.msuser.repositories.UserRepository;
import com.lewis.msuser.services.contracts.CategoryService;
import com.lewis.msuser.services.contracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void create(User user)
    {
        userRepository.save(user);
    }

    @Override
    public Page<User> findAll(PageModel pageModel)
    {
        Pageable paging = PageRequest.of(pageModel.getPagNumber(),pageModel.getPagSize(), Sort.by(pageModel.getSortBy()));
        return userRepository.findAll(paging);
    }

    @Override
    public User findById(UUID id)
    {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty())
            throw new NullPointerException(id + "");

        return user.get();
    }
    @Override
    public void delete(UUID id) {
          userRepository.deleteById(id);
    }

    @Override
    public void update(UserModel userModel) {
         User oldUser = findById(userModel.id);
         availablePropertiesToUpdate(oldUser, userModel);
         userRepository.save(oldUser);
    }
    public void availablePropertiesToUpdate(User oldUser, UserModel userUpdate)
    {
        oldUser.setDoc(userUpdate.getDoc());
        oldUser.setUsername(userUpdate.getUsername());
        oldUser.setAge(userUpdate.getAge());
        oldUser.setEmail(userUpdate.getEmail());
        oldUser.getPlan().setStart(userUpdate.getPlan().getStart());
        oldUser.getPlan().setFinish(userUpdate.getPlan().getFinish());
        oldUser.getPlan().setStatus(userUpdate.getPlan().getStatus());
        oldUser.getPlan().getCategory().setId(userUpdate.getPlan().getCategory().getId());
        oldUser.getPlan().getCategory().setName(userUpdate.getPlan().getCategory().getName());
    }
}
