package com.github.fabriciolfj.simulation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@EnableR2dbcRepositories
@SpringBootApplication
public class SimulationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimulationApplication.class, args);
	}

}
