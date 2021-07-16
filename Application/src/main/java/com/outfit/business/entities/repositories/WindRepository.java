package com.outfit.business.entities.repositories;

import com.outfit.business.entities.Wind;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WindRepository extends JpaRepository<Wind, Long> {
    Wind findByName(String name);
}
