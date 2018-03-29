package com.tinhvan.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.management.MalformedObjectNameException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
import com.tinhvan.model.User;

/*
 * @Purpose: TimeSheet Controller using register/update TimeSheet
 *  Using method Attribute set data for Project Name, Member, Process, Status, Type
 * @Author: NguyenManh
 * @Date: 2017/12/27
 * 
 * **/

@Controller
public class TimeSheetRegisterController {

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
	
	/*HttpServletRequest realRequest;*/
	// get User infor of current user login for menu user infor
			@ModelAttribute("UserInformation")
			public User getUserCurrentLogin(){
				final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
/*				System.out.println(realRequest.getUserPrincipal().getName());*/
				return  userDao.getUserInfoByUserMail(auth.getName());
				
			}
	// get list project for menu
		@ModelAttribute("list_Project_For_menu")
		public List<ProjectInfo> getListProject(Principal principal) {
			final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			//List<ProjectInfo> list_Project_For_Menu = new ArrayList<ProjectInfo>();
			//User user = get_User_current_loged(principal);
			User user = userDao.getUserInfoByUserMail(auth.getName());
			
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
		

	// Mapping view Screen Register TimeSheet
	@RequestMapping(value = { "/{id}/registerTimeSheet" }, method = RequestMethod.GET)
	public ModelAndView regisTimeSheet(@PathVariable(value = "id") int id,
			Model model, Principal principal) {
		ProjectInfo projectInfo = projectDao.getProjectById(id);
		//System.out.println("user_mail = "+principal.getName());
		
		//check role of user
		if(memberProjectDao.getMemberProjectByProject_Id_And_UserCurrentLogged(id, getUserCurrentLogin().getUser_id())==null){
			String message = "Access denied for "+principal.getName()+"!";
			model.addAttribute("message", message);
			return new ModelAndView("403Page");
		}
		else{
			//get userInfo by user_mail
			//get_User_current_loged(principal);
			/*List<TimeSheetDetail> list_TimeSheetOfOneProject = timeSheetDao
					.getListTimeSheetOfOneProject(id, get_User_current_loged(principal).getUser_id());*/	
			
			List<TimeSheetDetail> list_TimeSheetOfOneProject = timeSheetDao.getListTimeSheetDetailsOfOneProjectOfCurrentUserHaveStatusAreRequestAndReject(id, getUserCurrentLogin ().getUser_id());

			model.addAttribute("list_TimeSheetOfOneProject",
					list_TimeSheetOfOneProject);
			model.addAttribute("projectInfo", projectInfo);
			model.addAttribute("title", "Success");
			model.addAttribute("message", "RegisterTimeSheet");

			return new ModelAndView("timesheetRegister");
		}
		
	}
	
	@RequestMapping(value = "/{id}/actionSaveTimeSheet", method = RequestMethod.POST)
	public @ResponseBody ArrayList<TimeSheetDetail> save(@PathVariable int id, @RequestBody  final ArrayList<TimeSheetDetail> list_TimeSheetDetails, Principal principal) {
		//System.out.println("prj_id = "+id);
		ArrayList<TimeSheetDetail> list_TimeSheetDetails_To_Insert = new ArrayList<TimeSheetDetail>();
		for(int i=0;i<list_TimeSheetDetails.size();i++){
			//System.out.println("timesheet_id = "+list_TimeSheetDetails.get(i).getDetail_timesheet_id());//OK
			if(list_TimeSheetDetails.get(i).getDetail_timesheet_id()==0){
				list_TimeSheetDetails_To_Insert.add(list_TimeSheetDetails.get(i)); 
			}
		}
		//System.out.println("prj_id = "+id);//OK
		if(list_TimeSheetDetails_To_Insert.size()!=0){
			//System.out.println("getUser_id = "+get_User_current_loged(principal).getUser_id());//OK
			timeSheetDao.createListTimeSheet(list_TimeSheetDetails_To_Insert);
			
		}
		
		timeSheetDao.updateListTimeSheetToDB(list_TimeSheetDetails);
	
		return list_TimeSheetDetails;
	}
	
	
	@RequestMapping(value = "/{id}/actionDeleteListTimeSheet", method = RequestMethod.POST)
	public @ResponseBody ArrayList<TimeSheetDetail> deleteListTimeSheet(@RequestBody  final ArrayList<TimeSheetDetail> list_TimeSheetDetails_Delete) {
		
		timeSheetDao.deleteListTimeSheet(list_TimeSheetDetails_Delete);
	
		return list_TimeSheetDetails_Delete;
	}

	/*
	 * @Purpose: Methods Attributes
	 */

	
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

	@ModelAttribute("Tasks")
	public List<TaskInfo> getTaskInfos(@PathVariable int id, Model model) {
		ProjectInfo projectInfo = projectDao.getProjectById(id);

		// purpose: get project's name
		model.addAttribute("project_Infor", projectInfo);
		List<TaskInfo> list = TaskInfoDao.getTaskInfo_By_Status_Open_And_OnGoing(id);
		return list;
	}
	@ModelAttribute("picL")
	public List<MemberProject> getPICL() {
		List<MemberProject> list = memberProjectDao.getMember();
		return list;
	}
}
