package com.tinhvan.dao;

import java.util.List;

import com.tinhvan.model.QuestionAnwer;

public interface QuestionAnswerDao {
	
	List<QuestionAnwer> getAllQA();
	
	public QuestionAnwer getQAById(int id);
}
