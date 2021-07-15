package com.outfit.business.entities.repositories;

import com.outfit.business.entities.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    Category findByName(String category_title);
}
