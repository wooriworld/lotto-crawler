package com.example.lottocrawler.service;

import com.example.lottocrawler.domain.Store;
import com.example.lottocrawler.dto.StoreDto;
import com.example.lottocrawler.dto.StoreStatisticsDto;
import com.example.lottocrawler.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StoreService {
    private final StoreRepository repository;

    @Transactional
    public Long save(StoreDto dto) {
        return repository.save(dto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public List<StoreDto> findAll() {
        List<Store> storeList = repository.findAll();
        return storeList.stream().map(Store::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<StoreStatisticsDto> findGroupByName() {
        List<StoreStatisticsDto> list = new ArrayList<>();
        List<StoreStatisticsDto> result = repository.findGroupByName();
        if(result.size() > 0) {
            for(int i = 0; i < 3; i++) {
                list.add(result.get(i));
            }
        }
        return list;
    }

    @Transactional(readOnly = true)
    public List<StoreDto> findByNameAndAddress(String name, String address) {
        List<Store> storeList = repository.findByNameAndAddress(name, address);
        return storeList.stream().map(obj -> obj.toDto()).collect(Collectors.toList());
    }

}
