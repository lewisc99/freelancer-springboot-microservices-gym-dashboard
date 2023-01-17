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

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll(int pagNumber, int pagSize,String sortBy)
    {
        Pageable paging = PageRequest.of(pagNumber,pagSize, Sort.by(sortBy));
        Page<User> page =  userRepository.findAll(paging);
        return page.toList();
    }
}
