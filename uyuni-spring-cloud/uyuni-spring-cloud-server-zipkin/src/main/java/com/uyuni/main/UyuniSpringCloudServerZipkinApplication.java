package com.uyuni.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
public class UyuniSpringCloudServerZipkinApplication {

	public static void main(String[] args) {
		SpringApplication.run(UyuniSpringCloudServerZipkinApplication.class, args);
	}
}
