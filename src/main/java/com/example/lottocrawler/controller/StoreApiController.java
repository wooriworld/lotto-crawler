package com.example.lottocrawler.controller;

import com.example.lottocrawler.dto.StoreDto;
import com.example.lottocrawler.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class StoreApiController {
    private final StoreService service;

    @PostMapping("/read")
    public ResponseEntity<List<StoreDto>> registerPost(@RequestBody StoreDto dto) {
        List<StoreDto> storeDtoList = service.findByNameAndAddress(dto.getName(), dto.getAddress());
        return new ResponseEntity<>(storeDtoList, HttpStatus.OK);
    }
}
