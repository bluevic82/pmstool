package com.tinhvan.controller;

import java.util.List;
import java.security.Principal;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.management.MalformedObjectNameException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.tinhvan.model.TaskInfo;
import com.tinhvan.model.TimeSheetDetail;
import com.tinhvan.model.TimeSheetDetail_List;
import com.tinhvan.model.TimeSheet_Info;
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
		
		//Test DaiK
			/*get user infor current logged*/
			@ModelAttribute("user_current_logged")
			public User get_User_current_loged(Principal principal){
				String user_mail = principal.getName();
				//user_current_loged.setUser_mail(principal.getName());
				User user_current_loged = userDao.getUserInfoByUserMail(user_mail);
				return user_current_loged;
			}
	
	// Mapping view list TimeSheeet
			@RequestMapping("/timeSheetList")
			public ModelAndView listTimeSheet(@RequestParam(value="projectName",required=false,defaultValue = "0")
			int project_id,@RequestParam(value="member_project_id",required=false,defaultValue = "0") 
			int member_project_id,@RequestParam(value="process_id",required=false,defaultValue = "0") 
			int process_id,@RequestParam(value="status_name",required=false,defaultValue = "") 
			String status_name, Principal principal, Model model) {
				
				User user = userDao.getUserInfoByUserMail(principal.getName());
				System.out.println(status_name);
				//List<TimeSheetDetail_List> list_timeSheetDetail_Lists = new ArrayList<TimeSheetDetail_List>();
				
				//check role: if role = 1 or 2
				//if role = 1 => list all project
				if(user.getRole_id() == 1){
					
					List<ProjectInfo> listAllProjectInfos = projectDao.getAllProject();
					
					
					List<TimeSheetDetail> listAllTimeSheetDetails = timeSheetDao.getAllTimeSheet(project_id, member_project_id, process_id, status_name);
					List<MemberProject> list_PIC = memberProjectDao.getAllMember();
					//List<Process> list_Process = processDao.getAll();
					
					//System.out.println(listAllTimeSheetDetails.get(2).getMemberProject().getMember_project_id());
					
					model.addAttribute("listTimeSheetDetails", listAllTimeSheetDetails);
					model.addAttribute("listProjects", listAllProjectInfos);
					model.addAttribute("list_PIC", list_PIC);
					//model.addAttribute("list_Process", list_Process);
					
					
					
					
					//List<MemberProject> lisMemberProjects = memberProjectDao.getAllMember();
					//List<TimeSheetDetail> lisTimeSheetDetailsOfAllMember = timeSheetDao.getAllTimeSheet();
					//List<TimeSheetDetail> lisTimeSheetDetailsOfOneTs_Id 
					/*List<TimeSheet_Info> liTimeSheet_Infos = timeSheetDao.getAllTimeSheetInfor();
					
					for(int i=0;i<liTimeSheet_Infos.size();i++){
						MemberProject memberProject = memberProjectDao.getMemberProjectByMem_id(liTimeSheet_Infos.get(i).getMember_project_id());
						List<TimeSheetDetail> lisTimeSheetDetailsOfOneTs_Id = timeSheetDao.getListTimeSheetByTimeSheetId(liTimeSheet_Infos.get(i).getTs_id());
						TimeSheetDetail_List timeSheetDetail_List= new TimeSheetDetail_List();
						timeSheetDetail_List.setMemberProject(memberProject);
						timeSheetDetail_List.setTimeSheetDetail(lisTimeSheetDetailsOfOneTs_Id);
						list_timeSheetDetail_Lists.add(timeSheetDetail_List);
					}*/
					//System.out.println("size = "+list_timeSheetDetail_Lists.get(2).getTimeSheetDetail().size());
					//model.addAttribute("list_timeSheetDetail_Lists", list_timeSheetDetail_Lists);
					return new ModelAndView("timeSheetList");
					
					//if role = 1 => get all member_project => return all member_projects
					//get all timesheet_infors => have member_project_name
					//for ts_id => get member_project_name
					
				}
				else if(user.getRole_id() == 2){
					
					
					//if role = 2 => get list member_project of user 
					List<MemberProject> listMemberProjectsAssigned = memberProjectDao.getListMemberProjectsByCurrentUserAssigned(user.getUser_id());
					List<TimeSheetDetail> listTimeSheetDetails = new ArrayList<TimeSheetDetail>();
					List<MemberProject> lisMemberProjectsAssignedNotPM = new ArrayList<MemberProject>();
					List<MemberProject> lisMemberProjectsAssignedIsPM = new ArrayList<MemberProject>();
					List<MemberProject> list_PIC = new ArrayList<MemberProject>();
					
					
					
					
							for(int i=0;i<listMemberProjectsAssigned.size();i++){
								//lisMemberProjectsAssignedIsPM.add(listMemberProjects.get(i));
								List<TimeSheet_Info> listTimeSheet_Infos = timeSheetDao.getListTimeSheet_InfosByProjectId(listMemberProjectsAssigned.get(i).getProject_id());
								for(int j=0;j<listTimeSheet_Infos.size();j++){
									listTimeSheetDetails.addAll(timeSheetDao.getListTimeSheetByTimeSheetId(listTimeSheet_Infos.get(j).getTs_id()));
								}
								
								list_PIC.addAll(memberProjectDao.getMemberProjectByProjectId1(listMemberProjectsAssigned.get(i).getProject_id()));
								
							}
							
							/*
							 * Loc user_id trung nhau trong list_PIC
							 * 
							 */
					
							
							/*for(int i=0;i<listTimeSheet_Infos.size();i++){
								listTimeSheetDetails.addAll(timeSheetDao.getListTimeSheetByTimeSheetId(listTimeSheet_Infos.get(i).getTs_id()));
							}*/
						
							if(project_id!=0 ||member_project_id!=0||process_id!=0||status_name!="") {
								List<TimeSheetDetail> listTimeSheetDetails1=timeSheetDao.getTimeSheetDetailsByOneOrAllConditionsOfPM(project_id, member_project_id, process_id, status_name);
								List<ProjectInfo> listProjectInfos = projectDao.getListPRojectOfUserAccessed(user.getUser_id());
								
								//loc ra list timesheets trong project PM duoc assign
								for(int i=0;i<listTimeSheetDetails1.size();i++){
									//if(listTimeSheetDetails1)
									boolean flag = false;
									for(int j=0;j<listTimeSheetDetails.size();j++){
										if(listTimeSheetDetails1.get(i).getDetail_timesheet_id()==listTimeSheetDetails.get(j).getDetail_timesheet_id()){
											flag=true;
										}
										
									}
									if(flag==false){
										listTimeSheetDetails1.remove(i);
									}
									
								}
								
								
								model.addAttribute("listTimeSheetDetails", listTimeSheetDetails1);
								model.addAttribute("listProjects", listProjectInfos);
								model.addAttribute("list_PIC", list_PIC);
								//model.addAttribute("list_Process", list_Process);
								/*model.addAttribute("listProjects", listAllProjectInfos);
								model.addAttribute("list_PIC", list_PIC);
								model.addAttribute("list_Process", list_Process);*/
								//get list detail_timesheets by list timesheet_ids: have member_project_name
								return new ModelAndView("timeSheetList");
							}
							else{
								List<ProjectInfo> listProjectInfos = projectDao.getListPRojectOfUserAccessed(user.getUser_id());
								model.addAttribute("listTimeSheetDetails", listTimeSheetDetails);
								model.addAttribute("listProjects", listProjectInfos);
								model.addAttribute("list_PIC", list_PIC);
								//model.addAttribute("list_Process", list_Process);
								/*model.addAttribute("listProjects", listAllProjectInfos);
								model.addAttribute("list_PIC", list_PIC);
								model.addAttribute("list_Process", list_Process);*/
								//get list detail_timesheets by list timesheet_ids: have member_project_name
								return new ModelAndView("timeSheetList");
							}
					
					
				}
				else{
					String message = "Access denied for "+principal.getName()+"!";
					model.addAttribute("message", message);
					return new ModelAndView("403Page");
				}
				
				
				
				
				
				
				
				//List<TimeSheetDetail> list = timeSheetDao.getAllTimeSheet();
				//return new ModelAndView("timeSheetList", "list", list);
			}
			
			@RequestMapping(value = "/actionUpdateStatusTypeOfListTimesheets", method = RequestMethod.POST)
			public @ResponseBody ArrayList<TimeSheetDetail> save(@RequestBody  final ArrayList<TimeSheetDetail> list_TimeSheetDetails) {
				//System.out.println("prj_id = "+id);
				
				timeSheetDao.updateStatusOfListTimeSheetDetails(list_TimeSheetDetails);
			
				return list_TimeSheetDetails;
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

			/*@ModelAttribute("Tasks")
			public List<TaskInfo> getTaskInfos(@PathVariable int id, Model model) {
				ProjectInfo projectInfo = projectDao.getProjectById(id);

				// purpose: get project's name
				model.addAttribute("project_Infor", projectInfo);
				List<TaskInfo> list = TaskInfoDao.getTaskInfo_By_Status_Open_And_OnGoing(id);
				return list;
			}*/
			@ModelAttribute("picL")
			public List<MemberProject> getPICL() {
				List<MemberProject> list = memberProjectDao.getMember();
				return list;
			}
}
