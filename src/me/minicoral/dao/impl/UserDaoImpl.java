package me.minicoral.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import me.minicoral.dao.UserDao;
import me.minicoral.entities.User;
import me.minicoral.exception.DaoException;
import me.minicoral.util.DBCPUtil;

public class UserDaoImpl implements UserDao
{
	private QueryRunner qr = new QueryRunner(DBCPUtil.getDataSource());

	public List<User> findAllUser()
	{
		try
		{
			String sql = "select * from user";
			return qr.query(sql, new BeanListHandler<User>(User.class));
		}
		catch (SQLException e)
		{
			throw new DaoException(e);
		}
	}

	public List<User> findUserByCondition(String condition)
	{
		try
		{
			if (condition == null || condition.trim().equals(""))
			{
				return findAllUser();
			}
			if (!condition.trim().startsWith("where") && !condition.trim().startsWith("WHERE"))
			{
				throw new RuntimeException("The parmas must be start with where");
			}
			return qr.query("select * from user " + condition, new BeanListHandler<User>(User.class));
		}
		catch (Exception e)
		{
			throw new DaoException(e);
		}
	}

	@Override
	public User findUserByUsername(String uusername)
	{
		if (uusername == null || uusername.trim().equals(""))
		{
			throw new RuntimeException("The username can not be empty");
		}
		try
		{
			List<User> users = findUserByCondition("where uusername='" + uusername + "'");
			if (users != null && users.size() > 0)
			{
				return users.get(0);
			}
			else
			{
				return null;
			}
		}
		catch (Exception e)
		{
			throw new DaoException(e);
		}
	}

	@Override
	public int addUser(User user)
	{
		try
		{
			String sql = "insert into user(uuid,uusername,upassword,unick,ugender,uemail) values(?,?,?,?,?,?)";
			Object params[] = { user.getUuid(), user.getUusername(),
					user.getUpassword(), user.getUnick(),"男",null};
			return qr.update(sql, params);
		}
		catch (SQLException e)
		{
			return 0;
		}
	}

}
