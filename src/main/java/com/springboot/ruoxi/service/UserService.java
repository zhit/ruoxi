package com.springboot.ruoxi.service;

import com.springboot.ruoxi.model.User;

import java.util.List;

public interface UserService {
  void deleteAll();

  void delete(String id);

  List<User> list();

  void save(User user);

  void update(int id);

  User findOne(int id);
}
