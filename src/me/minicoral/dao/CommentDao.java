package me.minicoral.dao;

import java.util.List;

import me.minicoral.entities.PointComment;

public interface CommentDao
{

	int addComment(PointComment pointComment);

	List<PointComment> findCommentById(int pointId);

}
