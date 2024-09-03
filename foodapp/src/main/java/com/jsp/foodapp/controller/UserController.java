package com.jsp.foodapp.controller;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.foodapp.Dao.UserDao;
import com.jsp.foodapp.entities.Admin;
import com.jsp.foodapp.entities.User;

@Controller
public class UserController {
	@Autowired
	UserDao userdao;
	@RequestMapping("/adduser")
	public ModelAndView addUser() {
		ModelAndView mav=new ModelAndView("createuser");
		User user=new User();
		mav.addObject("user",user);
		return mav;
		
	}
	@RequestMapping("/saveuser")
	public ModelAndView saveUser(@ModelAttribute("user") User u) {
		ModelAndView mav=new ModelAndView("loginuser");
		userdao.saveUser(u);
		return mav;
		
	}
	@RequestMapping("/validateuser")
	public  ModelAndView loginUser(ServletRequest req,HttpSession session) {
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		User user=userdao.login(email,password);
		if(user!=null) {
			ModelAndView mav=new ModelAndView("Useroptions");
			session.setAttribute("user", user);
			mav.addObject("age","LOGIN SUCCESSFULLY:");
			return mav;
		}
		else {
			ModelAndView mav=new ModelAndView("loginuser");
			mav.addObject("age","invalid credentials:");
			return mav;
		}
	}

}
