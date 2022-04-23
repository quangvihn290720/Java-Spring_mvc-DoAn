package com.khoaluantotnghiep.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khoaluantotnghiep.dao.CategoryDAO;
import com.khoaluantotnghiep.entity.CategoryEntity;
import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.service.ICategoryService;

@Service
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	CategoryDAO categoryDAO;

	@Override
	public List<CategoryEntity> findAllCate() {
		return categoryDAO.findAllCate();
	}

	@Override
	public List<CategoryEntity> findAllCategoryShow() {
		return categoryDAO.findAllCategoryShow();
	}

	@Override
	public void addCategory(CategoryEntity category) {
		categoryDAO.addCategory(category);
	}

	@Override
	public void updateCategory(CategoryEntity category) {
		categoryDAO.updateCategory(category);
	}

	@Override
	public void deleteCategory(int id) {
		categoryDAO.deleteCategory(id);
	}

	@Override
	public CategoryEntity findCategoryById(CategoryEntity category) {
		return categoryDAO.findCategoryById(category);
	}

	@Override
	public List<CategoryEntity> findTrashCategory() {
		return categoryDAO.findTrashCategory();
	}

	@Override
	public void deltrash(int id, UserEntity loginInfo) {
		categoryDAO.deltrash(id,loginInfo);
	}

	@Override
	public void retrash(int id, UserEntity loginInfo) {
		categoryDAO.retrash(id,loginInfo);
	}

	@Override
	public void onOffCategory(int id, UserEntity loginInfo) {
		categoryDAO.onOffCategory(id,loginInfo);
	}

	@Override
	public List<CategoryEntity> GetDataCategoryPaginate(int start, int totalPage) {
		return categoryDAO.GetDataCategoryPaginate(start, totalPage);
	}

	@Override
	public List<CategoryEntity> GetDataCategoryTrashPaginate(int start, int totalPage) {
		return categoryDAO.GetDataCategoryTrashPaginate(start, totalPage);
	}

	@Override
	public Map<Integer, String> mapCate() {
		Map<Integer, String> map = new HashMap<Integer, String>();
		List<CategoryEntity> list = categoryDAO.findAllCate();
		for (CategoryEntity ct : list) {
			map.put(ct.getId(), ct.getName());
		}
		return map;
	}

	@Override
	public CategoryEntity findAllCateBySlug(String slug) {
		return categoryDAO.findAllCateBySlug(slug);
	}

	@Override
	public boolean isNameExists(String name) {
		return categoryDAO.isNameExists(name);
	}

	@Override
	public boolean isNameExists(String name, int id) {
		return categoryDAO.isNameExists(name, id);
	}

	@Override
	public boolean isSlugExists(String slug) {
		return categoryDAO.isSlugExists(slug);
	}

	@Override
	public boolean isSlugExists(String slug, int id) {
		return categoryDAO.isSlugExists(slug, id);
	}
}
