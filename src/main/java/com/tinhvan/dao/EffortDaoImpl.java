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

import com.tinhvan.model.Effort;

@Repository
@Transactional
public class EffortDaoImpl implements EffortDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<Effort> getAllEfort() {

		return jdbcTemplate.query("SELECT * FROM project_info",
				new RowMapper<Effort>() {

					@Override
					public Effort mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Effort effort = new Effort();
						effort.setProject_id(rs.getInt(1));
						effort.setProject_name(rs.getString(2));
						effort.setProject_charge_cost(rs.getInt(5));
						effort.setStatus_id(rs.getInt(6));
						effort.setStatus_type(getStatusTypeByStatusId(effort.getStatus_id()));
						// effort.setProject_actual_cost();

						effort.setProject_actual_cost(getSUMEffortByPrjId(effort.getProject_id())/100);
						return effort;
					}
				});

	}


	@Override
	public Effort getEffortById(int id) {
		String sql = "SELECT * FROM project_info WHERE PROJECT_ID = ? ";
		// TODO Auto-generated method stub
		Effort effort;
		effort =  jdbcTemplate.queryForObject(sql, new Object[] { id },
				new BeanPropertyRowMapper<Effort>(Effort.class));
		effort.setProject_actual_cost(getSUMEffortByPrjId(id)/100);
		effort.setStatus_type(getStatusTypeByStatusId(effort.getStatus_id()));
		return effort;
	}
	
	public String getStatusTypeByStatusId(int id){
		String sql= "SELECT STATUS_TYPE FROM status_info WHERE STATUS_ID = ? AND STATUS_NAME = 'project'";
		return jdbcTemplate.queryForObject(sql, new Object[] {id}, String.class);
	}
	
	public double getSUMEffortByPrjId(int id){
		try{
			String sql = "SELECT SUM(MEMBER_PROJECT_EFFORT) FROM member_project WHERE PROJECT_ID = ?";
			return jdbcTemplate.queryForObject(sql, new Object[] {id}, Double.class);
		}
		catch(Exception e){
			return 0;
		}
		
	}
	

}
