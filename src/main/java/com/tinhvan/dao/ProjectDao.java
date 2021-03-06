package com.tinhvan.dao;

import java.util.ArrayList;
import java.util.List;

import com.tinhvan.model.MemberProject;
import com.tinhvan.model.ProjectInfo;
import com.tinhvan.model.ScopeProject;

public interface ProjectDao {
	public List<ProjectInfo> getAllProject();
	
	public void addProject(ProjectInfo project);
	public void addScopeProject(int id, List<Integer> a);
	public int findProjectIdMax();
	
	public void updateProject(ProjectInfo project);
	
	public ProjectInfo getProjectById(int id);
	public ProjectInfo getProjectById1(int id);
	public List<MemberProject> getPm(int id);
	public List<ProjectInfo> getAllProject1(String name,String pm,String from,String to);
	
	public List<ScopeProject> getScope(int id);
	public List<ProjectInfo> getListPRojectOfUserAccessed(int user_id);
}
