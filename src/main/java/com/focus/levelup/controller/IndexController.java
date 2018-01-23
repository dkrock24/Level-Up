package com.focus.levelup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.focus.levelup.model.Roles;
import com.focus.levelup.services.RoleServices;

@Controller
@RequestMapping("Dashboard")
public class IndexController {

	@Autowired
	RoleServices roleServices;
	
	@RequestMapping("index")
	public String demo(Model model) {
		
		List<Roles> role =  (List<Roles>) roleServices.findAll();
		
		model.addAttribute("data", role);
		return "home/index";
	}
}
