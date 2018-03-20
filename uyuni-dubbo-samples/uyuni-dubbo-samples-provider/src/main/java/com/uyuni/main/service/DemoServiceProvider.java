package com.uyuni.main.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.uyuni.main.api.DemoService;

@Component("demoService")
public class DemoServiceProvider implements DemoService{
	
	private static final Logger log = LoggerFactory.getLogger(DemoServiceProvider.class);

	@Override
	public String takeItEasy(String name) {
		log.info("take it easy {}, just for fun!...",name);
		return "take it easy "+name+", just for fun! from provider1";
	}

}
