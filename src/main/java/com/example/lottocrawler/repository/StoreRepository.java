package com.example.lottocrawler.repository;

import com.example.lottocrawler.domain.Store;
import com.example.lottocrawler.domain.StoreId;
import com.example.lottocrawler.dto.StoreStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, StoreId> {
    @Query(value = "SELECT name, address, count(*) AS count FROM Store where lotto_type = 0 group by name, address order by count(*) desc", nativeQuery = true)
    List<StoreStatistics> findGroupByName();
}
