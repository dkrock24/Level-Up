package com.focus.levelup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.focus.levelup.model.ProgrammingLanguage;
import com.focus.levelup.model.QuestionTypes;
import com.focus.levelup.model.QuizLevels;
import com.focus.levelup.model.Users;
import com.focus.levelup.services.ProgrammingLanguageService;
import com.focus.levelup.services.QuestionTypesService;
import com.focus.levelup.services.QuizLevelsService;
import com.focus.levelup.services.QuizzesServices;
import com.focus.levelup.services.TestsService;



@Controller
@RequestMapping("QuestionType")
public class QuestionType {
	
	@Autowired
	ProgrammingLanguageService languagesServices;
	
	@Autowired
	QuizLevelsService QlevelServices;
	
	@Autowired
	QuizzesServices quizzesServices;
	
	@Autowired
	TestsService testServices;
	
	@Autowired
	QuestionTypesService questionType;

	@RequestMapping("index")
	public String index(Model model) {
		
		int countLanguages = (int) languagesServices.count();
		int countQLevel = (int) QlevelServices.count();
		int countQuizz = (int) quizzesServices.count();
		int countPendingTest = (int) testServices.count();
				
		model.addAttribute("totalLanguages", countLanguages);
		model.addAttribute("totalLevels", countQLevel);
		model.addAttribute("countQuizz", countQuizz);
		model.addAttribute("countPendingTest", countPendingTest);
		
		return "questionType/addQuestionType";
	}
	
	@RequestMapping("addQuestionType")
	public String addQuestionType(Model model) {
		
		List<QuestionTypes> qt = (List<QuestionTypes>) questionType.findAll();
		
		model.addAttribute("qt",qt);
		
		// Basic Count 
		int countLanguages = (int) languagesServices.count();
		int countQLevel = (int) QlevelServices.count();
		int countQuizz = (int) quizzesServices.count();
		int countPendingTest = (int) testServices.count();
				
		model.addAttribute("totalLanguages", countLanguages);
		model.addAttribute("totalLevels", countQLevel);
		model.addAttribute("countQuizz", countQuizz);
		model.addAttribute("countPendingTest", countPendingTest);
		// End Basic Count
		
		return "questionType/addQuestionType";
	}
	
	/*
	 * SAVE NEW PROGRAMMING LANGUAGES
	 */
	@RequestMapping("saveQestionType")
	public ModelAndView saveQestionType(@ModelAttribute("QuestionTypes") QuestionTypes types, BindingResult result) {
		
		QuestionTypes type = new QuestionTypes();
		type.setType(types.getType());
		type.setFriendlyName(types.getFriendlyName());
		type.setStatus(types.getStatus());
		
		questionType.save(type);
		
		return new ModelAndView("redirect:/QuestionType/addQuestionType");
	}	
	
	/*
	 * EDIT PROGRAMMING LANGUAGES
	 */
	@RequestMapping(value ="editQuestionType/{id}", method= RequestMethod.GET)
	public String editLevel(Model model,@PathVariable int id) {
		
		List<QuestionTypes> qt = (List<QuestionTypes>) questionType.findAll();			
		QuestionTypes qt_edit = questionType.findOne(id);
		
		model.addAttribute("qt",qt);
		model.addAttribute("inputs", qt_edit);
		
		return ("questionType/editQuestionType");
	}
	
	
	/*
	 * UPDATE PROGRAMMING LANGUAGES
	 */
	@RequestMapping("updateQuestionType")
	public ModelAndView updateQuestionType(@ModelAttribute("QuestionTypes") QuestionTypes types, BindingResult result) {
		
		QuestionTypes qt = questionType.findOne(types.getIdType());
		qt.setType(types.getType());
		qt.setFriendlyName(types.getFriendlyName());
		qt.setStatus(types.getStatus());
		
		questionType.save(qt);		
		
		return new ModelAndView("redirect:/QuestionType/addQuestionType");
	}	
	
}
