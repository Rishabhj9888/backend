package com.edigest.hloo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HlooApplication {

	public static void main(String[] args) {

		SpringApplication.run(HlooApplication.class, args);

	}


}
