package com.example.lottocrawler.repository;

import com.example.lottocrawler.domain.Store;
import com.example.lottocrawler.dto.StoreDto;
import com.example.lottocrawler.dto.StoreStatisticsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {
    @Query(value = "SELECT name, address, count(*) AS count FROM Store where lotto_type = 0 group by name, address order by count(*) desc", nativeQuery = true)
    List<StoreStatisticsDto> findGroupByName();
    List<Store> findByNameAndAddress(String name, String address);
}
