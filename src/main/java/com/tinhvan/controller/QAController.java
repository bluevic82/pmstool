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
import com.tinhvan.dao.QuestionAnswerDao;
import com.tinhvan.dao.StatusDao;
import com.tinhvan.model.MemberProject;
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
	QuestionAnswerDao answerDao;
	@Autowired
	StatusDao statusDao;
	@Autowired
	MemberProjectDao memberProjectDao;

	// Mapping view ListQuestion & Answer
	@RequestMapping(value = "/qaList", method = RequestMethod.GET)
	public String listQA(Model model) {
		model.addAttribute("title", "ListQA");
		model.addAttribute("message", "List Question and Answer");
		return "qaList";
	}

	// Mapping view Register Q&A
	@RequestMapping(value = { "/registerQA" }, method = RequestMethod.GET)
	public String updateQA(Model model) {
		model.addAttribute("title", "Welcome");
		model.addAttribute("message", "Register Q&A");
		return "registerQandA";
	}
	
	//Mapping event click save Q&A
	@RequestMapping(value = "/actionSave", method = RequestMethod.POST)
	public ModelAndView saveQA(Model model, @ModelAttribute(value = "qa") QuestionAnwer anwer) {
		answerDao.saveQA(anwer);
		return new ModelAndView("redirect:/");
	}
	
	// Mapping get dataById for update Q&A
	@RequestMapping(value = "/editQA{q_a_id}")
	public ModelAndView editQA(@PathVariable int id, ModelMap mm) {
		QuestionAnwer anwer = answerDao.getQAById(id);
		mm.put("command", answerDao.getQAById(id));
		return new ModelAndView("registerQandA", "command", anwer);
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
	
	//get all member project in Task/Spec/Issue
	@ModelAttribute("pic")
	public List<MemberProject> getPIC() {
		List<MemberProject> list = memberProjectDao.getAllMember();
		return list;
	}

}
