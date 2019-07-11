package com.example.demo.business.entities.repositories;

import com.example.demo.business.entities.Category;
import com.example.demo.business.entities.Climate;
import com.example.demo.business.entities.Item;
import com.example.demo.business.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> { //Use JpaRepository for pagination
    Iterable<Item> findAllByCategory_Id(Long id);

    Iterable<Item> findAllByClimate_Id(Long id);

    Iterable<Item> findAllByOccasion_Id(Long id);

    Iterable<Item> findAllByWind_Id(Long id);

    Item findByName(String name);

    Iterable<Item> findAllByUser(User user);

    Page<Item> findAllByUser(User user, Pageable pageable);

    //For detail page with user
    Iterable<Item> findAllByCategory_IdAndUser(Long id, User user);

    Iterable<Item> findAllByClimate_IdAndUser(Long id, User user);

    Iterable<Item> findAllByOccasion_IdAndUser(Long id, User user);

    Iterable<Item> findAllByWind_IdAndUser(Long id, User user);

    List<Item> findAllByCategoryAndClimate(Category category, Climate climate); //for admin

    List<Item> findAllByCategoryAndClimateAndUser(Category category, Climate climate, User user);

    //Search by Name and Description
    Page<Item> findAllByNameContainingOrDescriptionContainingAllIgnoreCase(String title, String description, Pageable pageable);

    Page<Item> findAllByUserAndNameContainingOrUserAndDescriptionContainingAllIgnoreCase(User user1, String title, User user, String description, Pageable pageable);

}
