package com.uyuni.main;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
@RestController
@EnableSwagger2
public class UyuniSpringCloudEurekaClientCopyApplication {

	public static void main(String[] args) {
		SpringApplication.run(UyuniSpringCloudEurekaClientCopyApplication.class, args);
	}
	
	@Value("${server.port}")
	String port;
	
	@ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
	@ApiImplicitParam(name = "name", value = "用户名称", required = true, dataType = "String")
	@RequestMapping("sayHi")
	public String sayHi(String name) {
		return "Hi "+name+" I am from port:"+port;
	}
}
