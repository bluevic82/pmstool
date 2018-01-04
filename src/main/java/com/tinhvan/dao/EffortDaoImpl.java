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

import com.tinhvan.controller.EffortController;
import com.tinhvan.model.Effort;
import com.tinhvan.model.MemberProject;

@Repository
@Transactional
public class EffortDaoImpl implements EffortDao {
	private final int he_So_Width_Pixel = 100;
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	EffortController effortController;

	@Override
	public List<Effort> getAllEfort() {

		return jdbcTemplate.query("SELECT * FROM project_info",
				new RowMapper<Effort>() {

					@Override
					public Effort mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Effort effort = new Effort();
						effort.setProject_id(rs.getInt(1));
						effort.setProject_name(rs.getString(2));
						effort.setProject_charge_cost(rs.getInt(5));
						effort.setStatus_id(rs.getInt(6));
						effort.setStatus_type(getStatusTypeByStatusId(effort
								.getStatus_id()));
						// effort.setProject_actual_cost();

						effort.setProject_actual_cost(getSumEffortByPrjId(effort
								.getProject_id()) / 30); // get MM
						effort.setOver_head((double)Math.round(effortController.OverheadCal(
								effort.getProject_charge_cost(),
								effort.getProject_actual_cost()) * 100) / 100 );
						effort.setWidth_project_charge_cost((effort.getProject_charge_cost())*he_So_Width_Pixel); // lay size bieu do charge
						effort.setWidth_project_actual_cost((effort.getProject_actual_cost())*he_So_Width_Pixel); // lay size bieu do actual
						return effort;
					}
				});

	}

	@Override
	public Effort getEffortById(int id) {
		String sql = "SELECT * FROM project_info WHERE PROJECT_ID = ? ";
		// TODO Auto-generated method stub
		Effort effort;
		effort = jdbcTemplate.queryForObject(sql, new Object[] { id },
				new BeanPropertyRowMapper<Effort>(Effort.class));
		effort.setProject_actual_cost((double)Math.round((getSumEffortByPrjId(id) / 30) * 100) / 100); // chia 30
																		// ngay
																		// => MM
		effort.setStatus_type(getStatusTypeByStatusId(effort.getStatus_id()));
		return effort;
	}

	public String getStatusTypeByStatusId(int id) {
		String sql = "SELECT STATUS_TYPE FROM status_info WHERE STATUS_ID = ? AND STATUS_NAME = 'project'";
		return jdbcTemplate.queryForObject(sql, new Object[] { id },
				String.class);
	}



	public double getSumEffortByPrjId(int id) {// get sum effort of prj_id
		double result = 0;
		try {
			String sql_getList_TS_ID = "SELECT TS_ID FROM timesheet_info WHERE PROJECT_ID = ?";
			String sql_getSUM_Effort = "SELECT SUM(HOUR) FROM detail_timesheet WHERE TS_ID = ?";
			Object[] params = new Object[] { id };
			List<Integer> list_TS_ID = jdbcTemplate.queryForList(
					sql_getList_TS_ID, params, Integer.class);

			for (int i = 0; i < list_TS_ID.size(); i++) {
				double sumOfEffortOfPrj = jdbcTemplate.queryForObject(
						sql_getSUM_Effort, new Object[] { list_TS_ID.get(i) },
						Double.class);
				result += sumOfEffortOfPrj;
			}
			return result;
		} catch (Exception ex) {
			return 0;
		}

	}


}
