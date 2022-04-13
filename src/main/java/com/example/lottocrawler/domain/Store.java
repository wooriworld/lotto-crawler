package com.example.lottocrawler.domain;

import com.example.lottocrawler.dto.StoreDto;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@EqualsAndHashCode(callSuper = false)
@Getter
@NoArgsConstructor
@Entity
public class Store extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private int round;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private LottoType lottoType;
    @Column(nullable = false)
    private String address;
    @Builder
    public Store(int round, String name, LottoType lottoType, String address) {
        this.round = round;
        this.name = name;
        this.lottoType = lottoType;
        this.address = address;
    }
    public StoreDto toDto() {
        return StoreDto.builder()
                .round(round)
                .name(name)
                .lottoType(lottoType)
                .address(address)
                .build();
    }
}
