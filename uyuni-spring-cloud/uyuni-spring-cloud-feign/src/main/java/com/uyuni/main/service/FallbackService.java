package com.uyuni.main.service;

import org.springframework.stereotype.Component;

@Component
public class FallbackService implements SchedualService{

	@Override
	public String sayHiFromClient(String name) {
		return "sorry "+name+",the server is busy,please try again later!";
	}

}
