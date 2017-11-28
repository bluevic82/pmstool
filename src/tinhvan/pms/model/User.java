package tinhvan.pms.model;
/**
 * @Purpose: POJO class User
 * @author NguyenManhIT
 * @version 1.0
 * @Date 24/11/2017
 **/
public class User {
	private String username;
	private String email;
	private String password;
	private int role;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
