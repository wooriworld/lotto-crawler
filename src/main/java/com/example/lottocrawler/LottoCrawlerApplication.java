package com.example.lottocrawler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class LottoCrawlerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LottoCrawlerApplication.class, args);
	}

}
