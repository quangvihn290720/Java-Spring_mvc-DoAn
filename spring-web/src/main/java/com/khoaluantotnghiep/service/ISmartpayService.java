package com.khoaluantotnghiep.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.khoaluantotnghiep.entity.SmartpayEntity;
import com.khoaluantotnghiep.entity.UserEntity;

@Service
public interface ISmartpayService {
	public List<SmartpayEntity> GetDataSmartpayPaginate(int start, int totalPage);

	public List<SmartpayEntity> GetDataSmartpayTrashPaginate(int start, int totalPage);

	public List<SmartpayEntity> findAllSmartpay();

	public List<SmartpayEntity> findAllSmartpayShow();

	public SmartpayEntity findSmartpayById(SmartpayEntity smartpay);

	public List<SmartpayEntity> findTrashSmartpay();

	public void deltrashSmartpay(int id,UserEntity loginInfo);

	public void updateSmartpay(SmartpayEntity smartpay);

	public void addSmartpay(SmartpayEntity smartpay);

	public void retrashSmartpay(int id,UserEntity loginInfo);

	public void deleteSmartpay(int id);

	public void onOffSmartpay(int id,UserEntity loginInfo);

	public boolean isNameExists(String name);

	public boolean isNameExists(String name, int id);

}

