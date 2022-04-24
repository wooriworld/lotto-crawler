package com.example.lottocrawler.repository;

import com.example.lottocrawler.domain.LottoRound;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LottoRoundRepositoryTest {
    @Autowired
    private LottoRoundRepository repository;

    @Test
    void test() {
        LottoRound input = LottoRound.builder().round(1L).localDate(LocalDate.of(2022,4,24)).build();

        repository.save(input);

        List<LottoRound> list = repository.findAll();
        System.out.println(list.size());
    }
}