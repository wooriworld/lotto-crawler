package com.example.lottocrawler.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LottoTypeTest {
    @Test
    void test() {
        assertThat(LottoType.get("자동")).isEqualTo(LottoType.AUTO);
    }
}