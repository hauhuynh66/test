package com.house.repository;

import com.house.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String email);
}
