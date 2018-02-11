package com.tinhvan.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

/*import org.springframework.web.bind.annotation.ModelAttribute;
 import org.springframework.web.bind.annotation.PathVariable;
 import org.springframework.web.bind.annotation.RequestBody;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestMethod;*/
import org.springframework.web.bind.annotation.*;

import com.tinhvan.dao.MemberProjectDao;
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
	@Autowired
	ProjectDao projectDao;

	// get list project for menu
	@ModelAttribute("list_Project_For_menu")
	public List<ProjectInfo> getListProject() {
		List<ProjectInfo> list_Project_For_Menu = projectDao.getAllProject();
		return list_Project_For_Menu;
	}

	@RequestMapping(value = "/{id}/resource", method = RequestMethod.GET)
	public String resourceMember(@PathVariable int id, ModelMap model) {
		List<MemberProject> listMemberOfProject = memberProjectDao
				.getMemberProjectByProjectId1(id);

		ProjectInfo projectInfo = projectDao.getProjectById(id);
		model.put("listMemberOfProject", listMemberOfProject);
		model.put("projectInfo", projectInfo);
		return "resourceMember";
	}


	//@RequestMapping(value = "/aaaaaaa", method = RequestMethod.GET)
	@RequestMapping(value = "/{id}/actionSaveMemberToDB", method = RequestMethod.POST)
	public @ResponseBody ArrayList<MemberProject> post(@PathVariable int id, @RequestBody  final ArrayList<MemberProject> list_MemberProjects) {
		
		memberProjectDao.updateMemberProjectBy_PrjId(list_MemberProjects, id);
	
		return list_MemberProjects;

	}
	
	
	
	@RequestMapping(value = "/{id}/deleteOneMemberProject", method = RequestMethod.POST)
	public @ResponseBody MemberProject post(@PathVariable int id, @RequestBody  final int member_project_id) {
		MemberProject m=new MemberProject();
		
		memberProjectDao.deleteOneMemberProject(member_project_id);
	
		return m;

	}
	

	@ModelAttribute("roleUser")
	public List<Role> getRole() {
		List<Role> list = roleDao.getAllRole();
		return list;
	}

	@ModelAttribute("getAllUser")
	public List<User> getAllUser() {
		List<User> us = userDao.gettAllUser();
		return us;
	}

}