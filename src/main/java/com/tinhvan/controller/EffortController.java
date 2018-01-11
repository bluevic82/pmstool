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

/**
 * @purpose: calculate actual cost, over head, charge request.
 * @author: DaiCQ
 * @date: 2017/12/30
 * **/
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
		return new ModelAndView("effortManagementPage", "list", list);
	}

	@RequestMapping(value = "/{id}/effortCalculate")
	public ModelAndView effortCalculate(@PathVariable int id, ModelMap model) {
		Effort effort = effortDao.getEffortById(id);
		effort.setOver_head((double) Math.round(OverheadCal(
				effort.getProject_charge_cost(),
				effort.getProject_actual_cost()) * 100) / 100);
		model.put("effort", effort);
		// effort.set
		return new ModelAndView("effortCanculate", "effort", effort);
	}

	public double OverheadCal(double charge, double actual) { // get overHead
		double overHeadCal = ((actual / charge) * 100 - 100);

		if (overHeadCal < 0) {
			return overHeadCal + 100;
		}
		return overHeadCal;
	}

	// Demo
	@RequestMapping(value = "/listProject")
	public ModelAndView EffortManagementPage1(Model model) {
		model.addAttribute("message", "List project!");
		List<Effort> list = effortDao.getAllEfort();
		return new ModelAndView("ListProject_Demo", "list", list);
	}

}
