package com.seventysevenagency.chat.dao;

import com.seventysevenagency.chat.domain.User;

public interface UserDAO {
	public int create(User user);

	public void update(User user);

	public User findById(int id);

	public int deleteById(int id);
	
	public User findByUsername(String username);
	
	public User authorize(String username, String password);
}
