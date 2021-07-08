package com.outfit.business.entities.repositories;

import com.outfit.business.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);

    User findByEmail(String email);

    Long countByEmail(String email);

    Long countByUsername(String username);
}
