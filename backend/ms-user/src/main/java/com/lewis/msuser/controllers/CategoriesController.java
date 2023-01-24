package com.lewis.msuser.controllers;

import com.lewis.msuser.entities.domain.Category;
import com.lewis.msuser.entities.dto.CategoryDTO;
import com.lewis.msuser.entities.dto.UsersDTO;
import com.lewis.msuser.services.contracts.CategoryService;
import io.swagger.annotations.ApiOperation;
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


    @ApiOperation(value="get all Categories",
            notes = "will get all categories this method is called to help in the frontend to create a form containing all categories",
            response = Category.class
    )
    @GetMapping
    public ResponseEntity<List<Category>> get()
    {
        List<Category> categories = categoryService.findAll();

       return ResponseEntity.ok(categories);
    }

}
