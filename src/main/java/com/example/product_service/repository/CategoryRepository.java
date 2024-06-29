package com.example.digid.digid.productservice.repository;

import com.example.digid.digid.productservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category , Integer > {
}
