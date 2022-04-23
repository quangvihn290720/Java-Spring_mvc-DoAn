package com.khoaluantotnghiep.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.khoaluantotnghiep.entity.BannerEntity;

public class BannerMapper implements RowMapper<BannerEntity> {

	@Override
	public BannerEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		BannerEntity banner = new BannerEntity();
		banner.setId(rs.getInt("id"));
		banner.setBanner_name(rs.getString("banner_name"));
		banner.setBanner_img(rs.getString("banner_img"));
		banner.setBanner_status(rs.getInt("banner_status"));
		banner.setCreated_by(rs.getInt("created_by"));
		banner.setUpdated_by(rs.getInt("updated_by"));
		banner.setCreated_at(rs.getDate("created_at"));
		banner.setUpdated_at(rs.getDate("updated_at"));
		return banner;
	}

}
