package com.tinhvan.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tinhvan.dao.MileStoneDao;
import com.tinhvan.dao.PermissionDao;
import com.tinhvan.dao.ProjectDao;
import com.tinhvan.dao.UserDao;
import com.tinhvan.model.MileStone;
import com.tinhvan.model.ProjectInfo;
import com.tinhvan.model.User;

/**
 * @purpose: MileStoneController using Setting Milestone Using method Attribute
 *  set data for Status
 * @author: NguyenManh
 * @date: 2017/12/15
 **/
@Controller
public class MileStoneController {

	@Autowired
	MileStoneDao mileStoneDao;
	@Autowired(required = true)
	ProjectDao projectDao;
	@Autowired UserDao userDao;
	@Autowired
	PermissionDao per;
	
	// get User infor of current user login for menu user infor
			@ModelAttribute("UserInformation")
			public User getUserCurrentLogin(Principal principal){
				return  userDao.getUserInfoByUserMail(principal.getName());
				
			}
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

	// Mapping to view MileStone JSP
	/*@RequestMapping(value = { "/createMileStone" }, method = RequestMethod.GET)
	public String createMileStone(Model model) {
		model.addAttribute("title", "Welcome");
		model.addAttribute("message", "Create MileStone");

		return "mileStone";
	}*/

	@RequestMapping(value = "/{id}/createMileStone", method = RequestMethod.GET)
	public ModelAndView createMileStone(@PathVariable int id, ModelMap model) {
		Boolean checker = per.checker("set_reg");
		if(checker==true) {
		ProjectInfo projectInfor = projectDao.getProjectById(id);
		List<MileStone> m = mileStoneDao.getMileStoneByProjectId(id);
		model.addAttribute("ml",m);
		model.put("projectInfor", projectInfor);
		model.addAttribute("projectInfor", projectInfor);
		//System.out.println(projectInfor.getProject_id());
		//System.out.println(projectInfor.getProject_name());
		return new ModelAndView("createMilestone");
		}else {
			return new ModelAndView("403Page");
		}
	}

	// Mapping button click create MileStone
	
	@RequestMapping(value = "/{id}/actionSaveMileStone", method = RequestMethod.POST)
	public @ResponseBody ArrayList<MileStone> update(@PathVariable int id, @RequestBody final ArrayList<MileStone> milestone){
		mileStoneDao.updateMilestone(milestone);
		List<MileStone> m = mileStoneDao.getMileStoneByProjectId(id);
		ArrayList<MileStone> mileStones = new ArrayList<MileStone>(m);
		return mileStones;
	}
	// Mapping button click create MileStone
		/*@RequestMapping(value = "/{id}/actionAddMileStone", method = RequestMethod.POST)
		public @ResponseBody ArrayList<MileStone> save(@RequestBody final ArrayList<MileStone> milestone){
			mileStoneDao.insertMilestone(milestone);	
			return milestone;
		}*/

	// Mapping button click delete MileStone
	/*@RequestMapping(value = "/{id}/actionDeleteMileStone")
	public ModelAndView deleteMileStone(Model model, @ModelAttribute(value = "milestone") MileStone mileStone) {
		mileStoneDao.deleteMidelStone(mileStone);
		return new ModelAndView("whileSuccess");
	}*/
	
	@RequestMapping(value = "/{id}/actionDeleteMileStone", method = RequestMethod.POST)
	public @ResponseBody ArrayList<MileStone> deleteMileStone(@PathVariable int id, @RequestBody final int milestone_id){
		mileStoneDao.deleteMidelStone(milestone_id);	
		return new ArrayList<MileStone>(mileStoneDao.getMileStoneByProjectId(id));
	}

}
