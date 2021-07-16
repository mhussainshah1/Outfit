package com.outfit.business.entities.repositories;

import com.outfit.business.entities.InvalidPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvalidPasswordRepository extends JpaRepository<InvalidPassword, Long> {
}
