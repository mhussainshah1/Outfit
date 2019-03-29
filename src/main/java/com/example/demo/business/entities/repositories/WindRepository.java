package com.example.demo.business.entities.repositories;

import com.example.demo.business.entities.Wind;
import org.springframework.data.repository.CrudRepository;

public interface WindRepository extends CrudRepository<Wind, Long> {
    Wind findByName(String name);
}
