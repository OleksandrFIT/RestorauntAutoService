package com.work.restorauntautoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//@SpringBootApplication
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class RestorauntAutoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestorauntAutoServiceApplication.class, args);
	}

}
