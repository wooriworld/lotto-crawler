package com.example.lottocrawler.dto;

import com.example.lottocrawler.domain.LottoType;
import com.example.lottocrawler.domain.Store;
import lombok.*;

@ToString
@EqualsAndHashCode
@Getter
@NoArgsConstructor
public class StoreDto {
    private int round;
    private String name;
    private LottoType lottoType;
    private String address;

    @Builder
    public StoreDto(int round, String name, LottoType lottoType, String address) {
        this.round = round;
        this.name = name;
        this.lottoType = lottoType;
        this.address = address;
    }

    public Store toEntity() {
        return Store.builder()
                .round(round)
                .name(name)
                .lottoType(lottoType)
                .address(address)
                .build();
    }
}
