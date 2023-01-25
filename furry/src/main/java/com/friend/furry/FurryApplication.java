package com.friend.furry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FurryApplication {

	public static void main(String[] args) {
		SpringApplication.run(FurryApplication.class, args);
	}

}
