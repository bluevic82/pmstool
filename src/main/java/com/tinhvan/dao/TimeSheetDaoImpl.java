package com.tinhvan.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tinhvan.model.TimeSheetDetail;


@Transactional
@Repository
public class TimeSheetDaoImpl implements TimeSheetDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<TimeSheetDetail> getAllTimeSheet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveTimeSheet(TimeSheetDetail timeSheetDetail) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void approveTimeSheet(TimeSheetDetail timeSheetDetail) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteTimeSheet(TimeSheetDetail timeSheetDetail) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TimeSheetDetail getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
