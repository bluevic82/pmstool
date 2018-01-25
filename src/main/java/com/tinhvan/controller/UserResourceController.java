package com.tinhvan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
/*import org.springframework.web.bind.annotation.ModelAttribute;
 import org.springframework.web.bind.annotation.PathVariable;
 import org.springframework.web.bind.annotation.RequestBody;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestMethod;*/
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

//import org.springframework.http.MediaType;


import com.tinhvan.dao.MemberProjectDao;
import com.tinhvan.dao.ProjectDao;
import com.tinhvan.dao.RoleDao;
import com.tinhvan.dao.UserDao;
import com.tinhvan.model.MemberProject;
import com.tinhvan.model.ProjectInfo;
import com.tinhvan.model.Role;
import com.tinhvan.model.User;

/**
 * @purpose: process for resourceMember.jsp;
 * @author: Edit by DaiCQ
 * @date: 2017/12/30
 * **/
@Controller
public class UserResourceController {
	@Autowired
	RoleDao roleDao;
	@Autowired
	UserDao userDao;
	@Autowired
	MemberProjectDao memberProjectDao;
	@Autowired
	ProjectDao projectDao;

	// get list project for menu
	@ModelAttribute("list_Project_For_menu")
	public List<ProjectInfo> getListProject() {
		List<ProjectInfo> list_Project_For_Menu = projectDao.getAllProject();
		return list_Project_For_Menu;
	}

	
	
	@RequestMapping(value = "/{id}/{nameOfProject}/resource", method = RequestMethod.GET)
	public ModelAndView resourceMember(@PathVariable int id,
			@PathVariable(value = "nameOfProject") String nameOfProject,
			ModelMap model) {
		List<MemberProject> listMemberOfProject = memberProjectDao
				.getMemberProjectByProjectId1(id);

		model.put("listMemberOfProject", listMemberOfProject);
		model.put("nameOfProject", nameOfProject);
		return new ModelAndView("resourceMember", "listMemberOfProject",
				listMemberOfProject);
	}
	
	/*@RequestMapping(value = "/saveReSourceMemberToDB", method = RequestMethod.POST)
	public String saveResourceMemberToDB(
			Model model,@ModelAttribute("listMemberOfProject") List<MemberProject> listMemberOfProject) {
			
		System.out.println(listMemberOfProject.size());
		return "welcomePage";
	}*/
	
	@PostMapping(value = "/saveReSourceMemberToDB")
	   @ResponseBody
	   public MemberProject save(@ModelAttribute MemberProject memberProject) {

		  // System.out.println("name = " + employee.get(0).getFirstName() );
		   System.out.println("name =  " + memberProject.getMember_project_name() );

	        return memberProject;
	   }
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public @ResponseBody String test(@ModelAttribute MemberProject bookingTableWrapper) {
		//bookingTableWrapper.ge
		System.out.println(bookingTableWrapper.getMember_project_name());
		System.out.println(bookingTableWrapper.getMember_project_effort());

	    return "whileSuccess";
	}
	

	/*
	 * @RequestMapping(value = "/submitForm.web", method = RequestMethod.POST)
	 * public @ResponseBody MemberProject submittedFromData(
	 * 
	 * @RequestBody MemberProject memberProject, HttpServletRequest request) {
	 * return memberProject; }
	 */

	// @PostMapping(value = "/saveReSourceMemberToDB")
	// @ResponseBody
	/*
	 * @RequestMapping(value = "/saveReSourceMemberToDB") public @ResponseBody
	 * List<MemberProject> saveResourceMemberToDB(
	 * 
	 * @RequestBody List<MemberProject> list_MemberProject, HttpServletRequest
	 * request) {
	 * 
	 * List<MemberProject> response_List = new ArrayList<MemberProject>();
	 * for(int i = 0; i < list_MemberProject.size(); i++){ //test MemberProject
	 * memberProject = new MemberProject();
	 * memberProject.setMember_project_name(
	 * list_MemberProject.get(i).getMember_project_name());
	 * 
	 * response_List.add(memberProject);
	 * 
	 * } return response_List;
	 * 
	 * }
	 */

	/*@RequestMapping(value = "/saveReSourceMemberToDB", method = RequestMethod.POST)
	public @ResponseBody List<MemberProject> saveResourceMemberToDB(
			@RequestBody List<MemberProject> list_MemberProject) {

		List<MemberProject> response_List = new ArrayList<MemberProject>();
		for (int i = 0; i < list_MemberProject.size(); i++) {
			System.out.println(list_MemberProject.get(i)
					.getMember_project_name());

			// test
			MemberProject memberProject = new MemberProject();
			memberProject.setMember_project_name(list_MemberProject.get(i)
					.getMember_project_name());

			response_List.add(memberProject);

		}
		return response_List;

	}*/

	

	/*
	 * public static void main(String[] args ){ UserResourceController u=new
	 * UserResourceController(); List<MemberProject> list= new ArrayList<>();
	 * MemberProject m=new MemberProject(); m.setMember_project_name("aaaa");
	 * list.add(m); System.out.println(u.saveResourceMemberToDB(list));
	 * 
	 * }
	 */

	@ModelAttribute("roleUser")
	public List<Role> getRole() {
		List<Role> list = roleDao.getAllRole();
		return list;
	}

	@ModelAttribute("getAllUser")
	public List<User> getAllUser() {
		List<User> us = userDao.gettAllUser();
		return us;
	}

}
