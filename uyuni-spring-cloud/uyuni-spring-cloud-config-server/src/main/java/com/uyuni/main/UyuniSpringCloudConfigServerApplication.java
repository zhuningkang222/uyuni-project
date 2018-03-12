package com.uyuni.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class UyuniSpringCloudConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UyuniSpringCloudConfigServerApplication.class, args);
	}
}
