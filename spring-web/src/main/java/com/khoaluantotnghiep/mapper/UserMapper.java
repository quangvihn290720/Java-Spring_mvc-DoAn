package com.khoaluantotnghiep.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.khoaluantotnghiep.entity.UserEntity;

public class UserMapper implements RowMapper<UserEntity> {

	@Override
	public UserEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserEntity user = new UserEntity();
		user.setUser_id(rs.getInt("user_id"));
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("password"));
		user.setUser_fullname(rs.getString("user_fullname"));
		user.setUser_email(rs.getString("user_email"));
		user.setRole(rs.getString("role"));
		user.setRole2(rs.getInt("role2"));
		user.setUser_gender(rs.getString("user_gender"));
		user.setUser_phone(rs.getString("user_phone"));
		user.setUser_img(rs.getString("user_img"));
		user.setEnabled(rs.getInt("enabled"));
		user.setReset_password_token(rs.getString("reset_password_token"));
		user.setCreated_by(rs.getInt("created_by"));
		user.setUpdated_by(rs.getInt("updated_by"));
		user.setCreated_at(rs.getDate("created_at"));
		user.setUpdated_at(rs.getDate("updated_at"));
		return user;
	}

}