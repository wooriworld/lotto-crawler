package com.example.lottocrawler.dto;

import com.example.lottocrawler.domain.LottoRound;
import lombok.*;

import java.time.LocalDate;

@ToString
@EqualsAndHashCode
@Getter
@NoArgsConstructor
public class LottoRoundDto {
    private Long round;
    private LocalDate localDate;

    @Builder
    public LottoRoundDto(Long round, LocalDate localDate) {
        this.round = round;
        this.localDate = localDate;
    }

    public LottoRound toEntity() {
        return LottoRound.builder()
                .round(round)
                .localDate(localDate)
                .build();
    }
}
