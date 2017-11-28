package tinhvan.pms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import tinhvan.pms.dao.ProjectDao;
import tinhvan.pms.model.ProjectModel;

@Controller
public class ProjectController {
	
	@Autowired
	private ProjectDao projectdao;
	
	 @RequestMapping(value="/addProject")
	 public ModelAndView insert(ModelMap model, @ModelAttribute(value="project") ProjectModel project) {
		 projectdao.insertProject(project);
	  return new ModelAndView("addProject");
	 }
}