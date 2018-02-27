package com.tinhvan.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tinhvan.model.Status;

@Repository
@Transactional
public class StatusDaoImpl implements StatusDao{

	@Autowired
	JdbcTemplate jdbctemplate;
	@Override
	public List<Status> getAllStatus() {
		// TODO Auto-generated method stub
		return jdbctemplate.query("SELECT * FROM STATUS_INFO WHERE STATUS_NAME='project'", new RowMapper<Status>() {
			public Status mapRow(ResultSet rs, int row) throws SQLException{
				Status st = new Status();
				st.setStatus_id(rs.getInt(1));
				st.setStatus_type(rs.getString(2));
				st.setStatus_name(rs.getString(3));
				return st;
				}
		});
	}
	
	@Override
	public List<Status> getStatusOfTask() {
		return jdbctemplate.query("SELECT * FROM STATUS_INFO WHERE STATUS_NAME='task'", new RowMapper<Status>() {
			@Override
			public Status mapRow(ResultSet rs, int rowNum) throws SQLException {
				Status st = new Status();
				st.setStatus_id(rs.getInt(1));
				st.setStatus_name(rs.getString(2));
				st.setStatus_type(rs.getString(3));
				return st;
			}

		});

	}

	@Override
	public List<Status> getStatusOfQA() {
		    //return jdbctemplate.query("SELECT * FROM STATUS_INFO WHERE STATUS_NAME='QA'and (Status_type='Open' or status_type='answer' or status_type='external')", new RowMapper<Status>() {
			return jdbctemplate.query("SELECT * FROM STATUS_INFO WHERE STATUS_NAME='QA'", new RowMapper<Status>() {
			@Override
			public Status mapRow(ResultSet rs, int rowNum) throws SQLException {
				Status st = new Status();
				st.setStatus_id(rs.getInt(1));
				st.setStatus_type(rs.getString(2));
				st.setStatus_name(rs.getString(3));
				return st;
			}
		});
	}

	@Override
	public List<Status> getStatusOfTS() {
		// TODO Auto-generated method stub
		return jdbctemplate.query("SELECT * FROM STATUS_INFO WHERE STATUS_NAME='timesheet'", new RowMapper<Status>(){

			@Override
			public Status mapRow(ResultSet rs, int rowNum) throws SQLException {
				Status st = new Status();
				st.setStatus_id(rs.getInt(1));
				st.setStatus_name(rs.getString(2));
				st.setStatus_type(rs.getString(3));
				return st;
			}
		});
	}

	@Override
	public List<Status> getStatusOfBug() {
		return jdbctemplate.query("SELECT * FROM STATUS_INFO WHERE STATUS_NAME='task'", new RowMapper<Status>() {
			@Override
			public Status mapRow(ResultSet rs, int rowNum) throws SQLException {
				Status st = new Status();
				st.setStatus_id(rs.getInt(1));
				st.setStatus_name(rs.getString(2));
				st.setStatus_type(rs.getString(3));
				return st;
			}

		});
	}
	
}
