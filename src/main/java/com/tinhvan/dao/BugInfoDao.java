package com.tinhvan.dao;

import java.util.List;

import com.tinhvan.model.BugInfo;

public interface BugInfoDao {
	
	public List<BugInfo> getAllBug();
	
	public void addBug(BugInfo bugInfo);
	
	public void updateBug(BugInfo bugInfo);
	
	public BugInfo getBugById(int id);
	
	public List<BugInfo> getBugByIdPro(int id);
}
