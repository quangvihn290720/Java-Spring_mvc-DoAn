package com.khoaluantotnghiep.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khoaluantotnghiep.dao.UtilitiesDAO;
import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.entity.UtilitiesEntity;
import com.khoaluantotnghiep.service.IUtilitiesService;

@Service
public class UtilitiesServiceImpl implements IUtilitiesService {

	@Autowired
	UtilitiesDAO utilitiesDAO;
	@Autowired
	ProductServiceImpl productService;

	@Override
	public List<UtilitiesEntity> findAllUtilities() {
		return utilitiesDAO.findAllUtilities();
	}

	@Override
	public UtilitiesEntity findUtilById(UtilitiesEntity util) {
		return utilitiesDAO.findUtilById(util);
	}

	@Override
	public void addUtilities(UtilitiesEntity util) {
		utilitiesDAO.addUtilities(util);
	}

	@Override
	public void updateUtilities(UtilitiesEntity util) {
		utilitiesDAO.updateUtilities(util);
	}

	@Override
	public void deleteUtilities(int utilities_id) {
		utilitiesDAO.deleteUtilities(utilities_id);
	}

	@Override
	public List<UtilitiesEntity> findAllUtilitiesShow() {
		return utilitiesDAO.findAllUtilitiesShow();
	}

	@Override
	public List<UtilitiesEntity> GetDataUtilitiesPaginate(int start, int totalPage) {
		return utilitiesDAO.GetDataUtilitiesPaginate(start, totalPage);
	}

	@Override
	public List<UtilitiesEntity> GetDataUtilitiesTrashPaginate(int start, int totalPage) {
		return utilitiesDAO.GetDataUtilitiesTrashPaginate(start, totalPage);
	}

	@Override
	public void deltrash(int utilities_id,UserEntity loginInfo) {
		utilitiesDAO.deltrash(utilities_id, loginInfo);
	}

	@Override
	public void retrash(int utilities_id,UserEntity loginInfo) {
		utilitiesDAO.retrash(utilities_id, loginInfo);
	}

	@Override
	public void onOffTopic(int utilities_id,UserEntity loginInfo) {
		utilitiesDAO.onOffTopic(utilities_id, loginInfo);
	}

	@Override
	public List<UtilitiesEntity> findAllTrashUtilities() {
		return utilitiesDAO.findAllTrashUtilities();
	}

	@Override
	public UtilitiesEntity findUtilityByName(UtilitiesEntity utility) {
		return utilitiesDAO.findUtilityByName(utility);
	}
	@Override
	public UtilitiesEntity findOtherUtilityByName(UtilitiesEntity utility) {
		return utilitiesDAO.findOtherUtilityByName(utility);
	}
}
