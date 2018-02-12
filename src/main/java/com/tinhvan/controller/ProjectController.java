package com.tinhvan.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tinhvan.dao.CategoryDao;
import com.tinhvan.dao.EffortDao;
import com.tinhvan.dao.MemberProjectDao;
import com.tinhvan.dao.PermissionDao;
import com.tinhvan.dao.ProjectDao;
import com.tinhvan.dao.ScopeDao;
import com.tinhvan.dao.StatusDao;
import com.tinhvan.dao.TaskInfoDao;
import com.tinhvan.dao.TypeDao;
import com.tinhvan.model.MemberProject;
import com.tinhvan.model.Permission;
import com.tinhvan.model.ProjectAndScope;
import com.tinhvan.model.ProjectInfo;
import com.tinhvan.model.Scope;
import com.tinhvan.model.Status;
import com.tinhvan.model.TaskInfo;
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
	@Autowired
	TaskInfoDao taskInfoDao;
	@Autowired
	PermissionDao per;


	// get list project for menu
				@ModelAttribute("list_Project_For_menu")
				public List<ProjectInfo> getListProject() {
					List<ProjectInfo> list_Project_For_Menu = projectDao.getAllProject();
					return list_Project_For_Menu;
				}
	
	// mapping add project
	@RequestMapping(value = "/addProject")
	public ModelAndView addProject() {

		return new ModelAndView("addProject");
	}

	// mapping Create Project
	@PostMapping(value = "/actionAdd")
	public @ResponseBody ProjectAndScope actionAddProject(@RequestBody ProjectAndScope ps, HttpServletRequest request) {

		System.out.println(ps.getProject_name());
		System.out.println(ps.getType_id());
		System.out.println(ps.getProject_from());
		System.out.println(ps.getProject_to());
		System.out.println(ps.getProject_technical());
		System.out.println(ps.getProject_charge_cost());
		System.out.println(ps.getScope_id());
		System.out.println(ps.getStatus_id());
		System.out.println(ps.getProject_description());
		
		final List<Integer> sp = new ArrayList<>();
		sp.addAll(ps.getScope_id());
		
		System.out.println(sp);
		
		  ProjectInfo p = new ProjectInfo(); 
		  p.setProject_name(ps.getProject_name());
		  p.setType_id(ps.getType_id()); p.setProject_from(ps.getProject_from());
		  p.setProject_to(ps.getProject_to());
		  p.setProject_technical(ps.getProject_technical());
		  p.setProject_charge_cost(ps.getProject_charge_cost());
		  p.setStatus_id(ps.getStatus_id());
		  p.setProject_description(ps.getProject_description());
		  
		projectDao.addProject(p);
		int id = projectDao.findProjectIdMax();
		projectDao.addScopeProject(id, sp);
		
		return ps;
	}

	// mapping getdata project_id for update Project
	@RequestMapping(value = "/editproject/{id}")
	public ModelAndView editProject(@PathVariable int id, ModelMap model) {
		ProjectInfo projectInfo = projectDao.getProjectById(id);
		//model.put("command", projectDao.getProjectById(id));
		//return new ModelAndView("updateProject", "command", projectInfo);
		return null;
	}
	
	//mapping getdata project_it for view detail
		@RequestMapping(value = "/detalproject/{id}")
		public ModelAndView detailProject(@PathVariable int id, ModelMap model,HttpServletRequest request) {
				Boolean checker = per.checker("pro_detail");
				if(checker==true) {
					ProjectInfo projectInfo = projectDao.getProjectById1(id);
					System.out.println(projectInfo.getPm()+"hahahah");
					int idT=projectInfo.getType_id();
					int project_id = projectInfo.getProject_id();
					List<MemberProject> pm = projectDao.getPm(project_id);
					model.put("pm", pm);
//					model.put("status",statusDao.getStatusById(idT));
					List<TaskInfo> taskByIdPro = taskInfoDao.getTaskByIdPro(project_id);
					model.put("taskid", taskByIdPro);
					List<Integer> listPercent= new ArrayList<Integer>();
					
					List<Map<Integer, String>> perr= new ArrayList<Map<Integer, String>>() ;
					for (TaskInfo taskInfo : taskByIdPro) {
						 String from = taskInfo.getTask_from();
						 String to = taskInfo.getTask_to();
							Date parse = null; 
					        Date parse1 = null;
					        Date parse2 = null;
					        try {
					        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					        Date date = new Date(); 
					        String format = sdf.format(date);
									parse = sdf.parse(from);
									 parse1 = sdf.parse(to);
									 parse2 = sdf.parse(format);
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
					        Calendar c1 = Calendar.getInstance();
					        Calendar c2 = Calendar.getInstance();
					        Calendar c3 = Calendar.getInstance();
					        c1.setTime(parse);
					        c2.setTime(parse1);
					        c3.setTime(parse2);
					         long noDay = (c2.getTime().getTime() - c1.getTime().getTime())
					                / (24 * 3600 * 1000);
					         long noDay1 = (c3.getTime().getTime() - c1.getTime().getTime())
						                / (24 * 3600 * 1000);	
					         if(noDay1>noDay) {
					        	 noDay1=noDay;
					         }
					         if(noDay>0 && noDay1>0) {
					        	 Float target=(float) noDay;
						         Float current=(float) noDay1;
						         Float percent=  current/ target*100f;      
						       
						         listPercent.add(Math.round(percent));
						         
						         float tinh=taskInfo.getTask_done()-percent;
					        	 if(tinh>0) {
					        		 Map<Integer, String> map = new HashMap<Integer, String>();
					        		 map.put(Math.round(tinh), "green");
					        		 perr.add(map);
					        		 
					        	 }
					        	 if(tinh<0) {
					        		 Map<Integer, String> map = new HashMap<Integer, String>();
					        		
					        		 float tinh1=percent-taskInfo.getTask_done();
					        		 
					        		 map.put(Math.round(tinh1), "red");
					        		
					        		 perr.add(map);
					        	 }
					        	 if(tinh==0) {
//						        	 listPercent.add(Math.round(taskInfo.getTask_done()));
						        	 Map<Integer, String> map = new HashMap<Integer, String>();
					        		 map.put(Math.round(taskInfo.getTask_done()), "khong");
					        		 perr.add(map);
								}
					         }else {

//					        	 listPercent.add(Math.round(taskInfo.getTask_done()));
					        	 Map<Integer, String> map = new HashMap<Integer, String>();
				        		 map.put(Math.round(taskInfo.getTask_done()), "khong");
				        		 perr.add(map);
							} 
					        
					        
					}
					
					model.put("per", perr);
					return new ModelAndView("detailProject","command",projectInfo);
				}else {
					System.out.println("goodby");
//					 String referer = request.getHeader("Referer");
//					    return  new ModelAndView("redirect:"+ referer);
					return new ModelAndView("403Page");
				}
			
			
			
			
		}

	// mapping detail project
	@RequestMapping(value = "/detailProject/{id}")
	public String detailProject() {

		return "detailProject";
	}

	// get type project
	@ModelAttribute("projectTypes")
	public List<Type> getTypes() {
		List<Type> list = typeDao.getAllType();
		return list;
	}

	// get Status project
	@ModelAttribute("projectStatus")
	public List<Status> getStatus() {
		List<Status> list = statusDao.getAllStatus();
		return list;
	}

	// get Scope project
	@ModelAttribute("projectScope")
	public List<Scope> getAllScope() {
		List<Scope> list = scopeDao.getAllScope();
		return list;
	}

}
