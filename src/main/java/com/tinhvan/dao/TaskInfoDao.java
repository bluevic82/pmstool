package com.tinhvan.dao;

import java.util.List;

import com.tinhvan.model.TaskInfo;

public interface TaskInfoDao {
	
	public List<TaskInfo> detailTask();
	
	public void addTask(TaskInfo taskInfo);
	
	public int updateTask(TaskInfo taskInfo);
	
	public TaskInfo getTaskById(int id);
}
