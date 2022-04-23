package com.khoaluantotnghiep.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.khoaluantotnghiep.entity.TopbarEntity;

public class TopbarMapper implements RowMapper<TopbarEntity> {

	@Override
	public TopbarEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		TopbarEntity topbar = new TopbarEntity();
		topbar.setId(rs.getInt("id"));
		topbar.setName(rs.getString("name"));
		topbar.setImg(rs.getString("img"));
		topbar.setStatus(rs.getInt("status"));
		topbar.setCreated_at(rs.getDate("created_at"));
		topbar.setUpdated_at(rs.getDate("updated_at"));
		topbar.setCreated_by(rs.getInt("created_by"));
		topbar.setUpdated_by(rs.getInt("updated_by"));
		return topbar;
	}

}
