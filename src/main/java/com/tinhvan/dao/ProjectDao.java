package com.tinhvan.dao;

import java.util.List;

import com.tinhvan.model.ProjectInfo;

public interface ProjectDao {
	public List<ProjectInfo> getAllProject();
	public void addProject(ProjectInfo project);
	
}
