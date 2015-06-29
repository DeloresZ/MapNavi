package me.minicoral.entities;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.opensymphony.xwork2.ActionSupport;

import me.minicoral.service.UserService;
import me.minicoral.service.impl.UserServiceImpl;
import me.minicoral.util.MD5Util;

public class User extends ActionSupport
{
	private static final long serialVersionUID = 1L;
	private String uuid;
	private String uusername;
	private String ugender;
	private String upassword;
	private String unick;
	private String uemail;


	public String getUuid()
	{
		return uuid;
	}

	public void setUuid(String uuid)
	{
		this.uuid = uuid;
	}

	public String getUusername()
	{
		return uusername;
	}

	public void setUusername(String uusername)
	{
		this.uusername = uusername;
	}

	public String getUgender()
	{
		return ugender;
	}

	public void setUgender(String ugender)
	{
		this.ugender = ugender;
	}

	public String getUpassword()
	{
		return upassword;
	}

	public void setUpassword(String upassword)
	{
		this.upassword = upassword;
	}

	public String getUnick()
	{
		return unick;
	}

	public void setUnick(String unick)
	{
		this.unick = unick;
	}

	public String getUemail()
	{
		return uemail;
	}

	public void setUemail(String uemail)
	{
		this.uemail = uemail;
	}

	@Override
	public String toString()
	{
		return "User [uuid=" + uuid + ", uusername=" + uusername + ", ugender=" + ugender + ", upassword=" + upassword
				+ ", unick=" + unick + ", uemail=" + uemail + "]";
	}

	Map<Object, Object> dataMap = new HashMap<Object, Object>();

	public Map<Object, Object> getDataMap()
	{
		return dataMap;
	}

	private UserService s = new UserServiceImpl();

	public String login()
	{
		System.out.println(uusername + "......" + upassword);
		User user = s.findUserByUsername(uusername);
		upassword = MD5Util.encode(upassword);
		System.out.println(upassword);
		System.out.println(user);
		dataMap.clear();
		dataMap.put("status", false);
		dataMap.put("message", "登陆失败，用户不存在");
		System.out.println(upassword.equals(user.getUpassword()));
		if (user != null)
		{
			dataMap.put("message", "登陆失败，用户名或密码不正确");
			if ((uusername.equals(user.getUusername()) && (upassword.equals(user.getUpassword()))))
			{
				dataMap.put("uuid", user.getUuid());
				dataMap.put("user", user);
				dataMap.put("status", true);
				dataMap.put("message", "登录成功");
			}
		}
		return SUCCESS;
	}

	public String register()
	{
		int i = 0;
		dataMap.clear();
		dataMap.put("status", false);
		User user = s.findUserByUsername(uusername);
		if (user != null)
		{
			dataMap.put("message", "用户已经存在，请勿重新注册");
		}
		else
		{
			uuid = UUID.randomUUID().toString();
			if (unick == null)
			{
				unick = "用户" + uusername;
			}
			ugender = "男";
			upassword = MD5Util.encode(upassword);
			i = s.addUser(this);
			System.out.println(this);
			System.out.println(i);
			if (i == 1)
			{
				dataMap.put("status", true);
				dataMap.put("message", "注册成功");
			}
		}
		return SUCCESS;
	}

}
