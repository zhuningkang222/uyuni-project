package com.uyuni.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class UyuniSpringCloudEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UyuniSpringCloudEurekaServerApplication.class, args);
	}
}
