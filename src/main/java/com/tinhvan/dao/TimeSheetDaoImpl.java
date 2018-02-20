package com.tinhvan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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

	/* Test DaiK */
	@Override
	public List<TimeSheetDetail> getListTimeSheetOfOneProject(int projectId) {
		/* List TimeSheet of one Project */
		List<TimeSheetDetail> list_TimeSheetOfOneProject = new ArrayList<TimeSheetDetail>();

		List<Integer> list_TimeSheetId = new ArrayList<Integer>();
		list_TimeSheetId.addAll(getListTimeSheetId(projectId));// OK

		for (int i = 0; i < list_TimeSheetId.size(); i++) {

			List<TimeSheetDetail> listTimeSheetDetailOfTs_Id = new ArrayList<TimeSheetDetail>();
			listTimeSheetDetailOfTs_Id
					.addAll(getListTimeSheetByTimeSheetId(list_TimeSheetId
							.get(i)));
			
			

			for (int j = 0; j < listTimeSheetDetailOfTs_Id.size(); j++) {

				List<String> list_Name_Of_Id = new ArrayList<String>();

				String pre_defined_name = getPreDifinedName(listTimeSheetDetailOfTs_Id
						.get(j).getPre_defined_id());
				String process_name = getProcessName(listTimeSheetDetailOfTs_Id
						.get(j).getProcess_id());
				String type_name = getTypeName(listTimeSheetDetailOfTs_Id
						.get(j).getType_id());
				
				String task_subject = null;
				
				try{
					String sql_GetTaskSubject = "SELECT task_info.TASK_SUBJECT FROM task_info INNER JOIN detail_timesheet "
							+ "ON task_info.TASK_ID = detail_timesheet.TASK_ID WHERE detail_timesheet.DETAIL_TIMESHEET_ID = ?";
							
					
					task_subject = jdbcTemplate.queryForObject(
							sql_GetTaskSubject,
							new Object[] { listTimeSheetDetailOfTs_Id.get(j)
									.getDetail_timesheet_id() }, String.class);
				}
				catch(Exception e){
					 task_subject = null;
				}

				
				
				list_Name_Of_Id.add(pre_defined_name);
				list_Name_Of_Id.add(process_name);
				list_Name_Of_Id.add(type_name);
				list_Name_Of_Id.add(task_subject);

				listTimeSheetDetailOfTs_Id.get(j).setList_Name_Of_Id(
						list_Name_Of_Id);
				// requiredType, args);

				list_TimeSheetOfOneProject.add(listTimeSheetDetailOfTs_Id
						.get(j));

			}

		}

		return list_TimeSheetOfOneProject;
	}

	/* get List TimeSheet Id by project Id */
	public List<Integer> getListTimeSheetId(int projectId) {
		List<Integer> list_TimeSheetId = new ArrayList<Integer>();
		String sql = "SELECT TS_ID FROM timesheet_info WHERE PROJECT_ID = "
				+ projectId + "";
		list_TimeSheetId = jdbcTemplate.queryForList(sql, Integer.class); // OK

		return list_TimeSheetId;
	}

	/* get Object TimeSheetDetail of one DetailTimeSheet_Id */
	public List<TimeSheetDetail> getListTimeSheetByTimeSheetId(int timeSheetId) {
		String sql = "SELECT * FROM detail_timesheet WHERE TS_ID = "
				+ timeSheetId + "";
		List<TimeSheetDetail> List_timeSheetDetail_Of_timeSheetId = jdbcTemplate
				.query(sql, new BeanPropertyRowMapper<TimeSheetDetail>(
						TimeSheetDetail.class));

		return List_timeSheetDetail_Of_timeSheetId;

	}


	/* get PreDifined_Name by pre_defined_id */
	public String getPreDifinedName(int pre_defined_id) {
		String sql = "SELECT PRE_DEFINED_NAME FROM pre_defined WHERE PRE_DEFINED_ID = ?";
		return (String) jdbcTemplate.queryForObject(sql,
				new Object[] { pre_defined_id }, String.class);

	}

	/* get Process_Name by process_id */
	public String getProcessName(int process_id) {
		String sql = "SELECT PROCESS_NAME FROM process WHERE PROCESS_ID = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { process_id },
				new RowMapper<String>() {
					public String mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						return rs.getString("PROCESS_NAME");
					}

				});

	}

	/* get TypeName by type_id */
	public String getTypeName(int type_id) {
		String sql = "SELECT TYPE_NAME FROM type WHERE TYPE_ID = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { type_id },
				new RowMapper<String>() {
					public String mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						return rs.getString("TYPE_NAME");
					}

				});
	}

	@Override
	public void updateListTimeSheetToDB(
			ArrayList<TimeSheetDetail> list_TimeSheetDetails) {
		// TODO Auto-generated method stub
		
		ArrayList<TimeSheetDetail> list_TimeSheetDetails_toInsert = new ArrayList<TimeSheetDetail>();
		for(int i = 0 ; i < list_TimeSheetDetails.size(); i++){
			if(list_TimeSheetDetails.get(i).getDetail_timesheet_id() == 0){
				list_TimeSheetDetails_toInsert.add(list_TimeSheetDetails.get(i));
			}
		}
		//add new member to DB
		createListTimeSheet(list_TimeSheetDetails_toInsert, list_TimeSheetDetails.get(0).getTs_id());//all same ts_id
		
		// Update member
		String sql_update = "update detail_timesheet set DETAIL_TIMESHEET_DATE = ?, HOUR = ?, PRE_DEFINED_ID = ?, PROCESS_ID = ?, TYPE_ID = ?, TASK_ID = ?, WORKCONTENT = ? where DETAIL_TIMESHEET_ID = ?";
		jdbcTemplate.batchUpdate(sql_update, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				// TODO Auto-generated method stub
				//MemberProject memberProject = list_MemberProjects.get(i);
				ps.setString(1, list_TimeSheetDetails.get(i).getDetail_timesheet_date());
				ps.setFloat(2, list_TimeSheetDetails.get(i).getHour());
				ps.setInt(3, list_TimeSheetDetails.get(i).getPre_defined_id());
				ps.setInt(4, list_TimeSheetDetails.get(i).getProcess_id());
				ps.setInt(5, list_TimeSheetDetails.get(i).getType_id());
				ps.setInt(6, list_TimeSheetDetails.get(i).getTask_id());
				ps.setString(7, list_TimeSheetDetails.get(i).getWorkcontent());
				ps.setInt(8, list_TimeSheetDetails.get(i).getDetail_timesheet_id());
				
			}

			@Override
			public int getBatchSize() {
				// TODO Auto-generated method stub
				return list_TimeSheetDetails.size();
			}
		});
		
		
	}

	@Override
	public void createListTimeSheet(
			ArrayList<TimeSheetDetail> list_TimeSheetDetails, int _ts_id) {
		// TODO Auto-generated method stub
		
		String sql_insert = "INSERT INTO detail_timesheet(DETAIL_TIMESHEET_DATE, HOUR, PRE_DEFINED_ID, PROCESS_ID, TYPE_ID, TASK_ID, WORKCONTENT, TS_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.batchUpdate(sql_insert, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				// TODO Auto-generated method stub
				//MemberProject memberProject = list_MemberProjects.get(i);
				ps.setString(1, list_TimeSheetDetails.get(i).getDetail_timesheet_date());
				ps.setFloat(2, list_TimeSheetDetails.get(i).getHour());
				ps.setInt(3, list_TimeSheetDetails.get(i).getPre_defined_id());
				ps.setInt(4, list_TimeSheetDetails.get(i).getProcess_id());
				ps.setInt(5, list_TimeSheetDetails.get(i).getType_id());
				ps.setInt(6, list_TimeSheetDetails.get(i).getTask_id());
				ps.setString(7, list_TimeSheetDetails.get(i).getWorkcontent());
				ps.setInt(8, _ts_id);

			}

			@Override
			public int getBatchSize() {
				// TODO Auto-generated method stub
				return list_TimeSheetDetails.size();
			}
		});
		
	}

	@Override
	public void deleteListTimeSheet(
			ArrayList<Integer> list_TimeSheetDetails_id) {
		String sql_deleteList = "DELETE FROM detail_timesheet WHERE DETAIL_TIMESHEET_ID = ?";
		jdbcTemplate.batchUpdate(sql_deleteList, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				// TODO Auto-generated method stub
				ps.setInt(1, list_TimeSheetDetails_id.get(i));

			}

			@Override
			public int getBatchSize() {
				// TODO Auto-generated method stub
				return list_TimeSheetDetails_id.size();
			}
		});
		
	}

	/* get task_Name */
	/*
	 * public String getTaskName(int type_id) { String sql =
	 * "SELECT TYPE_NAME FROM type WHERE TYPE_ID = ?"; return
	 * jdbcTemplate.queryForObject(sql, new Object[] { type_id }, new
	 * RowMapper<String>() { public String mapRow(ResultSet rs, int rowNum)
	 * throws SQLException { return rs.getString("TYPE_NAME"); }
	 * 
	 * }); }
	 */
}
