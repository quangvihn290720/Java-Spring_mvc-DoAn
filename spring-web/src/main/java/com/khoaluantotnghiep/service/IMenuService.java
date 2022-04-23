package com.khoaluantotnghiep.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.khoaluantotnghiep.entity.MenuEntity;
import com.khoaluantotnghiep.entity.UserEntity;

@Service
public interface IMenuService {
	public List<MenuEntity> GetDataMenuShow();

	public List<MenuEntity> findAllMenu();

	public List<MenuEntity> GetDataMenuPaginate(int start, int totalPage);

	public List<MenuEntity> GetDataMenuTrashPaginate(int start, int totalPage);

	public void addMenu(MenuEntity menu);

	public boolean isNameExists(String name);

	public boolean isNameExists(String name, int id);

	public boolean isSlugExists(String slug);

	public boolean isSlugExists(String slug, int id);

	public MenuEntity findMenuById(int menu_id);

	public void updateMenu(MenuEntity menu);

	public void onOffMenu(int post_id,UserEntity loginInfo);

	public List<MenuEntity> findTrashMenu();

	public void delTrash(int menu_id,UserEntity loginInfo);

	public void reTrash(int menu_id,UserEntity loginInfo);

	public void deleteMenu(int menu_id);

	public List<MenuEntity> listMenuByParent(int parent_id);

	public Map<Integer, List<MenuEntity>> mapchildMenu();

}
