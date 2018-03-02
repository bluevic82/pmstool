package com.tinhvan.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tinhvan.model.Process;

@Transactional
@Repository
public class ProcessDaoImpl implements ProcessDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<Process> getAll() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("SELECT * FROM process", new RowMapper<Process>() {

			@Override
			public Process mapRow(ResultSet rs, int rowNum) throws SQLException {
				Process pc = new Process();
				pc.setProcess_id(rs.getInt(1));
				pc.setProcess_name(rs.getString(2));
				pc.setProcess_type(rs.getString(3));
				return pc;
			}			
		});
	}

	@Override
	public Process getProcessByProcessId(int process_id) {
		// TODO Auto-generated method stub
		try{
			return jdbcTemplate.queryForObject("SELECT * FROM process where process_id="+process_id+"", new RowMapper<Process>() {

				@Override
				public Process mapRow(ResultSet rs, int rowNum) throws SQLException {
					Process pc = new Process();
					pc.setProcess_id(rs.getInt(1));
					pc.setProcess_name(rs.getString(2));
					pc.setProcess_type(rs.getString(3));
					return pc;
				}			
			});
		}
		catch(Exception e){
			return null;
		}
		
	}

}
