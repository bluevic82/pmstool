package tinhvan.pms.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import tinhvan.pms.model.ProjectModel;
/**
 * @author Luong
 * @version 1.0
 * @Date 27/11/2017
 * @category MySql insert, update, get all project, get project by name
 **/
public class ProjectDaoImpl implements ProjectDao {

	@Override
	public void insertProject(ProjectModel project) {
		// TODO Auto-generated method stub
		String sql = "insert into student()" + "values (?,?,?,?,?,?,?,?,?)";
		 jdbctemplate.update(sql, new Object[]{project.getProject_id(),project.getProject_name(),project.getProject_from(),project.getProject_to(),project.getProject_charge_cost(),project.getStatus_id(),project.getType_id(), project.getProject_description(), project.getProject_technical()});
	}

	@Override
	public int updateProject(ProjectModel project) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ProjectModel> getAllProject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProjectModel getProjectbyName(String project_name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Autowired
	private JdbcTemplate jdbctemplate;

	

}
