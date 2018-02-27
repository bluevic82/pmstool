package com.tinhvan.model;

import java.util.ArrayList;
import java.util.List;


public class TimeSheetDetail_List {

	private List<TimeSheetDetail> timeSheetDetail =new ArrayList<TimeSheetDetail>();
	private MemberProject memberProject = new MemberProject();
	
	
	public List<TimeSheetDetail> getTimeSheetDetail() {
		return timeSheetDetail;
	}
	public void setTimeSheetDetail(List<TimeSheetDetail> timeSheetDetail) {
		this.timeSheetDetail = timeSheetDetail;
	}
	public MemberProject getMemberProject() {
		return memberProject;
	}
	public void setMemberProject(MemberProject memberProject) {
		this.memberProject = memberProject;
	}
	
	
}
