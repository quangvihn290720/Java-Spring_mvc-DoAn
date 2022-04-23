package com.khoaluantotnghiep.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.khoaluantotnghiep.entity.ProductEntity;

public class ProductMapper implements RowMapper<ProductEntity> {

	@Override
	public ProductEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductEntity product = new ProductEntity();
		product.setProduct_id(rs.getInt("product_id"));
		product.setProductname(rs.getString("productname"));
		product.setProductprice(rs.getFloat("productprice"));
		product.setProductpricesale(rs.getFloat("productpricesale"));
		product.setProductweight(rs.getFloat("productweight"));
		product.setProductdetail(rs.getString("productdetail"));
		product.setProductshortdesc(rs.getString("productshortdesc"));
		product.setProductimg(rs.getString("productimg"));
		product.setProduct_guarantee(rs.getString("product_guarantee"));
		product.setProduct_catid(rs.getInt("product_catid"));
		product.setProduct_slug(rs.getString("product_slug"));
		product.setProduct_status(rs.getInt("product_status"));
		product.setCreated_at(rs.getDate("created_at"));
		product.setUpdated_at(rs.getDate("updated_at"));
		product.setCreated_by(rs.getInt("created_by"));
		product.setUpdated_by(rs.getInt("updated_by"));
		product.setManufacturer_id(rs.getInt("manufacturer_id"));
		product.setProduct_condition(rs.getString("product_condition"));
		product.setProduct_available(rs.getInt("available"));
		return product;
	}

}
