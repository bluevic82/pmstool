package com.tinhvan.model;

public class MemberProject {
	
	private int member_project_id;
	private int user_id;
	private String member_project_name;
	private int role_id;
	private int member_project_effort;
	private int project_id;
	private String role_name;

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public MemberProject() {

	}

	public int getMember_project_id() {
		return member_project_id;
	}

	public void setMember_project_id(int member_project_id) {
		this.member_project_id = member_project_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getMember_project_name() {
		return member_project_name;
	}

	public void setMember_project_name(String member_project_name) {
		this.member_project_name = member_project_name;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public int getMember_project_effort() {
		return member_project_effort;
	}

	public void setMember_project_effort(int member_project_effort) {
		this.member_project_effort = member_project_effort;
	}

	public int getProject_id() {
		return project_id;
	}

	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}

	public MemberProject(int member_project_id, int user_id,
			String member_project_name, int role_id, int member_project_effort,
			int project_id) {
		super();
		this.member_project_id = member_project_id;
		this.user_id = user_id;
		this.member_project_name = member_project_name;
		this.role_id = role_id;
		this.member_project_effort = member_project_effort;
		this.project_id = project_id;
	}
	
	
	
	

}
