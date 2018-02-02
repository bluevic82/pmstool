package com.tinhvan.controller;

import java.io.File;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.tinhvan.dao.ProjectDao;
import com.tinhvan.dao.RoleDao;
import com.tinhvan.dao.UserDao;
import com.tinhvan.model.ProjectInfo;
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
	ProjectDao projectDao;
	@Autowired
	private UserValidator userValidator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(userValidator);
	}

	// get list project for menu
	@ModelAttribute("list_Project_For_menu")
	public List<ProjectInfo> getListProject() {
		List<ProjectInfo> list_Project_For_Menu = projectDao.getAllProject();
		return list_Project_For_Menu;
	}

	// mapping add userInfo
	@RequestMapping(value = "/user")
	public ModelAndView userRegister(Locale locale, Model model) {
		model.addAttribute("user", new User());
		return new ModelAndView("userRegister");
	}

	@RequestMapping("/actionCreateUser")
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

	/*
	 * @RequestMapping(method=RequestMethod.GET) public String
	 * uploadPage(@RequestParam("fileName") MultipartFile file,ModelMap model)throws
	 * Exception{ if(!file.isEmpty()){ String name=file.getOriginalFilename(); long
	 * size=file.getSize();
	 * 
	 * File f=new File("c:/temp/"+name); if(!f.exists()){ f.getParentFile().mkdir();
	 * } model.addAttribute("name",name); model.addAttribute("size",size);
	 * 
	 * }else { model.addAttribute("fail","bi loi"); }
	 * 
	 * return "userRegister"; }
	 */
}
