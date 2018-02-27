package com.tinhvan.model;

import java.util.ArrayList;
import java.util.List;


public class TimeSheetDetail {
	private int detail_timesheet_id;
	private String detail_timesheet_name;
	private float hour;
	private int pre_defined_id;
	private int process_id;
	private int type_id;
	private int task_id;
	private String workcontent;
	private int ts_id;
	private int status_id;
	
	
	private String status_type;
	private String type_name;
	private String pre_defined_name;
	private String process_name;
	private String task_subject;
	private MemberProject memberProject;
	

	
	public MemberProject getMemberProject() {
		return memberProject;
	}

	public void setMemberProject(MemberProject memberProject) {
		this.memberProject = memberProject;
	}

	public String getTask_subject() {
		return task_subject;
	}

	public void setTask_subject(String task_subject) {
		this.task_subject = task_subject;
	}

	public String getStatus_type() {
		return status_type;
	}

	public void setStatus_type(String status_type) {
		this.status_type = status_type;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public String getPre_defined_name() {
		return pre_defined_name;
	}

	public void setPre_defined_name(String pre_defined_name) {
		this.pre_defined_name = pre_defined_name;
	}

	public String getProcess_name() {
		return process_name;
	}

	public void setProcess_name(String process_name) {
		this.process_name = process_name;
	}

	/*Test DaiK*/
	private String detail_timesheet_date;
	
	
	public int getStatus_id() {
		return status_id;
	}

	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}

	private List<String> list_Name_Of_Id = new ArrayList<String>();
	
	public List<String> getList_Name_Of_Id() {
		return list_Name_Of_Id;
	}

	public void setList_Name_Of_Id(List<String> list_Name_Of_Id) {
		this.list_Name_Of_Id = list_Name_Of_Id;
	}

	public String getDetail_timesheet_date() {
		return detail_timesheet_date;
	}

	public void setDetail_timesheet_date(String detail_timesheet_date) {
		this.detail_timesheet_date = detail_timesheet_date;
	}

	public TimeSheetDetail() {
		
	}

	public int getDetail_timesheet_id() {
		return detail_timesheet_id;
	}

	public void setDetail_timesheet_id(int detail_timesheet_id) {
		this.detail_timesheet_id = detail_timesheet_id;
	}

	public String getDetail_timesheet_name() {
		return detail_timesheet_name;
	}

	public void setDetail_timesheet_name(String detail_timesheet_name) {
		this.detail_timesheet_name = detail_timesheet_name;
	}

	public float getHour() {
		return hour;
	}

	public void setHour(float hour) {
		this.hour = hour;
	}

	public int getPre_defined_id() {
		return pre_defined_id;
	}

	public void setPre_defined_id(int pre_defined_id) {
		this.pre_defined_id = pre_defined_id;
	}

	public int getProcess_id() {
		return process_id;
	}

	public void setProcess_id(int process_id) {
		this.process_id = process_id;
	}

	public int getType_id() {
		return type_id;
	}

	public void setType_id(int type_id) {
		this.type_id = type_id;
	}

	public int getTask_id() {
		return task_id;
	}

	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}

	public String getWorkcontent() {
		return workcontent;
	}

	public void setWorkcontent(String workcontent) {
		this.workcontent = workcontent;
	}

	public int getTs_id() {
		return ts_id;
	}

	public void setTs_id(int ts_id) {
		this.ts_id = ts_id;
	}
	
	

}
