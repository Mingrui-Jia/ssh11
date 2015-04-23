package com.angular.dao;

import java.util.*;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.angular.entity.*;

public class CommentDAO implements ICommentDAO{

	private SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	@Transactional
	public void saveComment(Comment comment) {
		Session s=sessionFactory.openSession();
		s.beginTransaction();
		s.save(comment);
		s.getTransaction().commit();
		s.close();
	}
	
	@Override
	@Transactional
	public void deleteComment(Comment comment) {
		Session s=sessionFactory.openSession();
		s.beginTransaction();
		s.delete(comment);
		s.getTransaction().commit();
		s.close();
		
	}
	
	@Override
	@Transactional
//	update的时候在controller里面调用这个方法
	public void updateComment(String id, String comment) {
		Session s=sessionFactory.openSession();
		s.beginTransaction();
		Comment comment=(Comment)s.get(Comment.class, id);
		comment.setRate(ratevalue);
		comment.setRateDateTime(new Date());
		s.update(rate);
		s.getTransaction().commit();
		s.close();
	}
}
