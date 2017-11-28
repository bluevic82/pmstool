package tinhvan.pms.dao;

import tinhvan.pms.model.Login;
import tinhvan.pms.model.User;

/**
 * @Purpose: class UserDAO
 * @author NguyenManhIT
 * @version 1.0
 * @Date 24/11/2017
 **/
public interface UserDAO {
	
	User validateUser(Login login);
}
