package com.angular.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.angular.entity.Book;

public class BookDAO implements IBookDAO{

	private SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	@Transactional
	public void saveBook(Book book) {
		Session s=sessionFactory.openSession();
		s.beginTransaction();
		s.save(book);
		s.getTransaction().commit();
		s.close();
		
//		
//		sessionFactory.getCurrentSession().save(user);
//		
//		Session s=sessionFactory.getCurrentSession();
//		s.beginTransaction();
//		
//		s.save(user);
//		s.getTransaction().commit();
		
	}
	@Override
	public boolean checkBook(Book book) {
		// TODO Auto-generated method stub
		Session s=sessionFactory.openSession();
		s.beginTransaction();
		Book book1=(Book)s.get(Book.class, book.getId());
		
		s.getTransaction().commit();
		return(book1!=null);
	}

}
