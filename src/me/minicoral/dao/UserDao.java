package me.minicoral.dao;

import me.minicoral.entities.User;

public interface UserDao
{

	User findUserByUsername(String uusername);

	int addUser(User user);

}
