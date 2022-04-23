package com.khoaluantotnghiep.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.khoaluantotnghiep.entity.NoteEntity;

@Service
public interface INoteService {
	public List<NoteEntity> findAllNote();

	public List<NoteEntity> GetDataNotePaginate(int start, int totalPage);

	public List<NoteEntity> findAllNoteByUser(int created_at);

	public void deleteNote(int id);

	public void addNote(NoteEntity note);

	public List<NoteEntity> findNoteByTimeandUser(int created_by, Date to, Date from);

	public List<NoteEntity> GetDataNoteByTimePaginate(int start, int totalPage, String to, String from);

	public List<NoteEntity> findNoteByTimeBetween(String to, String from);

}
