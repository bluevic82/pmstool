package com.tinhvan.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tinhvan.model.QuestionAnwer;

@Repository
@Transactional
public class QuestionAnswerDaoImpl implements QuestionAnswerDao{

	@Autowired
	JdbcTemplate jdbctemplate;

	@Override
	public List<QuestionAnwer> getAllQA() {
		// TODO Auto-generated method stub
		return jdbctemplate.query("SELECT * From anser_question", new RowMapper<QuestionAnwer>() {
			public QuestionAnwer mapRow(ResultSet rs, int row) throws SQLException{
				QuestionAnwer qa = new QuestionAnwer();
					qa.setProject_id(rs.getInt(1));
					qa.setQ_a_title(rs.getString(2));
					qa.setQ_a_question_jp(rs.getString(3));
					qa.setQ_a_question_vi(rs.getString(4));
					qa.setQ_a_anser_jp(rs.getString(5));
					qa.setQ_a_answer_vi(rs.getString(6));
					qa.setQ_a_id(rs.getInt(7));
					qa.setReferencepoint(rs.getString(8));
					qa.setMember_from(rs.getInt(9));
					qa.setMember_project_id(rs.getInt(10));
					qa.setStatus_id(rs.getInt(11));
					qa.setQ_a_dealine(rs.getString(12));
				return qa;
				}
		});
	}
	
	
}
