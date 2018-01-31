package com.tinhvan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tinhvan.dao.MemberProjectDao;
import com.tinhvan.dao.PreDefinedTaskDao;
import com.tinhvan.dao.ProcessDao;
import com.tinhvan.dao.ProjectDao;
import com.tinhvan.dao.StatusDao;
import com.tinhvan.dao.TimeSheetDao;
import com.tinhvan.dao.TypeDao;
import com.tinhvan.model.MemberProject;
import com.tinhvan.model.Process;
import com.tinhvan.model.ProjectInfo;
import com.tinhvan.model.Status;
import com.tinhvan.model.TimeSheetDetail;


/*
 * @Purpose: TimeSheet Controller using register/update TimeSheet
 *  Using method Attribute set data for Project Name, Member, Process, Status, Type
 * @Author: NguyenManh
 * @Date: 2017/12/27
 * 
 * **/

@Controller
public class TimeSheetController {
	
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
	
	// get list project for menu
				@ModelAttribute("list_Project_For_menu")
				public List<ProjectInfo> getListProject() {
					List<ProjectInfo> list_Project_For_Menu = projectDao.getAllProject();
					return list_Project_For_Menu;
				}
	
	//Mapping view list TimeSheeet
	@RequestMapping("/timeSheetList")
	public ModelAndView listTimeSheet() {
		List<TimeSheetDetail> list = timeSheetDao.getAllTimeSheet();
		return new ModelAndView("timeSheetList", "list", list);
	}
	
	//Mapping view Screen Register TimeSheet
	@RequestMapping(value = {"/{id}/registerTimeSheet"}, method = RequestMethod.GET)
	public ModelAndView regisTimeSheet( @PathVariable (value = "id")  int id, Model model) {
		ProjectInfo projectInfo = projectDao.getProjectById(id);
		List<TimeSheetDetail> list_TimeSheetOfOneProject = timeSheetDao.getListTimeSheetOfOneProject(id);
		model.addAttribute("list_TimeSheetOfOneProject", list_TimeSheetOfOneProject);
		model.addAttribute("projectInfo", projectInfo);
		model.addAttribute("title", "Success");
		model.addAttribute("message", "RegisterTimeSheet");

		
		
		return new ModelAndView("timesheetRegister");
	}
	
	/*
	 *@Purpose: Methods Attributes
	 */
	
	//method get Name of Project
	@ModelAttribute("projectName")
	public List<ProjectInfo> getAllProject(){
		List<ProjectInfo> list = projectDao.getAllProject();
		return list;
	}
	
	//method get all member project Timesheet of project
	@ModelAttribute("pic")
	public List<MemberProject> getPIC() {
		List<MemberProject> list = memberProjectDao.getAllMember();
		return list;
	}
	
	//method get process of timesheet
	@ModelAttribute("process")
	public List<Process> getProcess(){
		List<Process> list = processDao.getAll();
		return list;
	}
	
	//method get status of timesheet
	@ModelAttribute("timeSheetStatus")
	public List<Status> getStatusOfTimeSheet(){
		List<Status> list = statusDao.getStatusOfTS();
		return list;
	}
	
}
