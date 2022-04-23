package com.khoaluantotnghiep.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.khoaluantotnghiep.entity.ServiceEntity;

public class ServiceMapper implements RowMapper<ServiceEntity> {

	@Override
	public ServiceEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		ServiceEntity service = new ServiceEntity();
		service.setId(rs.getInt("id"));
		service.setName(rs.getString("name"));
		service.setImg(rs.getString("img"));
		service.setMetadesc(rs.getString("metadesc"));
		service.setMetakey(rs.getString("metakey"));
		service.setStatus(rs.getInt("status"));
		service.setCreated_at(rs.getDate("created_at"));
		service.setUpdated_at(rs.getDate("updated_at"));
		service.setCreated_by(rs.getInt("created_by"));
		service.setUpdated_by(rs.getInt("updated_by"));
		return service;
	}

}