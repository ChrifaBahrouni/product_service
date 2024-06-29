package com.example.digid.digid.productservice.controller;


import com.example.digid.digid.productservice.dto.ProductRequest;
import com.example.digid.digid.productservice.dto.ProductResponse;
import com.example.digid.digid.productservice.model.Category;
import com.example.digid.digid.productservice.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@CrossOrigin(origins = "*")
public class CategoriyController {

    @Autowired
    CategoryRepository repository ;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Category createProduct(@RequestBody Category category) {
        return  repository.save(category);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Category> getAllcategories() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Category getcategory(@PathVariable Integer id ) {
        return repository.findById(id).get() ;
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String   deletecategory(@PathVariable Integer id ) {
         repository.deleteById(id) ;
          return  " Catégorie supprimer avec succées " ;
    }


}
