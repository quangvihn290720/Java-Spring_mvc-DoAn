package com.khoaluantotnghiep.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khoaluantotnghiep.dao.ServiceDAO;
import com.khoaluantotnghiep.entity.ServiceEntity;
import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.service.IServiceService;

@Service
public class ServiceServiceImpl implements IServiceService {

	@Autowired
	ServiceDAO serviceDAO;

	@Override
	public List<ServiceEntity> GetDataServicePaginate(int start, int totalPage) {
		return serviceDAO.GetDataServicePaginate(start, totalPage);
	}

	@Override
	public List<ServiceEntity> GetDataServiceTrashPaginate(int start, int totalPage) {
		return serviceDAO.GetDataServiceTrashPaginate(start, totalPage);
	}

	@Override
	public List<ServiceEntity> findAllService() {
		return serviceDAO.findAllService();
	}

	@Override
	public List<ServiceEntity> findAllServiceShow() {
		return serviceDAO.findAllServiceShow();
	}

	@Override
	public List<ServiceEntity> findTrashService() {
		return serviceDAO.findTrashService();
	}

	@Override
	public ServiceEntity findServiceById(int id) {
		return serviceDAO.findServiceById(id);
	}

	@Override
	public void onOff(int id,UserEntity loginInfo) {
		serviceDAO.onOff(id, loginInfo);
	}

	@Override
	public void delTrash(int id,UserEntity loginInfo) {
		serviceDAO.delTrash(id, loginInfo);
	}

	@Override
	public void reTrash(int id,UserEntity loginInfo) {
		serviceDAO.reTrash(id, loginInfo);
	}

	@Override
	public void delete(int id) {
		serviceDAO.delete(id);
	}

	@Override
	public void add(ServiceEntity service) {
		serviceDAO.add(service);
	}

	@Override
	public void update(ServiceEntity service) {
		serviceDAO.update(service);
	}
	@Override
	public boolean isNameExists(String name) {
		return serviceDAO.isNameExists(name);
	}

	@Override
	public boolean isNameExists(String name, int id) {
		return serviceDAO.isNameExists(name, id);
	}
}