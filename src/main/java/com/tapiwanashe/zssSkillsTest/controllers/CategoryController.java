package com.tapiwanashe.zssSkillsTest.controllers;

import com.tapiwanashe.zssSkillsTest.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.tapiwanashe.zssSkillsTest.repositories.CategoryRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

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

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    private Optional<Category> getONeCategory(@PathVariable(name = "id") Long id){

        return  categoryRepository.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private  Category createCategory (@RequestBody Category category){


        return  categoryRepository.save(category);

    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    private void doDelete(@PathVariable(name = "id") Long id){

        categoryRepository.deleteById(id);
    }

}
