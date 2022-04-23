package com.khoaluantotnghiep.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khoaluantotnghiep.dao.ManufacturerDAO;
import com.khoaluantotnghiep.entity.ManufacturerEntity;
import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.service.IManufacturerService;

@Service
public class ManufacturerServiceImpl implements IManufacturerService {

	@Autowired
	ManufacturerDAO manufacturerDAO;

	@Override
	public List<ManufacturerEntity> findAllManufacturer() {
		return manufacturerDAO.findAllManufacturer();
	}

	@Override
	public List<ManufacturerEntity> findAllManufacturerShow() {
		return manufacturerDAO.findAllManufacturerShow();
	}

	@Override
	public void addManufacturer(ManufacturerEntity manufacturer) {
		manufacturerDAO.addManufacturer(manufacturer);
	}

	@Override
	public void deleteManufacturer(int manufacturer_id) {
		manufacturerDAO.deleteManufacturer(manufacturer_id);
	}

	@Override
	public void updateManufacturer(ManufacturerEntity manufacturer) {
		manufacturerDAO.updateManufacturer(manufacturer);
	}

	@Override
	public ManufacturerEntity findManufacturerById(ManufacturerEntity manufacturer) {
		return manufacturerDAO.findManufacturerById(manufacturer);
	}

	@Override
	public List<ManufacturerEntity> findTrashManufacturer() {
		return manufacturerDAO.findTrashManufacturer();
	}

	@Override
	public void deltrash(int manufacturer_id,UserEntity loginInfo) {
		manufacturerDAO.deltrash(manufacturer_id,loginInfo);
	}

	@Override
	public void retrash(int manufacturer_id, UserEntity loginInfo) {
		manufacturerDAO.retrash(manufacturer_id,loginInfo);
	}

	@Override
	public void onOffManufacturer(int manufacturer_id, UserEntity loginInfo) {
		manufacturerDAO.onOffManufacturer(manufacturer_id,loginInfo);
	}

	@Override
	public List<ManufacturerEntity> GetDataManufacturerPaginate(int start, int totalPage) {
		return manufacturerDAO.GetDataManufacturerPaginate(start, totalPage);
	}

	@Override
	public List<ManufacturerEntity> GetDataManufacturerTrashPaginate(int start, int totalPage) {
		return manufacturerDAO.GetDataManufacturerTrashPaginate(start, totalPage);
	}

	@Override
	public Map<Integer, String> mapManu() {
		Map<Integer, String> map = new HashMap<Integer, String>();
		List<ManufacturerEntity> list = manufacturerDAO.findAllManufacturer();
		for (ManufacturerEntity ct : list) {
			map.put(ct.getManufacturer_id(), ct.getManufacturer_name());
		}
		return map;
	}

	@Override
	public ManufacturerEntity findAllManufacturerBySlug(String manufacturer_slug) {
		return manufacturerDAO.findAllManufacturerBySlug(manufacturer_slug);
	}
	@Override
	public boolean isNameExists(String email) {
		return manufacturerDAO.isNameExists(email);
	}

	@Override
	public boolean isNameExists(String email, int id) {
		return manufacturerDAO.isNameExists(email, id);
	}

	@Override
	public boolean isSlugExists(String slug) {
		return manufacturerDAO.isSlugExists(slug);
	}

	@Override
	public boolean isSlugExists(String slug, int id) {
		return manufacturerDAO.isSlugExists(slug, id);
	}
}
