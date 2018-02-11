package com.tinhvan.dao;

import java.util.List;

import com.tinhvan.model.Type;


public interface TypeDao {
	public List<Type> getAllType();
	public List<Type> getTypeOfTask();
	public List<Type> getTypeOfBug();
	public List<Type> getAllTypeOfTimeSheet();
}