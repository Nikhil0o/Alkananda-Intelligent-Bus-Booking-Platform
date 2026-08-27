package com.example.alkananda.repository;

import com.example.alkananda.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface userRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);
}
