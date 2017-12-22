package com.tinhvan.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tinhvan.model.ProjectInfo;

@Repository
@Transactional
public class ProjectDaoImpl implements ProjectDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<ProjectInfo> getAllProject() {
		
		return jdbcTemplate.query("SELECT * FROM project_info", new RowMapper<ProjectInfo>() {

			@Override
			public ProjectInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				ProjectInfo projectInfo = new ProjectInfo();
				projectInfo.setProject_id(rs.getInt(1));
				projectInfo.setProject_name(rs.getString(2));
				projectInfo.setProject_from(rs.getString(3));
				projectInfo.setProject_to(rs.getString(4));
				projectInfo.setProject_charge_cost(rs.getInt(5));
				projectInfo.setStatus_id(rs.getInt(6));
				projectInfo.setType_id(rs.getInt(7));
				projectInfo.setProject_description(rs.getString(8));
				projectInfo.setProject_technical(rs.getString(9));
				return projectInfo;
			}
		});
		
	}

	@Override
	public void addProject(ProjectInfo project) {
		// TODO Auto-generated method stub
		String sql="INSERT INTO project_info(PROJECT_NAME, PROJECT_FROM, PROJECT_TO, PROJECT_CHARGE_COST, STATUS_ID, TYPE_ID, PROJECT_DESCRIPTION, PROJECT_TECHNICAL)"+"VALUES (?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, new Object[] {project.getProject_name(),project.getProject_from(),project.getProject_to(),project.getProject_charge_cost(),project.getStatus_id(),project.getType_id(),project.getProject_description(),project.getProject_technical()});
	}

	@Override
	public void updateProject(ProjectInfo project) {
		// TODO Auto-generated method stub
		String sql="update project_info set project_name='"+project.getProject_name()+"', project_from ='"+project.getProject_from()+"',  project_to='"+project.getProject_to()+"',  project_charge_cost="+project.getProject_charge_cost()+",  status_id="+project.getStatus_id()+",  type_id="+project.getType_id()+",  project_description='"+project.getProject_description()+"',  project_technical='"+project.getProject_technical()+"' where project_id="+project.getProject_id()+""; 
		jdbcTemplate.update(sql);
		
	}

	@Override
	public ProjectInfo getProjectById(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM project_info where PROJECT_ID = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] {id}, new BeanPropertyRowMapper<ProjectInfo>(ProjectInfo.class));
	}
	
	
}
