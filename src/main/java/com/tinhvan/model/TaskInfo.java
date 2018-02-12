package com.tinhvan.model;

public class TaskInfo {
	private int task_id;
	private int type_id;
	private int status_id;
	private int task_done;
	private String task_from;
	private String task_to;
	private String task_subject;
	private String task_description;
	private String task_solution;
	private int member_project_id;
	private int category_id;
	private String task_priority;
	private int project_id;
	private String status;
	private String pm;
	private String mb;
	public int getTask_id() {
		return task_id;
	}
	public void setTask_id(int task_id) {
		this.task_id = task_id;
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
	public int getTask_done() {
		return task_done;
	}
	public void setTask_done(int task_done) {
		this.task_done = task_done;
	}
	public String getTask_from() {
		return task_from;
	}
	public void setTask_from(String task_from) {
		this.task_from = task_from;
	}
	public String getTask_to() {
		return task_to;
	}
	public void setTask_to(String task_to) {
		this.task_to = task_to;
	}
	public String getTask_subject() {
		return task_subject;
	}
	public void setTask_subject(String task_subject) {
		this.task_subject = task_subject;
	}
	public String getTask_description() {
		return task_description;
	}
	public void setTask_description(String task_description) {
		this.task_description = task_description;
	}
	public String getTask_solution() {
		return task_solution;
	}
	public void setTask_solution(String task_solution) {
		this.task_solution = task_solution;
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
	public String getTask_priority() {
		return task_priority;
	}
	public void setTask_priority(String task_priority) {
		this.task_priority = task_priority;
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
	
	public String getPm() {
		return pm;
	}
	public void setPm(String pm) {
		this.pm = pm;
	}
	public TaskInfo() {
		super();
	}
	public String getMb() {
		return mb;
	}
	public void setMb(String mb) {
		this.mb = mb;
	}
	
	
	
		
	
}
