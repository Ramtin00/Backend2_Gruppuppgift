package com.example.springboot_grupparbete.Repositories;

import com.example.springboot_grupparbete.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
