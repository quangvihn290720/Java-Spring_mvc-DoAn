package com.khoaluantotnghiep.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.khoaluantotnghiep.entity.SocialNetWorkEntity;

public class SocialNetWorkMapper implements RowMapper<SocialNetWorkEntity> {

	@Override
	public SocialNetWorkEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		SocialNetWorkEntity socialnetwork = new SocialNetWorkEntity();
		socialnetwork.setId(rs.getInt("id"));
		socialnetwork.setName(rs.getString("name"));
		socialnetwork.setImg(rs.getString("img"));
		socialnetwork.setAddress(rs.getString("address"));
		socialnetwork.setIcon(rs.getString("icon"));
		socialnetwork.setStatus(rs.getInt("status"));
		socialnetwork.setCreated_by(rs.getInt("created_by"));
		socialnetwork.setUpdated_by(rs.getInt("updated_by"));
		socialnetwork.setCreated_at(rs.getDate("created_at"));
		socialnetwork.setUpdated_at(rs.getDate("updated_at"));
		return socialnetwork;
	}

}
