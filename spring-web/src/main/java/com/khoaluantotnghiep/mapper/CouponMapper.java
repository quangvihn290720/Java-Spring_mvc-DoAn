package com.khoaluantotnghiep.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.khoaluantotnghiep.entity.CouponEntity;

public class CouponMapper implements RowMapper<CouponEntity> {

	@Override
	public CouponEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		CouponEntity coupon = new CouponEntity();
		coupon.setId(rs.getInt("id"));
		coupon.setCode(rs.getString("code"));
		coupon.setAvailable(rs.getInt("available"));
		coupon.setPricesale(rs.getDouble("pricesale"));
		coupon.setStart(rs.getDate("start"));
		coupon.setEnd(rs.getDate("end"));
		coupon.setStatus(rs.getInt("status"));
		coupon.setCreated_at(rs.getDate("created_at"));
		coupon.setCreated_by(rs.getInt("created_by"));
		coupon.setUpdated_at(rs.getDate("updated_at"));
		coupon.setUpdated_by(rs.getInt("updated_by"));
		return coupon;
	}

}
