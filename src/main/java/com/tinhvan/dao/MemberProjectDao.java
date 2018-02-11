package com.tinhvan.dao;

import java.util.List;

import com.tinhvan.model.MemberProject;

public interface MemberProjectDao {
	public List<MemberProject> getAllMember();
	public List<MemberProject> getMemberProjectByProjectId1(int id);
	public void updateMemberProjectBy_PrjId(List<MemberProject> list_MemberProjects, int project_id);
	public void deleteOneMemberProject(int memberProject_id);
	public void addListMemberProjectToDB(List<MemberProject> list_MemberProjects_toInsert, int project_id);
	
}
