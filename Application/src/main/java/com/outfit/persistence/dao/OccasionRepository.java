package com.outfit.persistence.dao;

import com.outfit.persistence.model.Occasion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OccasionRepository extends JpaRepository<Occasion, Long> {
    Occasion findByName(String name);
}
