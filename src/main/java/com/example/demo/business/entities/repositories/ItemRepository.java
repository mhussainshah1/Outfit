package com.example.demo.business.entities.repositories;

import com.example.demo.business.entities.Category;
import com.example.demo.business.entities.Climate;
import com.example.demo.business.entities.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, Long> {
    Iterable<Item> findAllByCategory_Id(Long id);
    Iterable<Item> findAllByClimate_Id(Long id);
    Iterable<Item> findAllByOccasion_Id(Long id);
    Item findByName(String name);
    Iterable<Item> findAllByWind_Id(Long id);
    List<Item> findAllByCategoryAndClimate(Category category, Climate climate);
}
