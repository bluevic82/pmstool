package com.tinhvan.dao;

import java.util.List;

import com.tinhvan.model.Role;

public interface RoleDao {
      public List<Role> getAllRole();
      public String getRoleNameByRoleId(int id);
      public List<Role> getListRoleExceptManagerIfUserLoginIsManager(int role_id);
}
