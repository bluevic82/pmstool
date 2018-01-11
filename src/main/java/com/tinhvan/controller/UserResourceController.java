package com.tinhvan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tinhvan.dao.MemberProjectDao;
import com.tinhvan.dao.RoleDao;
import com.tinhvan.dao.UserDao;
import com.tinhvan.model.MemberProject;
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

	@RequestMapping(value = "/{id}/{nameOfProject}/resource")
	//
	public ModelAndView resourceMember(@PathVariable int id,
			@PathVariable(value = "nameOfProject") String nameOfProject,
			ModelMap model) {
		List<MemberProject> listMemberOfProject = memberProjectDao
				.getMemberProjectByProjectId1(id);

		model.put("listMemberOfProject", listMemberOfProject);
		model.put("nameOfProject", nameOfProject);
		return new ModelAndView("resourceMember", "listMemberOfProject",
				listMemberOfProject);
	}

	@RequestMapping(value = "/saveReSourceMemberToDB", method = RequestMethod.POST)
	public String saveResourceMemberToDB() {

		return "index";
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
