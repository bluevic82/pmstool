package com.tinhvan.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tinhvan.model.PreDefinedTask;

@Transactional
@Repository
public class PreDefinedTaskDaoImpl implements PreDefinedTaskDao{
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<PreDefinedTask> getAll() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("SELECT * FROM pre_defined", new RowMapper<PreDefinedTask>() {

			@Override
			public PreDefinedTask mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				PreDefinedTask definedTask = new PreDefinedTask();
				definedTask.setPre_defined_id(rs.getInt(1));
				definedTask.setPre_defined_name(rs.getString(2));
				return definedTask;
			}
		});
	}

}
