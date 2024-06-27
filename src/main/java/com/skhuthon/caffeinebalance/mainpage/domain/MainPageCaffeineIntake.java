package com.skhuthon.caffeinebalance.mainpage.domain;

import com.skhuthon.caffeinebalance.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "caffeine_intake")
public class MainPageCaffeineIntake {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "intake_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "amount", nullable = false)
    private int amount;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;
}
