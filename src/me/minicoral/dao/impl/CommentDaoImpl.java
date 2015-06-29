package me.minicoral.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import me.minicoral.dao.CommentDao;
import me.minicoral.entities.PointComment;
import me.minicoral.exception.DaoException;
import me.minicoral.util.DBCPUtil;

public class CommentDaoImpl implements CommentDao
{

	private QueryRunner qr = new QueryRunner(DBCPUtil.getDataSource());

	@Override
	public int addComment(PointComment pointComment)
	{
		try
		{
			String sql = "insert into pointcomment(uuid,pointId,cusername,comment) values(?,?,?,?)";
			Object params[] = { pointComment.getUuid(), pointComment.getPointId(), pointComment.getCusername(),
					pointComment.getComment() };
			for (Object object : params)
			{
				System.out.println(object);
			}
			return qr.update(sql, params);
		}
		catch (SQLException e)
		{
			throw new DaoException();
		}
	}

	@Override
	public List<PointComment> findCommentById(int pointId)
	{
		try
		{
			String sql = "select * from pointcomment where pointId=?";
			Object params[] = {pointId};
			return qr.query(sql, new BeanListHandler<PointComment>(PointComment.class),params);
		}
		catch (SQLException e)
		{
			throw new DaoException();
		}
	}
}
