package com.angular;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.angular.entity.*;

import com.angular.service.IBookManager;
import com.angular.service.ICommentsManager;
import com.angular.service.IFavorManager;
import com.angular.service.IRateManager;


@Controller
@RequestMapping(value="/book")
public class bookController {
	@Resource(name="bookManager")
	private IBookManager bookManager;
	@Resource(name="favorManager")
	private IFavorManager favorManager;
	@Resource(name="rateManager")
	private IRateManager rateManager;
	@Resource(name="commentsManager")
	private ICommentsManager commentsManager;
	
////	4.23 15:32
//	@RequestMapping(value="")
//	public String toCreateComment() {
//		
//	}
	
	
	
//	4.21 17:00
	@RequestMapping(value="/saveRate")
	public String addRate( String bid,  String uid,  int ratevalue) {
		rateManager.saveRate(bid, uid, ratevalue);
		return "/rateSuccess";
	}
//
////	4.20 18:16 用{username}做参数方便调试
//	
//	@RequestMapping(value="/findFavoriteBookByUser/{username}")
//	public String findFavoriteBookByUser(@PathVariable String username){
//		List<String> books = favorManager.findFavoriteBookByUser(username);
//		for(String bookId : books) {
//			System.out.println(bookId);
//		}
//		return "/success";
//	}
//	
////	也可以考虑加在UserController里面
//	@RequestMapping(value="/findUserByFavoriteBook/{bookid}")
//	public String findUserByFavoriteBook(@PathVariable String bookid){
//		List<String> users = favorManager.findUserByFavoriteBook(bookid);
//		for(String username : users) {
//			System.out.println(username);
//		}
//		return "/success";
//	}
//	
	
	@RequestMapping(value="/search",method=RequestMethod.GET)
	public ModelAndView toIndex(){
		String result ="this is delUser------";
		return new ModelAndView("/static/000","result",result);
	}
//	@RequestMapping(value="/{bookID}")
//	public ModelAndView toDetails(@PathVariable String bookID){
//
//		String result ="this is details";
//		return new ModelAndView("book/details","id",bookID);
//	}
	@RequestMapping(value="/{bookID}")
	public String toDetails(@PathVariable String bookID, Model model){

		//System.out.println("this is details");
		List<String> users=(List<String>) favorManager.findUserByFavoriteBook(bookID);
		
		model.addAttribute("users", users);
		model.addAttribute("id", bookID);
		return  "book/details";
	}
//	@RequestMapping(value="/add/{bookID}")
//	public ModelAndView addBook(@PathVariable String bookID,HttpServletRequest request){
//		System.out.println(bookID);
//		
//		Book book= new Book();
//		book.setId(bookID);
//		bookManager.saveBook(book);
//		String result ="this is details";
//		return toDetails(bookID);
//		//return new ModelAndView("/book/"+bookID,"id",bookID);
//	}
	
//	@RequestMapping(value="/addFavor/{bookID}/{username}")
//	public String addFavor(@PathVariable String bookID,@PathVariable String username,HttpServletRequest request){
//		if(username.equals("null")){
//			UserController uc= new UserController();
//			return uc.login();
//		}
//		Book book= new Book();
//		book.setId(bookID);
//		if(!bookManager.checkBook(book)){
//			
//			bookManager.saveBook(book);
//		}else{
//			System.out.println("book already exists");
//		}
//		Favor favor=new Favor();
//		favor.setBookID(bookID);
//		favor.setUserID(username);
//		if(favorManager.checkFavor(favor)){
//			return "book/already";
//		}else{
//			System.out.println(favor.getBookID());
//			System.out.println(favor.getUserID());
//			favorManager.saveFavor(favor);
//			HttpSession session=request.getSession();
//			session.setAttribute("bookID", bookID);
//			return "book/confirm";
//		}
//		
//		//return new ModelAndView("/book/"+bookID,"id",bookID);
//	}
	
	@RequestMapping(value="/addFavor/{bookID}/{username}")
	public String addFavor(@PathVariable String bookID,@PathVariable String username,HttpServletRequest request){
		if(username.equals("null")){
			UserController uc= new UserController();
			return uc.login();
		}
		Book book= new Book();
		book.setId(bookID);
		Favor favor=new Favor();
		favor.setBookID(bookID);
		favor.setUserID(username);
		
		if(!bookManager.checkBook(book)){
			
			bookManager.saveBook(book);
		}else{
			System.out.println("book already exists");
		}
	
		if(favorManager.checkFavor(favor)){
			favorManager.deleteFavor(favor);
			return "book/unfavor";
		}else{
			System.out.println(favor.getBookID());
			System.out.println(favor.getUserID());
			favorManager.saveFavor(favor);
			HttpSession session=request.getSession();
			session.setAttribute("bookID", bookID);
			return "book/favor";
		}
		
		//return new ModelAndView("/book/"+bookID,"id",bookID);
	}
	
	
//	@RequestMapping(value="/deleteFavor/{bookID}/{username}")
//	public String deleteFavor(@PathVariable String bookID,@PathVariable String username,HttpServletRequest request){
//
//		Favor favor=new Favor();
//		favor.setBookID(bookID);
//		favor.setUserID(username);
//		
//		favorManager.deleteFavor(favor);
//		return "book/confirm";
//	}
	

}