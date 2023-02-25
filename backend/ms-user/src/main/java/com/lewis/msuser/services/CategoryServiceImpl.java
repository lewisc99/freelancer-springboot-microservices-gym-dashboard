package com.lewis.msuser.services;

import com.lewis.msuser.entities.domain.Category;
import com.lewis.msuser.repositories.CategoryRepository;
import com.lewis.msuser.services.contracts.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
      return  categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(UUID id) {return categoryRepository.findById(id);}
}
