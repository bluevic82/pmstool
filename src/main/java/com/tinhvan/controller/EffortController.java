package com.tinhvan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tinhvan.dao.EffortDao;
import com.tinhvan.dao.ProjectDao;
import com.tinhvan.model.Effort;

@Controller
public class EffortController {
	@Autowired
	ProjectDao projectDao;
	@Autowired
	EffortDao effortDao;
	
	@RequestMapping(value = "/effortManagement")
	public ModelAndView EffortManagementPage(Model model) {
		model.addAttribute("message", "Effort Management!");
		List<Effort> list = effortDao.getAllEfort();
		return new ModelAndView("effortManagementPage","list",list);
	}	
	@RequestMapping(value = "/{id}/effortCalculate")
	public ModelAndView effortCalculate(@PathVariable int id, ModelMap model) {
		Effort effort = effortDao.getEffortById(id);
		effort.setOver_head(OverheadCal(effort.getProject_charge_cost(), effort.getProject_actual_cost()));
		model.put("effort", effort);
		//effort.set
		return new ModelAndView("effortCanculate","effort",effort);
	}
	
	public double OverheadCal(double charge, double actual){
		return ((actual/charge)*100-100);
	}
	
	
}
