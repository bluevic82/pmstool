package com.tinhvan.controller;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tinhvan.dao.CategoryDao;
import com.tinhvan.dao.MemberProjectDao;
import com.tinhvan.dao.PermissionDao;
import com.tinhvan.dao.ProjectDao;
import com.tinhvan.dao.QuestionAnswerDao;
import com.tinhvan.dao.ScopeDao;
import com.tinhvan.dao.StatusDao;
import com.tinhvan.dao.TaskInfoDao;
import com.tinhvan.dao.TypeDao;
import com.tinhvan.dao.UserDao;
import com.tinhvan.model.MemberProject;
import com.tinhvan.model.Permission;
import com.tinhvan.model.ProjectInfo;
import com.tinhvan.model.TaskInfo;
import com.tinhvan.model.User;


/**
 * @Purpose: Controller
 * 
 **/

@Controller
public class LoginController {

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
	TaskInfoDao taskInfoDao;
	@Autowired
	QuestionAnswerDao qaDao;
	@Autowired
	ScopeDao scopeDao;
	@Autowired
	PermissionDao per;
	@Autowired UserDao userDao;


	//
	
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
		
		//return list_Project_For_Menu;
		
	}
	
	
	
//	@RequestMapping(value="/updatePer",method = RequestMethod.POST)
	@RequestMapping("/permissionManager")
		public String bomaytroll(Model model){
			List<Permission> allPer = per.getAllPer();
			model.addAttribute("listPer", allPer);
			
			return "permissionManager";
		}
		
		
		
		
		@RequestMapping(value="/updatePer",method = RequestMethod.POST)
		public @ResponseBody void savePer(@RequestBody List<Permission> perrr, HttpServletRequest request){
			
			
			for (Permission permission : perrr) {
				per.updatePer(permission);
			}
				
			
		}
		
	
	@RequestMapping(value = { "/", "/welcome" })
	public ModelAndView welcomePage(Model model,@RequestParam(value="name",defaultValue="") String sname,@RequestParam(value="pm",defaultValue="") String spm,@RequestParam(value="from",defaultValue="") String sfrom,@RequestParam(value="to",defaultValue="") String sto) {
		model.addAttribute("title", "OverView");
		model.addAttribute("message", "OverView");
		Boolean checker = per.checker("over_view");
		if(checker==true) {
		List<ProjectInfo> list = projectDao.getAllProject1(sname,spm,sfrom,sto);
		List<Map<Integer, String>> pm= new ArrayList<Map<Integer, String>>() ;
		List<Integer> tongPer=new ArrayList<Integer>();
		List<Map<Integer, String>> thuaTHieu= new ArrayList<Map<Integer, String>>() ;
		for (int i = 0; i < list.size(); i++) {
			int idP=list.get(i).getProject_id();
			List<MemberProject> mp = projectDao.getPm(idP);
			Map<Integer, String> map = new HashMap<Integer, String>();
			for (int j = 0; j < mp.size(); j++) {
				
				 map.put(j, mp.get(j).getMember_project_name());;
				 
				
			}
			pm.add(map);
			
			
			

			
			
			List<TaskInfo>	taskByIdPro = taskInfoDao.getTaskByIdPro(idP);
			
			float done=0;
			float percent=0;
			float perTask=0;
			float currentask=0;
			float tong=0;
			float cham=0;
			float nhanh=0;
			
			for (TaskInfo taskInfo : taskByIdPro) {
				 String from = taskInfo.getTask_from();
				 String to = taskInfo.getTask_to();
				 int taskDone=taskInfo.getTask_done();
				
				
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
			         float convert = (int) noDay;
			         float convert1= (int) noDay1;
			         
			         
//			         if(taskDone==100) {
//			        	 done+=convert;
//			         }
			         
			         
			        
			        	 float thuaThieu=(float)noDay/100f*(float)taskDone;
			        	 done+=thuaThieu;
			        	 
			        	 
			        	 
			        	if((float)noDay1-thuaThieu>0) {
			        		float a=noDay1-thuaThieu;
			        		cham+=a;
			        		
			        	}
			        	if((float)noDay1-thuaThieu<0) {
			        		float a=thuaThieu-noDay1;
			        		nhanh+=a;
			        		
			        	}
			        	if((float)noDay1-thuaThieu==0) {
			        		float a=noDay1-thuaThieu;
			        		
			        		
			        	}
			        	 
				         percent+= convert1;   
				         
			         
			         tong+=convert;
			         
			         
			         
			}
			
			perTask+=(float)done/(float)tong*100f;
	
			if(nhanh>cham) {
				Map<Integer, String> map1 = new HashMap<Integer, String>();
				nhanh=nhanh-cham;
		
				map1.put(Math.round((float)nhanh/(float)tong*100f), "green");
				thuaTHieu.add(map1);
				tongPer.add(Math.round(perTask));
				
			}
			if(nhanh<cham) {
				Map<Integer, String> map1 = new HashMap<Integer, String>();
				cham=cham-nhanh;
				
				map1.put(Math.round((float)cham/(float)tong*100f), "red");
				thuaTHieu.add(map1);
				tongPer.add(Math.round(perTask));
				
			}
			if(nhanh==cham) {
				Map<Integer, String> map1 = new HashMap<Integer, String>();
		
				map1.put(Math.round(perTask), "khong");
				thuaTHieu.add(map1);
				tongPer.add(Math.round(perTask));
				
			}
			
			
		
			
			
			
				
		
			
			
			
			
			
			
			
			
			
			
			
			
			}
		model.addAttribute("thuaTHieu",thuaTHieu);
		model.addAttribute("tongPer",tongPer);
		model.addAttribute("pm",pm);
		
		return new ModelAndView("welcomePage","listP",list);
		}else {
			System.out.println("goodby");
//			 String referer = request.getHeader("Referer");
//			    return  new ModelAndView("redirect:"+ referer);
			return new ModelAndView("403Page");
		}
	}
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminPage(Model model) {
		return "adminPage";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(Model model) {
		return "loginPage";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutDo(HttpServletRequest request,HttpServletResponse response){
		HttpSession session= request.getSession(false);
		    SecurityContextHolder.clearContext();
		         session= request.getSession(false);
		        if(session != null) {
		            session.invalidate();
		        }
		        for(Cookie cookie : request.getCookies()) {
		            cookie.setMaxAge(0);
		        }
		    return "logoutSuccessfulPage";
	}

	@RequestMapping(value = "/userInfo", method = RequestMethod.GET)
	public String userInfo(Model model, Principal principal) {

		// Sau khi user login thanh cong se co principal
		String userName = principal.getName();

		/* test get user */
		System.out.println("User Name: " + userName);

		return "userInfoPage";
	}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accessDenied(Model model, Principal principal) {

		if (principal != null) {
			model.addAttribute("message",
					"Hi " + principal.getName() + "<br> You do not have permission to access this page!");
		} else {
			model.addAttribute("msg", "You do not have permission to access this page!");
		}
		return "403Page";
	}

//	@RequestMapping(value = "/answerAndQ")
//	public String qandA() {
//		return "answerAndQ";
//	}
//
//	@ModelAttribute("qaStatus")
//	public List<Status> getStatusOfQA() {
//		List<Status> list = statusDao.getStatusOfQA();
//		return list;
//	}
//
//	@ModelAttribute("allQA")
//	public List<QuestionAnwer> getQA() {
//		List<QuestionAnwer> list = qaDao.getAllQA();
//		return list;
//	}

}
