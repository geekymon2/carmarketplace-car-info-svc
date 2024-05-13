package com.geekymon2.carmarketplace.carinfoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"com.geekymon2.carmarketplace.carinfoservice",
		"com.geekymon2.carmarketplace.core.autoconfiguration"})
public class CarInfoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarInfoServiceApplication.class, args);
	}
}
