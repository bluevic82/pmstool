package com.tinhvan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tinhvan.model.MileStone;

@Repository
@Transactional
public class MileStoneDaoImpl implements MileStoneDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<MileStone> getAllMileStone() {
		
		return jdbcTemplate.query("SELECT * FROM milestone_info", new RowMapper<MileStone>() {

			@Override
			public MileStone mapRow(ResultSet rs, int rowNum) throws SQLException {
				MileStone ms = new MileStone();
				ms.setMilestone_id(rs.getInt(1));
				ms.setMilestone_date(rs.getString(2));
				ms.setMilestone_description(rs.getString(3));
				ms.setProject_id(rs.getInt(4));
				return ms;
			}
		});
	}

	@Override
	public void addMileStone(MileStone mileStone) {
		String sql = "INSERT INTO milestone_info (PROJECT_ID, MILESTONE_DATE, MILESTONE_DESCRIPTION)"+" VALUES (?,?,?)";
		jdbcTemplate.update(sql, new Object[] {
			mileStone.getProject_id(),
			mileStone.getMilestone_date(),
			mileStone.getMilestone_description()
		});
	}

	@Override
	public void deleteMidelStone(MileStone mileStone) {
		String sql = "DELETE FROM milestone_info WHERE MILESTONE_ID = ?";
		jdbcTemplate.update(sql, new Object[] {
			mileStone.getProject_id(),
			mileStone.getMilestone_date(),
			mileStone.getMilestone_description(),
			mileStone.getMilestone_id()
		});	
	}
	//method add multiple milestone
			@Override
			public void insertMilestone(List<MileStone> mileStone) {
				// TODO Auto-generated method stub
				String sql = "INSERT INTO milestone_info (PROJECT_ID, MILESTONE_DATE, MILESTONE_DESCRIPTION)"+" VALUES (?,?,?)";
				jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
					
					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						// TODO Auto-generated method stub
						MileStone milestone = mileStone.get(i);
						ps.setInt(1, milestone.getProject_id());
						ps.setString(2, milestone.getMilestone_date());
						ps.setString(3, milestone.getMilestone_description());
						
					}
					
					@Override
					public int getBatchSize() {
						// TODO Auto-generated method stub
						return mileStone.size();
					}
				});			
				}
}
