package com.uyuni.main.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/swagger")
public class Swagger2Controller {
	
	@ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
	@ApiImplicitParam(name = "name", value = "用户名称", required = true, dataType = "String")
	@RequestMapping(value="/sayHi")
	public String sayHi(String name) {
		return "Hi "+name+" !";
	}

}
