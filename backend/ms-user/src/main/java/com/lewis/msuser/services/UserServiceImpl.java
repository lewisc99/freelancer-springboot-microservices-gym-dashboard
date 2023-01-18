package com.lewis.msuser.services;

import com.lewis.msuser.config.UserConvert;
import com.lewis.msuser.entities.domain.User;
import com.lewis.msuser.entities.dto.UserDTO;
import com.lewis.msuser.entities.dto.UsersDTO;
import com.lewis.msuser.entities.models.UserModel;
import com.lewis.msuser.entities.models.PageModel;
import com.lewis.msuser.repositories.UserRepository;
import com.lewis.msuser.services.contracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConvert userConvert;

    @Override
    public void create(User user)
    {
        userRepository.save(user);
    }

    @Override
    public UsersDTO findAll(PageModel pageModel)
    {
        Pageable paging = PageRequest.of(pageModel.getPagNumber(),pageModel.getPagSize(), Sort.by(pageModel.getSortBy()));
        Page<User> page =  userRepository.findAll(paging);
        List<UserDTO> usersConvertedToDTO = userConvert.toUsersDTO(page.toList());

        return userConvert.toUsersWithPagination(pageModel, page, usersConvertedToDTO);

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
        try
        {
            userRepository.deleteById(id);
        } catch (NullPointerException e)
        {
            throw new NullPointerException();
        }
    }

    @Override
    public void update(UserModel userModel) {
         User oldUser = findById(userModel.id);
         availablePropertiesToUpdate(oldUser, userModel);
    }

    public void availablePropertiesToUpdate(User oldUser, UserModel userUpdate)
    {

    }




}
