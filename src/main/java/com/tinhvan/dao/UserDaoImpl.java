package com.tinhvan.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tinhvan.mapper.UserMapper;
import com.tinhvan.model.User;
import com.tinhvan.model.UserInfo;

/**
 * @Purpose: get user_mail, user_password and role from database
 **/
@Service
@Transactional
public class UserDaoImpl extends JdbcDaoSupport implements UserDao {

	/* using datasource */
	@Autowired
	public UserDaoImpl(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	@Autowired
	JdbcTemplate jdbcTemplate;

	/**
	 * @Purpose: get user_mail, user_password
	 * 
	 **/
	@Override
	public UserInfo findUserInfo(String user_mail) {
		// sql select user
		String sql = "Select u.USER_MAIL,u.USER_PASSWORD " + " from user_info u where u.USER_MAIL = ? ";

		Object[] params = new Object[] { user_mail };
		UserMapper mapper = new UserMapper();
		try {
			UserInfo userInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
			return userInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	/**
	 * @Purpose: get role
	 * 
	 **/
	@Override
	public List<String> getUserRoles(String user_mail) {
		// sql select role for user
		String sql = "Select r.ROLE_ID "//
				+ " from user_info r where r.USER_MAIL= ? ";

		Object[] params = new Object[] { user_mail };

		List<String> roles = this.getJdbcTemplate().queryForList(sql, params, String.class);

		return roles;
	}

	@Override
	public void addUserSystem(User users) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO user_info( USER_FULLNAME, USER_MAIL, USER_PASSWORD, ROLE_ID)" + "VALUES (?,?,?,?)";
		jdbcTemplate.update(sql, new Object[] { users.getUser_fullName(), users.getUser_mail(),
				users.getUser_passWord(), users.getRole_id() });
	}

	@Override
	public List<User> gettAllUser() {
		// TODO Auto-generated method stub
		String sql = "SELECT * From user_info";
		try{
			return jdbcTemplate.query(sql, new RowMapper<User>() {
				
				@Override
				public User mapRow(ResultSet rs, int rowNum) throws SQLException {
					// TODO Auto-generated method stub
					User user = new User();
					user.setUser_id(rs.getInt(1));
					user.setUser_fullName(rs.getString(2));
					user.setUser_mail(rs.getString(3));
					user.setUser_passWord(rs.getString(4));
					user.setRole_id(rs.getInt(5));
					return user;
				}
			} );
		}
		catch(Exception e){
			return null;
		}
		
	}

	@Override
	public User getUserInfoByUserMail(String user_mail) {
		String sql = "SELECT * FROM user_info WHERE USER_MAIL =?";
		Object[] params = new Object[] { user_mail };
		try{
			return jdbcTemplate.queryForObject(sql, params, new RowMapper<User>(){

				@Override
				public User mapRow(ResultSet rs, int rowNum) throws SQLException {
					// TODO Auto-generated method stub
					User user = new User();
					user.setUser_id(rs.getInt(1));
					user.setUser_fullName(rs.getString(2));
					user.setUser_mail(rs.getString(3));
					user.setUser_passWord(rs.getString(4));
					user.setRole_id(rs.getInt(5));
					return user;
				}
				
			});
		}
		catch(Exception e){
			return null;
		}
		
	}

	@Override
	public User getUserInfoByUser_Id(int user_id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM user_info WHERE USER_ID =?";
		Object[] params = new Object[] { user_id };
		try{
			return jdbcTemplate.queryForObject(sql, params, new RowMapper<User>(){

				@Override
				public User mapRow(ResultSet rs, int rowNum) throws SQLException {
					// TODO Auto-generated method stub
					User user = new User();
					user.setUser_id(rs.getInt(1));
					user.setUser_fullName(rs.getString(2));
					user.setUser_mail(rs.getString(3));
					//not return user_pass
					user.setRole_id(rs.getInt(5));
					return user;
				}
				
			});
		}
		catch(Exception e){
			return null;
		}
	}

	@Override
	public List<User> getAllUser_Except_Admin() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				String sql = "SELECT * From user_info where role_id != 1";
				try{
					return jdbcTemplate.query(sql, new RowMapper<User>() {
						
						@Override
						public User mapRow(ResultSet rs, int rowNum) throws SQLException {
							// TODO Auto-generated method stub
							User user = new User();
							user.setUser_id(rs.getInt(1));
							user.setUser_fullName(rs.getString(2));
							user.setUser_mail(rs.getString(3));
							user.setUser_passWord(rs.getString(4));
							user.setRole_id(rs.getInt(5));
							return user;
						}
					} );
				}
				catch(Exception e){
					return null;
				}
	}
}
