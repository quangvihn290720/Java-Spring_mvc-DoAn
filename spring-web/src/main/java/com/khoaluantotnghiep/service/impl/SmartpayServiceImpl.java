package com.khoaluantotnghiep.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khoaluantotnghiep.dao.SmartpayDAO;
import com.khoaluantotnghiep.entity.SmartpayEntity;
import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.service.ISmartpayService;

@Service
public class SmartpayServiceImpl implements ISmartpayService {

	@Autowired
	SmartpayDAO smartpayDAO;

	@Override
	public List<SmartpayEntity> GetDataSmartpayPaginate(int start, int totalPage) {
		return smartpayDAO.GetDataSmartpayPaginate(start, totalPage);
	}

	@Override
	public List<SmartpayEntity> GetDataSmartpayTrashPaginate(int start, int totalPage) {
		return smartpayDAO.GetDataSmartpayTrashPaginate(start, totalPage);
	}

	@Override
	public List<SmartpayEntity> findAllSmartpay() {
		return smartpayDAO.findAllSmartpay();
	}

	@Override
	public List<SmartpayEntity> findAllSmartpayShow() {
		return smartpayDAO.findAllSmartpayShow();
	}

	@Override
	public SmartpayEntity findSmartpayById(SmartpayEntity Smartpay) {
		return smartpayDAO.findSmartpayById(Smartpay);
	}

	@Override
	public List<SmartpayEntity> findTrashSmartpay() {
		return smartpayDAO.findTrashSmartpay();
	}

	@Override
	public void deltrashSmartpay(int id,UserEntity loginInfo) {
		smartpayDAO.deltrashSmartpay(id, loginInfo);
	}

	@Override
	public void updateSmartpay(SmartpayEntity smartpay) {
		smartpayDAO.updateSmartpay(smartpay);
	}

	@Override
	public void addSmartpay(SmartpayEntity smartpay) {
		smartpayDAO.addSmartpay(smartpay);
	}

	@Override
	public void retrashSmartpay(int id,UserEntity loginInfo) {
		smartpayDAO.retrashSmartpay(id, loginInfo);
	}

	@Override
	public void deleteSmartpay(int id) {
		smartpayDAO.deleteSmartpay(id);
	}

	@Override
	public void onOffSmartpay(int id,UserEntity loginInfo) {
		smartpayDAO.onOffSmartpay(id, loginInfo);
	}

	@Override
	public boolean isNameExists(String name) {
		return smartpayDAO.isNameExists(name);
	}

	@Override
	public boolean isNameExists(String name, int id) {
		return smartpayDAO.isNameExists(name, id);
	}
}

