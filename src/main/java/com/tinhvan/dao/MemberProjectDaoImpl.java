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
	@Autowired
	RoleDao roleDao;

	@Override
	public List<MemberProject> getAllMember() {

		return jdbcTemplate.query("SELECT * FROM MEMBER_PROJECT",
				new RowMapper<MemberProject>() {

					@Override
					public MemberProject mapRow(ResultSet rs, int rowNum)
							throws SQLException {
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

	// public List<MemberProject> getMemberProjectByProjectId(int id) {
	// String sql = "SELECT * FROM MEMBER_PROJECT WHERE PROJECT_ID = ?";
	// Object[] params = new Object[] { id };
	// List<MemberProject> listMemberOfProject = jdbcTemplate.queryForList(sql,
	// params, MemberProject.class);
	// return listMemberOfProject;
	// }

	@Override
	public List<MemberProject> getMemberProjectByProjectId1(int id) {
		Object[] params = new Object[] { id };
		return jdbcTemplate.query(
				"SELECT * FROM MEMBER_PROJECT WHERE PROJECT_ID = ?", params,
				new RowMapper<MemberProject>() {

					@Override
					public MemberProject mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						MemberProject listMemberOfProject = new MemberProject();
						listMemberOfProject.setMember_project_id(rs.getInt(1));
						listMemberOfProject.setUser_id(rs.getInt(2));
						listMemberOfProject.setMember_project_name(rs
								.getString(3));
						listMemberOfProject.setRole_id(rs.getInt(4));
						listMemberOfProject.setMember_project_effort(rs
								.getInt(5));
						listMemberOfProject.setProject_id(rs.getInt(6));
						listMemberOfProject.setRole_name(roleDao
								.getRoleNameByRoleId(listMemberOfProject
										.getRole_id()));
						return listMemberOfProject;
					}
				});
	}
}
