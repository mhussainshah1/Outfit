package com.example.demo.business.entities.repositories;

import com.example.demo.business.entities.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {

    Item findByName(String name);

}
