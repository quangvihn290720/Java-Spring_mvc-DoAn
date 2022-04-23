package com.khoaluantotnghiep.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.khoaluantotnghiep.entity.NoteEntity;
import com.khoaluantotnghiep.mapper.NoteMapper;

@Transactional
@Repository
public class NoteDAO extends BaseDAO {
	public List<NoteEntity> findAllNote() {
		String sql = "SELECT * FROM note ";
		List<NoteEntity> list = jdbcTemplate.query(sql, new NoteMapper());
		return list;
	}

	public List<NoteEntity> findAllNoteByUser(int created_at) {
		String sql = "SELECT * FROM note where created_by = " + created_at;
		List<NoteEntity> list = jdbcTemplate.query(sql, new NoteMapper());
		return list;
	}

	public void addNote(NoteEntity note) {
		String sql = "INSERT INTO `note`(`content`, `created_by`, `created_at`) VALUES (?,?,?)";
		jdbcTemplate.update(sql, note.getContent(), note.getCreated_by(), note.getCreated_at());
	}

	public void deleteNote(int id) {
		String sql = "DELETE FROM note WHERE id  = " + id;
		jdbcTemplate.update(sql);
	}

	public List<NoteEntity> findNoteByTimeandUser(int created_by, Date to, Date from) {
		String sql = "SELECT * FROM note where created_by = '" + created_by + "' AND created_at BETWEEN '" + to
				+ "' AND '" + from + "'";
		List<NoteEntity> result = jdbcTemplate.query(sql, new NoteMapper());
		return result;
	}

	public List<NoteEntity> findNoteByTimeBetween(String to, String from) {
		String sql = "SELECT * FROM note where created_at BETWEEN '" + to + "' AND '" + from + "'";
		List<NoteEntity> result = jdbcTemplate.query(sql, new NoteMapper());
		return result;
	}

	private StringBuffer SqlStringByTime(String to, String from) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM note where created_at BETWEEN '" + to + "' AND '" + from + "'");
		return sql;
	}

	private String SqlNoteByTimePaginate(int start, int totalPage, String to, String from) {
		StringBuffer sql = SqlStringByTime(to, from);
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	public List<NoteEntity> GetDataNoteByTimePaginate(int start, int totalPage, String to, String from) {
		StringBuffer sqlGetData = SqlStringByTime(to, from);
		List<NoteEntity> listNote = jdbcTemplate.query(sqlGetData.toString(), new NoteMapper());
		List<NoteEntity> listNotes = new ArrayList<NoteEntity>();
		if (listNote.size() > 0) {
			String sql = SqlNoteByTimePaginate(start, totalPage, to, from);
			listNotes = jdbcTemplate.query(sql, new NoteMapper());
		}
		return listNotes;
	}

	private StringBuffer SqlString() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM note ");
		return sql;
	}

	private String SqlNotePaginate(int start, int totalPage) {
		StringBuffer sql = SqlString();
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	public List<NoteEntity> GetDataNotePaginate(int start, int totalPage) {
		StringBuffer sqlGetData = SqlString();
		List<NoteEntity> listNote = jdbcTemplate.query(sqlGetData.toString(), new NoteMapper());
		List<NoteEntity> listNotes = new ArrayList<NoteEntity>();
		if (listNote.size() > 0) {
			String sql = SqlNotePaginate(start, totalPage);
			listNotes = jdbcTemplate.query(sql, new NoteMapper());
		}
		return listNotes;
	}
}
