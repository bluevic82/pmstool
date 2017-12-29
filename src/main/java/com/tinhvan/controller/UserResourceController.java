package com.tinhvan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tinhvan.dao.RoleDao;
import com.tinhvan.dao.UserDao;
import com.tinhvan.model.Role;
import com.tinhvan.model.User;


@Controller
public class UserResourceController {
	@Autowired
	RoleDao roleDao;
	@Autowired
	UserDao userDao;
	
	@RequestMapping(value="/resource")
	public String resourceMember() {
		return "resourceMember";
	}
	@ModelAttribute("roleUser")
	public List<Role> getRole(){
		List<Role> list = roleDao.getAllRole(); 
		return list;
	}
	
	@ModelAttribute("getAllUser")
	public List<User> getAllUser(){
		List<User> us = userDao.gettAllUser();
		return us;
	}
}
