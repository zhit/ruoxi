package com.springboot.ruoxi.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Car {
	
	
	@Bean
	public Demo getDemo() {
		return new Demo("xiaoming", 15);
	}
}
