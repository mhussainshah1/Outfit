package com.example.demo.business.entities.repositories;

import com.example.demo.business.entities.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {
    Iterable<Item> findAllByCategory_Id(Long id);
    Item findByName(String name);
}
