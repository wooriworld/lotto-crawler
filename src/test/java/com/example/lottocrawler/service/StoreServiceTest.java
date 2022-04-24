package com.example.lottocrawler.service;

import com.example.lottocrawler.domain.LottoRound;
import com.example.lottocrawler.domain.LottoType;
import com.example.lottocrawler.dto.StoreDto;
import com.example.lottocrawler.repository.StoreRepository;
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
class StoreServiceTest {
    @Autowired
    private StoreService service;

    @Autowired
    private StoreRepository repository;

    private  List<StoreDto> storeDtoList;

    @BeforeEach
    void setup() {
        LottoRound lottoRound = LottoRound.builder().round(1L).localDate(LocalDate.now()).build();

        storeDtoList = new ArrayList<>();
        storeDtoList.add(StoreDto.builder().name("name1").address("address1").round(lottoRound).lottoType(LottoType.AUTO).build());
        storeDtoList.add(StoreDto.builder().name("name1").address("address1").round(lottoRound).lottoType(LottoType.AUTO).build());
        storeDtoList.add(StoreDto.builder().name("name1").address("address1").round(lottoRound).lottoType(LottoType.AUTO).build());
        storeDtoList.add(StoreDto.builder().name("name1").address("address1").round(lottoRound).lottoType(LottoType.AUTO).build());
        storeDtoList.add(StoreDto.builder().name("name1").address("address1").round(lottoRound).lottoType(LottoType.AUTO).build());
        repository.deleteAll();
        for(StoreDto dto : storeDtoList)
            service.save(dto);
    }

    @Test
    void saveAndFindAll() {
        //given
        StoreDto input = storeDtoList.get(0);

        //when
        service.save(input);
        List<StoreDto> storeList = service.findAll();

        //then
        StoreDto output = storeList.get(0);
        assertThat(input).isEqualTo(output);
    }

    @Test
    void findByNameAndAddress() {
        //given
        String name = storeDtoList.get(0).getName();
        String address = storeDtoList.get(0).getAddress();

        //when
        List<StoreDto> output = service.findByNameAndAddress(name, address);


        //then
        assertThat(output.size()).isEqualTo(storeDtoList.size());
    }
}