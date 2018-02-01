package com.tinhvan.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	/* Test DaiK */
	@Override
	public List<TimeSheetDetail> getListTimeSheetOfOneProject(int projectId) {
		/* List TimeSheet of one Project */
		List<TimeSheetDetail> list_TimeSheetOfOneProject = new ArrayList<TimeSheetDetail>();

		List<Integer> list_TimeSheetId = new ArrayList<Integer>();
		list_TimeSheetId.addAll(getListTimeSheetId(projectId));// OK
		System.out
				.println("size list_TimeSheetId = " + list_TimeSheetId.size());

		for (int i = 0; i < list_TimeSheetId.size(); i++) {

			System.out.println("value list_TimeSheetId at i = "
					+ list_TimeSheetId.get(i));

			List<TimeSheetDetail> listTimeSheetDetailOfTs_Id = new ArrayList<TimeSheetDetail>();
			listTimeSheetDetailOfTs_Id
					.addAll(getListTimeSheetByTimeSheetId(list_TimeSheetId
							.get(i)));
			System.out.println("size listTimeSheetDetailOfTs_Id = "
					+ listTimeSheetDetailOfTs_Id.size());
			
			

			for (int j = 0; j < listTimeSheetDetailOfTs_Id.size(); j++) {

				List<String> list_Name_Of_Id = new ArrayList<String>();

				String pre_defined_name = getPreDifinedName(listTimeSheetDetailOfTs_Id
						.get(j).getPre_defined_id());
				String process_name = getProcessName(listTimeSheetDetailOfTs_Id
						.get(j).getProcess_id());
				String type_name = getTypeName(listTimeSheetDetailOfTs_Id
						.get(j).getType_id());

				System.out.println("value of timeSheetDetailOfTs_Id = "+ listTimeSheetDetailOfTs_Id.get(j)
						.getDetail_timesheet_id());
				
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

		System.out.println("size of list_TimeSheetOfOneProject = "
				+ list_TimeSheetOfOneProject.size());

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
		// Test
		for (int i = 0; i < List_timeSheetDetail_Of_timeSheetId.size(); i++) {
			System.out.println("HOUR of listTSByTSId = "
					+ List_timeSheetDetail_Of_timeSheetId.get(i).getHour());
			System.out.println("date of listTSByTSId = "
					+ List_timeSheetDetail_Of_timeSheetId.get(i)
							.getDetail_timesheet_date());
		}

		return List_timeSheetDetail_Of_timeSheetId;

	}
	
	/* get list detailTimesheetId of one Ts_ID */
/*	public List<Integer> getListDetailTimeSheetId(int timesheet_Id) {
		String sql = "SELECT PRE_DEFINED_NAME FROM pre_defined WHERE PRE_DEFINED_ID = ?";
		return  jdbcTemplate.queryForList(sql,
				new Object[] { timesheet_Id }, Integer.class);

	}*/


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
