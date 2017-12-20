package com.tinhvan.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
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
					qa.setQ_a_anser_jp(rs.getString(6));
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
	public void saveQA(QuestionAnwer questionAnwer) {
		if(questionAnwer.getQ_a_id() > 0) {
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
					questionAnwer.getQ_a_anser_jp(),
					questionAnwer.getReferencepoint(),
					questionAnwer.getMember_project_id(),
					questionAnwer.getStatus_id(),
					questionAnwer.getMember_from(),
					questionAnwer.getQ_a_dealine(),
					questionAnwer.getQ_a_id());
		}else {
			//insert
			String sql = "INSERT INTO anser_question (PROJECT_ID, Q_A_QUESTION_VI, Q_A_TITLE, Q_A_QUESTION_JP, Q_A_ANSWER_VI, Q_A_ANSWER_JP, REFERENCEPOINT, MEMBER_PROJECT_ID, STATUS_ID, MEMBER_FROM, Q_A_DEALINE) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			jdbcTemplate.update(sql,
					questionAnwer.getProject_id(),
					questionAnwer.getQ_a_title(),
					questionAnwer.getQ_a_question_vi(),
					questionAnwer.getQ_a_question_jp(),
					questionAnwer.getQ_a_answer_vi(),
					questionAnwer.getQ_a_anser_jp(),
					questionAnwer.getReferencepoint(),
					questionAnwer.getMember_project_id(),
					questionAnwer.getStatus_id(),
					questionAnwer.getMember_from(),
					questionAnwer.getQ_a_dealine());
		}
		
	}

	@Override
	public QuestionAnwer getQAById(int q_a_id) {
		String sql = "SELECT * FROM anser_question WHERE Q_A_ID =" + q_a_id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<QuestionAnwer>() {

			@Override
			public QuestionAnwer extractData(ResultSet rs) throws SQLException, DataAccessException {
				if(rs.next()) {
					QuestionAnwer questionAnwer = new QuestionAnwer();
					questionAnwer.setQ_a_id(rs.getInt("q_a_id"));
					questionAnwer.setQ_a_title(rs.getString("q_a_title"));
					questionAnwer.setQ_a_question_vi(rs.getString("q_a_question_vi"));
					questionAnwer.setQ_a_question_jp(rs.getString("q_a_question_jp"));
					questionAnwer.setQ_a_answer_vi(rs.getString("q_a_answer_vi"));
					questionAnwer.setQ_a_anser_jp(rs.getString("q_a_anser_jp"));
					questionAnwer.setReferencepoint(rs.getString("referencepoint"));
					questionAnwer.setMember_project_id(rs.getInt("member_project_id"));
					questionAnwer.setStatus_id(rs.getInt("status_id"));
					questionAnwer.setMember_from(rs.getInt("member_from"));
					questionAnwer.setQ_a_dealine(rs.getString("q_a_dealine"));
					return questionAnwer;
				}
				return null;
			}
			
		});
	}
	
	
}
