package com.khoaluantotnghiep.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.khoaluantotnghiep.entity.UtilitiesEntity;

public class UtilitiesMapper implements RowMapper<UtilitiesEntity> {

	@Override
	public UtilitiesEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		UtilitiesEntity utilities = new UtilitiesEntity();
		utilities.setUtilities_id(rs.getInt("utilities_id"));
		utilities.setProduct_id(rs.getInt("product_id"));
		utilities.setUtilitiesname(rs.getString("utilitiesname"));
		utilities.setMetakey(rs.getString("metakey"));
		utilities.setMetadesc(rs.getString("metadesc"));
		utilities.setStatus(rs.getInt("status"));
		utilities.setCreated_by(rs.getInt("created_by"));
		utilities.setUpdated_by(rs.getInt("updated_by"));
		utilities.setCreated_at(rs.getDate("created_at"));
		utilities.setUpdated_at(rs.getDate("updated_at"));
		return utilities;
	}

}
