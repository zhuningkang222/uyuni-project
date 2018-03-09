package com.uyuni.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.uyuni.main.service.HelloService;

@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
@RestController
public class UyuniSpringCloudRibbonApplication {

	public static void main(String[] args) {
		SpringApplication.run(UyuniSpringCloudRibbonApplication.class, args);
	}
	
	@Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
	
	@Autowired
    HelloService helloService;
	
    @RequestMapping(value = "/hi")
    public String hi(@RequestParam String name){
        return helloService.sayHi(name)+" use ribbon";
    }
	
}
