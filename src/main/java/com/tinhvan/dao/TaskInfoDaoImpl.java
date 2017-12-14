package com.tinhvan.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tinhvan.model.TaskInfo;

@Repository
@Transactional
public class TaskInfoDaoImpl implements TaskInfoDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<TaskInfo> detailTask() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("SELECT TASK_ID, TASK_SUBJECT, MEMBER_PROJECT_ID, TASK_PRIORITY, TASK_TO, STATUS_ID, TASK_DONE, TASK_DESCRIPTION FROM task_info", new RowMapper<TaskInfo>() {

			@Override
			public TaskInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				TaskInfo taskInfo = new TaskInfo();
				taskInfo.setId(rs.getInt(1));
				taskInfo.setTask_subject(rs.getString(2));
				taskInfo.setMember_project_id(rs.getInt(3));
				taskInfo.setTask_priority(rs.getString(4));
				taskInfo.setTask_to(rs.getString(5));
				taskInfo.setStatus_id(rs.getInt(6));
				taskInfo.setDone(rs.getInt(7));
				taskInfo.setTask_description(rs.getString(8));
				return taskInfo;
			}
			
		});
	}

	@Override
	public void addTask(TaskInfo taskInfo) {
		// TODO Auto-generated method stub
		String sql="INSERT INTO task_info (TASK_SUBJECT, TYPE_ID, STATUS_ID, TASK_DONE, TASK_FROM, TASK_TO, TASK_DESCRIPTION, TASK_Solution, MEMBER_PROJECT_ID, TASK_PRIORITY, CATEGORY_ID, PROJECT_ID)"+"VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, new Object[] {
				taskInfo.getTask_subject(), 
				taskInfo.getType_id(), 
				taskInfo.getStatus_id(), 
				taskInfo.getDone(), 
				taskInfo.getTask_from(), 
				taskInfo.getTask_to(), 
				taskInfo.getTask_description(), 
				taskInfo.getTask_solution(), 
				taskInfo.getMember_project_id(),
				taskInfo.getTask_priority(), 
				taskInfo.getCategory_id(),
				taskInfo.getProject_id()
			});
	}

	@Override
	public void updateTask(TaskInfo taskInfo) {
		// TODO Auto-generated method stub
		
	}

	
}
