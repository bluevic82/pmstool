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

import com.tinhvan.dao.BugInfoDao;
import com.tinhvan.dao.CategoryDao;
import com.tinhvan.dao.MemberProjectDao;
import com.tinhvan.dao.ProjectDao;
import com.tinhvan.dao.StatusDao;
import com.tinhvan.dao.TypeDao;
import com.tinhvan.model.BugInfo;
import com.tinhvan.model.Category;
import com.tinhvan.model.MemberProject;
import com.tinhvan.model.ProjectInfo;
import com.tinhvan.model.Status;
import com.tinhvan.model.Type;

/**
 * @purpose: Bug Controller using create,update Bug Using method Attribute set
 *           data for ProjectName, Type, Status, PIC, Category, Priority
 * @author: NguyenManh
 * @date: 2018/02/02
 **/
@Controller
public class BugController {

	@Autowired
	ProjectDao projectDao;
	@Autowired
	TypeDao typeDao;
	@Autowired
	StatusDao statusDao;
	@Autowired
	MemberProjectDao memberProjectDao;
	@Autowired
	CategoryDao categoryDao;
	@Autowired
	BugInfoDao bugInfoDao;

	// get list project for menu
	@ModelAttribute("list_Project_For_menu")
	public List<ProjectInfo> getListProject() {
		List<ProjectInfo> list_Project_For_Menu = projectDao.getAllProject();
		return list_Project_For_Menu;
	}

	// Mapping view page create Bug
	@RequestMapping(value = "{id}/createBug")
	public ModelAndView createBug(@PathVariable int id, Model model) {
		model.addAttribute("title", "Welcome");
		model.addAttribute("message", "Create Bug");
		ProjectInfo projectInfo = projectDao.getProjectById(id);

		// purpose: get project's name
		model.addAttribute("project_Infor", projectInfo);
		return new ModelAndView("createBug", "command", new BugInfo());
	}

	// Mapping button click create Bug
	@RequestMapping(value = "/actionCreateBug", method = RequestMethod.POST)
	public ModelAndView addBug(Model model, @ModelAttribute(value = "bug") BugInfo bugInfo) {
		bugInfoDao.addBug(bugInfo);
		return new ModelAndView("redirect:/bugList");
	}


	// Mapping view page update Bug
	@RequestMapping(value = { "/updateBug" }, method = RequestMethod.GET)
	public String updateBug(Model model) {
		model.addAttribute("title", "Welcome");
		model.addAttribute("message", "Update Bug");
		return "updateBug";
	}

	// Mapping button click save Bug
	@RequestMapping(value = "/actionUpdateBug", method = RequestMethod.POST)
	public ModelAndView UpdateBug(Model model, @ModelAttribute(value = "bug") BugInfo bugInfo) {
		bugInfoDao.updateBug(bugInfo);
		return new ModelAndView("redirect:/bugList");
	}

	// Mapping get dataById for update Bug
	@RequestMapping(value = "{id}/editBug")
	public ModelAndView editBug(@PathVariable int id, ModelMap model) {
		BugInfo bugInfo = bugInfoDao.getBugById(id);
		ProjectInfo projectInfo = projectDao.getProjectById(id);
		model.put("project_Infor", projectInfo);

		model.put("command", bugInfoDao.getBugById(id));
		return new ModelAndView("updateBug", "command", bugInfo);
	}

	// Mapping view list Bug
	@RequestMapping("/bugList")
	public ModelAndView listTask() {
		List<BugInfo> list = bugInfoDao.getAllBug();
		return new ModelAndView("bugList", "list", list);
	}

	/*
	 * @purpose: Methods Attributes
	 */
	@ModelAttribute("projectName")
	public List<ProjectInfo> getAllProject() {
		List<ProjectInfo> list = projectDao.getAllProject();
		return list;
	}

	// get type of Bug
	@ModelAttribute("bugTypes")
	public List<Type> getTypeOfBug() {
		List<Type> list = typeDao.getTypeOfBug();
		return list;
	}

	// get status of Bug
	@ModelAttribute("taskStatus")
	public List<Status> getStatusOfBug() {
		List<Status> list = statusDao.getStatusOfTask();
		return list;
	}

	// get all member project in Bug
	@ModelAttribute("pic")
	public List<MemberProject> getPIC() {
		List<MemberProject> list = memberProjectDao.getAllMember();
		return list;
	}

	// get category in Bug
	@ModelAttribute("category")
	public List<Category> getCategory() {
		List<Category> list = categoryDao.getAllCategory();
		return list;
	}
}