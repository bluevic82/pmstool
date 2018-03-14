package com.tinhvan.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tinhvan.model.Role;

@Repository
@Transactional
public class RoleDaoImpl implements RoleDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<Role> getAllRole() {
		// TODO Auto-generated method stub
		
		try{
			return jdbcTemplate.query("SELECT * FROM role WHERE ROLE_ID != 1 ",
					new RowMapper<Role>() {

						@Override
						public Role mapRow(ResultSet arg0, int arg1)
								throws SQLException {
							// TODO Auto-generated method stub
							Role rl = new Role();
							rl.setRole_id(arg0.getInt(1));
							rl.setRole_name(arg0.getString(2));
							return rl;
						}
					});
		}
		catch(Exception e){
			return null;
		}

	}

	@Override
	public String getRoleNameByRoleId(int id) {
		String sql = "SELECT ROLE_NAME FROM role WHERE ROLE_ID = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { id },
				String.class);
	}

	@Override
	public List<Role> getListRoleExceptManagerIfUserLoginIsManager(int role_id) {
		// TODO Auto-generated method stub
		String sqlxx="";
		if(role_id==2){
			sqlxx = "AND ROLE_ID !=2";
		}
		
		try{
			return jdbcTemplate.query("SELECT * FROM role WHERE ROLE_ID != 1 "+sqlxx,
					new RowMapper<Role>() {

						@Override
						public Role mapRow(ResultSet arg0, int arg1)
								throws SQLException {
							// TODO Auto-generated method stub
							Role rl = new Role();
							rl.setRole_id(arg0.getInt(1));
							rl.setRole_name(arg0.getString(2));
							return rl;
						}
					});
		}
		catch(Exception e){
			return null;
		}
	}

}
