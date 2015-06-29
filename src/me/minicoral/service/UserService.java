package me.minicoral.service;

import me.minicoral.entities.User;

public interface UserService
{

	User findUserByUsername(String uusername);

	int addUser(User user);

}
