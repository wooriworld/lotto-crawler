package com.example.lottocrawler.service;

import com.example.lottocrawler.domain.LottoRound;
import com.example.lottocrawler.dto.LottoRoundDto;
import com.example.lottocrawler.repository.LottoRoundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LottoRoundService {
    private final LottoRoundRepository repository;

    @Transactional
    public Long save(LottoRoundDto dto) {
        return repository.save(dto.toEntity()).getRound();
    }

    @Transactional(readOnly = true)
    public List<LottoRoundDto> findAll() {
        List<LottoRound> lottoRoundList = repository.findAll();
        return lottoRoundList.stream().map(LottoRound::toDto).collect(Collectors.toList());
    }
}
