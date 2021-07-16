package com.outfit.business.entities.repositories;

import com.outfit.business.entities.Occasion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OccasionRepository extends JpaRepository<Occasion, Long> {
    Occasion findByName(String name);
}
