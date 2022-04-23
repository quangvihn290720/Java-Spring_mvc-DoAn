package com.khoaluantotnghiep.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.khoaluantotnghiep.entity.ConfigwebEntity;

public class ConfigwebMapper implements RowMapper<ConfigwebEntity> {

	@Override
	public ConfigwebEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		ConfigwebEntity configweb = new ConfigwebEntity();
		configweb.setId(rs.getInt("id"));
		configweb.setNameweb(rs.getString("nameweb"));
		configweb.setWeb_detail(rs.getString("web_detail"));
		configweb.setHotline(rs.getString("hotline"));
		configweb.setLogo(rs.getString("logo"));
		configweb.setLogomobile(rs.getString("logomobile"));
		configweb.setIcon(rs.getString("icon"));
		configweb.setEmail(rs.getString("email"));
		configweb.setAddress_store(rs.getString("address_store"));
		configweb.setStatus(rs.getInt("status"));
		return configweb;
	}

}
