package com.claims.manage;

import com.claims.manage.exception.NotFoundException;
import com.claims.manage.service.UsersService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ManageApplication {
	public static void main(String[] args) {
		SpringApplication.run(ManageApplication.class, args);
	}
	@Bean
	public ModelMapper mapper(){
		return new ModelMapper();
	}
}
