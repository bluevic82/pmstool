package com.tinhvan.model;

public class QuestionAnwer {
	private int project_id;
	private String title;
	private String qa_quest_vn;
	private String qa_quest_jp;
	private String qa_answer_vn;
	private String qa_anser_jp;
	private int qa_id;
	private String reference;
	private String member_to;
	private String member_from;
	private Status status_id;
	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getQa_quest_vn() {
		return qa_quest_vn;
	}
	public void setQa_quest_vn(String qa_quest_vn) {
		this.qa_quest_vn = qa_quest_vn;
	}
	public String getQa_quest_jp() {
		return qa_quest_jp;
	}
	public void setQa_quest_jp(String qa_quest_jp) {
		this.qa_quest_jp = qa_quest_jp;
	}
	public String getQa_answer_vn() {
		return qa_answer_vn;
	}
	public void setQa_answer_vn(String qa_answer_vn) {
		this.qa_answer_vn = qa_answer_vn;
	}
	public String getQa_anser_jp() {
		return qa_anser_jp;
	}
	public void setQa_anser_jp(String qa_anser_jp) {
		this.qa_anser_jp = qa_anser_jp;
	}
	public int getQa_id() {
		return qa_id;
	}
	public void setQa_id(int qa_id) {
		this.qa_id = qa_id;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getMember_to() {
		return member_to;
	}
	public void setMember_to(String member_to) {
		this.member_to = member_to;
	}
	public String getMember_from() {
		return member_from;
	}
	public void setMember_from(String member_from) {
		this.member_from = member_from;
	}
	public Status getStatus_id() {
		return status_id;
	}
	public void setStatus_id(Status status_id) {
		this.status_id = status_id;
	}
	public QuestionAnwer() {
		super();
	}
}
