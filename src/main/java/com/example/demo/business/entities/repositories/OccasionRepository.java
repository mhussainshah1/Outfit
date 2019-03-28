package com.example.demo.business.entities.repositories;

import com.example.demo.business.entities.Occasion;
import org.springframework.data.repository.CrudRepository;

public interface OccasionRepository extends CrudRepository<Occasion, Long> {
    Occasion findByName(String name);
}
