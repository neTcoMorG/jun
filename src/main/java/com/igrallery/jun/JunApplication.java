package com.igrallery.jun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class JunApplication {

	public static void main(String[] args) {
		SpringApplication.run(JunApplication.class, args);
	}

}
