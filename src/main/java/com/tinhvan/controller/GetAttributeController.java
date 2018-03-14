package com.tinhvan.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.tinhvan.dao.ProjectDao;
import com.tinhvan.dao.UserDao;
import com.tinhvan.model.ProjectInfo;
import com.tinhvan.model.User;


@Controller
public class GetAttributeController {

	@Autowired ProjectDao projectDao;
	@Autowired UserDao userDao;
	
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
}
