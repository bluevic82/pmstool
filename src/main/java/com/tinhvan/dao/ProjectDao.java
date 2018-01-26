package com.tinhvan.dao;

import java.util.List;

import com.tinhvan.model.MemberProject;
import com.tinhvan.model.ProjectInfo;

public interface ProjectDao {
	public List<ProjectInfo> getAllProject();
	
	public void addProject(ProjectInfo project);
	
	public void updateProject(ProjectInfo project);
	
	public ProjectInfo getProjectById(int id);
	public ProjectInfo getProjectById1(int id);
	public List<MemberProject> getPm(int id);
	public List<ProjectInfo> getAllProject1();
	
}
