package com.cybertek;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

@SpringBootApplication
@ComponentScan
public class SpringMvcProjectManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMvcProjectManagementApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){

		return new ModelMapper();
	}

}
