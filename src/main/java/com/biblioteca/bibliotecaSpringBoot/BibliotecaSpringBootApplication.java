package com.biblioteca.bibliotecaSpringBoot;

import com.biblioteca.bibliotecaSpringBoot.mappers.Mapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class BibliotecaSpringBootApplication {

	@Bean
	public Mapper modelMapper(){return new Mapper();}
	public static void main(String[] args) {
		SpringApplication.run(BibliotecaSpringBootApplication.class, args);
	}

}
