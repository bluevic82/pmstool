package com.tinhvan.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tinhvan.model.Permission;
import com.tinhvan.model.ProjectInfo;
@Transactional
@Repository
public class PermissionDaoImpl implements PermissionDao {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	
		
	
		
		
	

	@Override
	public Boolean checker(String code) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Set<String> roles = authentication.getAuthorities().stream()
			     .map(r -> r.getAuthority()).collect(Collectors.toSet());
		String[] rolo = roles.toArray(new String[roles.size()]);
	
		int role=Integer.parseInt(rolo[0]);
		Permission per = getPer(role);
		if(role==1) {
			return true;
			
		}else {
			switch (code) {
			case "over_view":
				if(per.getOver_view()==true) {
					return true;
				}else {
					return false;
				}
			case "pro_detail":
				if(per.getPro_detail()==true) {
					return true;
				}else {
					return false;
				}
			case "add_pro":
				if(per.getAdd_pro()==true) {
					return true;
				}else {
					return false;
				}
			case "set_upd":
				if(per.getSet_upd()==true) {
					return true;
				}else {
					return false;
				}
			case "set_res":
				if(per.getSet_res()==true) {
					return true;
				}else {
					return false;
				}
			case "cre_iss":
				if(per.getCre_iss()==true) {
					return true;
				}else {
					return false;
				}
			case "upd_iss":
				if(per.getUpd_iss()==true) {
					return true;
				}else {
					return false;
				}
			case "set_reg":
				if(per.getSet_reg()==true) {
					return true;
				}else {
					return false;
				}
			case "eff_mana":
				if(per.getEff_mana()==true) {
					return true;
				}else {
					return false;
				}
			case "eff_can":
				if(per.getEff_can()==true) {
					return true;
				}else {
					return false;
				}
			case "qva_upd":
				if(per.getQva_upd()==true) {
					return true;
				}else {
					return false;
				}
			case "tms_re":
				if(per.getTms_re()==true) {
					return true;
				}else {
					return false;
				}
			case "user_reg":
				if(per.getUser_reg()==true) {
					return true;
				}else {
					return false;
				}
			case "iss_list":
				if(per.getIss_list()==true) {
					return true;
				}else {
					return false;
				}
			case "tms_list":
				if(per.getTms_list()==true) {
					return true;
				}else {
					return false;
				}
			case "qva_list":
				if(per.getQva_list()==true) {
					return true;
				}else {
					return false;
				}
			default:
				return false;
			}
		}
		
		
		
		
		
	}

	@Override
	public Permission getPer(int id) {
		String sql = "select * from permission where ROLE_ID= ?";
		Permission per=jdbcTemplate.queryForObject(sql, new Object[] {id}, new BeanPropertyRowMapper<Permission>(Permission.class));
		return per;
	}
	
	
	@Override
	public List<Permission> getAllPer() {
		return jdbcTemplate.query("select * from permission", new RowMapper<Permission>() {

			@Override
			public Permission mapRow(ResultSet rs, int rowNum) throws SQLException {
				Permission perm = new Permission();
				
				perm.setPer_id(rs.getInt(1));
				perm.setOver_view(rs.getBoolean(2));
				perm.setPro_detail(rs.getBoolean(3));
				perm.setAdd_pro(rs.getBoolean(4));
				perm.setSet_upd(rs.getBoolean(5));
				perm.setSet_res(rs.getBoolean(6));
				perm.setCre_iss(rs.getBoolean(7));
				perm.setUpd_iss(rs.getBoolean(8));
				perm.setSet_reg(rs.getBoolean(9));
				perm.setEff_mana(rs.getBoolean(10));
				perm.setEff_can(rs.getBoolean(11));
				perm.setQva_upd(rs.getBoolean(12));
				perm.setTms_re(rs.getBoolean(13));
				perm.setUser_reg(rs.getBoolean(14));
				perm.setIss_list(rs.getBoolean(15));
				perm.setTms_list(rs.getBoolean(16));
				perm.setQva_list(rs.getBoolean(17));
				perm.setROLE_ID(rs.getInt(18));
				return perm;
			}
		});
	}

	@Override
	public boolean updatePer(Permission per) {
		jdbcTemplate.update("update permission set over_view=?,pro_detail=?,"
				+ "add_pro=?,set_upd=?,set_res=?,cre_iss=?,upd_iss=?,set_reg=?,"
				+ "eff_mana=?,eff_can=?,qva_upd=?,tms_re=?,user_reg=?,iss_list=?,"
				+ "tms_list=?,qva_list=?"
				+ " where per_id = ?", 
				
				
			
				
				
                per.getOver_view(),per.getPro_detail(), per.getAdd_pro(),per.getSet_upd(),
                per.getSet_res(),per.getCre_iss(),per.getUpd_iss(),per.getSet_reg(),
                per.getEff_mana(),per.getEff_can(),per.getQva_upd(),per.getTms_re(),per.getUser_reg(),
                per.getIss_list(),per.getTms_list(),per.getQva_list(),per.getPer_id());
		
	
		return true;
	}

	
}
