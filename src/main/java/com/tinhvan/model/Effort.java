package com.tinhvan.model;

public class Effort {
	private int project_id;
	private String project_name;
	private double project_charge_cost;
	private double project_actual_cost;
	private int status_id;
	private double over_head;
	private double change_Request;
	private String status_type;
	private double width_project_charge_cost;
	private double width_project_actual_cost;

	public double getWidth_project_charge_cost() {
		return width_project_charge_cost;
	}

	public void setWidth_project_charge_cost(double width_project_charge_cost) {
		this.width_project_charge_cost = width_project_charge_cost;
	}

	public double getWidth_project_actual_cost() {
		return width_project_actual_cost;
	}

	public void setWidth_project_actual_cost(double width_project_actual_cost) {
		this.width_project_actual_cost = width_project_actual_cost;
	}

	public String getStatus_type() {
		return status_type;
	}

	public void setStatus_type(String status_type) {
		this.status_type = status_type;
	}

	public void setProject_charge_cost(double project_charge_cost) {
		this.project_charge_cost = project_charge_cost;
	}

	public void setProject_actual_cost(double project_actual_cost) {
		this.project_actual_cost = project_actual_cost;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public double getOver_head() {
		return over_head;
	}

	public void setOver_head(double over_head) {
		this.over_head = over_head;
	}

	public double getChange_Request() {
		return change_Request;
	}

	public void setChange_Request(double change_Request) {
		this.change_Request = change_Request;
	}

	public int getProject_id() {
		return project_id;
	}

	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}

	public double getProject_charge_cost() {
		return project_charge_cost;
	}

	public void setProject_charge_cost(int project_charge_cost) {
		this.project_charge_cost = project_charge_cost;
	}

	public double getProject_actual_cost() {
		return project_actual_cost;
	}

	public void setProject_actual_cost(int project_actual_cost) {
		this.project_actual_cost = project_actual_cost;
	}

	public int getStatus_id() {
		return status_id;
	}

	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}

}
