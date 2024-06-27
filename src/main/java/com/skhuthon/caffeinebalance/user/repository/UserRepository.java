package com.skhuthon.caffeinebalance.user.repository;

import com.skhuthon.caffeinebalance.user.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
