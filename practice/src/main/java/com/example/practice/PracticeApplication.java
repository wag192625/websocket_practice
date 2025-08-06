package com.example.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// BaseTimeEntity 사용하기 위한 어노테이션
@EnableJpaAuditing
@SpringBootApplication
public class PracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticeApplication.class, args);
	}

}
