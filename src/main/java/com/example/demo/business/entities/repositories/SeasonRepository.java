package com.example.demo.business.entities.repositories;

import com.example.demo.business.entities.Season;
import org.springframework.data.repository.CrudRepository;

import java.util.Iterator;

public interface SeasonRepository extends CrudRepository<Season, Long> {
    Season findByName(String name);
}
