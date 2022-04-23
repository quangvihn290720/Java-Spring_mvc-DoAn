package com.khoaluantotnghiep.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.khoaluantotnghiep.entity.ConfigwebEntity;
import com.khoaluantotnghiep.mapper.ConfigwebMapper;
@Transactional
@Repository
public class ConfigwebDAO extends BaseDAO {

	public ConfigwebEntity findConfigweb() {
		String sql = "SELECT * FROM configweb";
		List<ConfigwebEntity> list = jdbcTemplate.query(sql, new ConfigwebMapper());
		return list.get(0);
	}

	public void update(ConfigwebEntity config) {
		String sql = "UPDATE configweb SET nameweb = ?, web_detail = ?, hotline = ? "
				+ (config.getLogo()!=null?(",logo = '" + config.getLogo() + "'" ):"")
				+ (config.getLogomobile()!=null?(",logomobile = '" + config.getLogomobile() + "'" ):"")
				+ (config.getIcon()!=null?(",icon = '" + config.getIcon() + "'" ):"")
				+ ", email = ?, address_store = ? WHERE id = ?";
		System.out.println(sql);
		jdbcTemplate.update(sql,config.getNameweb(),config.getWeb_detail(),config.getHotline(),config.getEmail(),config.getAddress_store(),config.getId());
	}

	public void changeStatus(int id) {
		String sql = "UPDATE configweb SET status = case when  status =0 then 1 when  status =1 then 0 end where  id =" +  id;
		jdbcTemplate.update(sql);
		
	}

}

