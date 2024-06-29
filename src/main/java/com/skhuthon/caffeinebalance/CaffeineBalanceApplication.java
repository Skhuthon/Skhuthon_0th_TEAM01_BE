package com.skhuthon.caffeinebalance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CaffeineBalanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CaffeineBalanceApplication.class, args);
    }

}
