package com.example.lottocrawler.repository;

import com.example.lottocrawler.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
