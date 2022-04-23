package com.khoaluantotnghiep.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.khoaluantotnghiep.entity.BilldetailEntity;

public class BilldetailMapper implements RowMapper<BilldetailEntity> {

	@Override
	public BilldetailEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		BilldetailEntity billdetail = new BilldetailEntity();
		billdetail.setId(rs.getInt("id"));
		billdetail.setProduct_id(rs.getInt("product_id"));
		billdetail.setBills_id(rs.getInt("bills_id"));
		billdetail.setQuanty(rs.getInt("quanty"));
		billdetail.setTotal(rs.getDouble("total"));
		return billdetail;
	}

}
