package com.tinhvan.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.tinhvan.dao.PermissionDao;
import com.tinhvan.dao.ProjectDao;
import com.tinhvan.dao.RoleDao;
import com.tinhvan.dao.UserDao;
import com.tinhvan.model.ProjectInfo;
import com.tinhvan.model.Role;
import com.tinhvan.model.User;
import com.tinhvan.validator.UserValidator;

@Controller
public class UserSystemController {
	@Autowired
	UserDao userDao;
	@Autowired
	RoleDao roleDao;
	@Autowired
	ProjectDao projectDao;
	@Autowired
	private UserValidator userValidator;
	@Autowired
	PermissionDao per;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(userValidator);
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

	// mapping add userInfo
	@RequestMapping(value = "/user")
	public ModelAndView userRegister(Locale locale, Model model) {
		Boolean checker = per.checker("user_reg");
		if(checker==true) {
		model.addAttribute("user", new User());
		return new ModelAndView("userRegister");
		}
		else {
			return new ModelAndView("403Page");
		}
	}

	@RequestMapping("/actionCreateUser")
	public ModelAndView addUser(@RequestParam(value = "fileName", required = false) MultipartFile file, Model model,
			@ModelAttribute(value = "userInfo") @Validated User users, BindingResult result) {
		
	
		
		Boolean check1 = false;
		List<List<String>> listcheck=new ArrayList();
		
		try {
			InputStream is = file.getInputStream();
			Reader reader = new BufferedReader(new InputStreamReader(is));
			CsvToBean csvToBean = new CsvToBeanBuilder(reader).withType(User.class).withIgnoreLeadingWhiteSpace(true)
					.build();
			
			List<User> Usercsvs = csvToBean.parse();
			
			for (int i = 0; i < Usercsvs.size(); i++) {
				List<String> datacheck=new ArrayList();
				boolean check = true;
				List<User> gettAllUser = userDao.gettAllUser();
				for (User user2 : gettAllUser) {
					if (Usercsvs.get(i).getUser_mail().equals(user2.getUser_mail())) {
						check = false;
						datacheck.add("Email " + Usercsvs.get(i).getUser_mail() + " already exist");
						break;
					}	
				}
				if (Usercsvs.get(i).getUser_fullName() == null || Usercsvs.get(i).getUser_fullName() == "") {
					check = false;
					datacheck.add("UserName of account " + Usercsvs.get(i).getUser_mail() + " not be empty");
				}
				String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
				if (Usercsvs.get(i).getUser_mail() == null || Usercsvs.get(i).getUser_mail() == "" || !Usercsvs.get(i).getUser_mail().toString().matches(ePattern)==true) {
					check = false;
					datacheck.add("Email of account " + Usercsvs.get(i).getUser_mail() + " is invalid");
				}
				if (Usercsvs.get(i).getRole_id() < 2 || Usercsvs.get(i).getRole_id() > 6) {
					check = false;
					datacheck.add("Role of account " + Usercsvs.get(i).getUser_mail() + " is invalid");
				}
				String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}";
				if (Usercsvs.get(i).getUser_passWord() == null || Usercsvs.get(i).getUser_passWord() == "" || !Usercsvs.get(i).getUser_passWord().toString().matches(pattern)==true) {
					check = false;
					datacheck.add("PassWord of account " + Usercsvs.get(i).getUser_mail() + " is invalid");
				}
				
				listcheck.add(datacheck);
				
				if (check == true) {
					User u = new User();
					u.setRole_id(Usercsvs.get(i).getRole_id());
					u.setUser_fullName(Usercsvs.get(i).getUser_fullName());
					u.setUser_mail(Usercsvs.get(i).getUser_mail());
					u.setUser_passWord(Usercsvs.get(i).getUser_passWord());
					userDao.addUserSystem(u);
					check1 = true;
				}
					
				

				reader.close();
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		if (!result.hasErrors()) {
			userDao.addUserSystem(users);
			check1 = true;

		}
		
		model.addAttribute("listcheck",listcheck);
		model.addAttribute("check", check1);
		return new ModelAndView("userRegister");
	}

	@ModelAttribute("projectRole")
	public List<Role> getAllRole() {
		List<Role> list = roleDao.getAllRole();
		return list;
	}
	
	

	/*
	 * @RequestMapping(method=RequestMethod.GET) public String
	 * uploadPage(@RequestParam("fileName") MultipartFile file,ModelMap model)throws
	 * Exception{ if(!file.isEmpty()){ String name=file.getOriginalFilename(); long
	 * size=file.getSize();
	 * 
	 * File f=new File("c:/temp/"+name); if(!f.exists()){ f.getParentFile().mkdir();
	 * } model.addAttribute("name",name); model.addAttribute("size",size);
	 * 
	 * }else { model.addAttribute("fail","bi loi"); }
	 * 
	 * return "userRegister"; }
	 */
}
