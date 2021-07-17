package com.outfit.business.entities.repositories;

import com.outfit.business.entities.InvalidPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvalidPasswordRepository extends JpaRepository<InvalidPassword, Long> {
    Optional<InvalidPassword> findInvalidPasswordByValue(String value);
}
