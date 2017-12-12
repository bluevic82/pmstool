package com.tinhvan.model;


public class ProjectInfo {
	private String project_name;
	private String project_from;
	private String project_to;
	private int charge_cost;
	private int status;
	private int type;
	private String description;
	private String technical;
	
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getProject_from() {
		return project_from;
	}
	public void setProject_from(String project_from) {
		this.project_from = project_from;
	}
	public String getProject_to() {
		return project_to;
	}
	public void setProject_to(String project_to) {
		this.project_to = project_to;
	}
	public int getCharge_cost() {
		return charge_cost;
	}
	public void setCharge_cost(int charge_cost) {
		this.charge_cost = charge_cost;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTechnical() {
		return technical;
	}
	public void setTechnical(String technical) {
		this.technical = technical;
	}
	public ProjectInfo() {
		super();
	}
	
	
}
