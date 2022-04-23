package com.example.lottocrawler.repository;

import com.example.lottocrawler.crawler.Crawler;
import com.example.lottocrawler.domain.LottoType;
import com.example.lottocrawler.domain.Store;
import com.example.lottocrawler.domain.StoreId;
import com.example.lottocrawler.dto.StoreStatistics;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
class StoreRepositoryTest {
    @Autowired
    private StoreRepository repository;

    @Autowired
    private Crawler crawler;

    @Test
    void equals() {
        String name = "name1";
        String address = "address1";
        int round = 1;
        LottoType lottoType = LottoType.AUTO;

        StoreId storeId = StoreId.builder().name(name).address(address).round(round).build();

        Store input = Store.builder()
                .storeId(storeId)
                .lottoType(lottoType)
                .build();

        Store output = Store.builder()
                .storeId(storeId)
                .lottoType(lottoType)
                .build();

        assertThat(input).isEqualTo(output);
    }

    @Test
    void createAndRead() {
        //given
        String name = "name1";
        String address = "address1";
        int round = 1;
        LottoType lottoType = LottoType.AUTO;

        StoreId storeId = StoreId.builder().name(name).address(address).round(round).build();

        Store input = Store.builder()
                .storeId(storeId)
                .lottoType(lottoType)
                .build();

        //when
        repository.save(input);
        List<Store> storeList = repository.findAll();

        //then
        Store output = storeList.get(0);
        assertThat(input).isEqualTo(output);
    }

    @Test
    void findGroupByName() {
        //given
        crawler.start();

        //when
        List<StoreStatistics> storeList = repository.findGroupByName();

        //then
        for(StoreStatistics storeStatistics : storeList) {
            System.out.println(storeStatistics.getName());
        }
    }

}