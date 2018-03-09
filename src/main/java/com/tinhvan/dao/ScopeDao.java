package com.tinhvan.dao;

import java.util.List;

import com.tinhvan.model.Scope;
import com.tinhvan.model.ScopeProject;

public interface ScopeDao {
	public List<Scope> getAllScope();
	public List<ScopeProject> getListScopProjectByProjectIdAndListScopeId(int project_id, List<Integer> list_scope_id);
	public void updateScopeProject(List<ScopeProject> liScopeProjects);
	public void deleteListScopeProjectByProject_id(int project_id);
	public void createListScopeProject(int project_id, List<Integer> listScope_id);
}
