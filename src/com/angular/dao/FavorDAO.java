package com.angular.dao;

import java.util.*;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;


import com.angular.entity.*;




public class FavorDAO implements IFavorDAO{

	private SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	@Transactional
	public void saveFavor(Favor favor) {
		Session s=sessionFactory.openSession();
		s.beginTransaction();
		s.save(favor);
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
	public boolean checkFavor(Favor favor) {
		// TODO Auto-generated method stub
		Session s=sessionFactory.openSession();
		s.beginTransaction();
		Favor book1=(Favor)s.get(Favor.class, favor);
		
		s.getTransaction().commit();
		return(book1!=null);
	}
	
//	public List<Movie> readAllMovies() {
//		//select all instance of movie from class movie, not table movie
//		//这里是javax的query language，不是sql
//		Query query = em.createQuery("select movie from Movie movie");
//		return (List<Movie>)query.getResultList();
//		//query.getResultList()得到的是list of objects,cast成List<Movie>，return
//	}
//	
	
	public List<Book> findFavoriteBookByUser(User user) {
		Session s=sessionFactory.openSession();
		s.beginTransaction();
//		Query query = (Query) s.createQuery("select book from Book book");
		return (List<Book>)((Query) s.createQuery("select book from Book book")).getResultList();
	}

}
