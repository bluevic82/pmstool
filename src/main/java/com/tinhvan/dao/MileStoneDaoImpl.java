package com.tinhvan.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
}
