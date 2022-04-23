package com.khoaluantotnghiep.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.khoaluantotnghiep.entity.SmartpayEntity;

public class SmartpayMapper implements RowMapper<SmartpayEntity> {

	@Override
	public SmartpayEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		SmartpayEntity smartpay = new SmartpayEntity();
		smartpay.setId(rs.getInt("id"));
		smartpay.setName(rs.getString("name"));
		smartpay.setImg(rs.getString("img"));
		smartpay.setMetakey(rs.getString("metakey"));
		smartpay.setMetadesc(rs.getString("metadesc"));
		smartpay.setStatus(rs.getInt("status"));
		smartpay.setCreated_at(rs.getDate("created_at"));
		smartpay.setCreated_by(rs.getInt("created_by"));
		smartpay.setUpdated_at(rs.getDate("updated_at"));
		smartpay.setUpdated_by(rs.getInt("updated_by"));
		return smartpay;
	}

}