package com.tinhvan.dao;

import java.util.ArrayList;
import java.util.List;
import com.tinhvan.model.TimeSheetDetail;

public interface TimeSheetDao {
	
	public List<TimeSheetDetail> getAllTimeSheet();
	
	public void approveTimeSheet(TimeSheetDetail timeSheetDetail);
	
	public void deleteTimeSheet(TimeSheetDetail timeSheetDetail);
	
	public TimeSheetDetail getById(int id);
	
	/*Test Daik*/
	public List<TimeSheetDetail> getListTimeSheetOfOneProject(int projectId, int user_id);
	public void updateListTimeSheetToDB(ArrayList<TimeSheetDetail> list_TimeSheetDetails);
	public void createListTimeSheet(ArrayList<TimeSheetDetail> list_TimeSheetDetails);
	public void deleteListTimeSheet(ArrayList<TimeSheetDetail> list_TimeSheetDetails_Delete);
	
}
