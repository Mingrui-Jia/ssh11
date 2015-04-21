package com.angular.dao;

import java.util.*;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;


import com.angular.entity.*;




public class RateDAO implements IRateDAO{

	private SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	@Transactional
//	update��ʱ����controller��������������
	public void updateRate(String bid, String uid, int ratevalue) {
		Session s=sessionFactory.openSession();
		s.beginTransaction();
		ratePK ratePK = new ratePK();
		ratePK.setBid(bid);
		ratePK.setUid(uid);
		Rate rate=(Rate)s.get(Rate.class, ratePK);
		rate.setRate(ratevalue);
		rate.setRateDateTime(new Date());
		s.update(rate);
		s.getTransaction().commit();
		s.close();
	}
	
	@Override
	@Transactional
//	��ʱ���������������ߴ�pk��ʱ����õ�ǰʱ��
	public void saveRate(String bid, String uid, int ratevalue) {
		Session s=sessionFactory.openSession();
		s.beginTransaction();
		Rate rate = new Rate();
		rate.setBid(bid);
		rate.setUid(uid);
		rate.setRate(ratevalue);
		rate.setRateDateTime(new Date());
		s.save(rate);
		s.getTransaction().commit();
		s.close();
	}
	
//	@Override
//	@Transactional
////	��ʱ���������������ߴ�pk��ʱ����õ�ǰʱ��
//	public void saveRate(Rate rate) {
//		Session s=sessionFactory.openSession();
//		s.beginTransaction();
//		s.save(rate);
//		s.getTransaction().commit();
//		s.close();
//	}
	

//	return1��ʾ���book��rate���д���
//	�����ƺ���һ��rate��PK�ȽϺã��ٿ�
	@Override
	public boolean checkRate(String bid, String uid) {
		// TODO Auto-generated method stub
		Session s=sessionFactory.openSession();
		s.beginTransaction();
		ratePK ratePK = new ratePK();
		ratePK.setBid(bid);
		ratePK.setUid(uid);
		Rate rate=(Rate)s.get(Rate.class, ratePK);
	
		s.getTransaction().commit();
		return(rate!=null);
	}
	

//	ע��˴�HQL�ʹ��ݲ����ĸ�ʽ��ֻ����hibernate��query����EclipseLink�в�ͬ
//	@RequestMapping(value="/findFavoriteBookByUser/{username}")
//	favor.userID��favor.bookIDҪע������ݿ��е���������һ�£�ע���Сд
	@Override
	public List<String> findRatedBookByUser(String userid) {
		Session s=sessionFactory.openSession();
		s.beginTransaction();
		String hql = "select rate.bid "
				+ "from Rate rate "
				+ "where rate.uid=" + "'"+userid+"'";
		Query query = s.createQuery(hql);
		return (List<String>)query.list();
		
//		List<String> bookIdList = new ArrayList();
//		for (Book book : books) {
//			bookIdList.add(book.getId());
//		}
//		
//		return bookIdList;
	}
	
	@Override
	public List<String> findUserByRatedBook(String bookid) {
		Session s=sessionFactory.openSession();
		s.beginTransaction();
		String hql = "select rate.uid "
				+ "from Rate rate "
				+ "where rate.bid=" + "'"+bookid+"'";
		Query query = s.createQuery(hql);
		return (List<String>)query.list();
	}

}
