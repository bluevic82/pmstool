package com.tinhvan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

/*
 * @purpose: Task Controller using create,update Task/Spec/Issue
 *  Using method Attribute set data for ProjectName, Type, Status, PIC, Category, Priority
 * @author: NguyenManh
 * @date: 2017/12/13
 * 
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
	
	//Mapping create Task/Spec/Issue
	@RequestMapping(value = { "/createTask" }, method = RequestMethod.GET)
	public String createTask(Model model) {
		model.addAttribute("title", "Welcome");
		model.addAttribute("message", "Create Task/Spec/Issue");
		return "createTaskSpecIssue";
	}
	
	//Mapping button click create Task/Spec/Issue
	@RequestMapping(value = "actionCreateTask")
	public ModelAndView addTask(Model model, @ModelAttribute(value = "task") TaskInfo task) {
		taskInfoDao.addTask(task);
		return new ModelAndView("taskList");
	}

	//Mapping update Task/Spec/Issue
	@RequestMapping(value = { "/updateTask" }, method = RequestMethod.GET)
	public String updateTask(Model model) {
		model.addAttribute("title", "Welcome");
		model.addAttribute("message", "Update Task/Spec/Issue");
		return "updateTaskSpecIssue";
	}
	
	//Mapping button click save Task/Spec/Issue
	@RequestMapping(value = "actionUpdateTask")
	public ModelAndView UpdateTask(Model model, @ModelAttribute(value = "task") TaskInfo task) {
		taskInfoDao.updateTask(task);
		return new ModelAndView("whileSuccess");
	}
	
	//Mapping get dataById for update Task/Spec/Issue
	@RequestMapping(value = "/editTask/{id}")
	public ModelAndView editTask(@PathVariable int id) {
		TaskInfo taskInfo = taskInfoDao.getTaskById(id);
		return new ModelAndView("updateTaskSpecIssue","",taskInfo);
	}
	
	//Mapping view list Task/Spec/Issue
	@RequestMapping(value = { "/listTask" }, method = RequestMethod.GET)
	public String listTask(Model model) {
		model.addAttribute("title", "Welcome");
		model.addAttribute("message", "List Task/Spec/Issue");
		return "taskList";
	}

	// Methods Attributes
	// get name of project
	@ModelAttribute("projectName")
	public List<ProjectInfo> getAllProject() {
		List<ProjectInfo> list = projectDao.getAllProject();
		return list;
	}
	
	//get type of Task/Spec/Issue
	@ModelAttribute("taskTypes")
	public List<Type> getTypeOfTask() {
		List<Type> list = typeDao.getTypeOfTask();
		return list;
	}

	//get status of Task/Spec/Issue
	@ModelAttribute("taskStatus")
	public List<Status> getStatusOfTask() {
		List<Status> list = statusDao.getStatusOfTask();
		return list;
	}
	
	//get detail Task/Spec/Issue for view list Task/Spec/Issue
	@ModelAttribute("getDetailTask")
	public List<TaskInfo> getDetailTask(){
		List<TaskInfo> list = taskInfoDao.detailTask();
		return list;
	}
	
	//get all member project in Task/Spec/Issue
	@ModelAttribute("pic")
	public List<MemberProject> getPIC() {
		List<MemberProject> list = memberProjectDao.getAllMember();
		return list;
	}
	
	//get category in Task/Spec/Issue
	@ModelAttribute("category")
	public List<Category> getCategory() {
		List<Category> list = categoryDao.getAllCategory();
		return list;
	}
}
