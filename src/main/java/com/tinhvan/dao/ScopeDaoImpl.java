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

import com.tinhvan.model.Scope;
import com.tinhvan.model.ScopeProject;
import com.tinhvan.model.Status;

@Repository
@Transactional
public class ScopeDaoImpl implements ScopeDao{

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Override
	public List<Scope> getAllScope() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("SELECT * FROM SCOPE_INFO", new RowMapper<Scope>() {
			public Scope mapRow(ResultSet rs, int row) throws SQLException{
				Scope st = new Scope();
				st.setScope_id(rs.getInt(1));
				st.setScope_name(rs.getString(2));
				return st;
				}
		});
	}
	@Override
	public List<ScopeProject> getListScopProjectByProjectIdAndListScopeId(
			int project_id, List<Integer> list_scope_id) {
		// TODO Auto-generated method stub
		List<ScopeProject> list_ScopeProjects = new ArrayList<ScopeProject>();
		for(int j = 0;j<list_scope_id.size();j++){
			System.out.println(list_scope_id.get(j));
		}
		
		
		for(int i=0;i<list_scope_id.size();i++){
			try{
				/*list_ScopeProjects.add(jdbcTemplate.queryForObject(sql, new Object[] {list_scope_id.get(i)},ScopeProject.class)); 
				System.out.println("sc_prj = "+list_ScopeProjects.size());*/
				
				ScopeProject object_ScopeProject = jdbcTemplate.queryForObject("SELECT * FROM scope_project WHERE PROJECT_ID= "+project_id+" AND SCOPE_ID ="+list_scope_id.get(i)+"", new RowMapper<ScopeProject>() {
					@Override
					public ScopeProject mapRow(ResultSet rs, int rowNum) throws SQLException {
						ScopeProject scopeProject = new ScopeProject();
						scopeProject.setScope_project_id(rs.getInt(1));
						/*st.setStatus_name(rs.getString(2));
						st.setStatus_type(rs.getString(3));*/
						return scopeProject;
						//return null;
					}
					
				
				});
				
				list_ScopeProjects.add(object_ScopeProject);
			}
			catch(Exception e){
				continue;
			}
			
		}
		System.out.println("size queasdf = "+list_ScopeProjects.size());
		
		return list_ScopeProjects;
		
		
		/*try{*/
			
		/*}
		catch(Exception e){
			return null;
		}*/
	}
	@Override
	public void updateScopeProject(List<ScopeProject> liScopeProjects) {
		// TODO Auto-generated method stub
		String sql_update = "update scope_project set SCOPE_ID = ? where SCOPE_PROECT_ID = ?";
		jdbcTemplate.batchUpdate(sql_update,
				new BatchPreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						// TODO Auto-generated method stub
						// MemberProject memberProject =
						// list_MemberProjects.get(i);
						ps.setInt(1, liScopeProjects.get(i).getScope_id());
						ps.setInt(2, liScopeProjects.get(i).getScope_project_id());
						

					}

					@Override
					public int getBatchSize() {
						// TODO Auto-generated method stub
						return liScopeProjects.size();
					}
				});
	}
	@Override
	public void deleteListScopeProjectByProject_id(int project_id) {
		// TODO Auto-generated method stub
		String sqlDelete = "DELETE FROM scope_project WHERE PROJECT_ID = "+project_id+"";
		jdbcTemplate.execute(sqlDelete);
			
	}
	@Override
	public void createListScopeProject(int project_id,
			List<Integer> listScope_id) {
		// TODO Auto-generated method stub
		
		String sql_insert = "INSERT INTO scope_project(PROJECT_ID, SCOPE_ID) VALUES (?, ?)";
		jdbcTemplate.batchUpdate(sql_insert,
				new BatchPreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						// TODO Auto-generated method stub
						// MemberProject memberProject =
						// list_MemberProjects.get(i);
						ps.setInt(1, project_id);
						ps.setInt(2, listScope_id.get(i));
						

					}

					@Override
					public int getBatchSize() {
						// TODO Auto-generated method stub
						return listScope_id.size();
					}
				});
	}
	

	
}
