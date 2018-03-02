package com.tinhvan.dao;

import java.util.List;

import com.tinhvan.model.Process;

public interface ProcessDao {
	
	public List<Process> getAll();
	public Process getProcessByProcessId(int process_id);
		
}
