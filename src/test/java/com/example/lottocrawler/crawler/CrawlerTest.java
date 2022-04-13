package com.example.lottocrawler.crawler;

import com.example.lottocrawler.dto.StoreDto;
import com.example.lottocrawler.service.StoreService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class CrawlerTest {
    @Autowired
    StoreService service;

    @Autowired
    Crawler crawler;

    @Test
    void start() {
        crawler.start();

        List<StoreDto> storeList = service.findAll();
        System.out.println(storeList.size());
    }
}