package com.lewis.msuser.services;

import com.lewis.msuser.entities.domain.User;
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


    @Override
    public void create(User user)
    {
        userRepository.save(user);
    }
    @Override
    public List<User> findAll(int pagNumber, int pagSize,String sortBy)
    {
        try
        {
        Pageable paging = PageRequest.of(pagNumber,pagSize, Sort.by(sortBy));
        Page<User> page =  userRepository.findAll(paging);
        return page.toList();
        }
        catch (IllegalArgumentException exception)
        {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public User findById(UUID id)
    {
        try
        {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty())
            throw new NullPointerException();

        return user.get();
        }
        catch (NullPointerException e)
        {
            throw new NullPointerException();
        }
        catch (Exception e)
        {
            throw new RuntimeException();
        }

    }
}
