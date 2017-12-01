package tinhvan.pms.model;

/**
 * @Purpose: POJO class User
 * @author NguyenManhIT
 * @version 1.0
 * @Date 24/11/2017
 **/
public class User {
	
	private String userFullName;
	private String emailID;
	private String password;
	private int role;

	public String getUserFullName() {
		return userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

}
