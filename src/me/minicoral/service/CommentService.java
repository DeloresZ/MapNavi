package me.minicoral.service;

import java.util.List;

import me.minicoral.entities.PointComment;

public interface CommentService
{

	int addComment(PointComment pointComment);

	List<PointComment> findCommentById(int pointId);

}
