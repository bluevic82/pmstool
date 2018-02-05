package com.tinhvan.dao;

import java.util.List;

import com.tinhvan.model.TaskInfo;

public interface TaskInfoDao {
	
	public List<TaskInfo> getAllTask();
	
	public void addTask(TaskInfo taskInfo);
	
	public void updateTask(TaskInfo taskInfo);
	
	public TaskInfo getTaskById(int id);
	
	public List<TaskInfo> getTaskByIdPro(int id);
}
