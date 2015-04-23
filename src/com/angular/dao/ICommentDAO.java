package com.angular.dao;

import com.angular.entity.Comment;


public interface ICommentDAO {

	public void saveComment(Comment comment);
	public void deleteComment(Comment comment);
	public void updateComment(String id, String comment);
}
