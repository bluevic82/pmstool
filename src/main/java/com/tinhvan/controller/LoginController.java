package com.tinhvan.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tinhvan.dao.CategoryDao;
import com.tinhvan.dao.MemberProjectDao;
import com.tinhvan.dao.StatusDao;
import com.tinhvan.dao.TypeDao;
import com.tinhvan.model.Category;
import com.tinhvan.model.MemberProject;
import com.tinhvan.model.Status;
import com.tinhvan.model.Type;

/**
 * @Purpose: Controller
 * 
 * **/

@Controller
public class LoginController {
	
	@Autowired
	TypeDao typeDao;
	@Autowired
	StatusDao statusDao;
	@Autowired
	MemberProjectDao memberProjectDao;
	@Autowired
	CategoryDao categoryDao;
	

	@RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
	public String welcomePage(Model model) {
		model.addAttribute("title", "Welcome");
		model.addAttribute("message", "This is welcome page!");
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

		/*test get user*/
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
	
	   @RequestMapping(value="/addProject")
	   public String addProject() {
		   
		   return "addProject";
	   }
	   @RequestMapping(value="/updateProject")
	   public String updateProject() {
		   
		   return "updateProject";
	   }
	   
	   @RequestMapping(value="/answerAndQ")
	   public String qandA() {

		   return "answerAndQ";
	   }
	   
		@RequestMapping(value = { "/createTask" }, method = RequestMethod.GET)
		public String createTask(Model model) {
			model.addAttribute("title", "Welcome");
			model.addAttribute("message", "Create Task/Spec/Issue");
			return "createTaskSpecIssue";
		}
		
		@RequestMapping(value = { "/updateTask" }, method = RequestMethod.GET)
		public String updateTask(Model model) {
			model.addAttribute("title", "Welcome");
			model.addAttribute("message", "Update Task/Spec/Issue");
			return "updateTaskSpecIssue";
		}
		
	   //	Methods Attributes
		@ModelAttribute("projectTypes")
		public List<Type> getTypes() {
			List<Type> list = typeDao.getAllType();
			return list;
		}
		
		@ModelAttribute("taskTypes")
		public List<Type> getTypeOfTask() {
			List<Type> list = typeDao.getTypeOfTask();
			return list;
		}

		@ModelAttribute("projectStatus")
		public List<Status> getStatus() {
			List<Status> list = statusDao.getAllStatus();
			return list;
		}
		
		@ModelAttribute("taskStatus")
		public List<Status> getStatusOfTask() {
			List<Status> list = statusDao.getStatusOfTask();
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
		public List<Status> getStatusOfQA(){
			List<Status> list = statusDao.getStatusOfQA();
			return list;
		}
		
}
