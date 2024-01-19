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
//@RequiredArgsConstructor
public class ManageApplication implements CommandLineRunner {
	@Autowired
	private UsersService usersService;

	public static void main(String[] args) {
		SpringApplication.run(ManageApplication.class, args);
	}

	@Bean
	public ModelMapper mapper(){
		return new ModelMapper();
	}
	@Override
	public void run(String... args) throws Exception, NotFoundException {
		System.out.println(usersService.getById("659d55c67309f0470b070a83"));
	}


}
