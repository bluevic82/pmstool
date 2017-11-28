package tinhvan.pms.model;

import java.util.Date;
/**
 * @author Luong
 * @version 1.0
 * @Date 27/11/2017
 * @category getter, setter field project
 **/
public class ProjectModel {
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
	public Date getProject_from() {
		return project_from;
	}
	public void setProject_from(Date project_from) {
		this.project_from = project_from;
	}
	public Date getProject_to() {
		return project_to;
	}
	public void setProject_to(Date project_to) {
		this.project_to = project_to;
	}
	public int getProject_charge_cost() {
		return project_charge_cost;
	}
	public void setProject_charge_cost(int project_charge_cost) {
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
	private int project_id;
	private String project_name;
	private Date project_from;
	private Date project_to;
	private int project_charge_cost;
	private int status_id;
	private int type_id;
	private String project_description;
	private String project_technical;
	
	
}
