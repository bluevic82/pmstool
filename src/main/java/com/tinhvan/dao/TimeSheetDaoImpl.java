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

import com.tinhvan.model.MemberProject;
import com.tinhvan.model.TimeSheetDetail;
import com.tinhvan.model.TimeSheet_Info;

@Transactional
@Repository
public class TimeSheetDaoImpl implements TimeSheetDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	UserDao userDao;
	@Autowired
	MemberProjectDao memberProjectDao;
	private int project_id;
	private int user_id;
	private int member_project_id;
	
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
	public List<TimeSheetDetail> getListTimeSheetOfOneProject(int _projectId, int _user_id) {
		//set public var project_id = _projectId: purpose => use in function createListTimeSheet()
		project_id = _projectId;
		user_id = _user_id;
		
		/* List TimeSheet of one Project */
		List<TimeSheetDetail> list_TimeSheetOfOneProject = new ArrayList<TimeSheetDetail>();

		//List<Integer> list_TimeSheetId = new ArrayList<Integer>();
		try{
			int ts_id = getTimeSheetId(project_id, user_id).getTs_id();// OK

			//for (int i = 0; i < list_TimeSheetId.size(); i++) {

				List<TimeSheetDetail> listTimeSheetDetailOfTs_Id = new ArrayList<TimeSheetDetail>();
				listTimeSheetDetailOfTs_Id
						.addAll(getListTimeSheetByTimeSheetId(ts_id));
				
				

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
		catch(Exception e){
			return null;
		}
		

		return list_TimeSheetOfOneProject;
	}
	
	

	/* get List TimeSheet Id by project Id */
	public TimeSheet_Info getTimeSheetId(int _project_id, int _user_id) {
		
		try{
			MemberProject memberProject = memberProjectDao.getMemberProjectByProject_Id_And_UserCurrentLogged(_project_id, _user_id);
			//set value for global var member_project_id
			member_project_id = memberProject.getMember_project_id();
			System.out.println("member_id = "+member_project_id);
			
			String sql = "SELECT * FROM timesheet_info WHERE PROJECT_ID ="+_project_id+" AND MEMBER_PROECT_ID="+ member_project_id + "";
						
			return jdbcTemplate.queryForObject(sql, new RowMapper<TimeSheet_Info>() {

				
				@Override
				public TimeSheet_Info mapRow(ResultSet rs, int rowNum)
						throws SQLException {
					TimeSheet_Info timeSheet_Info = new TimeSheet_Info();
					timeSheet_Info.setTs_id(rs.getInt(1));
					timeSheet_Info.setProject_id(rs.getInt(2));
					timeSheet_Info.setMember_project_id(rs.getInt(3));
					return timeSheet_Info;
				}
			});
		}
		catch(Exception e){
			return null;
		}
	}

	/* get Object TimeSheetDetail of one DetailTimeSheet_Id */
	public List<TimeSheetDetail> getListTimeSheetByTimeSheetId(int timeSheetId) {
		
		
		if(timeSheetId == 0){
			return null;
		}
		else{
			String sql = "SELECT * FROM detail_timesheet WHERE TS_ID = "
					+ timeSheetId + "";
			List<TimeSheetDetail> List_timeSheetDetail_Of_timeSheetId = jdbcTemplate
					.query(sql, new BeanPropertyRowMapper<TimeSheetDetail>(
							TimeSheetDetail.class));
			
			return List_timeSheetDetail_Of_timeSheetId;
		}

	}



	@Override
	public void updateListTimeSheetToDB( 
			ArrayList<TimeSheetDetail> list_TimeSheetDetails) {
		// TODO Auto-generated method stub
		
		
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
	public void createListTimeSheet(ArrayList<TimeSheetDetail> list_TimeSheetDetails){
		// TODO Auto-generated method stub
		System.out.println(member_project_id);
			
			/*if(memberProjectDao.getMemberProjectByProject_Id_And_UserCurrentLogged(project_id, user_id) == null){
				jdbcTemplate.execute("INSERT INTO member_project()");
			}*/
			
			//get timesheet_id by project_id and member_project_id
			//if timesheet_id ==0 => create timesheet at table timesheet_infor
			if(getTimeSheet_idByProject_idAndMemberProjectId(project_id, member_project_id)==0){
				
				jdbcTemplate.execute("INSERT INTO timesheet_info(PROJECT_ID, MEMBER_PROECT_ID) VALUES ("+project_id+", "+member_project_id+")");
			}
			
			//get timesheet_id at table timesheet_info
			int timesheet_id = getTimeSheet_idByProject_idAndMemberProjectId(project_id, member_project_id);
			
		// insert timsheet_detail into table detail_timesheet
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
				ps.setInt(8, timesheet_id);

			}

			@Override
			public int getBatchSize() {
				// TODO Auto-generated method stub
				return list_TimeSheetDetails.size();
			}
		});
		
	}
	
	//get timesheet_id by project_id and member_project_id
	public int getTimeSheet_idByProject_idAndMemberProjectId(int project_id, int member_project_id){
		System.out.println("pid = "+project_id+" and mem_id = "+ member_project_id);
		try{
			int timeSheet_id = jdbcTemplate
					.queryForObject("SELECT TS_ID FROM timesheet_info WHERE PROJECT_ID = " + project_id + " AND MEMBER_PROECT_ID = "+member_project_id+"", Integer.class);
			return timeSheet_id;
		}
		catch(Exception e){
			return 0;
		}
		
	}

	@Override
	public void deleteListTimeSheet(
			ArrayList<TimeSheetDetail> list_TimeSheetDetails_Delete) {
		
		//if delete all data of table detail_timesheet => also delete at table timesheet_info 
		int sizeOfData_In_Table_Detail_TimeSheet_By_Ts_Id = jdbcTemplate.queryForObject("SELECT COUNT(DETAIL_TIMESHEET_ID) FROM detail_timesheet WHERE TS_ID = "+list_TimeSheetDetails_Delete.get(0).getTs_id()+"", Integer.class);
		System.out.println("size = "+sizeOfData_In_Table_Detail_TimeSheet_By_Ts_Id);
		if(sizeOfData_In_Table_Detail_TimeSheet_By_Ts_Id == list_TimeSheetDetails_Delete.size()){
			jdbcTemplate.execute("DELETE FROM timesheet_info WHERE TS_ID = "+list_TimeSheetDetails_Delete.get(0).getTs_id()+"");
		}
		
		String sql_deleteList = "DELETE FROM detail_timesheet WHERE DETAIL_TIMESHEET_ID = ?";
		jdbcTemplate.batchUpdate(sql_deleteList, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				// TODO Auto-generated method stub
				ps.setInt(1, list_TimeSheetDetails_Delete.get(i).getDetail_timesheet_id());

			}

			@Override
			public int getBatchSize() {
				// TODO Auto-generated method stub
				return list_TimeSheetDetails_Delete.size();
			}
		});
		
	}
	/* get PreDifined_Name by pre_defined_id */
	public String getPreDifinedName(int pre_defined_id) {
		String sql = "SELECT PRE_DEFINED_NAME FROM pre_defined WHERE PRE_DEFINED_ID = ?";
		try{

			return (String) jdbcTemplate.queryForObject(sql,
					new Object[] { pre_defined_id }, String.class);
		}
		catch(Exception e){
			return null;
		}

	}

	/* get Process_Name by process_id */
	public String getProcessName(int process_id) {
		String sql = "SELECT PROCESS_NAME FROM process WHERE PROCESS_ID = ?";
		try{
			return jdbcTemplate.queryForObject(sql, new Object[] { process_id },
					new RowMapper<String>() {
						public String mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							return rs.getString("PROCESS_NAME");
						}

					});
		}catch(Exception e){
			return null;
		}
		

	}

	/* get TypeName by type_id */
	public String getTypeName(int type_id) {
		String sql = "SELECT TYPE_NAME FROM type WHERE TYPE_ID = ?";
		try{
			return jdbcTemplate.queryForObject(sql, new Object[] { type_id },
					new RowMapper<String>() {
						public String mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							return rs.getString("TYPE_NAME");
						}

					});
		}
		catch(Exception e){
			return null;
		}
		
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
