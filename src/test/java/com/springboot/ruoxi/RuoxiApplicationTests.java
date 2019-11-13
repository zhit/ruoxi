package com.springboot.ruoxi;

import com.springboot.ruoxi.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RuoxiApplicationTests {
  @Resource private UserService userService;

  @Test
  public void contextLoads() {
    System.out.println(123);
  }
}
