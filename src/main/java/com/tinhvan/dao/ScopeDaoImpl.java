package com.tinhvan.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tinhvan.model.Scope;

@Repository
@Transactional
public class ScopeDaoImpl implements ScopeDao{

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Override
	public List<Scope> getAllScope() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("SELECT * FROM SCOPE_INFO", new RowMapper<Scope>() {
			public Scope mapRow(ResultSet rs, int row) throws SQLException{
				Scope st = new Scope();
				st.setScope_id(rs.getInt(1));
				st.setScope_name(rs.getString(2));
				return st;
				}
		});
	}

	
}
