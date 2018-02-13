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
	public List<QuestionAnwer> getAllQA(int projectName, int status, int pic) {	
		String sql = "";
		String sql02 = "SELECT anser_question.Q_A_ID,anser_question.Q_A_TITLE,member_project.MEMBER_PROJECT_NAME,\r\n" + 
				"				anser_question.Q_A_DEALINE,status_info.STATUS_TYPE, anser_question.PROJECT_ID FROM anser_question\r\n" + 
				"				LEFT  JOIN status_info\r\n" + 
				"				ON anser_question.STATUS_ID=status_info.STATUS_ID\r\n" + 
				"				 LEFT JOIN member_project\r\n" + 
				"				 ON anser_question.MEMBER_PROJECT_ID = member_project.MEMBER_PROJECT_ID";
		
		if (projectName!=999999 || status!=999999 || pic!=999999) {
			sql=" where anser_question.PROJECT_ID="+projectName+" and" + 
					"					anser_question.STATUS_ID="+status+" and" + 
					"					member_project.MEMBER_PROJECT_ID="+pic+"'";
		}
		
		return jdbcTemplate.query(sql02+sql
				, new RowMapper<QuestionAnwer>() {

			@Override
			public QuestionAnwer mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				QuestionAnwer anwer = new QuestionAnwer();
				anwer.setQ_a_id(rs.getInt(1));
				anwer.setQ_a_title(rs.getString(2));
				anwer.setMb(rs.getString(3));
				anwer.setQ_a_dealine(rs.getString(4));
				anwer.setStatus(rs.getString(5));
				return anwer;
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
