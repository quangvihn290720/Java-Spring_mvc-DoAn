package com.khoaluantotnghiep.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.khoaluantotnghiep.entity.ContactEntity;
import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.mapper.ContactMapper;

@Repository
@Transactional
public class ContactDAO extends BaseDAO {
	public void addContact(ContactEntity contact) {
		String sql = "INSERT INTO contact (`name`,`phone`,`email`,`address`,`subject`,`content`,`status`,`created_at`)"
				+ " VALUES (?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, contact.getName(), contact.getPhone(), contact.getEmail(), contact.getAddress(),
				contact.getSubject(), contact.getContent(), contact.getStatus(), contact.getCreated_at());
	}

	public void deleteContact(int id) {
		String sql = "DELETE FROM contact WHERE id = " + id;
		jdbcTemplate.update(sql);
	}

	public List<ContactEntity> findAllContact() {
		String sql = "SELECT * FROM contact where status != 0 ";
		List<ContactEntity> list = jdbcTemplate.query(sql, new ContactMapper());
		return list;
	}

	public List<ContactEntity> findAllTrashContact() {
		String sql = "SELECT * FROM contact where status = 0 ";
		List<ContactEntity> list = jdbcTemplate.query(sql, new ContactMapper());
		return list;
	}

	public ContactEntity findContactById(int id) {
		String sql = "SELECT * FROM contact where id = " + id;
		List<ContactEntity> result = jdbcTemplate.query(sql, new ContactMapper());
		return result.isEmpty() ? null : result.get(0);
	}

	private StringBuffer SqlString() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM contact ");
		return sql;
	}

	private String SqlContactsPaginate(int start, int totalPage) {
		StringBuffer sql = SqlString();
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	public List<ContactEntity> GetDataContactsPaginate(int start, int totalPage) {
		StringBuffer sqlGetData = SqlString();
		List<ContactEntity> listContact = jdbcTemplate.query(sqlGetData.toString(), new ContactMapper());
		List<ContactEntity> listContacts = new ArrayList<ContactEntity>();
		if (listContact.size() > 0) {
			String sql = SqlContactsPaginate(start, totalPage);
			listContacts = jdbcTemplate.query(sql, new ContactMapper());
		}
		return listContacts;
	}

	public void onOffContact(int id,UserEntity loginInfo) {
		String sql = "UPDATE contact SET status = case WHEN status =1 then 2 when status=2 then 1 end,updated_by ="
				+ loginInfo.getUser_id() + ", updated_at = NOW() where id = " + id;
		jdbcTemplate.update(sql);
	}
}
