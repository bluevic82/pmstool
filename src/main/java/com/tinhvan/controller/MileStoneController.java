package com.tinhvan.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tinhvan.dao.MileStoneDao;
import com.tinhvan.dao.ProjectDao;
import com.tinhvan.model.MileStone;
import com.tinhvan.model.ProjectInfo;

/**
 * @purpose: MileStoneController using Setting Milestone Using method Attribute
 *  set data for Status
 * @author: NguyenManh
 * @date: 2017/12/15
 **/
@Controller
public class MileStoneController {

	@Autowired
	MileStoneDao mileStoneDao;
	@Autowired(required = true)
	ProjectDao projectDao;

	// get list project for menu
	@ModelAttribute("list_Project_For_menu")
	public List<ProjectInfo> getListProject() {
		List<ProjectInfo> list_Project_For_Menu = projectDao.getAllProject();
		return list_Project_For_Menu;
	}

	// Mapping to view MileStone JSP
	@RequestMapping(value = { "/createMileStone" }, method = RequestMethod.GET)
	public String createMileStone(Model model) {
		model.addAttribute("title", "Welcome");
		model.addAttribute("message", "Create MileStone");

		return "mileStone";
	}

	@RequestMapping(value = "/{id}/createMileStone", method = RequestMethod.GET)
	public ModelAndView createMileStone(@PathVariable int id, ModelMap model) {
		ProjectInfo projectInfor = projectDao.getProjectById(id);
		List<MileStone> m = mileStoneDao.getMileStoneByProjectId(id);
		model.addAttribute("ml",m);
		model.put("projectInfor", projectInfor);
		model.addAttribute("projectInfor", projectInfor);
		System.out.println(projectInfor.getProject_id());
		System.out.println(projectInfor.getProject_name());
		return new ModelAndView("createMilestone");
	}

	// Mapping button click create MileStone
	@RequestMapping(value = "/{id}/actionSaveMileStone", method = RequestMethod.POST)
	public @ResponseBody ArrayList<MileStone> update(@RequestBody final ArrayList<MileStone> milestone){
		mileStoneDao.updateMilestone(milestone);	
		return milestone;
	}
	// Mapping button click create MileStone
		@RequestMapping(value = "/{id}/actionAddMileStone", method = RequestMethod.POST)
		public @ResponseBody ArrayList<MileStone> save(@RequestBody final ArrayList<MileStone> milestone){
			mileStoneDao.insertMilestone(milestone);	
			return milestone;
		}

	// Mapping button click delete MileStone
	@RequestMapping(value = "actionDeleteMileStone")
	public ModelAndView deleteMileStone(Model model, @ModelAttribute(value = "milestone") MileStone mileStone) {
		mileStoneDao.deleteMidelStone(mileStone);
		return new ModelAndView("whileSuccess");
	}

}
