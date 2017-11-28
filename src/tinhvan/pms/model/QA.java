package tinhvan.pms.model;
import java.util.Date;

public class QA {
	private int PROJECT_ID;
	private String Q_A_TITLE;
	private String Q_A_QUESTION_VI;
	private String Q_A_QUESTION_JP;
	private String Q_A_ANSWER_VI;
	private String Q_A_ANSWER_JP;
	private int Q_A_ID;
	private  String REFERENCEPOINT;
	private int STATUS_ID;
	private int member_project_id;
	private int MEMBER_FROM ;
	private Date Q_A_DEALINE;
	
	
	public Date getQ_A_DEALINE() {
		return Q_A_DEALINE;
	}
	public void setQ_A_DEALINE(Date q_A_DEALINE) {
		Q_A_DEALINE = q_A_DEALINE;
	}
	public int getMember_project_id() {
		return member_project_id;
	}
	public void setMember_project_id(int member_project_id) {
		this.member_project_id = member_project_id;
	}
	public int getPROJECT_ID() {
		return PROJECT_ID;
	}
	public void setPROJECT_ID(int pROJECT_ID) {
		PROJECT_ID = pROJECT_ID;
	}
	public String getQ_A_TITLE() {
		return Q_A_TITLE;
	}
	public void setQ_A_TITLE(String q_A_TITLE) {
		Q_A_TITLE = q_A_TITLE;
	}
	public String getQ_A_QUESTION_VI() {
		return Q_A_QUESTION_VI;
	}
	public void setQ_A_QUESTION_VI(String q_A_QUESTION_VI) {
		Q_A_QUESTION_VI = q_A_QUESTION_VI;
	}
	public String getQ_A_QUESTION_JP() {
		return Q_A_QUESTION_JP;
	}
	public void setQ_A_QUESTION_JP(String q_A_QUESTION_JP) {
		Q_A_QUESTION_JP = q_A_QUESTION_JP;
	}
	public String getQ_A_ANSWER_VI() {
		return Q_A_ANSWER_VI;
	}
	public void setQ_A_ANSWER_VI(String q_A_ANSWER_VI) {
		Q_A_ANSWER_VI = q_A_ANSWER_VI;
	}
	public String getQ_A_ANSWER_JP() {
		return Q_A_ANSWER_JP;
	}
	public void setQ_A_ANSWER_JP(String q_A_ANSWER_JP) {
		Q_A_ANSWER_JP = q_A_ANSWER_JP;
	}
	public int getQ_A_ID() {
		return Q_A_ID;
	}
	public void setQ_A_ID(int q_A_ID) {
		Q_A_ID = q_A_ID;
	}
	public String getREFERENCEPOINT() {
		return REFERENCEPOINT;
	}
	public void setREFERENCEPOINT(String rEFERENCEPOINT) {
		REFERENCEPOINT = rEFERENCEPOINT;
	}
	public int getSTATUS_ID() {
		return STATUS_ID;
	}
	public void setSTATUS_ID(int sTATUS_ID) {
		STATUS_ID = sTATUS_ID;
	}
	public int getMEMBER_FROM() {
		return MEMBER_FROM;
	}
	public void setMEMBER_FROM(int mEMBER_FROM) {
		MEMBER_FROM = mEMBER_FROM;
	}
	
}
