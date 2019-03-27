package com.example.demo.business.entities.repositories;

import com.example.demo.business.entities.Temperature;
import org.springframework.data.repository.CrudRepository;

public interface TemperatureRepository extends CrudRepository<Temperature, Long> {
}
