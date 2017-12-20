package com.tinhvan.dao;

import java.util.List;

import com.tinhvan.model.QuestionAnwer;

public interface QuestionAnswerDao {
	
	public List<QuestionAnwer> getAllQA();
	
	public void saveQA(QuestionAnwer questionAnwer);
	
	public QuestionAnwer getQAById(int q_a_id);
}
