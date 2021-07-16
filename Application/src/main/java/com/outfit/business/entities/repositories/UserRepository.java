package com.outfit.business.entities.repositories;

import com.outfit.business.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByEmail(String email);

    Long countByEmail(String email);

    Long countByUsername(String username);
}
