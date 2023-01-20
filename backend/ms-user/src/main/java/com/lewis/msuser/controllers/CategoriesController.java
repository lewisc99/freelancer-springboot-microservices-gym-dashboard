package com.lewis.msuser.controllers;

import com.lewis.msuser.entities.domain.Category;
import com.lewis.msuser.services.contracts.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/categories")
@CrossOrigin(value = "http://localhost:4200")
public class CategoriesController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> get()
    {
        List<Category> categories = categoryService.findAll();

       return ResponseEntity.ok(categories);
    }
}
