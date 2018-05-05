package com.tinhvan.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tinhvan.dao.EffortDao;
import com.tinhvan.dao.PermissionDao;
import com.tinhvan.dao.ProjectDao;
import com.tinhvan.dao.UserDao;
import com.tinhvan.model.Effort;
import com.tinhvan.model.ProjectInfo;
import com.tinhvan.model.User;

/**
 * @purpose: calculate actual cost, over head, charge request.
 * @author: DaiCQ
 * @date: 2017/12/30
 **/

@Controller
//@RequestMapping(value="effort")
public class EffortController {
	@Autowired
	ProjectDao projectDao;
	@Autowired
	EffortDao effortDao;
	@Autowired
	UserDao userDao;
	@Autowired
	PermissionDao per;
	
		
		
	// get User infor of current user login for menu user infor
			@ModelAttribute("UserInformation")
			public User getUserCurrentLogin(Principal principal){
		//		java.security.Principal principal = request.getUserPrincipal();
				 
				//final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				//User user = userDao.getUserInfoByUserMail(auth.getName());
				User user = userDao.getUserInfoByUserMail(principal.getName());
				return user ;
				
			}
	// get list project for menu
	@ModelAttribute("list_Project_For_menu")	
	public List<ProjectInfo> getListProject(Principal principal) {
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

	@RequestMapping(value = "/effortManagement")
	public ModelAndView EffortManagementPage(Model model) {
		Boolean checker = per.checker("eff_mana");
		if(checker==true) {
		model.addAttribute("message", "Effort Management!");

		List<Effort> list = effortDao.getAllEfort();
		return new ModelAndView("effortManagementPage", "list", list);
		}else {
			return new ModelAndView("403Page2");
		}
	}

	@RequestMapping(value = "/effortCalculate/{id}")
	public ModelAndView effortCalculate(@PathVariable(value = "id") int id, ModelMap model) {
		Boolean checker = per.checker("eff_can");
		if(checker==true) {
		Effort effort = effortDao.getEffortById(id);
		model.put("effort", effort);
		return new ModelAndView("effortCanculate", "effort", effort);
		}else {
			return new ModelAndView("403Page");
		}
	}

}
