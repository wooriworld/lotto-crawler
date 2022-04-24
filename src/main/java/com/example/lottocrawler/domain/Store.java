package com.example.lottocrawler.domain;

import com.example.lottocrawler.dto.LottoRoundDto;
import com.example.lottocrawler.dto.StoreDto;
import lombok.*;

import javax.persistence.*;
@Getter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
public class Store extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String address;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name="round")
    private LottoRound round;
    @Column(nullable = false)
    private LottoType lottoType;

    @Builder
    public Store(Long id, String name, String address, LottoRound round, LottoType lottoType) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.round = round;
        this.lottoType = lottoType;
    }

    public StoreDto toDto() {
        return StoreDto.builder()
                .name(name)
                .address(address)
                .round(round)
                .lottoType(lottoType)
                .build();
    }
}
