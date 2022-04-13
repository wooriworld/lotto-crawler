package com.example.lottocrawler.crawler;

import com.example.lottocrawler.domain.LottoType;
import com.example.lottocrawler.dto.StoreDto;
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

@RequiredArgsConstructor
@Service
public class Crawler {
    private final StoreService service;

    @Transactional
    public void start() {
        try {
            for(int round = 1010; round > 0; round--) {
                Connection.Response con = Jsoup.connect("https://www.dhlottery.co.kr/store.do?method=topStore&pageGubun=L645")
                        .data("method", "topStore")
                        .data("nowPage", "1")
                        .data("gameNo", "5133")
                        .data("drwNo", String.valueOf(round))
                        .data("schKey", "all")
                        .method(Connection.Method.POST)
                        .execute();
                Document doc = con.parse();
                Elements links = doc.select(".group_content").first().select(".tbl_data tbody tr");
                for(Element link : links) {
                    if(link.childNodeSize() > 3) {
                        String name = link.child(1).text();
                        LottoType lottoType = LottoType.get(link.child(2).text());
                        String address = link.child(3).text();

                        StoreDto dto = StoreDto.builder()
                                .round(round)
                                .name(name)
                                .lottoType(lottoType)
                                .address(address)
                                .build();
                        service.save(dto);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
