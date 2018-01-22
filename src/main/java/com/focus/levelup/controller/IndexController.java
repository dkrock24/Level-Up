package com.focus.levelup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("/index")
	public String demo(Model model) {
		
		model.addAttribute("data", "Welcome");
		return "home/index";
	}
}
