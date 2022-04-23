package com.khoaluantotnghiep.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.khoaluantotnghiep.entity.ContactEntity;
import com.khoaluantotnghiep.entity.UserEntity;

@Service
public interface IContactService {
	public void addContact(ContactEntity contact);

	public void deleteContact(int id);

	public List<ContactEntity> findAllContact();

	public List<ContactEntity> findAllTrashContact();

	public ContactEntity findContactById(int id);

	public List<ContactEntity> GetDataContactsPaginate(int start, int totalPage);

	public void onOffContact(int id,UserEntity loginInfo);
}
