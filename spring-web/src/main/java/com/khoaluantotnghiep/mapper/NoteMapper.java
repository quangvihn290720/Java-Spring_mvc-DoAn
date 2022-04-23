package com.khoaluantotnghiep.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.khoaluantotnghiep.entity.NoteEntity;

public class NoteMapper implements RowMapper<NoteEntity> {

	@Override
	public NoteEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		NoteEntity note = new NoteEntity();
		note.setId(rs.getInt("id"));
		note.setContent(rs.getString("content"));
		note.setCreated_by(rs.getInt("created_by"));
		note.setCreated_at(rs.getDate("created_at"));
		return note;
	}

}
