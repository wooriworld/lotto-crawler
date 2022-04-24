package com.example.lottocrawler.dto;

import com.example.lottocrawler.domain.LottoRound;
import com.example.lottocrawler.domain.LottoType;
import com.example.lottocrawler.domain.Store;
import lombok.*;

@ToString
@EqualsAndHashCode
@Getter
@NoArgsConstructor
public class StoreDto {
    private LottoRound round;
    private String name;
    private LottoType lottoType;
    private String address;

    @Builder
    public StoreDto(LottoRound round, String name, LottoType lottoType, String address) {
        this.round = round;
        this.name = name;
        this.lottoType = lottoType;
        this.address = address;
    }

    public Store toEntity() {
        return Store.builder()
                .name(name)
                .address(address)
                .round(round)
                .lottoType(lottoType)
                .build();
    }
}
