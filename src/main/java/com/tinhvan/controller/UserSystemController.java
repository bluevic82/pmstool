package com.tinhvan.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tinhvan.dao.RoleDao;
import com.tinhvan.dao.UserDao;
import com.tinhvan.model.Role;
import com.tinhvan.model.User;
import com.tinhvan.validator.UserValidator;

@Controller
public class UserSystemController {
	@Autowired
	UserDao userDao;
	@Autowired
	RoleDao roleDao;
	@Autowired
	private UserValidator userValidator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(userValidator);
	}

	// mapping add userInfo
	@RequestMapping(value = "/user")
	public ModelAndView userRegister(Locale locale, Model model) {
		model.addAttribute("user", new User());
		return new ModelAndView("userRegister");
	}

	@RequestMapping(value = "/actionCreateUser")
	public ModelAndView addUser(Model model, @ModelAttribute(value = "userInfo") @Validated User users,
			BindingResult result) {

		if (result.hasErrors()) {

			return new ModelAndView("403Page");
		}
		userDao.addUserSystem(users);
		return new ModelAndView("userRegister");
	}

	@ModelAttribute("projectRole")
	public List<Role> getAllRole() {
		List<Role> list = roleDao.getAllRole();
		return list;
	}
}
