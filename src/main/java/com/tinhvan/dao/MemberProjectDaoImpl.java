package com.tinhvan.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tinhvan.model.MemberProject;

@Repository
@Transactional
public class MemberProjectDaoImpl implements MemberProjectDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public List<MemberProject> getAllMember() {
		
		return jdbcTemplate.query("SELECT * FROM MEMBER_PROJECT", new RowMapper<MemberProject>() {

			@Override
			public MemberProject mapRow(ResultSet rs, int rowNum) throws SQLException {
				MemberProject mp = new MemberProject();
				mp.setMember_project_id(rs.getInt(1));
				mp.setUser_id(rs.getInt(2));
				mp.setMember_project_name(rs.getString(3));
				mp.setRole_id(rs.getInt(4));
				mp.setMember_project_effort(rs.getInt(5));
				mp.setProject_id(rs.getInt(6));
				return mp;
			}



		});
	}
}
