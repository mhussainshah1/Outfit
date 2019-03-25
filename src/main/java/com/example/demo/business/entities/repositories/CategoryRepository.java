package com.example.demo.business.entities.repositories;

import com.example.demo.business.entities.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
  //Category findByTitle(String category_title);
}
