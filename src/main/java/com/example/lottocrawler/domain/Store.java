package com.example.lottocrawler.domain;

import com.example.lottocrawler.dto.StoreDto;
import lombok.*;

import javax.persistence.*;
@Getter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
public class Store extends BaseEntity {
    @EmbeddedId
    private StoreId storeId;
    @Column(nullable = false)
    private LottoType lottoType;
    @Builder
    public Store(StoreId storeId, LottoType lottoType) {
        this.storeId = storeId;
        this.lottoType = lottoType;
    }
    public StoreDto toDto() {
        return StoreDto.builder()
                .name(storeId.getName())
                .address(storeId.getAddress())
                .round(storeId.getRound())
                .lottoType(lottoType)
                .build();
    }
}
