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

@Repository
@Transactional
public class EffortDaoImpl implements EffortDao {
	private final int Pixel = 800;
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
						effort.setOver_head((double) Math
								.round(OverheadCal(
										effort.getProject_charge_cost(),
										effort.getProject_actual_cost()) * 100) / 100);

						/* get width of charge and actual to draw chart */
						if (effort.getProject_charge_cost() == 0) {
							effort.setWidth_project_actual_cost(0);
							effort.setWidth_project_charge_cost(0);
						} else if (effort.getProject_charge_cost() > effort
								.getProject_actual_cost()) {
							/* charge > actual : chargePixel = 1000px, */
							effort.setWidth_project_charge_cost(Pixel);
							/* actualPixel = (actual*1000)/charge; */
							effort.setWidth_project_actual_cost((effort
									.getProject_actual_cost() * Pixel)
									/ effort.getProject_charge_cost());
						} else {
							/* charge <= actual : actualPixel = 1000px, */
							effort.setWidth_project_actual_cost(Pixel);
							/* chargelPixel = (charge*1000)/actual; */
							effort.setWidth_project_charge_cost((effort
									.getProject_charge_cost() * Pixel)
									/ effort.getProject_actual_cost());
						}

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
		effort.setProject_actual_cost((double) Math
				.round((getSumEffortByPrjId(id) / 30) * 100) / 100); // chia 30
		// ngay
		// => MM
		effort.setStatus_type(getStatusTypeByStatusId(effort.getStatus_id()));

		/* get change request */

		if (effort.getProject_charge_cost() == 0) {
			effort.setChange_Request(0);
			
		} else {
			effort.setChange_Request((double) Math
					.round((((getSumEffortOfCategoryByProjectId(id, "CR")) / 30) / effort
							.getProject_charge_cost()) * 100 * 100) / 100);
			
			effort.setOver_head((double) Math
					.round(OverheadCal(
							effort.getProject_charge_cost(),
							effort.getProject_actual_cost()) * 100) / 100);
		}
		return effort;
	}

	/* get status of project by status id */
	public String getStatusTypeByStatusId(int id) {
		String sql = "SELECT STATUS_TYPE FROM status_info WHERE STATUS_ID = ? AND STATUS_NAME = 'project'";
		return jdbcTemplate.queryForObject(sql, new Object[] { id },
				String.class);
	}

	/* get sum effort of all users of project by prj_id */
	public double getSumEffortByPrjId(int id) {//
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

	/*
	 * get sum effort of all task have category_name is ... of project =>
	 * change_request
	 */
	public double getSumEffortOfCategoryByProjectId(int prj_id,
			String nameOfCategory) {
		double sumEffortOfCategory = 0;

		String sql_get_CategoryId = "SELECT CATEGORY_ID FROM category WHERE CATEGORY_NAME = ?";
		int category_id = jdbcTemplate.queryForObject(sql_get_CategoryId,
				new Object[] { nameOfCategory }, Integer.class);
		List<Integer> listOfTaskId = jdbcTemplate.queryForList(
				"SELECT TASK_ID FROM task_info WHERE CATEGORY_ID ='"
						+ category_id + "'  AND PROJECT_ID = '" + prj_id + "'",
				Integer.class);

		/* get sum effort of category of all task_id of list */
		for (int i = 0; i < listOfTaskId.size(); i++) {
			// get sum effort of category of 1 task_id;
			try{
				sumEffortOfCategory += jdbcTemplate
						.queryForObject("SELECT SUM(HOUR) FROM detail_timesheet WHERE TASK_ID = ?",new Object[] { listOfTaskId.get(i) }, Double.class);
			}
			catch(Exception e){ //exception khi không co dl trong db
				continue;
			}
		}
			
		return sumEffortOfCategory;
	}
	
	/*get overhead*/
	public double OverheadCal(double charge, double actual) { // get overHead
		double overHeadCal = ((actual / charge) * 100 - 100);

		if (overHeadCal < 0) {
			return overHeadCal + 100;
		}
		return overHeadCal;
	}

}
