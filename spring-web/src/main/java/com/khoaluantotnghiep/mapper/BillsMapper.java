package com.khoaluantotnghiep.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.khoaluantotnghiep.entity.BillsEntity;

public class BillsMapper implements RowMapper<BillsEntity> {

	@Override
	public BillsEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		BillsEntity bills = new BillsEntity();
		bills.setId(rs.getInt("id"));
		bills.setEmail(rs.getString("email"));
		bills.setDisplay_name(rs.getString("display_name"));
		bills.setPhone(rs.getString("phone"));
		bills.setAddress(rs.getString("address"));
		bills.setProvince(rs.getString("province"));
		bills.setDistrict(rs.getString("district"));
		bills.setCity(rs.getString("city"));
		bills.setNote(rs.getString("note"));
		bills.setTotal(rs.getDouble("total"));
		bills.setQuanty(rs.getInt("quanty"));
		bills.setStatus(rs.getInt("status"));
		bills.setCreated_at(rs.getString("created_at"));
		bills.setUpdated_at(rs.getString("updated_at"));
		bills.setUpdated_by(rs.getInt("updated_by"));
		bills.setCoupon(rs.getBoolean("coupon"));
		bills.setCoupon_id(rs.getInt("coupon_id"));
		bills.setCode(rs.getString("code"));
		return bills;
	}

}
