package com.uyuni.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UyuniRedisDistributedLockApplication {

	public static void main(String[] args) {
		SpringApplication.run(UyuniRedisDistributedLockApplication.class, args);
	}
}
