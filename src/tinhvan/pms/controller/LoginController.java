package tinhvan.pms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import tinhvan.pms.model.Login;
import tinhvan.pms.model.User;
import tinhvan.pms.validator.UserValidator;

/**
 * @Purpose: Login
 * @author NguyenManhIT
 * @version 1.0
 * @Date 27/11/2017
 **/

@Controller
public class LoginController {
	
	@Autowired
	UserValidator userValidator;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("login");
		mav.addObject("login", new Login());
		return mav;
	}
	
	@RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
	public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("login") Login login) {
		ModelAndView mav = null;
		User user = new User();
		mav = new ModelAndView("welcome");
		mav.addObject("", user.getEmailID());
		return mav;

	}
	
}


