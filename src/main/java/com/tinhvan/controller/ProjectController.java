package com.tinhvan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tinhvan.dao.CategoryDao;
import com.tinhvan.dao.MemberProjectDao;
import com.tinhvan.dao.ProjectDao;
import com.tinhvan.dao.ScopeDao;
import com.tinhvan.dao.StatusDao;
import com.tinhvan.dao.TypeDao;
import com.tinhvan.model.ProjectInfo;
import com.tinhvan.model.Scope;
import com.tinhvan.model.Status;
import com.tinhvan.model.Type;

/**
 * @purpose: Project Controller using addProject, getProject
 * @author: Luong
 * @date: 2017/12/14
 * **/
@Controller
public class ProjectController {
	@Autowired
	ProjectDao projectDao;
	@Autowired
	TypeDao typeDao;
	@Autowired
	StatusDao statusDao;
	@Autowired
	MemberProjectDao memberProjectDao;
	@Autowired
	CategoryDao categoryDao;
	@Autowired
	ScopeDao scopeDao;
	//mapping add project
	@RequestMapping(value = "/addProject")
	public String addProject() {

		return "addProject";
	}
	//mapping Create Project
	@RequestMapping(value="actionCreateProject")
	public ModelAndView addproject(Model model, @ModelAttribute(value="project") ProjectInfo project) {
		projectDao.addProject(project);
		return new ModelAndView("whileSuccess");
	}
	//mapping update project
	@RequestMapping(value = "/updateProject")
	public String updateProject() {

		return "updateProject";
	}
	
	//mapping detail project
	@RequestMapping(value="/detailProject")
	public String detailProject() {
		
		return"detailProject";
	}
	// get type project
	@ModelAttribute("projectTypes")
	public List<Type> getTypes() {
		List<Type> list = typeDao.getAllType();
		return list;
	}
	//get Status project
	@ModelAttribute("projectStatus")
	public List<Status> getStatus() {
		List<Status> list = statusDao.getAllStatus();
		return list;
	}
	//get Scope project
	@ModelAttribute("projectScope")
	public List<Scope> getAllScope(){
		List<Scope> list = scopeDao.getAllScope();
		return list;
	}
}
