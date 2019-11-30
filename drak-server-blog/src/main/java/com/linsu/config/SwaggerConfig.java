package com.linsu.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configurable
public class SwaggerConfig {
	
	@Bean
	public Docket createRestApi() {
		
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
                .apis(RequestHandlerSelectors.basePackage("com.linsu"))
                .paths(PathSelectors.any())
                .build();
		
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("LS博客系统接口指南")
				.description("系统开发中，更多接口请稍后...")
				.version("v0.0.1-SNAPSHOT")
				.build();
				
	}

}
