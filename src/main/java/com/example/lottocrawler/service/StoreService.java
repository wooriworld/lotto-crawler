package com.example.lottocrawler.service;

import com.example.lottocrawler.domain.Store;
import com.example.lottocrawler.dto.StoreDto;
import com.example.lottocrawler.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
