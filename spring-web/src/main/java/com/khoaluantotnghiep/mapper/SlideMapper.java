package com.khoaluantotnghiep.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.khoaluantotnghiep.entity.SlideEntity;

public class SlideMapper implements RowMapper<SlideEntity> {
	@Override
	public SlideEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		SlideEntity slide = new SlideEntity();
		slide.setSlide_id(rs.getInt("slide_id"));
		slide.setSlide_caption(rs.getString("slide_caption"));
		slide.setSlide_img(rs.getString("slide_img"));
		slide.setSlide_status(rs.getInt("slide_status"));
		slide.setCreated_at(rs.getDate("created_at"));
		slide.setUpdated_at(rs.getDate("updated_at"));
		slide.setCreated_by(rs.getInt("created_by"));
		slide.setUpdated_by(rs.getInt("updated_by"));
		return slide;
	}
}
