package com.tinhvan.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tinhvan.dao.CategoryDao;
import com.tinhvan.dao.EffortDao;
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
	@Autowired
	EffortDao effortDao;
	
	//ffdg
	
	@RequestMapping(value = "/getProjectInfor")
	public @ResponseBody List<ProjectInfo> get_Project_Infor(HttpServletRequest request, HttpServletResponse response){
		
		//String query = request.getParameter("query_list_project");
		List<ProjectInfo> list_Project_Infor = projectDao.getAllProject();
		return list_Project_Infor;
	}
	
	
	//mapping add project
	@RequestMapping(value = "/addProject")
	public ModelAndView addProject() {

		return new ModelAndView("addProject","command",new ProjectInfo());
	}
	//mapping Create Project
	@RequestMapping(value="/actionCreateProject", method=RequestMethod.POST)
	public ModelAndView addproject(Model model, @ModelAttribute(value="project") ProjectInfo project) {
		projectDao.addProject(project);
		return new ModelAndView("redirect:/");
	}
	//mapping update project
	/*@RequestMapping(value = "/updateProject")
	public String updateProject() {

		return "updateProject";
	}*/
	//mapping action update project
	@RequestMapping(value="/actionUpdateProject", method=RequestMethod.POST)
	public ModelAndView actionUpdateProject(Model model, @ModelAttribute(value = "project") ProjectInfo project) {
		projectDao.updateProject(project);
		return new ModelAndView("redirect:/");
	}
	
	//mapping getdata project_id for update Project
	@RequestMapping(value = "/editproject/{id}")
	public ModelAndView editProject(@PathVariable int id, ModelMap model) {
		ProjectInfo projectInfo = projectDao.getProjectById(id);
		model.put("command", projectDao.getProjectById(id));
		return new ModelAndView("updateProject","command",projectInfo);
	}
	
	//mapping detail project
	@RequestMapping(value="/detailProject/{id}")
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
