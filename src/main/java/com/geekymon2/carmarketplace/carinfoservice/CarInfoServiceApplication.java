package com.geekymon2.carmarketplace.carinfoservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableEurekaClient
@SpringBootApplication
@OpenAPIDefinition(info =
@Info(title = "Car Info Service API", version = "1.0", description = "Documentation Car Info Service API v1.0"))
public class CarInfoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarInfoServiceApplication.class, args);
	}
}
