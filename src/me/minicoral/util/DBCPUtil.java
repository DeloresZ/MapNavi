package me.minicoral.util;

import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;

public class DBCPUtil
{
	private static BasicDataSource ds;
	static
	{
		try
		{
			InputStream in = DBCPUtil.class.getClassLoader()
					.getResourceAsStream("dbcpconfig.properties");
			Properties pros = new Properties();
			pros.load(in);
			ds = BasicDataSourceFactory.createDataSource(pros);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static DataSource getDataSource()
	{
		return ds;
	}
}
