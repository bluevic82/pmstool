package com.tinhvan.model;
/**
 * @purpose: getter, setter method for Status
 * **/
public class Status {
	private int status_id;
	private String status_type;
	private String status_name;
	public int getStatus_id() {
		return status_id;
	}
	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}
	public String getStatus_type() {
		return status_type;
	}
	public void setStatus_type(String status_type) {
		this.status_type = status_type;
	}
	public String getStatus_name() {
		return status_name;
	}
	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}
	public Status() {
		super();
	}
}
