package com.uyuni.main.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "eureka-client",fallback = FallbackService.class)
public interface SchedualService {
	
	@RequestMapping(value = "/sayHi",method = RequestMethod.GET)
	String sayHiFromClient(@RequestParam(value = "name")String name);
}
