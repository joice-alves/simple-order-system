package com.jma.orderapi.repository;

import com.jma.orderapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Este método valida o userName no Service depois
    boolean existsByUsername(String username);

    java.util.Optional<User> findByUsername(String username);
}