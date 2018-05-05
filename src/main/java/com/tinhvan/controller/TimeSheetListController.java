package com.tinhvan.controller;

import java.util.List;
import java.security.Principal;
import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tinhvan.dao.MemberProjectDao;
import com.tinhvan.dao.PreDefinedTaskDao;
import com.tinhvan.dao.ProcessDao;
import com.tinhvan.dao.ProjectDao;
import com.tinhvan.dao.StatusDao;
import com.tinhvan.dao.TaskInfoDao;
import com.tinhvan.dao.TimeSheetDao;
import com.tinhvan.dao.TypeDao;
import com.tinhvan.dao.UserDao;
import com.tinhvan.model.MemberProject;
import com.tinhvan.model.PreDefinedTask;
import com.tinhvan.model.Process;
import com.tinhvan.model.ProjectInfo;
import com.tinhvan.model.Status;
import com.tinhvan.model.TimeSheetDetail;
import com.tinhvan.model.User;

@Controller
public class TimeSheetListController {


	@Autowired
	TimeSheetDao timeSheetDao;
	@Autowired
	ProjectDao projectDao;
	@Autowired
	TypeDao typeDao;
	@Autowired
	StatusDao statusDao;
	@Autowired
	MemberProjectDao memberProjectDao;
	@Autowired
	ProcessDao processDao;
	@Autowired
	PreDefinedTaskDao definedTaskDao;
	@Autowired
	TaskInfoDao TaskInfoDao;
	@Autowired
	UserDao userDao;
	
	
	// get User infor of current user login for menu user infor
			@ModelAttribute("UserInformation")
			public User getUserCurrentLogin(Principal principal){
				return  userDao.getUserInfoByUserMail(principal.getName());
				
			}
	// get list project for menu
			@ModelAttribute("list_Project_For_menu")
			public List<ProjectInfo> getListProject(Principal principal) {
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
		
	/*	//Test DaiK
			get user infor current logged
			@ModelAttribute("user_current_logged")
			public User get_User_current_loged(Principal principal){
				String user_mail = principal.getName();
				//user_current_loged.setUser_mail(principal.getName());
				User user_current_loged = userDao.getUserInfoByUserMail(user_mail);
				return user_current_loged;
			}*/
	
	// Mapping view list TimeSheeet
			@RequestMapping(value = "/timeSheetList", method = RequestMethod.GET)
			public String listTimeSheet(Principal principal, Model model) {
				
				User user = userDao.getUserInfoByUserMail(principal.getName());
				
				//check role: if role = 1 or 2
				//if role = 1 => list all project
				if(user.getRole_id() == 1){
					
					List<ProjectInfo> listAllProjectInfos = projectDao.getAllProject();
									
					
					List<TimeSheetDetail> listAllTimeSheetDetails = timeSheetDao.getAllTimeSheet();
					List<MemberProject> list_PIC = memberProjectDao.get_All_MemberProjects_filter_duplicate();
					
					model.addAttribute("listTimeSheetDetails", listAllTimeSheetDetails);
					model.addAttribute("listProjects", listAllProjectInfos);
					model.addAttribute("list_PIC", list_PIC);
					return "timeSheetList";
					
				} 
				
				else if(user.getRole_id() == 2){
					//if role = 2 => get list member_project of user 
					List<MemberProject> list_PIC = memberProjectDao.getListMemberProject_By_Current_User_Is_PM_filter_duplicate(user.getUser_id());
					
					
					List<TimeSheetDetail> lisTimeSheetDetails_No_ConditionOfPM = timeSheetDao.getTimeSheetDetailsNoConditionsOfPM(user.getUser_id());
					
					
								model.addAttribute("listTimeSheetDetails", lisTimeSheetDetails_No_ConditionOfPM);
							
								List<ProjectInfo> listProjectInfos = projectDao.getListPRojectOfUserAccessed(user.getUser_id());
								
								model.addAttribute("listProjects", listProjectInfos);
								model.addAttribute("list_PIC", list_PIC);
								return "timeSheetList";
							
							
				}
				else{
					String message = "Access denied for "+principal.getName()+"!";
					model.addAttribute("message", message);
					return "403Page";
				}
				
			}
			
			@RequestMapping(value = "/actionUpdateStatusTypeOfListTimesheets", method = RequestMethod.POST)
			public @ResponseBody boolean save(@RequestBody  final ArrayList<TimeSheetDetail> list_TimeSheetDetails) {
				//System.out.println("prj_id = "+id);
				
				boolean result = timeSheetDao.updateStatusOfListTimeSheetDetails(list_TimeSheetDetails);
			
				return result;
			}
			
			
			// method get Name of Project
			@ModelAttribute("projectName")
			public List<ProjectInfo> getAllProject() {
				List<ProjectInfo> list = projectDao.getAllProject();
				return list;
			}

			// method get all member project Timesheet of project
			@ModelAttribute("pic")
			public List<MemberProject> getPIC() {
				
				List<MemberProject> list = memberProjectDao.getAllMember();
				return list;
			}

			// method get process of timesheet
			@ModelAttribute("process")
			public List<Process> getProcess() {
				List<Process> list = processDao.getAll();
				return list;
			}

			// method get status of timesheet
			@ModelAttribute("timeSheetStatus")
			public List<Status> getStatusOfTimeSheet() {
				List<Status> list = statusDao.getStatusOfTS();
				return list;
			}
			
			@ModelAttribute("pre_defined")
			public List<PreDefinedTask> getPreDefinedTasks(){
				List<PreDefinedTask> list = definedTaskDao.getAll();
				return list;
			}
			
			@ModelAttribute("timsheetTypes")
			public List<com.tinhvan.model.Type> getTypeOfTimeSheet() {
				List<com.tinhvan.model.Type> list = typeDao.getAllTypeOfTimeSheet();
				return list;
			}

			@ModelAttribute("picL")
			public List<MemberProject> getPICL() {
				List<MemberProject> list = memberProjectDao.getMember();
				return list;
			}
			
}
