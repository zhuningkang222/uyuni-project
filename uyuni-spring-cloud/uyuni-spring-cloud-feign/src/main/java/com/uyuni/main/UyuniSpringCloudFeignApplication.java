package com.uyuni.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uyuni.main.service.SchedualService;

/**
 * @EnableDiscoveryClient 向服务中心eureka注册
 * @EnableFeignClients 使用feign调用服务
 * @EnableHystrixDashboard
 * @EnableCircuitBreaker 微服务诊断需要开启这两个注解
 * @author zhanghailin
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrixDashboard
@EnableCircuitBreaker
@RestController
public class UyuniSpringCloudFeignApplication {
	
	private static final Logger log = LoggerFactory.getLogger(UyuniSpringCloudFeignApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(UyuniSpringCloudFeignApplication.class, args);
	}
	
	@Autowired
    SchedualService schedualService;
	
    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    public String sayHi(String name){
    	log.info("-------the request param:{}-----------",name);
        return schedualService.sayHiFromClient(name)+" use feign";
    }
}
