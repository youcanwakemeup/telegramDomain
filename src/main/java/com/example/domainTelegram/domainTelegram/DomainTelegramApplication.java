package com.example.domainTelegram.domainTelegram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class DomainTelegramApplication {
	public static void main(String[] args) {
		SpringApplication.run(DomainTelegramApplication.class, args);

	}
}

