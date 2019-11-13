package com.springboot.ruoxi.service.impl;

import com.springboot.ruoxi.mapper.UserMapper;
import com.springboot.ruoxi.model.User;
import com.springboot.ruoxi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
	
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public void deleteAll() {
		this.userMapper.deleteAll();
	}
	
	@Override
	public void delete(String id) {
		int userid = Integer.parseInt(id);
		this.userMapper.deleteOne(userid);
	}
	
	@Override
	public void save(User user) {
		this.userMapper.insertOne(user);
	}
	
	@Override
	public void update(int id) {
		this.userMapper.updateOne(id);
	}
	
	@Override
	public User findOne(int id) {
		return this.userMapper.findOne(id);
	}
	
	@Override
	public List<User> list() {
		return this.userMapper.findList();
	}
}
