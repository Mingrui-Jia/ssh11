package com.angular.service;

import java.util.List;


public interface ICommentsManager {

	public void updateComments(int id, String content);
	public void deleteComments(int id);
	public void saveComments(String bid, String uid, String content);
	public List<String> findCommentsByBook(String bookId);
}
