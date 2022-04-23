package com.khoaluantotnghiep.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.khoaluantotnghiep.entity.ServiceEntity;
import com.khoaluantotnghiep.entity.UserEntity;

@Service
public interface IServiceService {
	public List<ServiceEntity> GetDataServicePaginate(int start, int totalPage);

	public List<ServiceEntity> GetDataServiceTrashPaginate(int start, int totalPage);

	public List<ServiceEntity> findAllService();

	public List<ServiceEntity> findAllServiceShow();

	public List<ServiceEntity> findTrashService();

	public ServiceEntity findServiceById(int id);

	public void onOff(int id,UserEntity loginInfo);

	public void delTrash(int id,UserEntity loginInfo);

	public void reTrash(int id,UserEntity loginInfo);

	public void delete(int id);

	public void add(ServiceEntity service);

	public void update(ServiceEntity service);

	public boolean isNameExists(String name);

	public boolean isNameExists(String name, int id);
}
