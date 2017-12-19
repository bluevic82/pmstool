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

import com.tinhvan.dao.QuestionAnswerDao;
import com.tinhvan.dao.StatusDao;
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
	

	//Mapping view ListQuestion & Answer
	@RequestMapping(value = "/answerAndQ")
	public String qandA() {
		return "answerAndQ";
	}
	
	//Mapping view Register Q&A
	@RequestMapping(value = { "/registerQA" }, method = RequestMethod.GET)
	public String updateTask(Model model) {
		model.addAttribute("title", "Welcome");
		model.addAttribute("message", "Register Q&A");
		return "registerQandA";
	}
	
	//Mapping get dataById for update Q&A
	@RequestMapping(value = "/getQAbyId/{id}")
	public ModelAndView editTask(@PathVariable int id, ModelMap model) {
		QuestionAnwer questionAnwer = answerDao.getQAById(id);
		model.put("command", answerDao.getQAById(id));
		return new ModelAndView("registerQandA","command",questionAnwer);
	}
	
	/*
	 *@purpose: Methods Attributes
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


}
