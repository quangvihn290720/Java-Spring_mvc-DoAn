package com.khoaluantotnghiep.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.khoaluantotnghiep.entity.CategoryEntity;

public class CategoryMapper implements RowMapper<CategoryEntity> {

	@Override
	public CategoryEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		CategoryEntity category = new CategoryEntity();
		category.setId(rs.getInt("id"));
		category.setName(rs.getString("name"));
		category.setSlug(rs.getString("slug"));
		category.setMetakey(rs.getString("metakey"));
		category.setMetadesc(rs.getString("metadesc"));
		category.setStatus(rs.getInt("status"));
		category.setCreated_at(rs.getDate("created_at"));
		category.setUpdated_at(rs.getDate("updated_at"));
		category.setCreated_by(rs.getInt("created_by"));
		category.setUpdated_by(rs.getInt("updated_by"));
		return category;
	}

}
