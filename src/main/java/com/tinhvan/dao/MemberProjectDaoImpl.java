package com.tinhvan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tinhvan.model.MemberProject;

@Repository
@Transactional
public class MemberProjectDaoImpl implements MemberProjectDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	RoleDao roleDao;

	@Override
	public List<MemberProject> getAllMember() {

		try{
			return jdbcTemplate.query("SELECT * FROM MEMBER_PROJECT",
					new RowMapper<MemberProject>() {

						@Override
						public MemberProject mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							MemberProject mp = new MemberProject();
							mp.setMember_project_id(rs.getInt(1));
							mp.setUser_id(rs.getInt(2));
							mp.setMember_project_name(rs.getString(3));
							mp.setRole_id(rs.getInt(4));
							mp.setMember_project_effort(rs.getInt(5));
							mp.setProject_id(rs.getInt(6));
							return mp;
						}
					});
		}
		catch(Exception e){
			return null;
		}
		
	}

	@Override
	public List<MemberProject> getMemberProjectByProjectId1(int id) {
		Object[] params = new Object[] { id };
		try{
			return jdbcTemplate.query(
					"SELECT * FROM MEMBER_PROJECT WHERE PROJECT_ID = ?", params,
					new RowMapper<MemberProject>() {

						@Override
						public MemberProject mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							MemberProject listMemberOfProject = new MemberProject();
							listMemberOfProject.setMember_project_id(rs.getInt(1));
							listMemberOfProject.setUser_id(rs.getInt(2));
							listMemberOfProject.setMember_project_name(rs
									.getString(3));
							listMemberOfProject.setRole_id(rs.getInt(4));
							listMemberOfProject.setMember_project_effort(rs
									.getInt(5));
							listMemberOfProject.setProject_id(rs.getInt(6));
							listMemberOfProject.setRole_name(roleDao
									.getRoleNameByRoleId(listMemberOfProject
											.getRole_id()));
							return listMemberOfProject;
						}
					});
		}
		catch(Exception e){
			return null;
		}
		
	}

	
	// method add multiple milestone
		@Override
		public void updateMemberProjectBy_PrjId(List<MemberProject> list_MemberProjects, int project_id) {
			List<MemberProject> list_MemberProjects_toInsert = new ArrayList<MemberProject>();
			for(int i = 0 ; i < list_MemberProjects.size(); i++){
				if(list_MemberProjects.get(i).getMember_project_id() == 0){
					list_MemberProjects_toInsert.add(list_MemberProjects.get(i));
				}
			}
			//add new member to DB
			addListMemberProjectToDB(list_MemberProjects_toInsert, project_id);
			
			// Update member
			String sql_update = "update member_project set USER_ID = ?, MEMBER_PROJECT_NAME = ?, ROLE_ID = ?, MEMBER_PROJECT_EFFORT = ?, PROJECT_ID = ? where MEMBER_PROJECT_ID = ?";
			jdbcTemplate.batchUpdate(sql_update, new BatchPreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement ps, int i)
						throws SQLException {
					// TODO Auto-generated method stub
					//MemberProject memberProject = list_MemberProjects.get(i);
					ps.setInt(1, list_MemberProjects.get(i).getUser_id());
					ps.setString(2, list_MemberProjects.get(i).getMember_project_name());
					ps.setInt(3, list_MemberProjects.get(i).getRole_id());
					ps.setFloat(4, list_MemberProjects.get(i).getMember_project_effort());
					ps.setInt(5, project_id);
					ps.setInt(6, list_MemberProjects.get(i).getMember_project_id());
					

				}

				@Override
				public int getBatchSize() {
					// TODO Auto-generated method stub
					return list_MemberProjects.size();
				}
			});
		}

	@Override
	public void deleteOneMemberProject(int memberProject_id) {
		// TODO Auto-generated method stub
		String sqlDelete = "DELETE FROM member_project WHERE MEMBER_PROJECT_ID = " +memberProject_id+ "";
		jdbcTemplate.execute(sqlDelete);
	}
	
	public void addListMemberProjectToDB(List<MemberProject> list_MemberProjects_toInsert, int project_id){
		String sql_insert = "INSERT INTO member_project(USER_ID, MEMBER_PROJECT_NAME, ROLE_ID, MEMBER_PROJECT_EFFORT, PROJECT_ID) VALUES (?, ?, ?, ?, ?)";
		jdbcTemplate.batchUpdate(sql_insert, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				// TODO Auto-generated method stub
				//MemberProject memberProject = list_MemberProjects.get(i);
				ps.setInt(1, list_MemberProjects_toInsert.get(i).getUser_id());
				ps.setString(2, list_MemberProjects_toInsert.get(i).getMember_project_name());
				ps.setInt(3, list_MemberProjects_toInsert.get(i).getRole_id());
				ps.setFloat(4, list_MemberProjects_toInsert.get(i).getMember_project_effort());
				ps.setInt(5, project_id);

			}

			@Override
			public int getBatchSize() {
				// TODO Auto-generated method stub
				return list_MemberProjects_toInsert.size();
			}
		});
		
	}

	@Override
	public MemberProject getMemberProjectByProject_Id_And_UserCurrentLogged(int project_id, int user_id) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM member_project WHERE USER_ID = "+user_id+" AND PROJECT_ID = "+project_id+"";
		
		//Object[] params = new Object[] {project_id,  user_id};
		try{
			return jdbcTemplate.queryForObject(sql, new RowMapper<MemberProject>() {

				@Override
				public MemberProject mapRow(ResultSet rs, int rowNum)
						throws SQLException {
					MemberProject memberProject = new MemberProject();
					memberProject.setMember_project_id(rs.getInt(1));
					memberProject.setUser_id(rs.getInt(2));
					memberProject.setMember_project_name(rs
							.getString(3));
					memberProject.setRole_id(rs.getInt(4));
					memberProject.setMember_project_effort(rs
							.getInt(5));
					memberProject.setProject_id(rs.getInt(6));
					return memberProject;
				}
			});
		}
		catch(Exception e){
			System.out.println("return null");
			//System.out.println("rs = "+memberProject);
			return null;
			
		}
		
		/*return jdbcTemplate.query(sql, params,
				new RowMapper<MemberProject>() {

					@Override
					public MemberProject mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						MemberProject memberProject = new MemberProject();
						memberProject.setMember_project_id(rs.getInt(1));
						memberProject.setUser_id(rs.getInt(2));
						memberProject.setMember_project_name(rs
								.getString(3));
						memberProject.setRole_id(rs.getInt(4));
						memberProject.setMember_project_effort(rs
								.getInt(5));
						memberProject.setProject_id(rs.getInt(6));
						return memberProject;
					}
				});*/
	}

	@Override
	public List<MemberProject> getMember() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("SELECT * FROM MEMBER_PROJECT",
				new RowMapper<MemberProject>() {

					@Override
					public MemberProject mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						MemberProject mp = new MemberProject();
						mp.setMember_project_id(rs.getInt(1));
						mp.setUser_id(rs.getInt(2));
						mp.setMember_project_name(rs.getString(3));
						mp.setRole_id(rs.getInt(4));
						mp.setMember_project_effort(rs.getInt(5));
						mp.setProject_id(rs.getInt(6));
						return mp;
					}
				});
	}

		
	
	@Override
	public List<MemberProject> getListMemberProject_By_Current_User_Is_PM_filter_duplicate(
			int user_id) {
		// TODO Auto-generated method stub
		String sql = "select * from member_project m0 where m0.PROJECT_ID in ( select m.PROJECT_ID from member_project m where m.USER_ID = "+user_id+" and m.ROLE_ID = 2) group by m0.user_id";
		try{
			return jdbcTemplate.query(sql, new RowMapper<MemberProject>() {
				@Override
				public MemberProject mapRow(ResultSet rs, int rowNum) throws SQLException {
					MemberProject memberProject=new MemberProject();
					memberProject.setMember_project_id(rs.getInt(1));
					memberProject.setMember_project_name(rs.getString(3));
					memberProject.setUser_id(rs.getInt(2));
					memberProject.setRole_id(rs.getInt(4));
					memberProject.setMember_project_effort(rs.getInt(5));
					memberProject.setProject_id(rs.getInt(6));
					
					return memberProject;
				}
			});
		}
		catch(Exception e){
			return null;
		}
	}

	@Override
	public MemberProject getMemberProjectByMem_id(int memberProject_id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM member_project WHERE MEMBER_PROJECT_ID = "+memberProject_id+"";
		try{
			return jdbcTemplate.queryForObject(sql, new RowMapper<MemberProject>() {
				@Override
				public MemberProject mapRow(ResultSet rs, int rowNum) throws SQLException {
					MemberProject memberProject=new MemberProject();
					memberProject.setMember_project_id(rs.getInt(1));
					memberProject.setUser_id(rs.getInt(2));
					memberProject.setMember_project_name(rs.getString(3));
					memberProject.setRole_id(rs.getInt(4));
					memberProject.setMember_project_effort(rs.getInt(5));
					memberProject.setProject_id(rs.getInt(6));
					
					return memberProject;
				}
			});
		}
		catch(Exception e){
			return null;
		}
		
	}

	@Override
	public List<MemberProject> getListMemberProjectsByCurrentUserAssigned(
			int user_id) {
		// TODO Auto-generated method stub
		
		String sql = "SELECT * FROM member_project WHERE USER_ID = "+user_id+"";
		try{
			return jdbcTemplate.query(sql, new RowMapper<MemberProject>() {
				@Override
				public MemberProject mapRow(ResultSet rs, int rowNum) throws SQLException {
					MemberProject memberProject=new MemberProject();
					memberProject.setMember_project_id(rs.getInt(1));
					memberProject.setUser_id(rs.getInt(2));
					memberProject.setMember_project_name(rs.getString(3));
					memberProject.setRole_id(rs.getInt(4));
					memberProject.setMember_project_effort(rs.getInt(5));
					memberProject.setProject_id(rs.getInt(6));
					
					return memberProject;
				}
			});
		}
		catch(Exception e){
			return null;
		}
	}

	@Override
	public List<MemberProject> get_All_MemberProjects_filter_duplicate() {
		// TODO Auto-generated method stub
		
		try{
			return jdbcTemplate.query("select * from member_project m group by m.user_id;",
					new RowMapper<MemberProject>() {

						@Override
						public MemberProject mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							MemberProject mp = new MemberProject();
							mp.setMember_project_id(rs.getInt(1));
							mp.setUser_id(rs.getInt(2));
							mp.setMember_project_name(rs.getString(3));
							mp.setRole_id(rs.getInt(4));
							mp.setMember_project_effort(rs.getInt(5));
							mp.setProject_id(rs.getInt(6));
							return mp;
						}
					});
		}
		catch(Exception e){
			return null;
		}
	}

	@Override
	public List<MemberProject> getAllMemberT(int id) {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("SELECT * FROM MEMBER_PROJECT where project_id =" + id + "",
				new RowMapper<MemberProject>() {

					@Override
					public MemberProject mapRow(ResultSet rs, int rowNum) throws SQLException {
						MemberProject mp = new MemberProject();
						mp.setMember_project_id(rs.getInt(1));
						mp.setUser_id(rs.getInt(2));
						mp.setMember_project_name(rs.getString(3));
						mp.setRole_id(rs.getInt(4));
						mp.setMember_project_effort(rs.getInt(5));
						mp.setProject_id(rs.getInt(6));
						return mp;
					}
				});
	}
		

}
