package com.linsu;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = {"com.linsu.modular.*.dao"})
@EnableSwagger2
public class BlogApplication {
	
	private static Logger logger = LoggerFactory.getLogger(BlogApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
		logger.info("Blog start success!");
	}
}
