package com.khoaluantotnghiep.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khoaluantotnghiep.dao.ContactDAO;
import com.khoaluantotnghiep.entity.ContactEntity;
import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.service.IContactService;

@Service
public class ContactServiceImpl implements IContactService {
	@Autowired
	ContactDAO contactDAO;

	@Override
	public void addContact(ContactEntity contact) {
		contactDAO.addContact(contact);
	}

	@Override
	public void deleteContact(int id) {
		contactDAO.deleteContact(id);
	}

	@Override
	public List<ContactEntity> findAllContact() {
		return contactDAO.findAllContact();
	}

	@Override
	public List<ContactEntity> findAllTrashContact() {
		return contactDAO.findAllTrashContact();
	}

	@Override
	public ContactEntity findContactById(int id) {
		return contactDAO.findContactById(id);
	}

	@Override
	public List<ContactEntity> GetDataContactsPaginate(int start, int totalPage) {
		return contactDAO.GetDataContactsPaginate(start, totalPage);
	}
	@Override
	public void onOffContact(int id,UserEntity loginInfo) {
		contactDAO.onOffContact(id, loginInfo);
	}
}
