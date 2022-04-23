package com.khoaluantotnghiep.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.khoaluantotnghiep.entity.PageEntity;

public class PageMapper implements RowMapper<PageEntity> {

	@Override
	public PageEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		PageEntity page = new PageEntity();
		page.setPage_id(rs.getInt("page_id"));
		page.setPage_title(rs.getString("page_title"));
		page.setPage_slug(rs.getString("page_slug"));
		page.setPage_detail(rs.getString("page_detail"));
		page.setPage_img(rs.getString("page_img"));
		page.setPage_metakey(rs.getString("page_metakey"));
		page.setPage_metadesc(rs.getString("page_metadesc"));
		page.setPage_status(rs.getInt("page_status"));
		page.setPage_topic(rs.getInt("page_topic"));
		page.setCreated_by(rs.getInt("created_by"));
		page.setUpdated_by(rs.getInt("updated_by"));
		page.setCreated_at(rs.getDate("created_at"));
		page.setUpdated_at(rs.getDate("updated_at"));
		return page;
	}

}
