package com.springboot.ruoxi.web;

import com.springboot.ruoxi.model.User;
import com.springboot.ruoxi.redis.RedisService;
import com.springboot.ruoxi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
  @Autowired RedisService redisService;
  @Autowired private UserService userService;

  @GetMapping("users")
  public List<User> userList() {
    // 查缓存
    // Object users = redisService.get("users");
    return this.userService.list();
  }

  @GetMapping("/api/{name}")
  public String info(@PathVariable String name) {
    return "hello " + name + ", this your info";
  }

  @PostMapping("user")
  public List<User> createUser() {
    User user = new User();
    user.setUsername("lisi");
    this.userService.save(user);
    return this.userService.list();
  }

  @PutMapping("user")
  public List<User> updateUser() {
    int a = 100000;
    this.userService.update(a);
    return this.userService.list();
  }

  @DeleteMapping("user")
  public void deleteUser(@RequestParam String id) {
    this.userService.delete(id);
  }

  @DeleteMapping("users")
  public void deleteUsers() {
    this.userService.deleteAll();
  }
}
