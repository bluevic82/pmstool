package tinhvan.pms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import tinhvan.pms.model.Login;
import tinhvan.pms.model.User;

/**
 * @Purpose: class UserDaoImpl database handler
 * @author NguyenManhIT
 * @version 1.0
 * @Date 24/11/2017
 **/

public class UserDaoImpl implements UserDAO {

	@Autowired
	DataSource dataSource;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public User validateUser(Login login) {

		String sql = "select * from user_info where USER_MAIL='" + login.getUser_email() + "' and user_password='"
				+ login.getUser_password() + "'";
		List<User> users = jdbcTemplate.query(sql, new UserMapper());
		return users.size() > 0 ? users.get(0) : null;
	}
}

class UserMapper implements RowMapper<User> {

	public User mapRow(ResultSet rs, int arg1) throws SQLException {
		User user = new User();
		user.setEmailID(rs.getString("USER_MAIL"));
		user.setPassword(rs.getString("USER_PASSWORD"));
		user.setRole(rs.getInt("ROLE_ID"));

		return user;
	}

}
