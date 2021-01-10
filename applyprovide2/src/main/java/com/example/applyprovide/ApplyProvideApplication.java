package com.example.applyprovide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@SpringBootApplication
@EnableEurekaClient
public class ApplyProvideApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApplyProvideApplication.class, args);
	}
	@Bean
	public CorsFilter corsFilter() {


		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();


		CorsConfiguration corsConfiguration = new CorsConfiguration();


		corsConfiguration.addAllowedOrigin("*");


		corsConfiguration.addAllowedHeader("*");


		corsConfiguration.addAllowedMethod("*");


		source.registerCorsConfiguration("/**", corsConfiguration);


		return new CorsFilter(source);


	}
}
