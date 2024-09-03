package com.jsp.foodapp.controller;

import java.util.List;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jsp.foodapp.Dao.ProductDao;
import com.jsp.foodapp.entities.Product;

@Controller
public class ProductController {
	@Autowired
	ProductDao dao;
	@RequestMapping("/addproduct")
	public ModelAndView addProduct() {
		ModelAndView mav=new ModelAndView("createproduct");
		Product p=new Product();
		mav.addObject("Product",p);
		return mav;
	}
	@RequestMapping("/saveproduct")
	public ModelAndView saveProduct(@ModelAttribute("Product")Product p) {
		dao.saveProduct(p);
		ModelAndView mav=new ModelAndView("adminoptions");
		return mav;
	}
	@RequestMapping("/viewallproducts")
	public ModelAndView fetchallproducts() {
		List<Product> products=dao.viewAllProducts();
		ModelAndView mav=new ModelAndView("allproducts");
		mav.addObject("productlist",products);
		return mav;
	}
	@RequestMapping("/editproduct")
	public ModelAndView editProduct(@RequestParam("id")int id) {
		Product products=dao.viewProductById(id);
		ModelAndView mav=new ModelAndView("updateproduct");
		mav.addObject("productdata",products);
		return mav;
	}
	@RequestMapping("/updatepro")
	public ModelAndView updateProduct(ServletRequest req) {
		String id=req.getParameter("id");
		String name=req.getParameter("name");
		String type=req.getParameter("type");
		String cost=req.getParameter("cost");
		
		Product p=new Product();
		p.setId(Integer.parseInt(id));
		p.setName(name);
		p.setType(type);
		p.setCost(Double.parseDouble(cost));
		
		dao.updateProduct(p);
		ModelAndView mav=new ModelAndView("redirect:/viewallproducts");
		return mav;
	}
	@RequestMapping("/deleteproduct")
	public ModelAndView deleteProduct(@RequestParam("id")int id) {
		ModelAndView mav=new ModelAndView("redirect:/viewallproducts");
		dao.deleteProductById(id);
		return mav;
		
		
	}
	@RequestMapping("/viewallproduct")
	public ModelAndView fetchallproduct() {
		List<Product> products=dao.viewAllProducts();
		ModelAndView mav=new ModelAndView("viewproducts");
		mav.addObject("productlist",products);
		return mav;
	}

}
