package tinhvan.pms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
/**
 * @author Trinh
 * @date 27/11/2017
 * @category query CSDL 
**/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import tinhvan.pms.model.QA;
public class QADaoImpl implements QADao {
	
	@Autowired
	private JdbcTemplate jbdcTempalte;

	@Override
	public List<QA> getAllQA() {
		return jbdcTempalte.query("select * from anser_question", new RowMapper<QA>() {
			public QA mapRow(ResultSet rs, int row) throws SQLException{
				QA qa = new QA();
				qa.setPROJECT_ID(rs.getInt(1));
				qa.setQ_A_TITLE(rs.getString(2));
				qa.setQ_A_QUESTION_VI(rs.getString(3));
				qa.setQ_A_QUESTION_JP(rs.getString(4));
				qa.setQ_A_ANSWER_VI(rs.getString(5));
				qa.setQ_A_ANSWER_JP(rs.getString(6));
				qa.setQ_A_ID(rs.getInt(7));
				qa.setREFERENCEPOINT(rs.getString(8));
				qa.setMember_project_id(rs.getInt(9));
				qa.setSTATUS_ID(rs.getInt(10));
				qa.setMEMBER_FROM(rs.getInt(11));
				qa.setQ_A_DEALINE(rs.getDate(12));
				
				return qa;
			}
		});
		
	}
	
	
	





	
}
