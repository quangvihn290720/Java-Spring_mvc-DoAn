package com.khoaluantotnghiep.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khoaluantotnghiep.dao.MenuDAO;
import com.khoaluantotnghiep.entity.MenuEntity;
import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.service.IMenuService;

@Service
public class MenuServiceImpl implements IMenuService {

	@Autowired
	MenuDAO menuDAO;

	@Override
	public List<MenuEntity> GetDataMenuShow() {
		return menuDAO.GetDataMenuShow();
	}

	@Override
	public List<MenuEntity> findAllMenu() {
		return menuDAO.findAllMenu();
	}

	@Override
	public List<MenuEntity> GetDataMenuPaginate(int start, int totalPage) {
		return menuDAO.GetDataMenuPaginate(start, totalPage);
	}

	@Override
	public List<MenuEntity> GetDataMenuTrashPaginate(int start, int totalPage) {
		return menuDAO.GetDataMenuTrashPaginate(start, totalPage);
	}

	@Override
	public void addMenu(MenuEntity menu) {
		menuDAO.addMenu(menu);

	}

	@Override
	public boolean isNameExists(String name) {
		return menuDAO.isNameExists(name);
	}

	@Override
	public boolean isNameExists(String name, int id) {
		return menuDAO.isNameExists(name, id);
	}

	@Override
	public boolean isSlugExists(String slug) {
		return menuDAO.isSlugExists(slug);
	}

	@Override
	public boolean isSlugExists(String slug, int id) {
		return menuDAO.isSlugExists(slug, id);
	}

	@Override
	public MenuEntity findMenuById(int menu_id) {
		return menuDAO.findMenuById(menu_id);
	}

	@Override
	public void updateMenu(MenuEntity menu) {
		menuDAO.updateMenu(menu);

	}

	@Override
	public void onOffMenu(int post_id,UserEntity loginInfo) {
		menuDAO.onOffTPost(post_id, loginInfo);
	}

	@Override
	public List<MenuEntity> findTrashMenu() {
		return menuDAO.findTrashMenu();
	}

	@Override
	public void delTrash(int menu_id,UserEntity loginInfo) {
		menuDAO.delTrash(menu_id, loginInfo);
	}

	@Override
	public void reTrash(int menu_id,UserEntity loginInfo) {
		menuDAO.reTrash(menu_id, loginInfo);
	}

	@Override
	public void deleteMenu(int menu_id) {
		menuDAO.deleteMenu(menu_id);
	}

	@Override
	public Map<Integer, List<MenuEntity>> mapchildMenu() {
		Map<Integer, List<MenuEntity>> map = new HashMap<Integer, List<MenuEntity>>();
		List<MenuEntity> list = menuDAO.findAllMenu();
		for (MenuEntity ct : list) {

			List<MenuEntity> childlist = listMenuByParent(ct.getMenu_id());
			map.put(ct.getMenu_id(), childlist);
		}
		return map;
	}

	@Override
	public List<MenuEntity> listMenuByParent(int parent_id) {
		return menuDAO.listMenuByParent(parent_id);
	}
}
