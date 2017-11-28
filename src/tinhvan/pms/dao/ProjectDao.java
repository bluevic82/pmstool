package tinhvan.pms.dao;

import java.util.List;

import tinhvan.pms.model.ProjectModel;
/**
 * @author Luong
 * @version 1.0
 * @Date 27/11/2017
 * @category implement insert, update, get all project, get project by name
 **/
public interface ProjectDao {

	public void insertProject(ProjectModel project);
	//public int deleteProject(int id);
	public int updateProject(ProjectModel project);
	public List<ProjectModel> getAllProject();
	//public ProjectModel getProjectbyId(int id);
	public ProjectModel getProjectbyName(String project_name);
}
