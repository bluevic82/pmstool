package com.tinhvan.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tinhvan.dao.CategoryDao;
import com.tinhvan.dao.MemberProjectDao;
import com.tinhvan.dao.PermissionDao;
import com.tinhvan.dao.ProjectDao;
import com.tinhvan.dao.StatusDao;
import com.tinhvan.dao.TaskInfoDao;
import com.tinhvan.dao.TypeDao;
import com.tinhvan.dao.UserDao;
import com.tinhvan.model.Category;
import com.tinhvan.model.MemberProject;
import com.tinhvan.model.ProjectInfo;
import com.tinhvan.model.Status;
import com.tinhvan.model.TaskInfo;
import com.tinhvan.model.Type;
import com.tinhvan.model.User;

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
	@Autowired UserDao userDao;
	@Autowired
	PermissionDao per;
	
	// get User infor of current user login for menu user infor
			@ModelAttribute("UserInformation")
			public User getUserCurrentLogin(Principal principal){
				return  userDao.getUserInfoByUserMail(principal.getName());
				
			}
	// get list project for menu
		@ModelAttribute("list_Project_For_menu")
		public List<ProjectInfo> getListProject(Principal principal) {
			//List<ProjectInfo> list_Project_For_Menu = new ArrayList<ProjectInfo>();
			//User user = get_User_current_loged(principal);
			User user = userDao.getUserInfoByUserMail(principal.getName());
			
			//check role: if user is Admin => list all projects 
			if(user.getRole_id()==1){
				return projectDao.getAllProject();
			}
			else{
				//only get list projects that user access
				//get List project_ids of user is PM
				return projectDao.getListPRojectOfUserAccessed(user.getUser_id());
				
			}
		}

	// Mapping view page create Task/Spec/Issue
	@RequestMapping(value = "{id}/createTask")
	public ModelAndView createTask(@PathVariable int id, Model model) {
		Boolean checker = per.checker("cre_iss");
		if(checker==true) {
		model.addAttribute("title", "Welcome");
		model.addAttribute("message", "Create Task");
		ProjectInfo projectInfo = projectDao.getProjectById(id);

		// purpose: get project's name
		model.addAttribute("project_Infor", projectInfo);
		return new ModelAndView("createTaskSpecIssue", "command", new TaskInfo());
		}else {
			return new ModelAndView("403Page");
		}
	}

	// Mapping button click create Task/Spec/Issue
	@RequestMapping(value = "/{id}/actionCreateTask", method = RequestMethod.POST)
	public @ResponseBody TaskInfo actionCreate(@RequestBody TaskInfo taskInfo, HttpServletRequest request) {
		taskInfoDao.addTask(taskInfo);
		return taskInfo;
	}

	// Mapping view page update Task/Spec/Issue
	@RequestMapping(value = { "/updateTask" }, method = RequestMethod.GET)
	public String updateTask(Model model) {
		model.addAttribute("title", "Welcome");
		model.addAttribute("message", "Update Task");
		return "updateTask";
	}

	// Mapping button click save Task/Spec/Issue
	@RequestMapping(value = "/taskList/{idP}/{id}/actionUpdateTask", method = RequestMethod.POST)	
	public @ResponseBody TaskInfo actionUpdate(@RequestBody TaskInfo taskInfo, HttpServletRequest request) {
		taskInfoDao.updateTask(taskInfo);
		return taskInfo;
	}

	// Mapping get dataById for update Task/Spec/Issue
	@RequestMapping(value = "/taskList/{id}/editTask/{idP}")
	public ModelAndView editTask(@PathVariable int id, ModelMap model, @PathVariable int idP) {
		Boolean checker = per.checker("upd_iss");
		if(checker==true) {
		TaskInfo taskInfo = taskInfoDao.getTaskById(id);
		ProjectInfo projectInfo = projectDao.getProjectById(idP);
		model.put("project_Infor", projectInfo);
		model.put("taskInfo", taskInfo);
		model.put("command", taskInfoDao.getTaskById(id));
		
		return new ModelAndView("updateTaskSpecIssue", "command", taskInfo);

		}else {
			return new ModelAndView("403Page");
		}
	}

	// Mapping view list Task/Spec/Issue
		@RequestMapping("/taskList")
		public ModelAndView listTask(
				@RequestParam(value="projectName",required=false,defaultValue = "0")int projectName,
				@RequestParam(value="type_id",required=false,defaultValue = "0") int type_id,
				@RequestParam(value="status_id",required=false,defaultValue = "0") int status_id,
				@RequestParam(value="member_project_id",required=false,defaultValue = "0") int member_project_id,
				@RequestParam(value="task_priority",required=false,defaultValue = "") 
			String task_priority, Model modelMap,Principal principal) {
				modelMap.addAttribute("pn",projectName);
				modelMap.addAttribute("ti", type_id);
				modelMap.addAttribute("si",status_id);
				modelMap.addAttribute("mp", member_project_id);
				modelMap.addAttribute("tp", task_priority);
			List<TaskInfo> list = taskInfoDao.getAllTask(projectName,type_id,status_id,member_project_id,task_priority);

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

	// get status of Bug
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
