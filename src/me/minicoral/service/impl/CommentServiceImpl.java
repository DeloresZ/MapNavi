package me.minicoral.service.impl;

import java.util.List;

import me.minicoral.dao.CommentDao;
import me.minicoral.dao.impl.CommentDaoImpl;
import me.minicoral.entities.PointComment;
import me.minicoral.service.CommentService;

public class CommentServiceImpl implements CommentService
{
	private CommentDao dao = new CommentDaoImpl();
	@Override
	public int addComment(PointComment pointComment)
	{
		return dao.addComment(pointComment);
	}
	@Override
	public List<PointComment> findCommentById(int pointId)
	{
		return dao.findCommentById(pointId);
	}

}
