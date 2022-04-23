package com.khoaluantotnghiep.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.khoaluantotnghiep.entity.CategoryEntity;
import com.khoaluantotnghiep.entity.UserEntity;

@Service
public interface ICategoryService {
	public List<CategoryEntity> findAllCate();

	public CategoryEntity findAllCateBySlug(String slug);

	public List<CategoryEntity> findAllCategoryShow();

	public void addCategory(CategoryEntity category);

	public void updateCategory(CategoryEntity category);

	public void deleteCategory(int id);

	public CategoryEntity findCategoryById(CategoryEntity category);

	public List<CategoryEntity> findTrashCategory();

	public void deltrash(int id,UserEntity loginInfo);

	public void retrash(int id,UserEntity loginInfo);

	public void onOffCategory(int id,UserEntity loginInfo);

	public List<CategoryEntity> GetDataCategoryPaginate(int start, int totalPage);

	public List<CategoryEntity> GetDataCategoryTrashPaginate(int start, int totalPage);

	public Map<Integer, String> mapCate();

	public boolean isNameExists(String name);

	public boolean isNameExists(String name, int id);

	public boolean isSlugExists(String slug);

	public boolean isSlugExists(String slug, int id);
}
