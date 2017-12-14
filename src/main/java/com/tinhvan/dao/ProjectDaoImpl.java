package com.tinhvan.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

}
