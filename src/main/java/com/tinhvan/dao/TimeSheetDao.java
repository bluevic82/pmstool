package com.tinhvan.dao;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.tinhvan.model.TimeSheetDetail;
import com.tinhvan.model.TimeSheet_Info;

public interface TimeSheetDao {
	
	public List<TimeSheetDetail> getAllTimeSheet();
	
	public void approveTimeSheet(TimeSheetDetail timeSheetDetail);
	
	public void deleteTimeSheet(TimeSheetDetail timeSheetDetail);
	
	public TimeSheetDetail getById(int id);
	
	/*Test Daik*/
	public boolean updateListTimeSheetToDB(ArrayList<TimeSheetDetail> list_TimeSheetDetails);
	public boolean createListTimeSheet(ArrayList<TimeSheetDetail> list_TimeSheetDetails);
	public boolean deleteListTimeSheet(ArrayList<TimeSheetDetail> list_TimeSheetDetails_Delete);
	
	public List<TimeSheet_Info> getAllTimeSheetInfor();
	public boolean updateStatusOfListTimeSheetDetails(ArrayList<TimeSheetDetail> list_TimeSheetDetails);
	public List<TimeSheetDetail> getTimeSheetDetailsNoConditionsOfPM(int user_id_PM);
	
	public List<TimeSheetDetail> getListTimeSheetDetailsOfOneProjectOfCurrentUserHaveStatusAreRequestAndReject(int project_id, int user_id);
	
}
