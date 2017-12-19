package com.tinhvan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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
	QuestionAnswerDao qaDao;
	@Autowired
	StatusDao statusDao;
	
	@RequestMapping(value = "/answerAndQ")
	public String qandA() {
		return "answerAndQ";
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
		List<QuestionAnwer> list = qaDao.getAllQA();
		return list;
	}

}
