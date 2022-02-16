package com.te.pearson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class PearsonApplication {

	public static void main(String[] args) {
		SpringApplication.run(PearsonApplication.class, args);
	}

}
