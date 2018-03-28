package com.tinhvan.controller;


import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

/*import org.springframework.web.bind.annotation.ModelAttribute;
 import org.springframework.web.bind.annotation.PathVariable;
 import org.springframework.web.bind.annotation.RequestBody;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestMethod;*/
import org.springframework.web.bind.annotation.*;

import com.tinhvan.dao.MemberProjectDao;
import com.tinhvan.dao.PermissionDao;
import com.tinhvan.dao.ProjectDao;
import com.tinhvan.dao.RoleDao;
import com.tinhvan.dao.UserDao;
import com.tinhvan.model.MemberProject;
import com.tinhvan.model.ProjectInfo;
import com.tinhvan.model.Role;
import com.tinhvan.model.User;

/**
 * @purpose: process for resourceMember.jsp;
 * @author: Edit by DaiCQ
 * @date: 2017/12/30
 * **/
@Controller
public class UserResourceController {
	@Autowired
	RoleDao roleDao;
	@Autowired
	UserDao userDao;
	@Autowired
	MemberProjectDao memberProjectDao;
	
	@Autowired ProjectDao projectDao;
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

	@RequestMapping(value = "/{id}/resource", method = RequestMethod.GET)
	public String resourceMember(@PathVariable(value = "id") int id, ModelMap model) {
		Boolean checker = per.checker("set_res");
		if(checker==true) {
		List<MemberProject> listMemberOfProject = memberProjectDao
				.getMemberProjectByProjectId1(id);

		ProjectInfo projectInfo = projectDao.getProjectById(id);
		model.put("listMemberOfProject", listMemberOfProject);
		model.put("projectInfo", projectInfo);
		
		return "resourceMember";
		}else {
			return "403Page";
		}
	}


	//@RequestMapping(value = "/aaaaaaa", method = RequestMethod.GET)
	@RequestMapping(value = "/{id}/actionSaveMemberToDB", method = RequestMethod.POST)
	public @ResponseBody ArrayList<MemberProject> update(@PathVariable(value = "id") int id, @RequestBody  final ArrayList<MemberProject> list_MemberProjects) {
		
		memberProjectDao.updateMemberProjectBy_PrjId(list_MemberProjects, id);
	
		return new ArrayList<MemberProject>(memberProjectDao.getMemberProjectByProjectId1(id));

	}
	
	
	
	@RequestMapping(value = "/{id}/deleteOneMemberProject", method = RequestMethod.POST)
	public @ResponseBody ArrayList<MemberProject> delete(@PathVariable int id, @RequestBody  final int member_project_id) {
		memberProjectDao.deleteOneMemberProject(member_project_id);
		//MemberProject m = memberProjectDao.getMemberProjectByMem_id(memberProject_id);
		ArrayList<MemberProject> m =new ArrayList<MemberProject>(memberProjectDao.getMemberProjectByProjectId1(id));
		return m;

	}
	
	
	
	//comment to run testcase

	@ModelAttribute("roleUser")
	public List<Role> getRole() {
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Role> list = roleDao.getListRoleExceptManagerIfUserLoginIsManager(userDao.getUserInfoByUserMail(auth.getName()).getRole_id());
		return list;
	}

	//
	
	@ModelAttribute("getAllUser")
	public List<User> getAllUser() {
		List<User> us = userDao.getAllUser_Except_Admin();
		return us;
	}

}