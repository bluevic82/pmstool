package com.tinhvan.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tinhvan.model.Type;

@Repository
@Transactional
public class TypeDaoImpl implements TypeDao{

	@Autowired
	JdbcTemplate jdbctemplate;
	@Override
	public List<Type> getAllType() {
		// TODO Auto-generated method stub
		return jdbctemplate.query("SELECT TYPE_ID,TYPE_NAME FROM TYPE WHERE TYPE_TYPE='project'", new RowMapper<Type>() {
			public Type mapRow(ResultSet rs, int row) throws SQLException{
				Type st = new Type();
				st.setType_id(rs.getInt(1));
				st.setType_name(rs.getString(2));
				return st;
				}
		});
	}
	
	@Override
	public List<Type> getTypeOfTask() {
		return jdbctemplate.query("SELECT TYPE_ID,TYPE_NAME FROM TYPE WHERE TYPE_TYPE='task'",
				new RowMapper<Type>() {
					public Type mapRow(ResultSet rs, int row) throws SQLException {
						Type st = new Type();
						st.setType_id(rs.getInt(1));
						st.setType_name(rs.getString(2));
						return st;
					}
				});
	}

	@Override
	public List<Type> getTypeOfBug() {
		return jdbctemplate.query("SELECT TYPE_ID,TYPE_NAME FROM TYPE WHERE TYPE_TYPE='bug'",
				new RowMapper<Type>() {
					public Type mapRow(ResultSet rs, int row) throws SQLException {
						Type st = new Type();
						st.setType_id(rs.getInt(1));
						st.setType_name(rs.getString(2));
						return st;
					}
				});
	}
}
