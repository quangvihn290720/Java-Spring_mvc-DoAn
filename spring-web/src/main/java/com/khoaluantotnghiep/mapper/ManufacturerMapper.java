package com.khoaluantotnghiep.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.khoaluantotnghiep.entity.ManufacturerEntity;

public class ManufacturerMapper implements RowMapper<ManufacturerEntity> {

	@Override
	public ManufacturerEntity mapRow(ResultSet rs, int rowNum) throws SQLException {

		ManufacturerEntity manufacturer = new ManufacturerEntity();
		manufacturer.setManufacturer_id(rs.getInt("manufacturer_id"));
		manufacturer.setManufacturer_name(rs.getString("manufacturer_name"));
		manufacturer.setManufacturer_slug(rs.getString("manufacturer_slug"));
		manufacturer.setManufacturer_img(rs.getString("manufacturer_img"));
		manufacturer.setStatus(rs.getInt("status"));
		manufacturer.setCreated_at(rs.getDate("created_at"));
		manufacturer.setUpdated_at(rs.getDate("updated_at"));
		manufacturer.setCreated_by(rs.getInt("created_by"));
		manufacturer.setUpdated_by(rs.getInt("updated_by"));
		return manufacturer;
	}

}
