package com.tinhvan.dao;

import java.util.List;

import com.tinhvan.model.MileStone;

public interface MileStoneDao {
	
	public List<MileStone> getAllMileStone();
	
	public void addMileStone(MileStone mileStone);
	
	public void deleteMidelStone(MileStone MileStone);
	
	// update multiple mileStone
	public void updateMilestone(List<MileStone> mileStone);
	public List<MileStone> getMileStoneByProjectId(int id);
	
}
