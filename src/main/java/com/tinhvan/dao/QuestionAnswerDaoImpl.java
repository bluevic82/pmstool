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

import com.tinhvan.model.QuestionAnwer;

@Repository
@Transactional
public class QuestionAnswerDaoImpl implements QuestionAnswerDao{

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<QuestionAnwer> getAllQA() {
		// select all
		return jdbcTemplate.query("SELECT * From anser_question", new RowMapper<QuestionAnwer>() {
			public QuestionAnwer mapRow(ResultSet rs, int row) throws SQLException{
				QuestionAnwer qa = new QuestionAnwer();
					qa.setProject_id(rs.getInt(1));
					qa.setQ_a_id(rs.getInt(2));
					qa.setQ_a_title(rs.getString(3));
					qa.setQ_a_question_jp(rs.getString(4));
					qa.setQ_a_question_vi(rs.getString(5));
					qa.setQ_a_answer_jp(rs.getString(6));
					qa.setQ_a_answer_vi(rs.getString(7)); 
					qa.setReferencepoint(rs.getString(8));
					qa.setMember_from(rs.getInt(9));
					qa.setMember_project_id(rs.getInt(10));
					qa.setStatus_id(rs.getInt(11));
					qa.setQ_a_dealine(rs.getString(12));
				return qa;
				}
		});
	}

	@Override
	public void registerQA(QuestionAnwer questionAnwer) {
		//insert
		String sql = "INSERT INTO anser_question ("
				+ "PROJECT_ID, "
				+ "Q_A_TITLE, "
				+ "REFERENCEPOINT, "
				+ "Q_A_QUESTION_VI, "
				+ "Q_A_QUESTION_JP, "
				+ "Q_A_ANSWER_VI, "
				+ "Q_A_ANSWER_JP, "
				+ "MEMBER_PROJECT_ID, "
				+ "STATUS_ID, "
				+ "MEMBER_FROM, "
				+ "Q_A_DEALINE) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, new Object[] {
				questionAnwer.getProject_id(),
				questionAnwer.getQ_a_title(),
				questionAnwer.getReferencepoint(),
				questionAnwer.getQ_a_question_vi(),
				questionAnwer.getQ_a_question_jp(),
				questionAnwer.getQ_a_answer_vi(),
				questionAnwer.getQ_a_answer_jp(),
				questionAnwer.getMember_project_id(),
				questionAnwer.getStatus_id(),
				questionAnwer.getMember_from(),
				questionAnwer.getQ_a_dealine()
			});
	}
	
	@Override
	public void updateQA(QuestionAnwer questionAnwer) {
			//update
			String sql = "UPDATE anser_question SET Q_A_TITLE=?, "
					+ "Q_A_QUESTION_VI=?, "
					+ "Q_A_QUESTION_JP=?, "
					+ "Q_A_ANSWER_VI=?, "
					+ "Q_A_ANSWER_JP=?, "
					+ "REFERENCEPOINT=?, "
					+ "MEMBER_PROJECT_ID=?, "
					+ "STATUS_ID=?, "
					+ "MEMBER_FROM=?, "
					+ "Q_A_DEALINE=? "
					+ "WHERE Q_A_ID=?";
			jdbcTemplate.update(sql, questionAnwer.getQ_a_title(), 
					questionAnwer.getQ_a_question_vi(),
					questionAnwer.getQ_a_question_jp(),
					questionAnwer.getQ_a_answer_vi(),
					questionAnwer.getQ_a_answer_jp(),
					questionAnwer.getReferencepoint(),
					questionAnwer.getMember_project_id(),
					questionAnwer.getStatus_id(),
					questionAnwer.getMember_from(),
					questionAnwer.getQ_a_dealine(),
					questionAnwer.getQ_a_id());
		
	}

	@Override
	public QuestionAnwer getQAById(int q_a_id) {
		String sql = "SELECT * FROM anser_question WHERE Q_A_ID = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] {q_a_id},
				new BeanPropertyRowMapper<QuestionAnwer>(QuestionAnwer.class));
	}
}
