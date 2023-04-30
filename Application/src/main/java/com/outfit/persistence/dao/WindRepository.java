package com.outfit.persistence.dao;

import com.outfit.persistence.model.Wind;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WindRepository extends JpaRepository<Wind, Long> {
    Wind findByName(String name);
}
