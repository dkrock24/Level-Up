package com.focus.levelup.controller;

import java.util.Date;
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
import com.focus.levelup.model.Questions;
import com.focus.levelup.model.QuizLevels;
import com.focus.levelup.model.Quizzes;
import com.focus.levelup.model.Users;
import com.focus.levelup.services.ProgrammingLanguageService;
import com.focus.levelup.services.QuestionTypesService;
import com.focus.levelup.services.QuestionsService;
import com.focus.levelup.services.QuizLevelsService;
import com.focus.levelup.services.QuizzesServices;
import com.focus.levelup.services.TestsService;
import com.focus.levelup.services.UserService;



@Controller
@RequestMapping("Quizz")
public class QuizzController {
	
	@Autowired
	ProgrammingLanguageService languagesServices;
	
	@Autowired
	QuizLevelsService QlevelServices;
	
	@Autowired
	QuizzesServices quizzesServices;
	
	@Autowired
	TestsService testServices;
	
	@Autowired
	UserService userServices;
	
	@Autowired
	QuestionTypesService questionTypeServices;
	
	@Autowired
	QuestionsService questionServices;

	@RequestMapping("index")
	public String index(Model model) {
		
		List<Quizzes> quizzes = (List<Quizzes>) quizzesServices.findAll();		
		
		int countLanguages = (int) languagesServices.count();
		int countQLevel = (int) QlevelServices.count();
		int countQuizz = (int) quizzesServices.count();
		int countPendingTest = (int) testServices.count();
				
		model.addAttribute("totalLanguages", countLanguages);
		model.addAttribute("totalLevels", countQLevel);
		model.addAttribute("countQuizz", countQuizz);
		model.addAttribute("countPendingTest", countPendingTest);
		
		model.addAttribute("quizzes",quizzes);
		
		return "quizz/indexLanguages";
	}
	
	@RequestMapping("addLanguages")
	public String addLanguages(Model model) {
		
		List<ProgrammingLanguage> pl = (List<ProgrammingLanguage>) languagesServices.findAll();
		
		model.addAttribute("pl",pl);
		
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
		
		return "quizz/addLanguages";
	}
	
	/*
	 * SAVE NEW PROGRAMMING LANGUAGES
	 */
	@RequestMapping("saveLanguage")
	public ModelAndView saveLanguage(@ModelAttribute("ProgrammingLanguage") ProgrammingLanguage languages, BindingResult result) {
		
		ProgrammingLanguage pl = new ProgrammingLanguage();
		pl.setLanguage(languages.getLanguage());
		pl.setStatus(languages.getStatus());
		
		languagesServices.save(pl);
		
		return new ModelAndView("redirect:/Quizz/addLanguages");
	}	
	
	/*
	 * EDIT PROGRAMMING LANGUAGES
	 */
	@RequestMapping(value ="editLanguages/{id}", method= RequestMethod.GET)
	public String editLanguage(Model model,@PathVariable int id) {
		
		List<ProgrammingLanguage> pl = (List<ProgrammingLanguage>) languagesServices.findAll();			
		ProgrammingLanguage pl_edit = languagesServices.findOne(id);
		
		model.addAttribute("pl",pl);
		model.addAttribute("languages", pl_edit);
		
		return ("quizz/editLanguages");
	}
	
	
	/*
	 * UPDATE PROGRAMMING LANGUAGES
	 */
	@RequestMapping("updateLanguage")
	public ModelAndView updateLanguage(@ModelAttribute("ProgrammingLanguage") ProgrammingLanguage languages, BindingResult result) {
		
		ProgrammingLanguage pl = languagesServices.findOne(languages.getIdLanguage());
		pl.setLanguage(languages.getLanguage());
		pl.setStatus(languages.getStatus());
		
		languagesServices.save(pl);		
		
		return new ModelAndView("redirect:/Quizz/addLanguages");
	}	
	
	/*
	 * Quizzes Administration 
	 */
	
	@RequestMapping(value ="addQuizz")
	public String addQuizz(Model model) {
		
		List<Quizzes> quizzes = (List<Quizzes>) quizzesServices.findAll();		
		
		int countLanguages = (int) languagesServices.count();
		int countQLevel = (int) QlevelServices.count();
		int countQuizz = (int) quizzesServices.count();
		int countPendingTest = (int) testServices.count();
				
		model.addAttribute("totalLanguages", countLanguages);
		model.addAttribute("totalLevels", countQLevel);
		model.addAttribute("countQuizz", countQuizz);
		model.addAttribute("countPendingTest", countPendingTest);
		
		model.addAttribute("quizzes",quizzes);
		
		// Services		
		List<ProgrammingLanguage> pl = (List<ProgrammingLanguage>) languagesServices.findAll();	
		List<QuizLevels> ql = (List<QuizLevels>) QlevelServices.findAll();
		
		model.addAttribute("languages",pl);
		model.addAttribute("ql",ql);
		
		return ("quizz/addQuizz");
	}
	
	@RequestMapping(value ="saveQuizz")
	public ModelAndView saveQuizz(@ModelAttribute("Quizzes") Quizzes quizz, BindingResult result) {
		
		//Find user
		Users user = userServices.findOne(2);
		QuizLevels ql = QlevelServices.findOne(1);
		Date d = new Date();		
		
		//Create Instance of Quizz
		Quizzes quizzes = new Quizzes();
		quizzes.setProgrammingLanguage(quizz.getProgrammingLanguage());
		quizzes.setQuizLevel(quizz.getQuizLevel());
		quizzes.setDescription(quizz.getDescription());
		quizzes.setCreatedOn(d);
		quizzes.setCreatedBy("Test");
		quizzes.setStatus(quizz.getStatus());
		quizzes.setUser(user);		
		
		Quizzes quiz = quizzesServices.save(quizzes);		
		int idQuiz = quiz.getIdQuiz();
		
		return new ModelAndView("redirect:/Quizz/addQuestion/"+idQuiz);
	}
	
	/*
	 * QUESTION SECTION ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: 
	 */
	
	// Load Form of Question
	@RequestMapping(value ="addQuestion/{idQuiz}")
	public String addQuestion(Model model,@PathVariable int idQuiz) {
		
		// GET Quizz
		Quizzes quiz = quizzesServices.findOne(idQuiz);	
		model.addAttribute("quizz", quiz);
		
		// Return a list of questions
		List<Questions> question = (List<Questions>) quiz.getQuestions();
		model.addAttribute("question",question);
		
		// Find Quizz
		List<Quizzes> quizzes = (List<Quizzes>) quizzesServices.findAll();		
		model.addAttribute("quizzes",quizzes);
		
		int countLanguages = (int) languagesServices.count();
		int countQLevel = (int) QlevelServices.count();
		int countQuizz = (int) quizzesServices.count();
		int countPendingTest = (int) testServices.count();
				
		model.addAttribute("totalLanguages", countLanguages);
		model.addAttribute("totalLevels", countQLevel);
		model.addAttribute("countQuizz", countQuizz);
		model.addAttribute("countPendingTest", countPendingTest);	
		
		// Services		
		List<QuestionTypes> questionTypes = (List<QuestionTypes>) questionTypeServices.findAll();
		List<QuizLevels> ql = (List<QuizLevels>) QlevelServices.findAll();
		
		model.addAttribute("questionTypes", questionTypes);
		model.addAttribute("ql",ql);
		
		return ("quizz/addQuestion");
	}
	
	// Insert Question of Quizzes
	@RequestMapping(value ="saveQuestion")
	public ModelAndView saveQuestion(@ModelAttribute("Questions") Questions question, BindingResult result) {
				
		Date d = new Date();
		
		Questions questions = new Questions();
		
		questions.setQuizze(question.getQuizze());
		questions.setQuestionType(question.getQuestionType());			
		questions.setQuestion(question.getQuestion());
		questions.setStatus(question.getStatus());
		questions.setCreatedBy("tes");
		questions.setUpdatedBy("test");
		questions.setCreatedOn(d);
		questions.setUpdatedOn(d);
		
		questionServices.save(questions);
		
		
		return new ModelAndView("redirect:/Quizz/addQuestion/"+ question.getQuizze().getIdQuiz());	
	}
	
	@RequestMapping(value ="editQuestion/{idQuestion}", method=RequestMethod.GET)
	public String editQuestion(Model model, @PathVariable int idQuestion) {
		
		List<QuestionTypes> qt = (List<QuestionTypes>) questionTypeServices.findAll();
		
		Questions question = questionServices.findOne(idQuestion);
		
		model.addAttribute("question", question);
		model.addAttribute("questionType", qt);
		
		return "quizz/editQuestion";
	}
	
	@RequestMapping("updateQuestion")
	public ModelAndView updateQuestion(@ModelAttribute("Questions") Questions question, BindingResult result) {
		
		Date date = new Date();
		
		Questions questions = questionServices.findOne(question.getIdQuestions());
				
		questions.setQuestion(question.getQuestion());
		questions.setQuestionType(question.getQuestionType());
		questions.setStatus(question.getStatus());
		questions.setUpdatedOn(date);
		
		questionServices.save(questions);		
		
		return new ModelAndView("redirect:/Quizz/addQuestion/"+ questions.getQuizze().getIdQuiz() );
	}
	
	/*
	 * END QUESTION SECTION ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	 */
	
	@RequestMapping(value="editQuizz/{id}", method= RequestMethod.GET )
	public String editQuizz(Model model, @PathVariable int id) {
		
		List<ProgrammingLanguage> pl = (List<ProgrammingLanguage>) languagesServices.findAll();	
		List<QuizLevels> ql = (List<QuizLevels>) QlevelServices.findAll();		
		Quizzes quizz = quizzesServices.findOne(id);
		
		model.addAttribute("languages",pl);
		model.addAttribute("ql",ql);		
		model.addAttribute("quizz", quizz);
		
		return "quizz/editQuizz";
	}
	
	@RequestMapping(value ="updateQuizz")
	public ModelAndView updateQuizz(@ModelAttribute("Quizzes") Quizzes quizz, BindingResult result) {
		
		//Find Quizz
		Quizzes quizzes = quizzesServices.findOne(quizz.getIdQuiz());
		
		Date d = new Date();		
		
		//Create Instance of Quizz
		quizzes.setProgrammingLanguage(quizz.getProgrammingLanguage());
		quizzes.setQuizLevel(quizz.getQuizLevel());
		quizzes.setDescription(quizz.getDescription());
		quizzes.setUpdatedOn(d);
		quizzes.setCreatedBy("Test");
		quizzes.setStatus(quizz.getStatus());
		quizzes.setUser(quizz.getUser());		
		
		quizzesServices.save(quizzes);
		
		return new ModelAndView("redirect:/Quizz/index");
	}
	
	
	
}
