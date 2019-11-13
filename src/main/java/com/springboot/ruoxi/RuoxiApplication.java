package com.springboot.ruoxi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.springboot.ruoxi.mapper")
@SpringBootApplication
public class RuoxiApplication {
  public static void main(String[] args) {
    SpringApplication.run(RuoxiApplication.class, args);
  }
}
