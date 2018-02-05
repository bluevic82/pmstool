package com.tinhvan.model;

public class BugInfo {
	private int bug_id;
	private int type_id;
	private int status_id;
	private int bug_done;
	private String bug_from;
	private String bug_to;
	private String bug_subject;
	private String bug_description;
	private String bug_solution;
	private int member_project_id;
	private int category_id;
	private String bug_priority;
	private int project_id;
	private String status;
	
	public int getBug_id() {
		return bug_id;
	}
	public void setBug_id(int bug_id) {
		this.bug_id = bug_id;
	}
	public int getType_id() {
		return type_id;
	}
	public void setType_id(int type_id) {
		this.type_id = type_id;
	}
	public int getStatus_id() {
		return status_id;
	}
	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}
	public int getBug_done() {
		return bug_done;
	}
	public void setBug_done(int bug_done) {
		this.bug_done = bug_done;
	}
	public String getBug_from() {
		return bug_from;
	}
	public void setBug_from(String bug_from) {
		this.bug_from = bug_from;
	}
	public String getBug_to() {
		return bug_to;
	}
	public void setBug_to(String bug_to) {
		this.bug_to = bug_to;
	}
	public String getBug_subject() {
		return bug_subject;
	}
	public void setBug_subject(String bug_subject) {
		this.bug_subject = bug_subject;
	}
	public String getBug_description() {
		return bug_description;
	}
	public void setBug_description(String bug_description) {
		this.bug_description = bug_description;
	}
	public String getBug_solution() {
		return bug_solution;
	}
	public void setBug_solution(String bug_solution) {
		this.bug_solution = bug_solution;
	}
	public int getMember_project_id() {
		return member_project_id;
	}
	public void setMember_project_id(int member_project_id) {
		this.member_project_id = member_project_id;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public String getBug_priority() {
		return bug_priority;
	}
	public void setBug_priority(String bug_priority) {
		this.bug_priority = bug_priority;
	}
	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
