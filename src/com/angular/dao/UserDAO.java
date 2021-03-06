package com.angular.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.angular.entity.Follow;
import com.angular.entity.User;




public class UserDAO implements IUserDAO {

	
	private SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
//	20150423-11:00
//	20150423-14:36 revised
	@Override
	@Transactional
//	所有被谁follow
	public List<String> findFollowedByUser(String username) {
		Session s=sessionFactory.openSession();
		s.beginTransaction();
//		得到follow实体的list，没有数字的ID，麻烦！
		List<Follow> follows = new ArrayList<Follow>();
		String hql1 = "select follow from Follow follow";
		Query query1 = s.createQuery(hql1);
		follows = (List<Follow>)query1.list();
		List<String> resultsList = new ArrayList<String>();
		for (Follow follow :follows) {
			if (follow.getBeingFollowed().equals(username)) {
				System.out.println(follow.getFollow());
				resultsList.add(follow.getFollow());
			}
		}
		
		s.getTransaction().commit();

		return resultsList;
	}
	
//	//readAllMovies
//	public List<Movie> readAllMovies() {
//		//select all instance of movie from class movie, not table movie
//		//这里是javax的query language，不是sql
//		Query query = em.createQuery("select movie from Movie movie");
//		return (List<Movie>)query.getResultList();
//		//query.getResultList()得到的是list of objects,cast成List<Movie>，return
//	}
	
//	20150423-11:00
//	20150423-14:36 revised
	@Override
	@Transactional
//	所有follow的人
	public List<String> findFollowingByUser(String username) {
		Session s=sessionFactory.openSession();
		s.beginTransaction();
//		得到follow实体的list，没有数字的ID，麻烦！
		List<Follow> follows = new ArrayList<Follow>();
		String hql1 = "select follow from Follow follow";
		Query query1 = s.createQuery(hql1);
		follows = (List<Follow>)query1.list();
		List<String> resultsList = new ArrayList<String>();
		for (Follow follow :follows) {
			if (follow.getFollow().equals(username)) {
				resultsList.add(follow.getBeingFollowed());
			}
		}
		
		s.getTransaction().commit();

		return resultsList;
	}
	
//	public List<String> findFollowingByUser(String username) {
//		Session s=sessionFactory.openSession();
//		s.beginTransaction();
//		String hql = "select user.following "
//				+ "from User user "
//				+ "where user.userName=" + "'"+username+"'";
//		Query query = s.createQuery(hql);
//		s.getTransaction().commit();
//
//		return (List<String>)query.list();
//	}
	
//	20150423-11:00
	@Override
	@Transactional
	public User findUserByUsername(String username) {
		Session s=sessionFactory.openSession();
		s.beginTransaction();
		User user = (User) s.get(User.class, username);
		s.getTransaction().commit();

		return user;
	}
	
	
	
	@Override
	@Transactional
	public void saveUser(User user) {
		Session s=sessionFactory.openSession();
		s.beginTransaction();
		s.save(user);
		s.getTransaction().commit();
		s.close();
		
	}
	
	@Override
	@Transactional
	public boolean checkUserExist(User user) {
		// 检查输入的username是否存在
		Session s=sessionFactory.openSession();
		s.beginTransaction();
		User user1=(User)s.get(User.class, user.getUserName());
		
		s.getTransaction().commit();
		return(user1!=null);
	}
	
//	看username&password和数据库中的是否匹配
	@Override
	@Transactional
	public boolean checkUser(User user) {
		Session s=sessionFactory.openSession();
		s.beginTransaction();
		
		User user1= (User)s.load(User.class,user.getUserName());
		s.getTransaction().commit();
		if(user1.getPassword().equals(user.getPassword())){
			return true;
		}else{
			return false;
		}
		
	}
	

	
//	4.21 13：00
	public void updateProfile(User user) {
//		, String password, String firstName, String lastName, String email, String phoneNumber
		Session s=sessionFactory.openSession();
		s.beginTransaction();
		User user1=(User)s.get(User.class, user.getUserName());
		String password = user.getPassword();
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		String email = user.getEmail();
		String phoneNumber = user.getPhoneNumber();
		user1.setPassword(password);
		user1.setFirstName(firstName);
		user1.setLastName(lastName);
		user1.setEmail(email);
		user1.setPhoneNumber(phoneNumber);
		s.update(user1);
//		s.merge(user1);
		s.getTransaction().commit();
		s.close();
	}
	

	
}
