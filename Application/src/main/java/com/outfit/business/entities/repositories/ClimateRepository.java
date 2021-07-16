package com.outfit.business.entities.repositories;

import com.outfit.business.entities.Climate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClimateRepository extends JpaRepository<Climate, Long> {
    Climate findByName(String name);
}
