package com.tinhvan.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tinhvan.model.UserInfo;

@Repository
public class UserMapper implements RowMapper<UserInfo> {

	@Override
	public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		/*get user_mail and user_password*/
		String user_mail = rs.getString("USER_MAIL");
		String user_password =rs.getString("USER_PASSWORD"); 
		return new UserInfo(user_mail, user_password);
	}

}
