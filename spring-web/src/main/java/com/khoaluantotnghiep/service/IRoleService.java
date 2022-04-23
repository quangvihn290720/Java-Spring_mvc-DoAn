package com.khoaluantotnghiep.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.khoaluantotnghiep.entity.RoleEntity;

@Service
public interface IRoleService {
	public List<RoleEntity> GetDataRolePaginate(int start, int totalPage);

	public List<RoleEntity> GetDataRoleTrashPaginate(int start, int totalPage);

	public List<RoleEntity> findAllRole();

	public List<RoleEntity> findAllRoleShow();

	public List<RoleEntity> findTrashRole();

	public RoleEntity findRoleById(int id);

	public void onOff(int id);

	public void delTrash(int id);

	public void reTrash(int id);

	public void update(RoleEntity role);

	public void delete(int id);

	public void add(RoleEntity role);

	public boolean isNameExists(String name, int id);

	public boolean isNameExists(String name);

	public boolean isCodeExists(String code);

	public boolean isCodeExists(String code, int id);
	
	public Map<Integer, String> mapRole();
}