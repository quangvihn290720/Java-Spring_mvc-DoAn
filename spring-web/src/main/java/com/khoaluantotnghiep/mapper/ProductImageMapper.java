package com.khoaluantotnghiep.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.khoaluantotnghiep.entity.ProductImageEntity;

public class ProductImageMapper implements RowMapper<ProductImageEntity> {

	@Override
	public ProductImageEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductImageEntity prodimg = new ProductImageEntity();
		prodimg.setId(rs.getInt("id"));
		prodimg.setProduct_id(rs.getInt("product_id"));
		prodimg.setImg(rs.getString("img"));
		prodimg.setStatus(rs.getInt("status"));
		prodimg.setCreated_at(rs.getDate("created_at"));
		prodimg.setUpdated_at(rs.getDate("updated_at"));
		prodimg.setCreated_by(rs.getInt("created_by"));
		prodimg.setUpdated_by(rs.getInt("updated_by"));
		return prodimg;
	}

}