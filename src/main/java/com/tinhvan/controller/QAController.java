package com.tinhvan.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tinhvan.dao.MemberProjectDao;
import com.tinhvan.dao.ProjectDao;
import com.tinhvan.dao.QuestionAnswerDao;
import com.tinhvan.dao.StatusDao;
import com.tinhvan.dao.UserDao;
import com.tinhvan.model.MemberProject;
import com.tinhvan.model.ProjectInfo;
import com.tinhvan.model.QuestionAnwer;
import com.tinhvan.model.Status;
import com.tinhvan.model.TaskInfo;
import com.tinhvan.model.User;

/**
 * @purpose: QAController using QAResgister/Update Using method Attribute set
 *           data for Status
 * @author: NguyenManh
 * @date: 2017/12/19
 **/
@Controller
public class QAController {

	@Autowired
	ProjectDao projectDao;
	@Autowired
	QuestionAnswerDao answerDao;
	@Autowired
	StatusDao statusDao;
	@Autowired
	MemberProjectDao memberProjectDao;
	@Autowired UserDao userDao;
	
	// get list project for menu
		@ModelAttribute("list_Project_For_menu")
		public List<ProjectInfo> getListProject(Principal principal) {
			//List<ProjectInfo> list_Project_For_Menu = new ArrayList<ProjectInfo>();
			//User user = get_User_current_loged(principal);
			User user = userDao.getUserInfoByUserMail(principal.getName());
			
			//check role: if user is Admin => list all projects 
			if(user.getRole_id()==1){
				return projectDao.getAllProject();
			}
			else{
				//only get list projects that user access
				//get List project_ids of user is PM
				return projectDao.getListPRojectOfUserAccessed(user.getUser_id());
				
			}
		}
	
	// Mapping view ListQuestion & Answer
	@RequestMapping(value = "/qaList")
	public ModelAndView listQA(
			@RequestParam(value = "projectName", required = false, defaultValue = "999999") int projectName,
			@RequestParam(value = "status", required = false, defaultValue = "999999") int status,
			@RequestParam(value = "member_project_id", required = false, defaultValue = "999999") int member_project_id) {

		List<QuestionAnwer> list = answerDao.getAllQA(projectName, status, member_project_id);
		return new ModelAndView("qaList", "list", list);
	}

	// Mapping view page register QA
	@RequestMapping(value = "{id}/registerQA")
	public ModelAndView registerQA(@PathVariable int id, Model model) {
		ProjectInfo projectInfo = projectDao.getProjectById(id);

		/**
		 * purpose: get project's name
		 */
		model.addAttribute("project_Infor", projectInfo);
		return new ModelAndView("registerQandA", "command", new TaskInfo());
	}

	// Mapping button click registerQA
	@RequestMapping(value = "/actionRegisterQA", method = RequestMethod.POST)
	public ModelAndView registerQA(Model model, @ModelAttribute(value = "addqa") QuestionAnwer questionAnwer) {
		answerDao.registerQA(questionAnwer);
		return new ModelAndView("redirect:/qaList");
	}

	// Mapping view page update QA
	@RequestMapping(value = { "/updateQA" }, method = RequestMethod.GET)
	public String updateQA(Model model) {
		return "updateQandA";
	}

	// Mapping button click save QA
	@RequestMapping(value = "/actionUpdateQA", method = RequestMethod.POST)
	public ModelAndView updateQA(Model model, @ModelAttribute(value = "qa") QuestionAnwer questionAnwer) {
		answerDao.updateQA(questionAnwer);
		return new ModelAndView("redirect:/qaList");
	}

	// Mapping get dataById for update QA
	@RequestMapping(value = "/qaList/{q_a_id}/editQA/{pr_id}")
	public ModelAndView editQA(@PathVariable int q_a_id, ModelMap model,@PathVariable int pr_id) {
		QuestionAnwer questionAnwer = answerDao.getQAById(q_a_id);
		ProjectInfo projectInfo = projectDao.getProjectById(pr_id);
		model.put("project_infor", projectInfo);
		
		model.put("command", answerDao.getQAById(q_a_id));
		return new ModelAndView("updateQandA", "command", questionAnwer);
	}
	
	/*
	 * @purpose: Methods Attributes
	 */
	@ModelAttribute("qaStatus")
	public List<Status> getStatusOfQA() {
		List<Status> list = statusDao.getStatusOfQA();
		return list;
	}

	// get all member project in project this
	@ModelAttribute("pic")
	public List<MemberProject> getPIC() {
		List<MemberProject> list = memberProjectDao.getAllMember();
		return list;
	}

	// method get Name of Project
	@ModelAttribute("projectName")
	public List<ProjectInfo> getAllProject() {
		List<ProjectInfo> list = projectDao.getAllProject();
		return list;
	}

}
