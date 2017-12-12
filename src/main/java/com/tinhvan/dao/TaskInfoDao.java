package com.tinhvan.dao;

import java.util.List;

import com.tinhvan.model.TaskInfo;

public interface TaskInfoDao {
	
	public List<TaskInfo> listAllTask();
	
	public void addTask(TaskInfo taskInfo);
	
	public void updateTask(TaskInfo taskInfo);
	
}
