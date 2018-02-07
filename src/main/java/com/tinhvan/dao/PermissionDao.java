package com.tinhvan.dao;

import java.util.List;



import com.tinhvan.model.Permission;

public interface PermissionDao {
	public Permission getPer(int id);
	public Boolean checker(String code);
	public List<Permission> getAllPer();
	public boolean updatePer(Permission per);
	
}
