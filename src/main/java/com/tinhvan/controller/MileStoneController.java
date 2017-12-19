package com.tinhvan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tinhvan.dao.MileStoneDao;
import com.tinhvan.model.MileStone;

/*
 * @purpose: MileStoneController using Setting Milestone
 * @author: NguyenManh
 * @date: 2017/12/15
 * 
 * **/

@Controller
public class MileStoneController {
	
	@Autowired
	MileStoneDao mileStoneDao;
	
	//Mapping to view MileStone JSP
	@RequestMapping(value = { "/createMileStone" }, method = RequestMethod.GET)
	public String createMileStone(Model model) {
		model.addAttribute("title", "Welcome");
		model.addAttribute("message", "Create MileStone");
		return "mileStone";
	}
	
	//Mapping button click create MileStone
	@RequestMapping(value = "actionCreateMileStone")
	public ModelAndView addMileStone(Model model, @ModelAttribute(value = "milestone") MileStone mileStone) {
		mileStoneDao.addMileStone(mileStone);
		return new ModelAndView("whileSuccess");
	}
	
	//Mapping button click delete MileStone
	@RequestMapping(value = "actionDeleteMileStone")
	public ModelAndView deleteMileStone(Model model, @ModelAttribute(value = "milestone") MileStone mileStone) {
		mileStoneDao.deleteMidelStone(mileStone);
		return new ModelAndView("whileSuccess");
	}

}
