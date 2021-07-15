package com.outfit.business.entities.repositories;

import com.outfit.business.entities.Occasion;
import org.springframework.data.repository.CrudRepository;

public interface OccasionRepository extends CrudRepository<Occasion, Long> {
    Occasion findByName(String name);
}
