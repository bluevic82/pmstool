package com.tinhvan.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tinhvan.dao.UserDao;
import com.tinhvan.model.UserInfo;

@Service
public class MyDBAuthenticationService implements UserDetailsService {

	@Autowired
	public UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String user_mail) throws UsernameNotFoundException {
		//find user
		UserInfo userInfo = userDao.findUserInfo(user_mail);
		System.out.println("UserInfo= " + userInfo);
		//check user from database
		if (userInfo == null) {
			throw new UsernameNotFoundException("Mail " + user_mail + " was not found in the database");
		}

		// [USER,ADMIN,..] get and check role
		List<String> roles = userDao.getUserRoles(user_mail);

		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		if (roles != null) {
			for (String role : roles) {
				// ROLE_USER, ROLE_ADMIN,..
				GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
				grantList.add(authority);
			}
		}
		
		UserDetails userDetails = (UserDetails) new User(userInfo.getUser_mail(), //
				userInfo.getUser_password(), grantList);

		return userDetails;
	}

}
