package com.khoaluantotnghiep.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.khoaluantotnghiep.entity.ProductOptionsEntity;

public class ProductOptionMapper implements RowMapper<ProductOptionsEntity> {

	@Override
	public ProductOptionsEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductOptionsEntity productoption = new ProductOptionsEntity();
		productoption.setProductoptions_id(rs.getInt("productoptions_id"));
		productoption.setProduct_id(rs.getInt("product_id"));
		productoption.setOptiongroup_id(rs.getInt("optiongroup_id"));
		productoption.setOption_id(rs.getInt("option_id"));
		productoption.setMetakey(rs.getString("metakey"));
		productoption.setMetadesc(rs.getString("metadesc"));
		productoption.setStatus(rs.getInt("status"));
		productoption.setCreated_by(rs.getInt("created_by"));
		productoption.setUpdated_by(rs.getInt("updated_by"));
		productoption.setCreated_at(rs.getDate("created_at"));
		productoption.setUpdated_at(rs.getDate("updated_at"));
		return productoption;
	}

}
