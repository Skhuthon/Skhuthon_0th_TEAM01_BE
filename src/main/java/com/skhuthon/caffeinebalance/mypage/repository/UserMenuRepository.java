package com.skhuthon.caffeinebalance.mypage.repository;

import com.skhuthon.caffeinebalance.mypage.model.User;
import com.skhuthon.caffeinebalance.mypage.model.UserMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserMenuRepository extends JpaRepository<UserMenu, Long> {
    List<UserMenu> findByUser(User user);
}
