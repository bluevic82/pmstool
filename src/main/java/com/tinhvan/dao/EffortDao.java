package com.tinhvan.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.tinhvan.model.Effort;
import com.tinhvan.model.ProjectInfo;

public interface EffortDao {
	public List<Effort> getAllEfort();
	public Effort getEffortById(int id);

}
