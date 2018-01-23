package com.tinhvan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tinhvan.dao.MemberProjectDao;
import com.tinhvan.dao.ProjectDao;
import com.tinhvan.dao.QuestionAnswerDao;
import com.tinhvan.dao.StatusDao;
import com.tinhvan.model.MemberProject;
import com.tinhvan.model.ProjectInfo;
import com.tinhvan.model.QuestionAnwer;
import com.tinhvan.model.Status;

/*
 * @purpose: QAController using QAResgister/Update
 * Using method Attribute set data for Status
 * @author: NguyenManh
 * @date: 2017/12/19
 * 
 * **/
@Controller
public class QAController {

	@Autowired
	ProjectDao projectDao;
	@Autowired
	QuestionAnswerDao answerDao;
	@Autowired
	StatusDao statusDao;
	@Autowired
	MemberProjectDao memberProjectDao;
	
	// get list project for menu
			@ModelAttribute("list_Project_For_menu")
			public List<ProjectInfo> getListProject() {
				List<ProjectInfo> list_Project_For_Menu = projectDao.getAllProject();
				return list_Project_For_Menu;
			}

	// Mapping view ListQuestion & Answer
	@RequestMapping(value = "/qaList")
	public ModelAndView listQA(Model model) {
		List<QuestionAnwer> list = answerDao.getAllQA();
		return new ModelAndView("qaList", "list", list);
	}

	//Mapping view page register QA
	@RequestMapping(value = { "/registerQA" }, method = RequestMethod.GET)
	public String registerQA(Model model) {
		model.addAttribute("title", "Welcome");
		model.addAttribute("message", "register QA");
		return "registerQandA";
	}
	
	//Mapping button click registerQA
	@RequestMapping(value = "/actionRegisterQA", method=RequestMethod.POST)
	public ModelAndView registerQA(Model model, @ModelAttribute(value = "addqa") QuestionAnwer questionAnwer) {
		answerDao.registerQA(questionAnwer);
		return new ModelAndView("redirect:/qaList");
	}
	
	//Mapping view page update QA
	@RequestMapping(value = { "/updateQA" }, method = RequestMethod.GET)
	public String updateQA(Model model) {
		model.addAttribute("title", "Welcome");
		model.addAttribute("message", "Update QA");
		return "updateQandA";
	}
	
	//Mapping button click save QA
	@RequestMapping(value = "/actionUpdateQA", method=RequestMethod.POST)
	public ModelAndView updateQA(Model model, @ModelAttribute(value = "qa") QuestionAnwer questionAnwer) {
		answerDao.updateQA(questionAnwer);
		return new ModelAndView("redirect:/qaList");
	}
	
	//Mapping get dataById for update QA
	@RequestMapping(value = "/editQA/{q_a_id}")
	public ModelAndView editQA(@PathVariable int q_a_id, ModelMap model) {
		QuestionAnwer questionAnwer = answerDao.getQAById(q_a_id);
		model.put("command", answerDao.getQAById(q_a_id));
		return new ModelAndView("updateQandA","command",questionAnwer);
	}

	/*	
	 * @purpose: Methods Attributes
	 */
	@ModelAttribute("qaStatus")
	public List<Status> getStatusOfQA() {
		List<Status> list = statusDao.getStatusOfQA();
		return list;
	}

	@ModelAttribute("allQA")
	public List<QuestionAnwer> getQA() {
		List<QuestionAnwer> list = answerDao.getAllQA();
		return list;
	}
	
	//get all member project in project this
	@ModelAttribute("pic")
	public List<MemberProject> getPIC() {
		List<MemberProject> list = memberProjectDao.getAllMember();
		return list;
	}
	
	//method get Name of Project
	@ModelAttribute("projectName")
	public List<ProjectInfo> getAllProject(){
		List<ProjectInfo> list = projectDao.getAllProject();
		return list;
	}

}
