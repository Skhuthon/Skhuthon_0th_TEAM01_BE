package com.skhuthon.caffeinebalance.mypage.repository;

import com.skhuthon.caffeinebalance.mypage.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
