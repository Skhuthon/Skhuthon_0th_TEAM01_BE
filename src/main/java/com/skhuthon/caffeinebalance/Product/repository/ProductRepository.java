package com.skhuthon.caffeinebalance.caffeine.repository;

import com.skhuthon.caffeinebalance.caffeine.domain.Product;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select distinct p.brand from Product p")
    Optional<List<String>> getBrands();

    @Query("select p.menu from Product p where p.brand = :brand")
    Optional<List<String>> findMenuByBrand(@Param("brand") String brand);

    @Query("select p.caffeine from Product p where p.brand = :brand and p.menu = :menu")
    Optional<Double> findCaffeineByMenu(@Param("brand") String brand, @Param("menu") String menu);

    List<Product> findByMenuContaining(String keyword);
}
