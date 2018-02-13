package com.tinhvan.dao;

import java.util.List;

import com.tinhvan.model.QuestionAnwer;

public interface QuestionAnswerDao {
	
	public List<QuestionAnwer> getAllQA(int projectName, int status, int pic);
	
	public void registerQA(QuestionAnwer questionAnwer);
	
	public void updateQA(QuestionAnwer questionAnwer);
	
	public QuestionAnwer getQAById(int q_a_id);
}
