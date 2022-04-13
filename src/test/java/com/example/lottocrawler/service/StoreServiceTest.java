package com.example.lottocrawler.service;

import com.example.lottocrawler.domain.LottoType;
import com.example.lottocrawler.dto.StoreDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
class StoreServiceTest {
    @Autowired
    private StoreService service;

    @Test
    void saveAndFindAll() {
        //given
        int round = 1010;
        String name = "name1";
        LottoType lottoType = LottoType.AUTO;
        String address = "address1";

        StoreDto input = StoreDto.builder()
                .round(round)
                .name(name)
                .lottoType(lottoType)
                .address(address)
                .build();

        //when
        service.save(input);
        List<StoreDto> storeList = service.findAll();

        //then
        StoreDto output = storeList.get(0);
        assertThat(input).isEqualTo(output);
    }
}