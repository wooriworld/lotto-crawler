package com.example.lottocrawler.repository;

import com.example.lottocrawler.crawler.Crawler;
import com.example.lottocrawler.domain.LottoRound;
import com.example.lottocrawler.domain.LottoType;
import com.example.lottocrawler.domain.Store;
import com.example.lottocrawler.dto.StoreStatisticsDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
class StoreRepositoryTest {
    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private LottoRoundRepository lottoRoundRepository;

    @Autowired
    private Crawler crawler;

    private  List<Store> storeList;

    @BeforeEach
    void setup() {
        LottoRound lottoRound = LottoRound.builder().round(1L).localDate(LocalDate.now()).build();

        storeList = new ArrayList<>();
        storeList.add(Store.builder().name("name1").address("address1").round(lottoRound).lottoType(LottoType.AUTO).build());
        storeList.add(Store.builder().name("name1").address("address1").round(lottoRound).lottoType(LottoType.AUTO).build());
        storeList.add(Store.builder().name("name1").address("address1").round(lottoRound).lottoType(LottoType.AUTO).build());
        storeList.add(Store.builder().name("name1").address("address1").round(lottoRound).lottoType(LottoType.AUTO).build());
        storeList.add(Store.builder().name("name1").address("address1").round(lottoRound).lottoType(LottoType.AUTO).build());
        storeRepository.deleteAll();
        storeRepository.saveAll(storeList);
    }

    @Test
    void equals() {
        Store input = storeList.get(0);
        Store output = storeList.get(0);
        assertThat(input).isEqualTo(output);
    }

    @Test
    void createAndRead() {
        //given
        Store input = storeList.get(0);

        //when
        storeRepository.save(input);
        List<Store> storeList = storeRepository.findAll();

        //then
        Store output = storeList.get(0);
        assertThat(input).isEqualTo(output);
    }

    @Test
    void findGroupByName() {
        //given
        crawler.start();

        //when
        List<StoreStatisticsDto> storeList = storeRepository.findGroupByName();

        //then
        for(StoreStatisticsDto storeStatisticsDto : storeList) {
            System.out.println(storeStatisticsDto.getName());
        }
    }

    @Test
    void findByNameAndAddress() {
        //given
        String name = storeList.get(0).getName();
        String address = storeList.get(0).getAddress();

        //when
        List<Store> list = storeRepository.findByNameAndAddress(name, address);

        //then
        assertThat(list.size()).isEqualTo(storeList.size());
    }

}