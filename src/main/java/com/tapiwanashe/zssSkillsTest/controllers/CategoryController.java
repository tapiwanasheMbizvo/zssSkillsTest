package com.tapiwanashe.zssSkillsTest.controllers;

import com.tapiwanashe.zssSkillsTest.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.tapiwanashe.zssSkillsTest.repositories.CategoryRepository;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    private List<Category> getAllCategories (){

        return  categoryRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private  Category createCategory (@RequestBody Category category){


        return  categoryRepository.save(category);

    }

}
