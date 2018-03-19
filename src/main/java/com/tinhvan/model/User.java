package com.tinhvan.model;

import com.opencsv.bean.CsvBindByName;

public class User {
	@CsvBindByName( required = false)
	private int user_id;
	@CsvBindByName(column = "user_fullName")
	private String user_fullName;
	@CsvBindByName(column = "user_mail")
	private String user_mail;
	@CsvBindByName(column = "user_passWord")
	private String user_passWord;
	@CsvBindByName(column = "role_id")
	private int role_id;

	public User() {
		super();

	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUser_fullName() {
		return user_fullName;
	}

	public void setUser_fullName(String user_fullName) {
		this.user_fullName = user_fullName;
	}

	public String getUser_mail() {
		return user_mail;
	}

	public void setUser_mail(String user_mail) {
		this.user_mail = user_mail;
	}

	public String getUser_passWord() {
		return user_passWord;
	}

	public void setUser_passWord(String user_passWord) {
		this.user_passWord = user_passWord;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public User(int user_id, String user_fullName, String user_mail,
			String user_passWord, int role_id) {
		super();
		this.user_id = user_id;
		this.user_fullName = user_fullName;
		this.user_mail = user_mail;
		this.user_passWord = user_passWord;
		this.role_id = role_id;
	}
	
	

}
