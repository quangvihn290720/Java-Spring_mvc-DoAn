package com.khoaluantotnghiep.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khoaluantotnghiep.dao.NoteDAO;
import com.khoaluantotnghiep.entity.NoteEntity;
import com.khoaluantotnghiep.service.INoteService;

@Service
public class NoteServiceImpl implements INoteService {

	@Autowired
	NoteDAO noteDAO;

	@Override
	public List<NoteEntity> findAllNote() {
		return noteDAO.findAllNote();
	}

	@Override
	public List<NoteEntity> GetDataNotePaginate(int start, int totalPage) {
		return noteDAO.GetDataNotePaginate(start, totalPage);
	}

	@Override
	public List<NoteEntity> findAllNoteByUser(int created_at) {
		return noteDAO.findAllNoteByUser(created_at);
	}

	@Override
	public void deleteNote(int id) {
		noteDAO.deleteNote(id);
	}

	@Override
	public List<NoteEntity> findNoteByTimeandUser(int created_by, Date to, Date from) {
		return noteDAO.findNoteByTimeandUser(created_by, to, from);
	}

	@Override
	public List<NoteEntity> findNoteByTimeBetween(String to, String from) {
		return noteDAO.findNoteByTimeBetween(to, from);
	}

	@Override
	public void addNote(NoteEntity note) {
		noteDAO.addNote(note);
	}

	@Override
	public List<NoteEntity> GetDataNoteByTimePaginate(int start, int totalPage, String to, String from) {
		return noteDAO.GetDataNoteByTimePaginate(start, totalPage, to, from);
	}


}
