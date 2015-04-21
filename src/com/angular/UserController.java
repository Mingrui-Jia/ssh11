package com.angular;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.angular.entity.*;
import com.angular.service.*;



@Controller
@RequestMapping("/user")
public class UserController {
	@Resource(name="userManager")
	private IUserManager userManager;
	@Resource(name="followManager")
	private IFollowManager followManager;
	
	@RequestMapping("/updateProfile")
//	/{password}/{firstName}/{lastName}/{email}/{phoneNumber}
	public String updateProfile(User user) {
//		, @PathVariable String password, @PathVariable String firstName, @PathVariable String lastName, @PathVariable String email, @PathVariable String phoneNumber
		userManager.updateProfile(user);
		System.out.println("update success!");
		return "/updateSuccess";
	}
	
	
	@RequestMapping("/checkUserExist")
	public String checkUserExist(User user) {
		System.out.println(userManager.checkUserExist(user));
		if(userManager.checkUserExist(user)){
			System.out.println(userManager.checkUserExist(user));
			return "/userAlreadyExist";
		}
		else return "/addUser";
	}
	
	@RequestMapping(value="/follow/{follow}/{beingfollowed}")
	public String follow(@PathVariable String follow, @PathVariable String beingfollowed) {
		Follow f = new Follow(follow, beingfollowed);
		followManager.saveFollow(f);
		System.out.println("followSuccess");
		return "/followSuccess";
	}
	
	@RequestMapping("/unfollow/{follow}/{beingfollowed}")
	public String unfollow(@PathVariable String follow, @PathVariable String beingfollowed) {
		Follow f = new Follow(follow, beingfollowed);
		followManager.deleteFollow(f);
		System.out.println("unFollowSuccess");
		return "/unfollowSuccess";
	}	
	@RequestMapping("/toSaveUser")
	public String toSaveUser(){
		return "/addUser";
	}
	
	@RequestMapping("/checkUser")
	public String check(User user,HttpServletRequest request){
		//System.out.println(user.getUserName());
		  
		if(userManager.checkUser(user)){
			HttpSession session=request.getSession();
			session.setAttribute("currentUser", user.getUserName());
			
			return "/success";
		}else{
			return "/fail";
		}
		
	}

	@RequestMapping("/login")
	public String login(){
		
		return "/login";
	}
	
	@RequestMapping("/saveUser")
	public String saveUser(User user){
		userManager.saveUser(user);
		return "/welcome";
	}

}
