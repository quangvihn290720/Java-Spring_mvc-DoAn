package com.khoaluantotnghiep.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khoaluantotnghiep.dao.RoleDAO;
import com.khoaluantotnghiep.entity.RoleEntity;
import com.khoaluantotnghiep.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {
	@Autowired
	RoleDAO roleDAO;

	@Override
	public List<RoleEntity> GetDataRolePaginate(int start, int totalPage) {
		return roleDAO.GetDataRolePaginate(start, totalPage);
	}

	@Override
	public List<RoleEntity> GetDataRoleTrashPaginate(int start, int totalPage) {
		return roleDAO.GetDataRoleTrashPaginate(start, totalPage);
	}

	@Override
	public List<RoleEntity> findAllRole() {
		return roleDAO.findAllRole();
	}

	@Override
	public List<RoleEntity> findAllRoleShow() {
		return roleDAO.findAllRoleShow();
	}

	@Override
	public List<RoleEntity> findTrashRole() {
		return roleDAO.findTrashRole();
	}

	@Override
	public RoleEntity findRoleById(int id) {
		return roleDAO.findRoleById(id);
	}

	@Override
	public void onOff(int id) {
		roleDAO.onOff(id);
	}

	@Override
	public void delTrash(int id) {
		roleDAO.delTrash(id);
	}

	@Override
	public void reTrash(int id) {
		roleDAO.reTrash(id);
	}

	@Override
	public void update(RoleEntity role) {
		roleDAO.update(role);
	}

	@Override
	public void delete(int id) {
		roleDAO.delete(id);
	}

	@Override
	public void add(RoleEntity role) {
		roleDAO.add(role);
	}

	@Override
	public boolean isNameExists(String name, int id) {
		return roleDAO.isNameExists(name, id);
	}

	@Override
	public boolean isNameExists(String name) {
		return roleDAO.isNameExists(name);
	}

	@Override
	public boolean isCodeExists(String code) {
		return roleDAO.isCodeExists(code);
	}

	@Override
	public boolean isCodeExists(String code, int id) {
		return roleDAO.isCodeExists(code, id);
	}

	@Override
	public Map<Integer, String> mapRole() {
		Map<Integer, String> map = new HashMap<Integer, String>();
		List<RoleEntity> list = roleDAO.findAllRole();
		for (RoleEntity ct : list) {
			map.put(ct.getId(), ct.getName());
		}
		return map;
	}
}

