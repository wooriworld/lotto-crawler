package com.example.lottocrawler.crawler;

import com.example.lottocrawler.domain.LottoRound;
import com.example.lottocrawler.dto.LottoRoundDto;
import com.example.lottocrawler.dto.StoreDto;
import com.example.lottocrawler.service.LottoRoundService;
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
    LottoRoundService lottoRoundService;

    @Autowired
    Crawler crawler;

    @Test
    void start() {
        crawler.start();

        List<StoreDto> storeList = service.findAll();
        for (int i = 0; i < storeList.size(); i++) {
            StoreDto dto = storeList.get(i);
            System.out.println(dto);
        }
        System.out.println(storeList.size());
    }

    @Test
    void lottoRound() {
        crawler.lottoRound();

        List<LottoRoundDto> lottoRoundDtoList = lottoRoundService.findAll();
        for (int i = 0; i < lottoRoundDtoList.size(); i++) {
            LottoRoundDto dto = lottoRoundDtoList.get(i);
            System.out.println(dto);
        }
        System.out.println(lottoRoundDtoList.size());
    }
}