package com.tinhvan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.tinhvan.dao.QuestionAnswerDao;

@Controller
public class QAController {

	@Autowired
	QuestionAnswerDao answerDao;
}
