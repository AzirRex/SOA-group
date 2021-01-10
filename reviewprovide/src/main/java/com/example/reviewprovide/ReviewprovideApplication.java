package com.example.reviewprovide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@SpringBootApplication
@EnableEurekaClient
public class ReviewprovideApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReviewprovideApplication.class, args);
	}

}
