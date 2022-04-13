package com.example.lottocrawler.controller;

import com.example.lottocrawler.crawler.Crawler;
import com.example.lottocrawler.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class StoreController {
    private final StoreService service;
    private final Crawler crawler;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("list", service.findAll());
        return "store";
    }

    @GetMapping("/crawler")
    public ResponseEntity<String> crawler() {
        crawler.start();
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
