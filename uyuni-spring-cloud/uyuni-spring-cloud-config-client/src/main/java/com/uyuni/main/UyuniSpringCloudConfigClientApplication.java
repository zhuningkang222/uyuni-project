package com.uyuni.main;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RefreshScope//需要更改变量的类都需要加这个注解
public class UyuniSpringCloudConfigClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(UyuniSpringCloudConfigClientApplication.class, args);
	}
	
	@Value("${foo}")
    String foo;
    @RequestMapping(value = "/hi")
    public String hi(){
        return foo;
    }
}
