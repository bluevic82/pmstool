package com.tinhvan.dao;

import java.util.List;

import com.tinhvan.model.MemberProject;
import com.tinhvan.model.ProjectInfo;
import com.tinhvan.model.ScopeProject;

public interface ProjectDao {
	public List<ProjectInfo> getAllProject();
	
	/*public void addProject(ProjectInfo project);*/
	public void addProject(ProjectInfo project, ScopeProject scopeP);
	/*public void addProject(ProjectInfo project, List scopeP);*/
	public void updateProject(ProjectInfo project);
	public int findProjectIdMax();
	public ProjectInfo getProjectById(int id);
	public ProjectInfo getProjectById1(int id);
	public List<MemberProject> getPm(int id);
	public List<ProjectInfo> getAllProject1();
	
}
