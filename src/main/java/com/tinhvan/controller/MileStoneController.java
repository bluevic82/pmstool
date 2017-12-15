package com.tinhvan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.tinhvan.dao.MileStoneDao;

/*
 * @purpose: Task Controller using Setting Milestone
 * @author: NguyenManh
 * @date: 2017/12/15
 * 
 * **/

@Controller
public class MileStoneController {
	
	@Autowired
	MileStoneDao mileStoneDao;
	
	

}
