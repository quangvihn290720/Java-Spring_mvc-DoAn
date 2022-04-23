package com.khoaluantotnghiep.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.khoaluantotnghiep.entity.RoleEntity;

public class RoleMapper implements RowMapper<RoleEntity> {

	@Override
	public RoleEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		RoleEntity role = new RoleEntity();
		role.setId(rs.getInt("id"));
		role.setName(rs.getString("name"));
		role.setCode(rs.getString("code"));
		role.setStatus(rs.getInt("status"));
		role.setCreated_at(rs.getDate("created_at"));
		role.setUpdated_at(rs.getDate("updated_at"));
		role.setCreated_by(rs.getInt("created_by"));
		role.setUpdated_by(rs.getInt("updated_by"));
		return role;
	}

}

