package com.airbnb.airbnb.repository;

import com.airbnb.airbnb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String nickname);
    Optional<User> findByEmail(String email);
}
