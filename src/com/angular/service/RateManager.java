package com.angular.service;

import java.util.List;

import com.angular.dao.IBookDAO;
import com.angular.dao.IFavorDAO;
import com.angular.entity.Book;
import com.angular.entity.Favor;

public class RateManager implements IFavorManager {

	private IFavorDAO favorDao;
	public IFavorDAO getFavorDao() {
		return favorDao;
	}

	public void setFavorDao(IFavorDAO favorDao) {
		this.favorDao = favorDao;
	}

	@Override
	public void saveFavor(Favor favor) {
		// TODO Auto-generated method stub
		favorDao.saveFavor(favor);
		
	}
	
	@Override
	public void deleteFavor(Favor favor) {
		// TODO Auto-generated method stub
		favorDao.deleteFavor(favor);
		
	}

	@Override
	public boolean checkFavor(Favor favor) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public List<String> findFavoriteBookByUser(String username) {
		return favorDao.findFavoriteBookByUser(username);
	}
	
	@Override
	public List<String> findUserByFavoriteBook(String bookid) {
		return favorDao.findUserByFavoriteBook(bookid);
	}

	
	

}
