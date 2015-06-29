package me.minicoral.service.impl;

import me.minicoral.dao.UserDao;
import me.minicoral.dao.impl.UserDaoImpl;
import me.minicoral.entities.User;
import me.minicoral.service.UserService;

public class UserServiceImpl implements UserService
{

	private UserDao dao = new UserDaoImpl(); 
	@Override
	public User findUserByUsername(String uusername)
	{
		return dao.findUserByUsername(uusername);
	}

	@Override
	public int addUser(User user)
	{
		return dao.addUser(user);
	}

}
