package com.angular.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.angular.entity.*;


public class FollowDAO implements IFollowDAO{
	

	private SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	@Transactional
	public void follow(Follow follow) {
		Session s=sessionFactory.openSession();
		s.beginTransaction();
//		Ҫ��save���follow���ܴ����ݿ�����ȡ����ֱ�Ӹ�Ҳ���Ե���delete����
		s.save(follow);
//		����follow��������
		User user1 = (User)s.get(User.class, follow.getFollow());
		int user1following = user1.getFollowing();
		user1following = user1following + 1;
		user1.setFollowing(user1following);
		s.save(user1);
//		��follow����
		User user2 = (User)s.get(User.class, follow.getBeingFollowed());
		int user2followed = user2.getFollowed();
		user2followed = user2followed + 1;
		user2.setFollowed(user2followed);
		s.save(user2);
		
		s.getTransaction().commit();
		s.close();
		
	}

	@Override
	@Transactional
	public void unfollow(Follow follow) {
		Session s=sessionFactory.openSession();
		s.beginTransaction();
//		����follow��������
		User user1 = (User)s.get(User.class, follow.getFollow());
		int user1following = user1.getFollowing();
		user1following = user1following - 1;
		user1.setFollowing(user1following);
		s.save(user1);
//		��follow����
		User user2 = (User)s.get(User.class, follow.getBeingFollowed());
		int user2followed = user2.getFollowed();
		user2followed = user2followed - 1;
		user2.setFollowed(user2followed);
		s.save(user2);
		
		s.delete(follow);
		s.getTransaction().commit();
		s.close();
		
	}

	@Override
	public boolean checkFollow(Follow follow) {
		// TODO Auto-generated method stub
		Session s=sessionFactory.openSession();
		s.beginTransaction();
		Follow follow1=(Follow)s.get(Follow.class, follow);
		
		s.getTransaction().commit();
		return(follow1!=null);
	}

}
