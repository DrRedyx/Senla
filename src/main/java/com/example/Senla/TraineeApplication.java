package com.example.Senla;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class TraineeApplication {

	public static void main(String[] args) {
		SpringApplication.run(TraineeApplication.class, args);
	}

}
