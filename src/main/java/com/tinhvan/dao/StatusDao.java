package com.tinhvan.dao;

import java.util.List;

import com.tinhvan.model.Status;

public interface StatusDao {
	
	public List<Status> getAllStatus();
	
	public List<Status> getStatusOfTask();
	
	public List<Status> getStatusOfBug();
	
	public List<Status> getStatusOfQA();
	
	public List<Status> getStatusOfTS();
}