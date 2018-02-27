package com.tinhvan.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tinhvan.dao.BugInfoDao;
import com.tinhvan.dao.CategoryDao;
import com.tinhvan.dao.MemberProjectDao;
import com.tinhvan.dao.ProjectDao;
import com.tinhvan.dao.StatusDao;
import com.tinhvan.dao.TypeDao;
import com.tinhvan.dao.UserDao;
import com.tinhvan.model.BugInfo;
import com.tinhvan.model.Category;
import com.tinhvan.model.MemberProject;
import com.tinhvan.model.ProjectInfo;
import com.tinhvan.model.Status;
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

	// Mapping view page create Bug
	@RequestMapping(value = "{id}/createBug")
	public ModelAndView createBug(@PathVariable int id, Model model) {
		model.addAttribute("project_id", id);
		model.addAttribute("message", "Create Bug");
		ProjectInfo projectInfo = projectDao.getProjectById(id);

		/**
		 * purpose: get project's name
		 */
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
	@RequestMapping(value = "/bugList/{id}/editBug/{idP}")
	public ModelAndView editBug(@PathVariable int id, ModelMap model,  @PathVariable int idP) {
		BugInfo bugInfo = bugInfoDao.getBugById(id);
		ProjectInfo projectInfo = projectDao.getProjectById(idP);
		model.put("project_Infor", projectInfo);

		model.put("command", bugInfoDao.getBugById(id));
		return new ModelAndView("updateBug", "command", bugInfo);
	}

	// Mapping view list Bug
	@RequestMapping("/bugList")
	public ModelAndView listBug(@RequestParam(value="projectName",required=false,defaultValue = "999999")
		int projectName,@RequestParam(value="type_id",required=false,defaultValue = "999999") 
		int type_id,@RequestParam(value="status_id",required=false,defaultValue = "999999") 
		int status_id,@RequestParam(value="member_project_id",required=false,defaultValue = "999999") 
		int member_project_id,@RequestParam(value="bug_priority",required=false,defaultValue = "") 
		String bug_priority) {
	
		List<BugInfo> list = bugInfoDao.getAllBug(projectName,type_id,status_id,member_project_id,bug_priority);
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
	@ModelAttribute("picL")
	public List<MemberProject> getPICL() {
		List<MemberProject> listL = memberProjectDao.getMember();
		return listL;
	}
}
