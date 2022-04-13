package com.example.lottocrawler.repository;

import com.example.lottocrawler.domain.LottoType;
import com.example.lottocrawler.domain.Store;
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

    @Test
    void equals() {
        int round = 1010;
        String name = "name1";
        LottoType lottoType = LottoType.AUTO;
        String address = "address1";

        Store input = Store.builder()
                .round(round)
                .name(name)
                .lottoType(lottoType)
                .address(address)
                .build();

        Store output = Store.builder()
                .round(round)
                .name(name)
                .lottoType(lottoType)
                .address(address)
                .build();

        assertThat(input).isEqualTo(output);
    }

    @Test
    void createAndRead() {
        //given
        int round = 1010;
        String name = "name1";
        LottoType lottoType = LottoType.AUTO;
        String address = "address1";

        Store input = Store.builder()
                .round(1010)
                .name(name)
                .lottoType(lottoType)
                .address(address)
                .build();

        //when
        repository.save(input);
        List<Store> storeList = repository.findAll();

        //then
        Store output = storeList.get(0);
        assertThat(input).isEqualTo(output);
    }

}