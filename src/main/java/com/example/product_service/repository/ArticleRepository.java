package com.example.digid.digid.productservice.repository;

import com.example.digid.digid.productservice.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
//import org.springframework.data.mongodb.repository.MongoRepository;
/*
public interface ProductRepository extends MongoRepository<Product, String> {
}*/

public interface ArticleRepository extends JpaRepository<Article, String> {

    List<Article> findByDesignationIn(List<String> designation);
}
