package com.tinhvan.mapper;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.tinhvan.model.ProjectInfo;

public class ProjectMapper implements RowMapper {
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		ProjectInfo projectInfo = new ProjectInfo();
		projectInfo.setProject_id(rs.getInt(1));
		projectInfo.setProject_name(rs.getString(2));
		projectInfo.setProject_from(rs.getString(3));
		projectInfo.setProject_to(rs.getString(4));
		projectInfo.setProject_description(rs.getString(5));
		projectInfo.setProject_technical(rs.getString(6));
		projectInfo.setStatus(rs.getString(7));
		projectInfo.setType_id(rs.getInt(8));
		
		return projectInfo;
	}
}
