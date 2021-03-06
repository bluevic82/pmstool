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

import com.tinhvan.mapper.ProjectMapper;
import com.tinhvan.model.MemberProject;
import com.tinhvan.model.ProjectInfo;
import com.tinhvan.model.ScopeProject;

@Repository
@Transactional
public class ProjectDaoImpl implements ProjectDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<ProjectInfo> getAllProject() {
		
		return jdbcTemplate.query("SELECT * FROM project_info", new RowMapper<ProjectInfo>() {

			@Override
			public ProjectInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				ProjectInfo projectInfo = new ProjectInfo();
				projectInfo.setProject_id(rs.getInt(1));
				projectInfo.setProject_name(rs.getString(2));
				projectInfo.setProject_from(rs.getString(3));
				projectInfo.setProject_to(rs.getString(4));
				projectInfo.setProject_charge_cost(rs.getInt(5));
				projectInfo.setStatus_id(rs.getInt(6));
				projectInfo.setType_id(rs.getInt(7));
				projectInfo.setProject_description(rs.getString(8));
				projectInfo.setProject_technical(rs.getString(9));
				return projectInfo;
			}
		});
		
	}

	@Override
	public void addProject(ProjectInfo project) {
		// TODO Auto-generated method stub
		String sql="INSERT INTO project_info(PROJECT_NAME, PROJECT_FROM, PROJECT_TO, PROJECT_CHARGE_COST, STATUS_ID, TYPE_ID, PROJECT_DESCRIPTION, PROJECT_TECHNICAL)"+"VALUES (?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, new Object[] {project.getProject_name(),project.getProject_from(),project.getProject_to(),project.getProject_charge_cost(),project.getStatus_id(),project.getType_id(),project.getProject_description(),project.getProject_technical()});
	}
	
	

	@Override
	public void addScopeProject(int id, List<Integer> a) {
		// TODO Auto-generated method stub
		for(int i = 0; i < a.size(); i++ ) {
			System.out.println(a.get(i));
			String sql = "INSERT INTO scope_project(project_id, scope_id) VALUES ("+ id +",?)";
			jdbcTemplate.update(sql, new Object[] {a.get(i)});
		}
		
	}

	@Override
	public int findProjectIdMax() {
		// TODO Auto-generated method stub
		String sql = "SELECT MAX(project_id) FROM project_info";
		int maxId = jdbcTemplate.queryForObject(sql, Integer.class);
		return maxId;
	}

	@Override
	public void updateProject(ProjectInfo project) {
		// TODO Auto-generated method stub
		String sql="update project_info set project_name='"+project.getProject_name()+"', project_from ='"+project.getProject_from()+"',  project_to='"+project.getProject_to()+"',  project_charge_cost="+project.getProject_charge_cost()+",  status_id="+project.getStatus_id()+",  type_id="+project.getType_id()+",  project_description='"+project.getProject_description()+"',  project_technical='"+project.getProject_technical()+"' where project_id="+project.getProject_id()+""; 
		jdbcTemplate.update(sql);
		
	}

	@Override
	public ProjectInfo getProjectById(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM project_info where PROJECT_ID = ?";
		try{
			return jdbcTemplate.queryForObject(sql, new Object[] {id}, new BeanPropertyRowMapper<ProjectInfo>(ProjectInfo.class));
		}
		catch(Exception e){
			return null;
		}
		
	}
	
	@Override
	public ProjectInfo getProjectById1(int id)  {
		
		String sql = "SELECT project_info.PROJECT_ID,project_info.PROJECT_NAME,project_info.PROJECT_FROM,project_info.PROJECT_TO,project_info.PROJECT_DESCRIPTION,project_info.PROJECT_TECHNICAL,status_info.STATUS_TYPE,project_info.TYPE_ID,member_project.MEMBER_PROJECT_NAME" + 
				"											FROM project_info" + 
				"				                                LEFT  JOIN status_info" + 
				"												ON project_info.STATUS_ID=status_info.STATUS_ID" + 
				"                                               LEFT JOIN member_project              " + 
				"												ON 	project_info.PROJECT_ID = member_project.PROJECT_ID and(member_project.ROLE_ID=2)" + 
				"												where project_info.PROJECT_ID= ?";

		ProjectInfo pj = (ProjectInfo)jdbcTemplate.queryForObject(
				sql, new Object[] { id }, new ProjectMapper());

		return pj;
		
		
//		return jdbcTemplate.query("SELECT project_info.PROJECT_ID,project_info.PROJECT_NAME,project_info.PROJECT_FROM,"
//				+ "project_info.PROJECT_TO,project_info.PROJECT_DESCRIPTION,"
//				+ "project_info.PROJECT_TECHNICAL,status_info.STATUS_TYPE" + 
//				"							FROM project_info " + 
//				"                                LEFT  JOIN status_info" + 
//				"								ON project_info.STATUS_ID=status_info.STATUS_ID" + 
//				"								where project_info.PROJECT_ID=3;", new RowMapper<ProjectInfo>() {
//			
//			
//			
//			
//			
//			
//			
//			
//			
//			
//			
//			
//			@Override
//			public ProjectInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
//				ProjectInfo projectInfo = new ProjectInfo();
//				projectInfo.setProject_id(rs.getInt(1));
//				projectInfo.setProject_name(rs.getString(2));
//				projectInfo.setProject_from(rs.getString(3));
//				projectInfo.setProject_to(rs.getString(4));
//				projectInfo.setProject_description(rs.getString(5));
//				projectInfo.setProject_technical(rs.getString(6));
//				projectInfo.setStatus(rs.getString(7));
//				return projectInfo;
//			}
//		});
		
	}

	@Override
	public List<MemberProject> getPm(int id) {
		return jdbcTemplate.query("SELECT member_project.MEMBER_PROJECT_NAME" + 
				"							FROM project_info" + 
				"                                LEFT  JOIN member_project" + 
				"								ON project_info.PROJECT_ID= member_project.PROJECT_ID" + 
				"								where project_info.PROJECT_ID="+id+" and member_project.ROLE_ID=2;", new RowMapper<MemberProject>() {

			@Override
			public MemberProject mapRow(ResultSet rs, int rowNum) throws SQLException {
				MemberProject mb=new MemberProject();
				mb.setMember_project_name(rs.getString(1));
				return mb;
			}
		});
	}

	@Override
	public List<ProjectInfo> getAllProject1(String name,String pm,String from,String to) {
		String sqlxx="";
	
		System.out.println(from);
		if(!name.equals("") ||!from.equals("")||!to.equals("")||!pm.equals("")) {
			
			
			
			boolean x= false;
			if(!name.equals("")) {
				if(x==false) {
					
					sqlxx+="group by project_info.PROJECT_ID having project_info.PROJECT_NAME LIKE "+"'%"+name+"%'";
					x=true;
				}else {
					sqlxx+="and project_info.PROJECT_NAME LIKE"+"'%"+name+"%'";
				}	
			}
			if(!from.equals("")) {
				
				if(x==false) {
					
					sqlxx+="group by project_info.PROJECT_ID having project_info.PROJECT_FROM >="+"'"+from+"'";
					
					x=true;
					
				}else {
					sqlxx+="and project_info.PROJECT_FROM>="+"'"+from+"'";
					
				}	
			}
			if(!to.equals("")) {			
				if(x==false) {
					
					sqlxx+="group by project_info.PROJECT_ID having project_info.PROJECT_TO <="+"'"+to+"'";
					x=true;
				}else {
					sqlxx+="and project_info.PROJECT_TO<="+"'"+to+"'";
				}
				
			}
			if(!pm.equals("")) {			
				if(x==false) {
					
					sqlxx+="group by project_info.PROJECT_ID having member_project.MEMBER_PROJECT_NAME like"+"'%"+pm+"%'";
					x=true;
				}else {
					sqlxx+="and member_project.MEMBER_PROJECT_NAME like"+"'%"+pm+"%'";
				}
				
			}
		}
		
		
		
		return jdbcTemplate.query("SELECT project_info.PROJECT_ID,project_info.PROJECT_NAME,project_info.PROJECT_FROM,project_info.PROJECT_TO,project_info.PROJECT_DESCRIPTION,project_info.PROJECT_TECHNICAL,status_info.STATUS_TYPE,project_info.TYPE_ID,member_project.MEMBER_PROJECT_NAME" + 
				"													FROM project_info " + 
				"								                            LEFT  JOIN status_info" + 
				"															ON project_info.STATUS_ID=status_info.STATUS_ID" + 
				"                                                            LEFT JOIN member_project" + 

				"															ON 	project_info.PROJECT_ID = member_project.PROJECT_ID and(member_project.ROLE_ID=2)" + 
		 
				"				                                           where status_info.STATUS_TYPE='Open' or status_info.STATUS_TYPE='Pending'"+sqlxx, new RowMapper<ProjectInfo>() {
			
				

			@Override
			public ProjectInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				ProjectInfo projectInfo = new ProjectInfo();
				projectInfo.setProject_id(rs.getInt(1));
				projectInfo.setProject_name(rs.getString(2));
				projectInfo.setProject_from(rs.getString(3));
				projectInfo.setProject_to(rs.getString(4));
				projectInfo.setProject_description(rs.getString(5));
				projectInfo.setProject_technical(rs.getString(6));
				projectInfo.setStatus(rs.getString(7));
				projectInfo.setType_id(rs.getInt(8));
				projectInfo.setPm(rs.getString(9));
				
				return projectInfo;
			}
		});
	}

	@Override
	public List<ScopeProject> getScope(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT scope_id, scope_proect_id from scope_project where PROJECT_ID = ?";
		return jdbcTemplate.query(sql, new Object[] {id}, new RowMapper<ScopeProject>() {
			@Override
			public ScopeProject mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				ScopeProject s = new ScopeProject();
				s.setScope_id(rs.getInt(1));
				s.setScope_project_id(rs.getInt(2));
				return s;
			}
		} );
	}
	
	
	
	@Override
	public List<ProjectInfo> getListPRojectOfUserAccessed(int user_id) {
		List<ProjectInfo> list_Project_Of_Usser_Accessed = new ArrayList<ProjectInfo>();
		try{
			List<Integer> list_Project_Ids = jdbcTemplate.queryForList("SELECT PROJECT_ID FROM member_project WHERE USER_ID = "+user_id+"", Integer.class);
			//System.out.println(list_Project_Ids.size());
			for(int i = 0;i<list_Project_Ids.size();i++){
				list_Project_Of_Usser_Accessed.add(jdbcTemplate.queryForObject("SELECT * FROM project_info WHERE PROJECT_ID = "+list_Project_Ids.get(i)+"", new RowMapper<ProjectInfo>() {
					@Override
					public ProjectInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
						ProjectInfo projectInfo = new ProjectInfo();
						projectInfo.setProject_id(rs.getInt(1));
						projectInfo.setProject_name(rs.getString(2));
						projectInfo.setProject_from(rs.getString(3));
						projectInfo.setProject_to(rs.getString(4));
						projectInfo.setProject_charge_cost(rs.getFloat(5));
						projectInfo.setStatus_id(rs.getInt(6));
						projectInfo.setType_id(rs.getInt(7));
						projectInfo.setProject_description(rs.getString(8));
						projectInfo.setProject_technical(rs.getString(9));
						
						return projectInfo;
					}
				}));
			}
			return list_Project_Of_Usser_Accessed;
		}
		catch(Exception e){
			return null;
		}
		
		
	}
}
