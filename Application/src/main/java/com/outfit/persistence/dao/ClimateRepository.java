package com.outfit.persistence.dao;

import com.outfit.persistence.model.Climate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClimateRepository extends JpaRepository<Climate, Long> {
    Climate findByName(String name);
}
