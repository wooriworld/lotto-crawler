package com.example.lottocrawler.domain;

import com.example.lottocrawler.dto.LottoRoundDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
public class LottoRound extends BaseEntity {
    @Id
    private Long round;

    @Column
    private LocalDate localDate;

    @Builder
    public LottoRound(Long round, LocalDate localDate) {
        this.round = round;
        this.localDate = localDate;
    }

    public LottoRoundDto toDto() {
        return LottoRoundDto.builder()
                .round(round)
                .localDate(localDate)
                .build();
    }
}
