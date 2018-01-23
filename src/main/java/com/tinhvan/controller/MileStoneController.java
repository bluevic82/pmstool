package com.tinhvan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tinhvan.dao.MileStoneDao;
import com.tinhvan.dao.ProjectDao;
import com.tinhvan.model.MileStone;
import com.tinhvan.model.ProjectInfo;

/*
 * @Purpose: MileStoneController using Setting Milestone
 * @Author: NguyenManh
 * @Date: 2017/12/15
 * 
 * **/

@Controller
public class MileStoneController {
	
	@Autowired
	MileStoneDao mileStoneDao;
	@Autowired
	ProjectDao projectDao;
	
	// get list project for menu
				@ModelAttribute("list_Project_For_menu")
				public List<ProjectInfo> getListProject() {
					List<ProjectInfo> list_Project_For_Menu = projectDao.getAllProject();
					return list_Project_For_Menu;
				}
	
	//Mapping to view MileStone JSP
	@RequestMapping(value = { "/createMileStone" }, method = RequestMethod.GET)
	public String createMileStone(Model model) {
		model.addAttribute("title", "Welcome");
		model.addAttribute("message", "Create MileStone");
		
		return "mileStone";
	}
	
	/*@RequestMapping(value = "/{id}/createMileStone", method = RequestMethod.GET)
	public ModelAndView  createMileStone(@PathVariable int id, ModelMap model){
			ProjectInfo projectInfor = projectDao.getProjectById(id);
			model.put("projectInfor", projectInfor);
			model.addAttribute("projectInfor", projectInfor);
			return new ModelAndView("mileStone", "projectInfor", projectInfor);
		
	}*/
	
	//Mapping button click create MileStone
	@RequestMapping(value = "actionCreateMileStone")
	public ModelAndView addMileStone(Model model, @ModelAttribute(value = "mileStone") MileStone mileStone) {
		
		System.out.println("milestone: project_id = " + mileStone.getProject_id());
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
