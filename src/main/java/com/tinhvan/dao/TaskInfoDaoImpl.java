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
	public List<TaskInfo> listAllTask() {

		return jdbcTemplate.query("SELECT TASK_ID, TASK_SUBJECT, TYPE_ID, STATUS_ID, TASK_DONE, TASK_FROM, TASK_TO, TASK_DESCRIPTION, TASK_Solution, MEMBER_PROJECT_ID FROM task_info", new RowMapper<TaskInfo>() {

			@Override
			public TaskInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				TaskInfo taskInfo = new TaskInfo();
				taskInfo.setId(rs.getInt(1));
				taskInfo.setType_id(rs.getInt(2));
				taskInfo.setStatus_id(rs.getInt(3));
				taskInfo.setDone(rs.getInt(4));
				taskInfo.setTask_from(rs.getString(5));
				taskInfo.setTask_to(rs.getString(6));
				taskInfo.setTask_subject(rs.getString(7));
				taskInfo.setTask_description(rs.getString(8));
				taskInfo.setTask_solution(rs.getString(9));
				taskInfo.setMember_project_id(rs.getInt(10));
				taskInfo.setTask_priority(rs.getString(11));
				taskInfo.setCategory_id(rs.getInt(12));
				taskInfo.setProject_id(rs.getInt(13));
				return taskInfo;
			}

		});

	}

/*	private SqlParameterSource getParameterByModel(TaskInfo taskInfo) {
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		if (taskInfo != null) {
			parameterSource.addValue("TASK_ID", taskInfo.getId());
			parameterSource.addValue("TYPE_ID", taskInfo.getType_id());
			parameterSource.addValue("STATUS_ID", taskInfo.getStatus_id());
			parameterSource.addValue("TASK_DONE", taskInfo.getDone());
			parameterSource.addValue("TASK_FROM", taskInfo.getTask_from());
			parameterSource.addValue("TASK_TO", taskInfo.getTask_to());
			parameterSource.addValue("TASK_SUBJECT", taskInfo.getTask_subject());
			parameterSource.addValue("TASK_DESCRIPTION", taskInfo.getTask_description());
			parameterSource.addValue("TASK_Solution", taskInfo.getTask_solution());
			parameterSource.addValue("MEMBER_PROJECT_ID", taskInfo.getMember_project_id());
			parameterSource.addValue("TASK_PRIORITY", taskInfo.getTask_priority());
			parameterSource.addValue("CATEGORY_ID", taskInfo.getCategory_id());
			parameterSource.addValue("PROJECT_ID", taskInfo.getProject_id());
		}
		return parameterSource;
	}*/

	@Override
	public void addTask(TaskInfo taskInfo) {
		String sql="INSERT INTO task_info (TASK_SUBJECT, TYPE_ID, STATUS_ID, TASK_DONE, TASK_FROM, TASK_TO, TASK_DESCRIPTION, TASK_Solution, MEMBER_PROJECT_ID, TASK_PRIORITY, CATEGORY_ID, PROJECT_ID)"+"VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, new Object[] {taskInfo.getTask_subject(), taskInfo.getType_id(), taskInfo.getStatus_id(), taskInfo.getDone(), taskInfo.getTask_from(), taskInfo.getTask_to(), taskInfo.getTask_description(), taskInfo.getTask_solution(), taskInfo.getMember_project_id(),
						taskInfo.getTask_priority(), taskInfo.getCategory_id(), taskInfo.getProject_id()});
	}

	@Override
	public void updateTask(TaskInfo taskInfo) {
		// TODO Auto-generated method stub

	}

}
