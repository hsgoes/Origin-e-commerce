package br.com.rd.Backend.controllers;

import br.com.rd.Backend.DTOs.CategoryDTO;
import br.com.rd.Backend.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/create-category")
    public ResponseEntity saveCategory (@RequestBody CategoryDTO categoryDTO) {
        return categoryService.saveCategory(categoryDTO);
    }

    @DeleteMapping("/delete-category/{id}")
    public ResponseEntity deleteCategoryById (@PathVariable ("id")Long id) {
        return  categoryService.deleteCategoryById(id);
    }

}
