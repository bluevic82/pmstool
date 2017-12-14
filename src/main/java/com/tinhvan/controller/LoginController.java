package com.tinhvan.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tinhvan.dao.CategoryDao;
import com.tinhvan.dao.MemberProjectDao;
import com.tinhvan.dao.ProjectDao;
import com.tinhvan.dao.QuestionAnswerDao;
import com.tinhvan.dao.ScopeDao;
import com.tinhvan.dao.StatusDao;
import com.tinhvan.dao.TaskInfoDao;
import com.tinhvan.dao.TypeDao;
import com.tinhvan.model.Category;
import com.tinhvan.model.MemberProject;
import com.tinhvan.model.ProjectInfo;
import com.tinhvan.model.QuestionAnwer;
import com.tinhvan.model.Status;
import com.tinhvan.model.TaskInfo;
import com.tinhvan.model.Type;

/**
 * @Purpose: Controller
 * 
 **/

@Controller
public class LoginController {

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
	@Autowired
	QuestionAnswerDao qaDao;
	@Autowired
	ScopeDao scopeDao;


	@RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
	public String welcomePage(Model model) {
		model.addAttribute("title", "OverView");
		model.addAttribute("message", "This is OverView page!");
		return "welcomePage";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminPage(Model model) {
		return "adminPage";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(Model model) {
		return "loginPage";
	}

	@RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
	public String logoutSuccessfulPage(Model model) {
		model.addAttribute("title", "Logout");
		return "logoutSuccessfulPage";
	}

	@RequestMapping(value = "/userInfo", method = RequestMethod.GET)
	public String userInfo(Model model, Principal principal) {

		// Sau khi user login thanh cong se co principal
		String userName = principal.getName();

		/* test get user */
		System.out.println("User Name: " + userName);

		return "userInfoPage";
	}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accessDenied(Model model, Principal principal) {

		if (principal != null) {
			model.addAttribute("message",
					"Hi " + principal.getName() + "<br> You do not have permission to access this page!");
		} else {
			model.addAttribute("msg", "You do not have permission to access this page!");
		}
		return "403Page";
	}



	@RequestMapping(value = "/answerAndQ")
	public String qandA() {

		return "answerAndQ";
	}
	

	// Methods create and update task/spec/issue
	@RequestMapping(value = { "/createTask" }, method = RequestMethod.GET)
	public String createTask(Model model) {
		model.addAttribute("title", "Welcome");
		model.addAttribute("message", "Create Task/Spec/Issue");
		return "createTaskSpecIssue";
	}

	@RequestMapping(value = "actionCreateTask")
	public ModelAndView addTask(Model model, @ModelAttribute(value = "task") TaskInfo task) {
		taskInfoDao.addTask(task);
		return new ModelAndView("taskList");
	}

	@RequestMapping(value = { "/updateTask" }, method = RequestMethod.GET)
	public String updateTask(Model model) {
		model.addAttribute("title", "Welcome");
		model.addAttribute("message", "Update Task/Spec/Issue");
		return "updateTaskSpecIssue";
	}
	
	@RequestMapping(value = { "/listTask" }, method = RequestMethod.GET)
	public String listTask(Model model) {
		model.addAttribute("title", "Welcome");
		model.addAttribute("message", "List Task/Spec/Issue");
		return "taskList";
	}

	// Methods Attributes
	@ModelAttribute("projectName")
	public List<ProjectInfo> getAllProject() {
		List<ProjectInfo> list = projectDao.getAllProject();
		return list;
	}

	

	@ModelAttribute("taskTypes")
	public List<Type> getTypeOfTask() {
		List<Type> list = typeDao.getTypeOfTask();
		return list;
	}

	@ModelAttribute("taskStatus")
	public List<Status> getStatusOfTask() {
		List<Status> list = statusDao.getStatusOfTask();
		return list;
	}
	
	@ModelAttribute("getDetailTask")
	public List<TaskInfo> getDetailTask(){
		List<TaskInfo> list = taskInfoDao.detailTask();
		return list;
	}
	

	

	@ModelAttribute("pic")
	public List<MemberProject> getPIC() {
		List<MemberProject> list = memberProjectDao.getAllMember();
		return list;
	}

	@ModelAttribute("category")
	public List<Category> getCategory() {
		List<Category> list = categoryDao.getAllCategory();
		return list;
	}

	@ModelAttribute("qaStatus")
	public List<Status> getStatusOfQA() {
		List<Status> list = statusDao.getStatusOfQA();
		return list;
	}

	@ModelAttribute("allQA")
	public List<QuestionAnwer> getQA() {
		List<QuestionAnwer> list = qaDao.getAllQA();
		return list;
	}
	

}
