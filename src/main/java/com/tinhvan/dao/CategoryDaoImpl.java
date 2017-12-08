package com.tinhvan.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tinhvan.model.Category;

@Repository
@Transactional
public class CategoryDaoImpl implements CategoryDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<Category> getAllCategory() {

		return jdbcTemplate.query("SELECT * FROM category", new RowMapper<Category>() {

			@Override
			public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
				Category ct = new Category();
				ct.setCategory_id(rs.getInt(1));
				ct.setCategory_name(rs.getString(2));
				return ct;
			}

		});

	}
}