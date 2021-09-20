package com.retrofit.mapstruct.example.catfacts.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket docConfig() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
//	The following line defines what prefex of endpoints to document 
//	(a single * would include only level after (api/) 
//	so won't include (api/breeds/search)
//				.paths(PathSelectors.ant("/api/**"))
//	The following line includes all endpoints defined in our application 
//				.paths(PathSelectors.any())
//	The following line defines what packages to get endpoints from
				.apis(RequestHandlerSelectors.basePackage("com.retrofit."
						+ "mapstruct.example.catfacts.controller"))
//	The following line does not exclude default springmvc error controller apis				
//				.apis(RequestHandlerSelectors.any())
				.build()
				.apiInfo(apiDetails());
		
//	To exclude an api from an included controller's apis, add @ApiIgnore to it
//	directly with its mapping annotation in the controller.
	}

	private ApiInfo apiDetails() {
		return new ApiInfo("Cat-facts Api Documentation",
				"Api for supplying specific cat breeds info from The Cat Api", 
				"1.0.0",
				"https://cat-facts/Terms-of-Service.html",
				new springfox.documentation.service.Contact("Areej",
						"https://areej-ahmad.com/about",
						"areej@areej-ahmad.com"),
				"Creation Lisence",
				"https://license.com/creations",
				Collections.emptyList());
	}
}
