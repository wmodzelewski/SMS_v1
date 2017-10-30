package com.wmodzelewski;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class StsConfigurationServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StsConfigurationServerApplication.class, args);
	}
}
