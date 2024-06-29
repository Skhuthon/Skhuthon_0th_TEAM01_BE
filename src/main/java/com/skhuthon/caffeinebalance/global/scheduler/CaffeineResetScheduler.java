package com.skhuthon.caffeinebalance.global.scheduler;

import com.skhuthon.caffeinebalance.user.repository.UserRepository;
import com.skhuthon.caffeinebalance.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CaffeineResetScheduler {
    private final UserRepository userRepository;

    @Transactional
    @Scheduled(cron = "0 0 0 * * ?")
    public void resetDailyCaffeineIntake() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            user.resetDailyCaffeine();
        }
    }
}
