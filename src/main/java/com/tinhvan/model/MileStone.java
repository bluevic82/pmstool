package com.tinhvan.model;

public class MileStone {
	
	private int milestone_id;
	private String milestone_date;
	private String milestone_description;
	private int project_id;
	
	public int getMilestone_id() {
		return milestone_id;
	}
	public void setMilestone_id(int milestone_id) {
		this.milestone_id = milestone_id;
	}
	public String getMilestone_date() {
		return milestone_date;
	}
	public void setMilestone_date(String milestone_date) {
		this.milestone_date = milestone_date;
	}
	public String getMilestone_description() {
		return milestone_description;
	}
	public void setMilestone_description(String milestone_description) {
		this.milestone_description = milestone_description;
	}
	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
	
	
}
