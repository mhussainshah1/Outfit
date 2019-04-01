package com.example.demo.business.entities.repositories;

import com.example.demo.business.entities.Category;
import com.example.demo.business.entities.Climate;
import com.example.demo.business.entities.Item;
import com.example.demo.business.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, Long> {
    Iterable<Item> findAllByCategory_Id(Long id);

    Iterable<Item> findAllByClimate_Id(Long id);

    Iterable<Item> findAllByOccasion_Id(Long id);

    Iterable<Item> findAllByWind_Id(Long id);

    Item findByName(String name);

    Iterable<Item> findAllByUser(User user);

    //For detail page with user
    Iterable<Item> findAllByCategory_IdAndUser(Long id, User user);

    Iterable<Item> findAllByClimate_IdAndUser(Long id, User user);

    Iterable<Item> findAllByOccasion_IdAndUser(Long id, User user);

    Iterable<Item> findAllByWind_IdAndUser(Long id, User user);

    List<Item> findAllByCategoryAndClimate(Category category, Climate climate); //for admin

    List<Item> findAllByCategoryAndClimateAndUser(Category category, Climate climate, User user);


}
