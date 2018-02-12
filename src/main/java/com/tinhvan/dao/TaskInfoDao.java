package com.tinhvan.dao;

import java.util.List;

import com.tinhvan.model.TaskInfo;

public interface TaskInfoDao {
	
	public List<TaskInfo> getAllTask(int pn,int tp,int st, int pic,String pri);
	
	public void addTask(TaskInfo taskInfo);
	
	public void updateTask(TaskInfo taskInfo);
	
	public TaskInfo getTaskById(int id);
	
	public List<TaskInfo> getTaskByIdPro(int id);
	public List<TaskInfo> getTaskInfo_By_Status_Open_And_OnGoing();
}
