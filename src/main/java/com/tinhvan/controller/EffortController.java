package com.tinhvan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tinhvan.dao.EffortDao;
import com.tinhvan.dao.ProjectDao;
import com.tinhvan.model.Effort;
import com.tinhvan.model.ProjectInfo;

/**
 * @purpose: calculate actual cost, over head, charge request.
 * @author: DaiCQ
 * @date: 2017/12/30
 **/

@Controller
public class EffortController {
	@Autowired
	ProjectDao projectDao;
	@Autowired
	EffortDao effortDao;

	// get list project for menu
	@ModelAttribute("list_Project_For_menu")
	public List<ProjectInfo> getListProject() {
		List<ProjectInfo> list_Project_For_Menu = projectDao.getAllProject();
		return list_Project_For_Menu;
	}

	@RequestMapping(value = "/effortManagement")
	public ModelAndView EffortManagementPage(Model model) {
		model.addAttribute("message", "Effort Management!");

		List<Effort> list = effortDao.getAllEfort();
		return new ModelAndView("effortManagementPage", "list", list);
	}

	@RequestMapping(value = "/{id}/effortCalculate")
	public ModelAndView effortCalculate(@PathVariable int id, ModelMap model) {
		Effort effort = effortDao.getEffortById(id);
		model.put("effort", effort);
		return new ModelAndView("effortCanculate", "effort", effort);
	}

}
