package com.tinhvan.dao;

import java.util.List;

import com.tinhvan.model.TimeSheetDetail;

public interface TimeSheetDao {
	
	public List<TimeSheetDetail> getAllTimeSheet();
	
	public void saveTimeSheet(TimeSheetDetail timeSheetDetail);
	
	public void approveTimeSheet(TimeSheetDetail timeSheetDetail);
	
	public void deleteTimeSheet(TimeSheetDetail timeSheetDetail);
	
	public TimeSheetDetail getById(int id);
	
}
