package com.outfit.persistence.dao;

import com.outfit.persistence.model.Category;
import com.outfit.persistence.model.Climate;
import com.outfit.persistence.model.Item;
import com.outfit.persistence.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
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
