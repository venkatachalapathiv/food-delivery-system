  package com.jsp.foodapp.controller;

import java.util.ArrayList;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.foodapp.Dao.FoodOrderDao;
import com.jsp.foodapp.Dao.UserDao;
import com.jsp.foodapp.entities.FoodOrder;
import com.jsp.foodapp.entities.Item;
import com.jsp.foodapp.entities.User;

@Controller
public class FoodorderController {
	@Autowired
	FoodOrderDao dao;
	@Autowired
	UserDao udao;
	double totalbill=0;
	
	@RequestMapping("/createorder")
	public ModelAndView createOrder() {
		ModelAndView mav=new ModelAndView("createfoodorder");
		FoodOrder foodorder=new FoodOrder();
		mav.addObject("foodorder",foodorder);
		return mav;
	}
	@RequestMapping("/savefoodorder")
	public ModelAndView savefoodorder(@ModelAttribute("foodorder") FoodOrder order,HttpSession session) {
		session.setAttribute("userfoodorder", order);
		ModelAndView mav=new ModelAndView("redirect:/viewallproduct");
		return mav;
	}
	@RequestMapping("/submitfoodorder")
	public ModelAndView submitFoodorder(HttpSession session) {
		FoodOrder foodorder=(FoodOrder)session.getAttribute("userfoodorder");
		List<Item> items=(List<Item>)session.getAttribute("itemsList");
		foodorder.setItem(items);
		
		
		items.stream().forEach(i-> {
			totalbill+=i.getCost();
		});
		
		foodorder.setTotalbill(totalbill);
		
		User u=(User)session.getAttribute("user");
		List<FoodOrder> orders=u.getOrders();
		if(orders.size()>0) {
			orders.add(foodorder);
			foodorder.setUser(u);
			u.setOrders(orders);
		}else {
			List<FoodOrder> orders1=new ArrayList<FoodOrder>();
			orders1.add(foodorder);
			foodorder.setUser(u);
			u.setOrders(orders1);
		}
		dao.saveFoodOrder(foodorder);
		udao.updateUser(u);
		ModelAndView mav = new ModelAndView("Useroptions");
		return mav;
	}

}
