package com.focus.levelup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.focus.levelup.entity.Role;
import com.focus.levelup.services.RoleServices;

@Controller
public class IndexController {

	@Autowired
	RoleServices roleServices;
	
	@RequestMapping("/index")
	public String demo(Model model) {
		
		List<Role> role =  (List<Role>) roleServices.findAll();
		
		for(Role roles : role) {
			System.out.println(roles.getRole());
		}
		
		model.addAttribute("data", role);
		return "home/index";
	}
}
