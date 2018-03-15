package com.tinhvan.dao;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.tinhvan.model.TimeSheetDetail;
import com.tinhvan.model.TimeSheet_Info;

public interface TimeSheetDao {
	
	public List<TimeSheetDetail> getAllTimeSheet(int project_id, int user_id_member_project, int process_id, String status_name);
	
	public void approveTimeSheet(TimeSheetDetail timeSheetDetail);
	
	public void deleteTimeSheet(TimeSheetDetail timeSheetDetail);
	
	public TimeSheetDetail getById(int id);
	
	/*Test Daik*/
	/*public List<TimeSheetDetail> getListTimeSheetOfOneProject(int projectId, int user_id);*/
	public void updateListTimeSheetToDB(ArrayList<TimeSheetDetail> list_TimeSheetDetails);
	public void createListTimeSheet(ArrayList<TimeSheetDetail> list_TimeSheetDetails);
	public void deleteListTimeSheet(ArrayList<TimeSheetDetail> list_TimeSheetDetails_Delete);
	
	public List<TimeSheet_Info> getAllTimeSheetInfor();
	/*public List<TimeSheetDetail> getListTimeSheetByTimeSheetId(int timeSheetId);*/
	/*public List<TimeSheet_Info> getListTimeSheet_InfosByProjectId(int project_id);*/
	/*public List<TimeSheet_Info> getListTimeSheet_InfosByMemberProjectId(int member_project_id);*/
	//public List<TimeSheetDetail> getAllTimeSheets
	public void updateStatusOfListTimeSheetDetails(ArrayList<TimeSheetDetail> list_TimeSheetDetails);
	public List<TimeSheetDetail> getTimeSheetDetailsHaveConditionsOfPM(int project_id, int member_project_id, int process_id, String status_name, int user_id_PM);
	public List<TimeSheetDetail> getTimeSheetDetailsNoConditionsOfPM(int user_id_PM);
	
	public List<TimeSheetDetail> getListTimeSheetDetailsOfOneProjectOfCurrentUserHaveStatusAreRequestAndReject(int project_id, int user_id);
	
}
