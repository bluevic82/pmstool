package com.tinhvan.controller;

import java.security.Principal;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tinhvan.dao.BugInfoDao;
import com.tinhvan.dao.CategoryDao;
import com.tinhvan.dao.MemberProjectDao;
import com.tinhvan.dao.PermissionDao;
import com.tinhvan.dao.ProjectDao;
import com.tinhvan.dao.StatusDao;
import com.tinhvan.dao.TypeDao;
import com.tinhvan.dao.UserDao;
import com.tinhvan.model.BugInfo;
import com.tinhvan.model.Category;
import com.tinhvan.model.MemberProject;
import com.tinhvan.model.ProjectInfo;
import com.tinhvan.model.Status;
import com.tinhvan.model.TaskInfo;
import com.tinhvan.model.Type;
import com.tinhvan.model.User;

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
	@Autowired
	UserDao userDao;
	@Autowired
	PermissionDao per;

/*	// get User infor of current user login for menu user infor
	@ModelAttribute("UserInformation")
	public User getUserCurrentLogin(Principal principal) {
		return userDao.getUserInfoByUserMail(principal.getName());

	}

	// get list project for menu
	@ModelAttribute("list_Project_For_menu")
	public List<ProjectInfo> getListProject(Principal principal) {
		// List<ProjectInfo> list_Project_For_Menu = new ArrayList<ProjectInfo>();
		// User user = get_User_current_loged(principal);
		User user = userDao.getUserInfoByUserMail(principal.getName());

		// check role: if user is Admin => list all projects
		if (user.getRole_id() == 1) {
			return projectDao.getAllProject();
		} else {
			// only get list projects that user access
			// get List project_ids of user is PM
			return projectDao.getListPRojectOfUserAccessed(user.getUser_id());

		}
	}*/

	// Mapping view page create Bug
	@RequestMapping(value = "{id}/createBug", method=RequestMethod.GET)
	public ModelAndView createBug(@PathVariable int id, Model model) {
		Boolean checker = per.checker("cre_iss");
		if(checker==true) {
		model.addAttribute("title", "Welcome");
		model.addAttribute("message", "Create Bug");
		ProjectInfo projectInfo = projectDao.getProjectById(id);

		/**
		 * purpose: get project's name
		 */
		model.addAttribute("project_Infor", projectInfo);
		return new ModelAndView("createBug", "command", new BugInfo());
		}else {
			return new ModelAndView("403Page");
		}
	}

	// Mapping button click create Bug
	@RequestMapping(value = "/{id}/actionCreateBug", method = RequestMethod.POST)		
	public @ResponseBody ArrayList<BugInfo> actionCreate(@RequestBody BugInfo bugInfo,int id,
														HttpServletRequest request, ModelMap model) {
		bugInfoDao.addBug(bugInfo);
		ArrayList<BugInfo> arrBugInfo = new ArrayList<BugInfo>(bugInfoDao.getBugByIdPro(id)) ;
		return arrBugInfo;
	}

	// Mapping button click save Bug
	@RequestMapping(value = "/bugList/{id}/{idP}/actionUpdateBug", method = RequestMethod.POST)	
	public @ResponseBody ArrayList<BugInfo> actionUpdate(@PathVariable int id, @RequestBody BugInfo bugInfo, 
																		HttpServletRequest request) {
		bugInfoDao.updateBug(bugInfo);
		List<BugInfo> ti = bugInfoDao.getBugByIdPro(id);
		ArrayList<BugInfo> arrBugInfo = new ArrayList<BugInfo>(ti);
		return arrBugInfo;
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
	@ModelAttribute("bugStatus")
	public List<Status> getStatusOfBug() {
		List<Status> list = statusDao.getStatusOfBug();
		return list;
	}

	// get all member project in Bug
	@ModelAttribute("pic")
	public List<MemberProject> getPIC() {
		List<MemberProject> list = memberProjectDao.getAllMember();
		return list;
	}

	@ModelAttribute("picT")
	public List<MemberProject> getPICT( @PathVariable int id, Model model) {
		List<MemberProject> list = memberProjectDao.getAllMemberT(id);
		ProjectInfo projectInfo = projectDao.getProjectById(id);
		model.addAttribute("project_Infor", projectInfo);
		return list;
	}

	// get category in Bug
	@ModelAttribute("category")
	public List<Category> getCategory() {
		List<Category> list = categoryDao.getAllCategory();
		return list;
	}
}