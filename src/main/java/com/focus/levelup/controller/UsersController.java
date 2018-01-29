package com.focus.levelup.controller;

import java.util.List;

import javax.management.relation.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.focus.levelup.model.Roles;
import com.focus.levelup.model.Users;
import com.focus.levelup.services.RoleServices;
import com.focus.levelup.services.UserService;

@Controller
@RequestMapping("Users")
public class UsersController {

	@Autowired
	UserService usersService;
	
	@Autowired
	RoleServices roleServices;
	
	@Autowired
	BCryptPasswordEncoder encrypte;
	
	@RequestMapping("index")
	public String users(Model model) {
		
		List<Users> user =  (List<Users>) usersService.findAll();
		
		model.addAttribute("users", user);
		
		return "users/index";
	}
	
	@RequestMapping("newUser")
	public String newUser(Model model) {
		
		List<Roles> role = (List<Roles>) roleServices.findAll();
		model.addAttribute("roles", role);
			
		return "users/newUser";
	}
	
	@RequestMapping(value= "saveUser", method = RequestMethod.POST)
	public ModelAndView saveUser(@ModelAttribute("Users") Users users, BindingResult result ) {
		
		Users user =  new Users();
		
		user.setRole(users.getRole());		
		user.setFirstName(users.getFirstName());
		user.setLastName(users.getLastName());
		user.setEmail(users.getEmail());
		user.setStatus(users.getStatus());
		user.setPassword(encrypte.encode(users.getPassword()));
		
		usersService.save( user );
		
		return new ModelAndView("redirect:/Users/index");
	}
	
	@RequestMapping(value= "edit/{id}", method = RequestMethod.GET)
	public String edit(Model model, @PathVariable int id) {
		
		Users user =  usersService.findOne(id);
		
		List<Roles> role =  (List<Roles>) roleServices.findAll();
		
		model.addAttribute("users", user);
		model.addAttribute("roles", role);
		
		return "users/update";
	}
	
	@RequestMapping(value= "saveUpdate", method = RequestMethod.POST)
	public ModelAndView saveUpdate(@ModelAttribute("Users") Users users, BindingResult result ) {
		
		Users user =  usersService.findOne(users.getIdUser());
		
		user.setRole(users.getRole());		
		user.setFirstName(users.getFirstName());
		user.setLastName(users.getLastName());
		user.setEmail(users.getEmail());
		user.setStatus(users.getStatus());
		
		usersService.save( user );
		
		return new ModelAndView("redirect:/Users/index");
	}
	
	
}
