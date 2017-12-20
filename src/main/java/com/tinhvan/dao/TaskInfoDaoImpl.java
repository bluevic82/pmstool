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
	public List<TaskInfo> detailTask() {
		
		return jdbcTemplate.query("SELECT TASK_ID, "
				+ "TASK_SUBJECT, "
				+ "MEMBER_PROJECT_ID, "
				+ "TASK_PRIORITY, "
				+ "TASK_TO, STATUS_ID, "
				+ "TASK_DONE, "
				+ "TASK_DESCRIPTION "
				+ "FROM task_info", new RowMapper<TaskInfo>() {

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
				taskInfo.setTask_done(rs.getInt(7));
				taskInfo.setTask_description(rs.getString(8));
				return taskInfo;
			}
			
		});
	}

	@Override
	public void addTask(TaskInfo taskInfo) {
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
				taskInfo.getTask_subject(), 
				taskInfo.getType_id(), 
				taskInfo.getStatus_id(), 
				taskInfo.getTask_done(), 
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
	public int updateTask(TaskInfo taskInfo) {
		String sql = "UPDATE task_info SET TASK_SUBJECT='"+taskInfo.getTask_subject()
		+"',TYPE_ID ='"+taskInfo.getType_id()
		+"',STATUS_ID = '"+taskInfo.getStatus_id()
		+"',TASK_DONE = '"+taskInfo.getTask_done()
		+"',TASK_FROM = '"+taskInfo.getTask_from()
		+"',TASK_TO = '"+taskInfo.getTask_to()
		+"',TASK_DESCRIPTION = '"+taskInfo.getTask_description()
		+"',TASK_Solution = '"+taskInfo.getTask_solution()
		+"',MEMBER_PROJECT_ID = '"+taskInfo.getMember_project_id()
		+"',CATEGORY_ID = '"+taskInfo.getCategory_id()
		+"',TASK_PRIORITY = '"+taskInfo.getTask_priority()
		+"',PROJECT_ID = '"+taskInfo.getProject_id()
		+"'WHERE TASK_ID = "+taskInfo.getId();
		return jdbcTemplate.update(sql);
	}

	@Override
	public TaskInfo getTaskById(int id) {
		String sql = "SELECT * FROM task_info where TASK_ID = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] {id}, new BeanPropertyRowMapper<TaskInfo>(TaskInfo.class));
	}

	
}
