package com.lewis.msuser.services;

import com.lewis.msuser.entities.domain.Category;
import com.lewis.msuser.repositories.CategoryRepository;
import com.lewis.msuser.services.contracts.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
      return  categoryRepository.findAll();
    }
}
