package com.angular.dao;

import java.util.*;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.angular.entity.*;

public class CommentsDAO implements ICommentsDAO{

	private SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public List<String> findCommentsByBook(String bookId) {
		Session s=sessionFactory.openSession();
		s.beginTransaction();
//		Book book = (Book) s.get(Book.class, bookId);
		List<Comments> commentlList = new ArrayList<Comments>();
		String hql = "select comments from Comments comments";
		Query query = s.createQuery(hql);
		commentlList = (List<Comments>)query.list();
		List<String> resultsList = new ArrayList<String>();
		for (Comments c :commentlList) {
			if (c.getBid().equals(bookId)) {
				System.out.println(c.getBid());
				resultsList.add(c.getContent());
			}
		}
		s.getTransaction().commit();
		return resultsList;
	}
	
	@Override
	@Transactional
	public void saveComments(String bid, String uid, String content) {
		Session s=sessionFactory.openSession();
		s.beginTransaction();
		Comments comments = new Comments();
		comments.setBid(bid);
		comments.setUid(uid);
		comments.setContent(content);
		comments.setCommentDateTime(new Date());
		s.save(comments);
		s.getTransaction().commit();
		s.close();
	}
	
	@Override
	@Transactional
	public void deleteComments(int id) {
		Session s=sessionFactory.openSession();
		s.beginTransaction();
		Comments comments = (Comments) s.get(Comments.class, id);
		s.delete(comments);
		s.getTransaction().commit();
		s.close();
		
	}
	
	@Override
	@Transactional
//	update的时候在controller里面调用这个方法
	public void updateComments(int id, String content) {
		Session s=sessionFactory.openSession();
		s.beginTransaction();
		Comments c=(Comments)s.get(Comments.class, id);
		c.setContent(content);
		c.setCommentDateTime(new Date());
		s.update(c);
		s.getTransaction().commit();
		
	}
}
