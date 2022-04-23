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
        String name = "name1";
        String address = "address1";
        int round = 1;
        LottoType lottoType = LottoType.AUTO;

        StoreDto input = StoreDto.builder()
                .name(name)
                .address(address)
                .round(round)
                .lottoType(lottoType)
                .build();

        //when
        service.save(input);
        List<StoreDto> storeList = service.findAll();

        //then
        StoreDto output = storeList.get(0);
        assertThat(input).isEqualTo(output);
    }
}