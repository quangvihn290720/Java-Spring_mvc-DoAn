package com.khoaluantotnghiep.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.khoaluantotnghiep.entity.ManufacturerEntity;
import com.khoaluantotnghiep.entity.UserEntity;

@Service
public interface IManufacturerService {
	public List<ManufacturerEntity> findAllManufacturer();

	public ManufacturerEntity findAllManufacturerBySlug(String manufacturer_slug);

	public List<ManufacturerEntity> findAllManufacturerShow();

	public void addManufacturer(ManufacturerEntity manufacturer);

	public void deleteManufacturer(int manufacturer_id);

	public void updateManufacturer(ManufacturerEntity manufacturer);

	public ManufacturerEntity findManufacturerById(ManufacturerEntity manufacturer);

	public List<ManufacturerEntity> findTrashManufacturer();

	public void deltrash(int manufacturer_id,UserEntity loginInfo);

	public void retrash(int manufacturer_id,UserEntity loginInfo);

	public void onOffManufacturer(int manufacturer_id,UserEntity loginInfo);

	public List<ManufacturerEntity> GetDataManufacturerPaginate(int start, int totalPage);

	public List<ManufacturerEntity> GetDataManufacturerTrashPaginate(int start, int totalPage);

	public Map<Integer, String> mapManu();

	public boolean isNameExists(String email);

	public boolean isNameExists(String email, int id);

	public boolean isSlugExists(String slug);

	public boolean isSlugExists(String slug, int id);
}
