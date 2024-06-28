package com.skhuthon.caffeinebalance.mainpage.service;

import com.skhuthon.caffeinebalance.mainpage.domain.MainPageDrink;
import com.skhuthon.caffeinebalance.mainpage.repository.MainPageDrinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MainPageDrinkService {

    private final MainPageDrinkRepository mainPageDrinkRepository;

    public List<MainPageDrink> recommendDrinks(int canCaffeineIntakeAmount) {
        List<MainPageDrink> allDrinks = mainPageDrinkRepository.findAll();
        List<MainPageDrink> recommendedDrinks = new ArrayList<>();
        for (MainPageDrink mainPageDrink : allDrinks) {
            if (mainPageDrink.getCaffeineAmount() <= canCaffeineIntakeAmount) {
                recommendedDrinks.add(mainPageDrink);
            }
        }
        return recommendedDrinks;
    }
}
