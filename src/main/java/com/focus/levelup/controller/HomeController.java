package com.focus.levelup.controller;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.focus.levelup.model.Roles;
import com.focus.levelup.services.RoleServices;

@Controller
public class HomeController {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	RoleServices roleServices;

	@RequestMapping(value={"/", "/login"} , method = RequestMethod.GET)
	public ModelAndView home() {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home/login");

		return modelAndView;
	}

	@RequestMapping("/loginsuccess")
	public String defaultAfterLogin(HttpServletRequest request) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		//String newPassword = bCryptPasswordEncoder.encode("123456789");

		//List<Roles> userRoles =  (List<Roles>) roleServices.findAll();

		Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) auth.getAuthorities();

		for (GrantedAuthority authority : authorities) { 
			if(request.isUserInRole(authority.getAuthority())){
				System.out.println(authority.getAuthority());
			}else {
				System.out.println("NO : " + authority.getAuthority());
			}
		}

		System.out.println(request.isUserInRole("Admin") ? "Admin" : "no_admin");

	    // Validation to handle redirect when is an administrator
		if (request.isUserInRole("Role_Admin")) {
	        return "redirect:/admin/";
	    }

		// Redirecting for quizzes home page for normal users
	    return "redirect:/home/index";
	}
}
