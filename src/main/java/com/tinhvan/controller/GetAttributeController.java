package com.tinhvan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.tinhvan.dao.StatusDao;
import com.tinhvan.dao.TypeDao;
import com.tinhvan.model.Status;
import com.tinhvan.model.Type;




@Controller
public class GetAttributeController {
	@Autowired
	TypeDao typeDao;
	@Autowired
	StatusDao statusDao;
	
	   @ModelAttribute("projectTypes")
	   public List<Type> getTypes() {
		   List<Type> list= typeDao.getAllType();
	        return list;
	    }
	   @ModelAttribute("projectStatus")
	   public List<Status> geStatus(){
		   List<Status> list = statusDao.getAllStatus();
		   return list;
	   }
}
