package com.angular.dao;

import java.util.List;

import com.angular.entity.Favor;
import com.angular.entity.Rate;
import com.angular.entity.ratePK;

public interface IRateDAO {
	public List<String> findRatedBookByUser(String username);
	public List<String> findUserByRatedBook(String bookid);
	public void saveRate(Rate rate);
	public void deleteRate(Rate rate);
	public boolean checkRate(Rate rate);
	public void setRate(ratePK ratePK, double ratevalue);
	public void saveRate(ratePK ratePK, double ratevalue);
}
