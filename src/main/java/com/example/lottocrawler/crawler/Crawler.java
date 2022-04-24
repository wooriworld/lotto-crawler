package com.example.lottocrawler.crawler;

import com.example.lottocrawler.domain.LottoType;
import com.example.lottocrawler.dto.LottoRoundDto;
import com.example.lottocrawler.dto.StoreDto;
import com.example.lottocrawler.service.LottoRoundService;
import com.example.lottocrawler.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class Crawler {
    private final StoreService storeService;
    private final LottoRoundService lottoRoundService;

    @Transactional
    public void lottoRound() {
        try {
            for(Long round = 1012L; round > 0; round--) {
                Connection.Response con = Jsoup.connect("https://www.dhlottery.co.kr/gameResult.do?method=byWin&drwNo="+round+"&dwrNoList="+round)
                        .method(Connection.Method.POST)
                        .execute();
                Document doc = con.parse();
                String text = doc.select(".desc").text();
                text = text.replace("(", "").replace(")", "");

                int year = Integer.parseInt(text.substring(0, 4));
                int month = Integer.parseInt(text.substring(6, 8));
                int day = Integer.parseInt(text.substring(10, 12));

                LottoRoundDto dto = LottoRoundDto.builder().round(round).localDate(LocalDate.of(year, month, day)).build();
                lottoRoundService.save(dto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void start() {
//        try {
//            for(Long round = 1011L; round > 0; round--) {
//                Connection.Response con = Jsoup.connect("https://www.dhlottery.co.kr/store.do?method=topStore&pageGubun=L645")
//                        .data("method", "topStore")
//                        .data("nowPage", "1")
//                        .data("gameNo", "5133")
//                        .data("drwNo", String.valueOf(round))
//                        .data("schKey", "all")
//                        .method(Connection.Method.POST)
//                        .execute();
//                Document doc = con.parse();
//                Elements links = doc.select(".group_content").first().select(".tbl_data tbody tr");
//                for(Element link : links) {
//                    if(link.childNodeSize() > 3) {
//                        String name = link.child(1).text();
//                        LottoType lottoType = LottoType.get(link.child(2).text());
//                        String address = link.child(3).text();
//
//                        StoreDto dto = StoreDto.builder()
//                                .round(round)
//                                .name(name)
//                                .lottoType(lottoType)
//                                .address(address)
//                                .build();
//                        storeService.save(dto);
//                    }
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
