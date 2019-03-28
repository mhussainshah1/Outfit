package com.example.demo.business.entities.repositories;

import com.example.demo.business.entities.Climate;
import org.springframework.data.repository.CrudRepository;

public interface ClimateRepository extends CrudRepository<Climate, Long> {
    Climate findByName(String name);
}
