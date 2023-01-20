package com.lewis.msuser.services.contracts;

import com.lewis.msuser.entities.domain.Category;

import java.util.List;



public interface CategoryService {
    List<Category> findAll();
}
