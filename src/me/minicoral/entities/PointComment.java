package me.minicoral.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.opensymphony.xwork2.ActionSupport;

import me.minicoral.service.CommentService;
import me.minicoral.service.impl.CommentServiceImpl;

public class PointComment extends ActionSupport
{

	private static final long serialVersionUID = 1L;
	private String uuid;
	private int pointId;
	private String cusername;
	private String comment;

	public String getUuid()
	{
		return uuid;
	}

	public void setUuid(String uuid)
	{
		this.uuid = uuid;
	}

	public int getPointId()
	{
		return pointId;
	}

	public void setPointId(int pointId)
	{
		this.pointId = pointId;
	}

	public String getCusername()
	{
		return cusername;
	}

	public void setCusername(String cusername)
	{
		this.cusername = cusername;
	}

	public String getComment()
	{
		return comment;
	}

	public void setComment(String comment)
	{
		this.comment = comment;
	}

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

	@Override
	public String toString()
	{
		return "PointComment [uuid=" + uuid + ", pointId=" + pointId + ", cusername=" + cusername + ", comment="
				+ comment + "]";
	}

	Map<Object, Object> dataMap = new HashMap<Object, Object>();

	public Map<Object, Object> getDataMap()
	{
		return dataMap;
	}

	private CommentService c = new CommentServiceImpl();

	public String addComment()
	{
		int i = 0;
		dataMap.clear();
		dataMap.put("status", false);
		uuid = UUID.randomUUID().toString();
		System.out.println(this);
		i = c.addComment(this);
		System.out.println(this);
		System.out.println(i);
		if (i == 1)
		{
			dataMap.put("status", true);
			dataMap.put("message", "评论成功");
		}

		return SUCCESS;
	}

	public String findCommentById()
	{
		int i = 0;
		dataMap.clear();
		List<PointComment> pointComments = new ArrayList<PointComment>();
		pointComments = c.findCommentById(pointId);
		dataMap.put("status", true);
		dataMap.put("pointComment", pointComments);
		return SUCCESS;
	}
}
