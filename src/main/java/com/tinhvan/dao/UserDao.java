package com.tinhvan.dao;

import java.util.List;

import com.tinhvan.model.UserInfo;
/**
 * @Purpose: function interface get user 
 * @Params: String user_mail
 * **/
public interface UserDao {
	/*find user_mail*/
	public UserInfo findUserInfo(String user_mail);
	/*get role user*/
	public List<String> getUserRoles(String user_mail);
}
