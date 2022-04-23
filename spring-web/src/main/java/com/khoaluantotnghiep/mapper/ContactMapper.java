package com.khoaluantotnghiep.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.khoaluantotnghiep.entity.ContactEntity;

public class ContactMapper implements RowMapper<ContactEntity> {

	@Override
	public ContactEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		ContactEntity contact = new ContactEntity();
		contact.setId(rs.getInt("id"));
		contact.setName(rs.getString("name"));
		contact.setPhone(rs.getString("phone"));
		contact.setEmail(rs.getString("email"));
		contact.setAddress(rs.getString("address"));
		contact.setSubject(rs.getString("subject"));
		contact.setContent(rs.getString("content"));
		contact.setStatus(rs.getInt("status"));
		contact.setUpdated_by(rs.getInt("updated_by"));
		contact.setCreated_at(rs.getDate("created_at"));
		contact.setUpdated_at(rs.getDate("updated_at"));
		return contact;
	}

}
