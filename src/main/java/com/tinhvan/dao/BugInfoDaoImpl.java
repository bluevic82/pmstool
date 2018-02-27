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

import com.tinhvan.model.BugInfo;

@Repository
@Transactional
public class BugInfoDaoImpl implements BugInfoDao{
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<BugInfo> getAllBug(int pn,int tp,int st, int pic,String pri) {
		// TODO Auto-generated method stub
		String sqlxx="";
		if(pn!=0 ||tp!=0||st!=0||pic!=0||!pri.equals("")) {
			
			boolean x= false;
			if(pn!=0) {
				if(x==false) {
					
					sqlxx+=" where bug_info.PROJECT_ID="+pn;
					x=true;
				}else {
					sqlxx+=" and bug_info.PROJECT_ID="+pn;
				}	
			}
			if(tp!=0) {
				
				if(x==false) {
					
					sqlxx+=" where bug_info.TYPE_ID="+tp;
					
					x=true;
					
				}else {
					sqlxx+=" and bug_info.TYPE_ID="+tp;
					
				}	
			}
			if(st!=0) {
				
				if(x==false) {
					
					sqlxx+=" where bug_info.STATUS_ID="+st;
					
					x=true;
					
				}else {
					sqlxx+=" and bug_info.STATUS_ID="+st;
					
				}	
			}
			if(pic!=0) {
				
				if(x==false) {
					
					sqlxx+=" where member_project.MEMBER_PROJECT_ID="+pic;
					
					x=true;
					
				}else {
					sqlxx+=" and member_project.MEMBER_PROJECT_ID="+pic;
					
				}	
			}
			if(!pri.equals("")) {
				
				if(x==false) {
					
					sqlxx+=" where bug_info.BUG_PRIORITY="+"'"+pri+"'";
					
					x=true;
					
				}else {
					sqlxx+=" and bug_info.BUG_PRIORITY="+"'"+pri+"'";
					
				}	
			}
				
		}

		return jdbcTemplate.query("SELECT bug_info.BUG_ID,bug_info.BUG_SUBJECT,member_project.MEMBER_PROJECT_NAME," + 
				"bug_info.BUG_PRIORITY,bug_info.BUG_TO,status_info.STATUS_TYPE,bug_info.BUG_DONE," + 
				" bug_info.BUG_DESCRIPTION,bug_info.PROJECT_ID FROM bug_info" + 
				" LEFT JOIN status_info" + 
				" ON bug_info.STATUS_ID=status_info.STATUS_ID" + 
				" LEFT JOIN member_project" + 
				" ON bug_info.MEMBER_PROJECT_ID = member_project.MEMBER_PROJECT_ID"+sqlxx
				, new RowMapper<BugInfo>() {

			@Override
			public BugInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				BugInfo bugInfo = new BugInfo();
				bugInfo.setBug_id(rs.getInt(1));
				bugInfo.setBug_subject(rs.getString(2));
				bugInfo.setMb(rs.getString(3));
				bugInfo.setBug_priority(rs.getString(4));
				bugInfo.setBug_to(rs.getString(5));
				bugInfo.setStatus(rs.getString(6));
				bugInfo.setBug_done(rs.getInt(7));
				bugInfo.setBug_description(rs.getString(8));
				bugInfo.setProject_id(rs.getInt(9));
				return bugInfo;
			}
			
		});
	}

	@Override
	public void addBug(BugInfo bugInfo) {
		String sql="INSERT INTO bug_info ("
				+ "BUG_SUBJECT, "
				+ "TYPE_ID, "
				+ "STATUS_ID, "
				+ "BUG_DONE,"
				+ "BUG_FROM, "
				+ "BUG_TO, "
				+ "BUG_DESCRIPTION, "
				+ "BUG_Solution, "
				+ "MEMBER_PROJECT_ID, "
				+ "BUG_PRIORITY, "
				+ "CATEGORY_ID, "
				+ "PROJECT_ID)"+"VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, new Object[] {
				bugInfo.getBug_subject(),
				bugInfo.getType_id(), 
				bugInfo.getStatus_id(), 
				bugInfo.getBug_done(),
				bugInfo.getBug_from(), 
				bugInfo.getBug_to(), 
				bugInfo.getBug_description(), 
				bugInfo.getBug_solution(), 
				bugInfo.getMember_project_id(),
				bugInfo.getBug_priority(), 
				bugInfo.getCategory_id(),
				bugInfo.getProject_id()
			});
		
	}

	@Override
	public void updateBug(BugInfo bugInfo) {
		String sql="update bug_info set "
				+ "BUG_SUBJECT='"+bugInfo.getBug_subject()
				+"', BUG_ID="+bugInfo.getBug_id()
				+",  STATUS_ID="+bugInfo.getStatus_id()
				+",  BUG_DONE="+bugInfo.getBug_done()
				+",  BUG_FROM='"+bugInfo.getBug_from()
				+"',  BUG_TO='"+bugInfo.getBug_to()
				+"',  BUG_SOLUTION='"+bugInfo.getBug_solution()
				+"',  BUG_DESCRIPTION='"+bugInfo.getBug_description()
				+"',  MEMBER_PROJECT_ID="+bugInfo.getMember_project_id()
				+",  CATEGORY_ID="+bugInfo.getCategory_id()
				+",  BUG_PRIORITY='"+bugInfo.getBug_priority()
				+"',  PROJECT_ID="+bugInfo.getProject_id()
				+" where bug_id="+bugInfo.getBug_id()+""; 
		jdbcTemplate.update(sql);
		
	}

	@Override
	public BugInfo getBugById(int id) {
		String sql = "SELECT * FROM bug_info where BUG_ID = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] {id}, new BeanPropertyRowMapper<BugInfo>(BugInfo.class));
	}

	@Override
	public List<BugInfo> getBugByIdPro(int id) {
		return jdbcTemplate.query("SELECT bug_info.BUG_ID,bug_info.BUG_SUBJECT,bug_info.TYPE_ID,bug_info.STATUS_TYPE," + 
				"			bug_info.BUG_DONE,bug_info.BUG_FROM,bug_info.BUG_TO," + 
				"			bug_info.BUG_Solution,bug_info.BUG_DESCRIPTION,bug_info.MEMBER_PROJECT_ID," + 
				"			bug_info.CATEGORY_ID,bug_info.BUG_PRIORITY,bug_info.PROJECT_ID" + 
				"			FROM bug_info " + 
				"			INNER JOIN bug_info " + 
				"			ON bug_info.STATUS_ID=bug_info.STATUS_ID" + 
				"			where bug_info.PROJECT_ID="+id, new RowMapper<BugInfo>() {
						
			@Override
			public BugInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				BugInfo bugInfo = new BugInfo();
				bugInfo.setBug_id(rs.getInt(1));
				bugInfo.setBug_subject(rs.getString(2));
				bugInfo.setType_id(rs.getInt(3));
				bugInfo.setStatus(rs.getString(4));
				bugInfo.setBug_done(rs.getInt(5));
				bugInfo.setBug_from(rs.getString(6));
				bugInfo.setBug_to(rs.getString(7));
				bugInfo.setBug_solution(rs.getString(8));
				bugInfo.setBug_description(rs.getString(9));
				bugInfo.setMember_project_id(rs.getInt(10));
				bugInfo.setCategory_id(rs.getInt(11));
				bugInfo.setBug_priority(rs.getString(12));
				bugInfo.setProject_id(rs.getInt(13));
				return bugInfo;
			}
			
		});
	}
	
}