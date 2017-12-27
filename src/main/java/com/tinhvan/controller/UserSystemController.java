package com.tinhvan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tinhvan.dao.RoleDao;
import com.tinhvan.dao.UserDao;
import com.tinhvan.model.Role;
import com.tinhvan.model.User;

@Controller
public class UserSystemController {
	@Autowired
	UserDao userDao;
	@Autowired
	RoleDao roleDao;

	// mapping add userInfo
	@RequestMapping(value = "/user")
	public ModelAndView userRegister() {
		return new ModelAndView("userRegister");
	}

	@RequestMapping(value = "/actionCreateUser")
	public ModelAndView addUser(Model model, @ModelAttribute(value = "userInfo") User users) {
		userDao.addUserSystem(users);
		return new ModelAndView("userRegister");
	}

	@ModelAttribute("projectRole")
	public List<Role> getAllRole() {
            List<Role> list = roleDao.getAllRole();
            return list;
	}
}
