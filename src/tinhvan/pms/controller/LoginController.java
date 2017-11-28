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

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView viewLogin(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("login");
		modelAndView.addObject("login", new Login());
		return modelAndView;
	}
	
	public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response,
		@ModelAttribute("login") Login login) {
		ModelAndView modelAndView = null;
		return modelAndView;
	}
	
}


