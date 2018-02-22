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
	public void updateMilestone(List<MileStone> mileStone) {
		// TODO Auto-generated method stub
		/*String sql = "INSERT INTO milestone_info (PROJECT_ID, MILESTONE_DATE, MILESTONE_DESCRIPTION)"+" VALUES (?,?,?)";*/
		String sql = "update milestone_info set PROJECT_ID = ?, MILESTONE_DATE =?, MILESTONE_DESCRIPTION = ? where MILESTONE_ID = ?";
		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				// TODO Auto-generated method stub
				ps.setInt(1, mileStone.get(i).getProject_id());
				ps.setString(2, mileStone.get(i).getMilestone_date());
				ps.setString(3, mileStone.get(i).getMilestone_description());
				ps.setInt(4, mileStone.get(i).getMilestone_id());
			}
			
			@Override
			public int getBatchSize() {
				// TODO Auto-generated method stub
				return mileStone.size();
			}
		});			
	}

	@Override
	public List<MileStone> getMileStoneByProjectId(int id) {
		// TODO Auto-generated method stub
		Object[] params = new Object[] { id };
		String sql = "SELECT * FROM MILESTONE_INFO WHERE PROJECT_ID = ?";
		return jdbcTemplate.query(sql, params, new RowMapper<MileStone>() {
			
			@Override
			public MileStone mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				MileStone m = new MileStone();
				m.setProject_id(rs.getInt(1));
				m.setMilestone_date(rs.getString(2));
				m.setMilestone_description(rs.getString(3));
				m.setMilestone_id(rs.getInt(4));
				return m;
			}
		});
	}
}
