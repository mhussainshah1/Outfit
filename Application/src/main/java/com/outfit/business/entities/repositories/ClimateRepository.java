package com.outfit.business.entities.repositories;

import com.outfit.business.entities.Climate;
import org.springframework.data.repository.CrudRepository;

public interface ClimateRepository extends CrudRepository<Climate, Long> {
    Climate findByName(String name);
}
