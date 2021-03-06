package com.tinhvan.dao;

import java.util.List;

import com.tinhvan.model.MileStone;

public interface MileStoneDao {
	
	public List<MileStone> getAllMileStone();
	
	public void addMileStone(MileStone mileStone);
	
	public void deleteMidelStone(int milestone_id);
	
	// update multiple mileStone
	public void insertMilestone(List<MileStone> mileStone);
	public void updateMilestone(List<MileStone> mileStone);
	public List<MileStone> getMileStoneByProjectId(int id);
	
}
