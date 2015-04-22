package com.angular;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	@Resource(name="favorManager")
	private IFavorManager favorManager;
	
	
//	Model����������ҳ���д�ֵ������һ��map��key��"username"��ֵ�����ﴫ���username
//	Ҫ���õ���ǰ��¼���û����ٸ���profile���������û�����ʾ����һ�����ĵĽ���
	@RequestMapping("/update/{username}")
	public String toUpdate(@PathVariable String username, Model model){
		model.addAttribute("username", username);
		return "/updateProfile";
	}
//	����userDAO����ķ�������û
	@RequestMapping("/updateProfile/{username}")
	public String updateProfile(User user,@PathVariable String username) {
		System.out.println(user.getPassword());
		user.setUserName(username);
		userManager.updateProfile(user);
		System.out.println("update success!");
		return "/accountInfo";
	}
	
	
//	@RequestMapping(value="/follow/{follow}/{beingfollowed}")
//	public String follow(@PathVariable String follow, @PathVariable String beingfollowed) {
//		Follow f = new Follow(follow, beingfollowed);
//		followManager.saveFollow(f);
//		System.out.println("followSuccess");
//		return "/followSuccess";
//	}
//	
//	@RequestMapping("/unfollow/{follow}/{beingfollowed}")
//	public String unfollow(@PathVariable String follow, @PathVariable String beingfollowed) {
//		Follow f = new Follow(follow, beingfollowed);
//		followManager.deleteFollow(f);
//		System.out.println("unFollowSuccess");
//		return "/unfollowSuccess";
//	}	
	
//	@RequestMapping("/checkUserExist")
//	public String checkUserExist(User user) {
////		System.out.println(userManager.checkUserExist(user));
//		if(userManager.checkUserExist(user)){
////			System.out.println(userManager.checkUserExist(user));
//			return "/userAlreadyExist";
//		}
//		else return saveUser(user);
//	}
	
//	ͨ��toSaveUserת��/addUser���jsp���ٵ���saveUser
	@RequestMapping("/toSaveUser")
	public String toSaveUser(){
		return "/addUser";
	}
	
//	������addUser��jsp���������saveUser�ķ�����map������welcome.jsp��Angular.xml����ӵ�ǰ׺��׺��
	@RequestMapping("/saveUser")
	public String saveUser(User user){
		if (userManager.checkUserExist(user)) {
			System.out.println("userAlreadyExist");
			return "/addUser";
//			return toSaveUser();
		}
		else {
			userManager.saveUser(user);
		}
		return "/welcome";
	}
	
////	������addUser��jsp���������saveUser�ķ�����map������welcome.jsp��Angular.xml����ӵ�ǰ׺��׺��
//	@RequestMapping("/saveUser")
//	public String saveUser(User user){
//		
//		userManager.saveUser(user);
//		return "/welcome";
//	}
	
//	����profile.jsp��<h1> ${otheruser }<a class="btn btn-danger" href="<%=request.getContextPath()%>
//	/user/follow/<%=str %>/${otheruser}"><span class="glyphicon glyphicon-heart"></span></a></h1>
	@RequestMapping(value="/follow/{follower}/{followed}")
	public String addFollow(@PathVariable String follower,@PathVariable String followed,
			HttpServletRequest request, Model model){
		if(follower.equals("null")){
			UserController uc= new UserController();
			return uc.login();
		}
		Follow follow=new Follow(follower,followed);
		
		if(!follower.equals(followed)){
			
			
			if(followManager.checkFollow(follow)){
				followManager.deleteFollow(follow);
				System.out.println("delete");
			}else{
				followManager.saveFollow(follow);
				System.out.println("save");
			}
		}
		
		return toProfile(followed,model);
	
//		if(favorManager.checkFavor(favor)){
//			favorManager.deleteFavor(favor);
//			return "book/unfavor";
//		}else{
//			System.out.println(favor.getBookID());
//			System.out.println(favor.getUserID());
//			favorManager.saveFavor(favor);
//			HttpSession session=request.getSession();
//			session.setAttribute("bookID", bookID);
//			return "book/favor";
//		}
		
		
	}


	
	@RequestMapping("/{username}")
	public String toAccountInfo(@PathVariable String username,Model model) {
//		����õ���username��null��������¼ҳ�棬ע��������Ե���controller����ķ���
		if(username.equals("null")){
			return login();
		}
//		�����������list����model�У�������jsp�е��ã���accountInfoҳ���г�
		List<String> books=favorManager.findFavoriteBookByUser(username);
		model.addAttribute("books", books);
		return "/accountInfo";
	}
	
	@RequestMapping("/profile/{username}")
	public String toProfile(@PathVariable String username,Model model){
		
		List<String> books=favorManager.findFavoriteBookByUser(username);
		model.addAttribute("books", books);
		model.addAttribute("otheruser", username);
		return "/profile";
	}
	
	@RequestMapping("/checkUser")
	public String check(User user, HttpServletRequest request){
		//System.out.println(user.getUserName());
		  
		if(userManager.checkUser(user)){
			HttpSession session=request.getSession();
			session.setAttribute("currentUser", user.getUserName());
			
			return "/success";
		}else{
			return "/fail";
		}
		
	}

//	jspҳ���п��Ե������ͨ��"/login" map����֮����returnһ��String��map��
	@RequestMapping("/login")
	public String login(){
		
		return "/login";
	}
	


}
