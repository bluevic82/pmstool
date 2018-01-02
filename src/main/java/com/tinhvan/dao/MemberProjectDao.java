package com.tinhvan.dao;

import java.util.List;

import com.tinhvan.model.MemberProject;

public interface MemberProjectDao {
	public List<MemberProject> getAllMember();
	public MemberProject getMemberProjectByProjectId(int id);
}
