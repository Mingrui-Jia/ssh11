package com.angular.service;

import com.angular.entity.Follow;

public interface IFollowManager {

	public void saveFollow(Follow follow);

	public void deleteFollow(Follow follow);

	public boolean checkFollow(Follow follow);
	
}
