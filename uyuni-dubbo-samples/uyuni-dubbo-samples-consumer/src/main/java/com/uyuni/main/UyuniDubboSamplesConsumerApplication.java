package com.uyuni.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uyuni.main.api.DemoService;

@SpringBootApplication
@RestController
public class UyuniDubboSamplesConsumerApplication {
	
	@Autowired
	DemoService demoService;

	public static void main(String[] args) {
		SpringApplication.run(UyuniDubboSamplesConsumerApplication.class, args);
	}
	
	@RequestMapping("takeiteasy")
	public String takeiteasy(String name) {
		return demoService.takeItEasy(name);
	}
}
