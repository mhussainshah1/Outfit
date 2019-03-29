package com.example.demo.business.entities.repositories;

import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {
    Iterable<Item> findAllByCategory_Id(Long id);
    Iterable<Item> findAllByClimate_Id(Long id);
    Iterable<Item> findAllByOccasion_Id(Long id);
    Item findByName(String name);
}
