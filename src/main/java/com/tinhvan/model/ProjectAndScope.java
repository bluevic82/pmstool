package com.tinhvan.model;

import java.util.List;

public class ProjectAndScope {
	private int project_id;
	private String project_name;
	private String project_from;
	private String project_to;
	private float project_charge_cost;
	private int status_id;
	private int type_id;
	private String project_description;
	private String project_technical;
	private String status;
	private List<Integer> Scope_id;
	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
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
	
	public float getProject_charge_cost() {
		return project_charge_cost;
	}
	public void setProject_charge_cost(float project_charge_cost) {
		this.project_charge_cost = project_charge_cost;
	}
	public int getStatus_id() {
		return status_id;
	}
	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}
	public int getType_id() {
		return type_id;
	}
	public void setType_id(int type_id) {
		this.type_id = type_id;
	}
	public String getProject_description() {
		return project_description;
	}
	public void setProject_description(String project_description) {
		this.project_description = project_description;
	}
	public String getProject_technical() {
		return project_technical;
	}
	public void setProject_technical(String project_technical) {
		this.project_technical = project_technical;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public List<Integer> getScope_id() {
		return Scope_id;
	}
	public void setScope_id(List<Integer> scope_id) {
		Scope_id = scope_id;
	}
	public ProjectAndScope() {
		super();
	}	
}
