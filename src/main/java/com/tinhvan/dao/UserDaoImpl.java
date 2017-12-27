package com.tinhvan.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
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

}
