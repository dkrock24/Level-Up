package com.focus.levelup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.focus.levelup.model.Roles;
import com.focus.levelup.model.Users;
import com.focus.levelup.services.UserService;

@Controller
@RequestMapping("Users")
public class UsersController {

	@Autowired
	UserService usersServices;
	
	@RequestMapping("index")
	public String users(Model model) {
		
		List<Users> user =  (List<Users>) usersServices.findAll();
		
		model.addAttribute("users", user);
		
		return "users/index";
	}
	
	@RequestMapping(value= "edit/{id}", method = RequestMethod.GET)
	public String edit(Model model, @PathVariable int id) {
		
		Users user =  usersServices.findOne(id);	
		
		model.addAttribute("users", user);
		
		return "users/update";
	}
}
