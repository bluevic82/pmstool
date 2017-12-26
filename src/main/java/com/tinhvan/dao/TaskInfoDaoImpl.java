package com.tinhvan.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
	public List<TaskInfo> getAllTask() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("SELECT * FROM task_info", new RowMapper<TaskInfo>() {

			@Override
			public TaskInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				TaskInfo taskInfo = new TaskInfo();
				taskInfo.setTask_id(rs.getInt(1));
				taskInfo.setTask_subject(rs.getString(2));
				taskInfo.setType_id(rs.getInt(3));
				taskInfo.setStatus_id(rs.getInt(4));
				taskInfo.setTask_done(rs.getInt(5));
				taskInfo.setTask_from(rs.getString(6));
				taskInfo.setTask_to(rs.getString(7));
				taskInfo.setTask_solution(rs.getString(8));
				taskInfo.setTask_description(rs.getString(9));
				taskInfo.setMember_project_id(rs.getInt(10));
				taskInfo.setCategory_id(rs.getInt(11));
				taskInfo.setTask_priority(rs.getString(12));
				taskInfo.setProject_id(rs.getInt(13));
				return taskInfo;
			}
			
		});
	}

	@Override
	public void addTask(TaskInfo task) {
		// TODO Auto-generated method stub
		String sql="INSERT INTO task_info ("
				+ "TASK_SUBJECT, "
				+ "TYPE_ID, "
				+ "STATUS_ID, "
				+ "TASK_DONE,"
				+ "TASK_FROM, "
				+ "TASK_TO, "
				+ "TASK_DESCRIPTION, "
				+ "TASK_Solution, "
				+ "MEMBER_PROJECT_ID, "
				+ "TASK_PRIORITY, "
				+ "CATEGORY_ID, "
				+ "PROJECT_ID)"+"VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, new Object[] {
				task.getTask_subject(), 
				task.getType_id(), 
				task.getStatus_id(), 
				task.getTask_done(), 
				task.getTask_from(), 
				task.getTask_to(), 
				task.getTask_description(), 
				task.getTask_solution(), 
				task.getMember_project_id(),
				task.getTask_priority(), 
				task.getCategory_id(),
				task.getProject_id()
			});
	}

	@Override
	public void updateTask(TaskInfo task) {
		String sql="update task_info set TASK_SUBJECT='"+task.getTask_subject()+"', TYPE_ID="+task.getType_id()+",  STATUS_ID="+task.getStatus_id()+",  TASK_DONE="+task.getTask_done()+",  TASK_FROM='"+task.getTask_from()+"',  TASK_TO='"+task.getTask_to()+"',  TASK_SOLUTION='"+task.getTask_solution()+"',  TASK_DESCRIPTION='"+task.getTask_description()+"',  MEMBER_PROJECT_ID="+task.getMember_project_id()+",  CATEGORY_ID="+task.getCategory_id()+",  TASK_PRIORITY='"+task.getTask_priority()+"',  PROJECT_ID="+task.getProject_id()+" where task_id="+task.getTask_id()+""; 
		jdbcTemplate.update(sql);
		
	}

	@Override
	public TaskInfo getTaskById(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM task_info where TASK_ID = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] {id}, new BeanPropertyRowMapper<TaskInfo>(TaskInfo.class));
	}

	
}
