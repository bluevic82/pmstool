package com.tinhvan.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tinhvan.mapper.UserMapper;
import com.tinhvan.model.UserInfo;

/**
 * @Purpose: get user_mail, user_password and role from database
 * **/
@Service
@Transactional
public class UserDaoImpl extends JdbcDaoSupport implements UserDao {

	/*using datasource*/
	@Autowired
	public UserDaoImpl(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	/**
	 * @Purpose: get user_mail, user_password
	 * 
	 * **/
	@Override
	public UserInfo findUserInfo(String user_mail) {
		//sql select user
		String sql = "Select u.USER_MAIL,u.USER_PASSWORD "
				+ " from user_info u where u.USER_MAIL = ? ";

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
	 * **/
	@Override
	public List<String> getUserRoles(String user_mail) {
		//sql select role for user
		String sql = "Select r.ROLE_ID "//
				+ " from user_info r where r.USER_MAIL= ? ";
		
		Object[] params = new Object[] { user_mail };

		List<String> roles = this.getJdbcTemplate().queryForList(sql, params, String.class);

		return roles;
	}

}
