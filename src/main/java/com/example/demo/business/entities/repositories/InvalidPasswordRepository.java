package com.example.demo.business.entities.repositories;

import com.example.demo.business.entities.InvalidPassword;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InvalidPasswordRepository extends CrudRepository<InvalidPassword, Long> {
//List<InvalidPassword> findAllByValueOrderByValue();
}
