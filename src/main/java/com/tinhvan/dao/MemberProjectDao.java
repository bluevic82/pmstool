package com.tinhvan.dao;

import java.util.List;

import com.tinhvan.model.MemberProject;

public interface MemberProjectDao {
	public List<MemberProject> getAllMember();
	public List<MemberProject> getMemberProjectByProjectId1(int id);
	public void updateMemberProjectBy_PrjId(List<MemberProject> list_MemberProjects, int project_id);
	public void deleteOneMemberProject(int memberProject_id);
	public void addListMemberProjectToDB(List<MemberProject> list_MemberProjects_toInsert, int project_id);
	public MemberProject getMemberProjectByProject_Id_And_UserCurrentLogged(int project_id, int user_id);
	public List<MemberProject> getListMemberProject_By_Current_User_Is_PM_filter_duplicate(int user_id);
	public List<MemberProject> getMember();
	public MemberProject getMemberProjectByMem_id(int memberProject_id);
	public List<MemberProject> getListMemberProjectsByCurrentUserAssigned(int user_id);
	
	public List<MemberProject> get_All_MemberProjects_filter_duplicate();
	
	public List<MemberProject> getAllMemberT(int id);
}
