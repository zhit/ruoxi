package com.springboot.ruoxi.mapper;

import com.springboot.ruoxi.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "UserMapper")
public interface UserMapper {
	
	List<User> findList();
	
	void insertOne(User user);
	
	void updateOne(int id);
	
	void deleteOne(int id);
	
	void deleteAll();
	
	User findOne(int id);
}
