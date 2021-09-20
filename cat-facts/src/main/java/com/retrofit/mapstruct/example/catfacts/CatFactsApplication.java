package com.retrofit.mapstruct.example.catfacts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@SpringBootApplication
public class CatFactsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatFactsApplication.class, args);
	}

}
