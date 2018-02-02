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

import com.tinhvan.dao.CategoryDao;
import com.tinhvan.dao.MemberProjectDao;
import com.tinhvan.dao.ProjectDao;
import com.tinhvan.dao.StatusDao;
import com.tinhvan.dao.TaskInfoDao;
import com.tinhvan.dao.TypeDao;
import com.tinhvan.model.Category;
import com.tinhvan.model.MemberProject;
import com.tinhvan.model.ProjectInfo;
import com.tinhvan.model.Status;
import com.tinhvan.model.TaskInfo;
import com.tinhvan.model.Type;

/**
 * @purpose: Task Controller using create,update Task/Spec/Issue
 * 	Using method Attribute set data for ProjectName, Type, Status, PIC, Category, Priority
 * @author: NguyenManh
 * @date: 2017/12/13
 * **/
@Controller
public class TaskController {

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
	TaskInfoDao taskInfoDao;

	// get list project for menu
	@ModelAttribute("list_Project_For_menu")
	public List<ProjectInfo> getListProject() {
		List<ProjectInfo> list_Project_For_Menu = projectDao.getAllProject();
		return list_Project_For_Menu;
	}

	// Mapping view page create Task/Spec/Issue
	@RequestMapping(value = "{id}/createTask")
	public ModelAndView createTask(@PathVariable int id, Model model) {
		model.addAttribute("message", "Create Task/Spec/Issue");
		ProjectInfo projectInfo = projectDao.getProjectById(id);
		
		// purpose: get project's name
		model.addAttribute("project_Infor", projectInfo);
		return new ModelAndView("createTaskSpecIssue", "command", new TaskInfo());
	}

	// Mapping button click create Task/Spec/Issue
	@RequestMapping(value = "/actionCreateTask", method = RequestMethod.POST)
	public ModelAndView addTask(Model model, @ModelAttribute(value = "task") TaskInfo task) {
		System.out.println("ActionCreateTaskrun");
		taskInfoDao.addTask(task);
		return new ModelAndView("redirect:/taskList");
	}

	// Mapping view page update Task/Spec/Issue
	@RequestMapping(value = { "/updateTask" }, method = RequestMethod.GET)
	public String updateTask(Model model) {
		model.addAttribute("title", "Welcome");
		model.addAttribute("message", "Update Task/Spec/Issue");
		return "updateTaskSpecIssue";
	}

	// Mapping button click save Task/Spec/Issue
	@RequestMapping(value = "/actionUpdateTask", method = RequestMethod.POST)
	public ModelAndView UpdateTask(Model model, @ModelAttribute(value = "task") TaskInfo task) {
		taskInfoDao.updateTask(task);
		return new ModelAndView("redirect:/taskList");
	}

	// Mapping get dataById for update Task/Spec/Issue
	@RequestMapping(value = "{id}/editTask")
	public ModelAndView editTask(@PathVariable int id, ModelMap model) {
		TaskInfo taskInfo = taskInfoDao.getTaskById(id);
		ProjectInfo projectInfo = projectDao.getProjectById(id);
		model.put("project_Infor", projectInfo);

		model.put("command", taskInfoDao.getTaskById(id));
		return new ModelAndView("updateTaskSpecIssue", "command", taskInfo);
	}

	// Mapping view list Task/Spec/Issue
	@RequestMapping("/taskList")
	public ModelAndView listTask() {
		List<TaskInfo> list = taskInfoDao.getAllTask();
		return new ModelAndView("taskList", "list", list);
	}

	/*
	 * @purpose: Methods Attributes
	 */
	@ModelAttribute("projectName")
	public List<ProjectInfo> getAllProject() {
		List<ProjectInfo> list = projectDao.getAllProject();
		return list;
	}

	// get type of Task/Spec/Issue
	@ModelAttribute("taskTypes")
	public List<Type> getTypeOfTask() {
		List<Type> list = typeDao.getTypeOfTask();
		return list;
	}

	// get status of Task/Spec/Issue
	@ModelAttribute("taskStatus")
	public List<Status> getStatusOfTask() {
		List<Status> list = statusDao.getStatusOfTask();
		return list;
	}

	// get all member project in Task/Spec/Issue
	@ModelAttribute("pic")
	public List<MemberProject> getPIC() {
		List<MemberProject> list = memberProjectDao.getAllMember();
		return list;
	}

	// get category in Task/Spec/Issue
	@ModelAttribute("category")
	public List<Category> getCategory() {
		List<Category> list = categoryDao.getAllCategory();
		return list;
	}
}
