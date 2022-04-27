package com.outfit.business.entities.repositories;

import com.outfit.business.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Locale;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String category_title);
}
