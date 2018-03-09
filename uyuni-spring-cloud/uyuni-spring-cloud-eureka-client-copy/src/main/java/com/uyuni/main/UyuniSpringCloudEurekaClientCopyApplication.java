package com.uyuni.main;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class UyuniSpringCloudEurekaClientCopyApplication {

	public static void main(String[] args) {
		SpringApplication.run(UyuniSpringCloudEurekaClientCopyApplication.class, args);
	}
	
	@Value("${server.port}")
	String port;
	
	@RequestMapping("sayHi")
	public String sayHi(String name) {
		return "Hi "+name+" I am from port:"+port;
	}
}
