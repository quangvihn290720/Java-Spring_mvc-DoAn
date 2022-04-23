package com.khoaluantotnghiep.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.khoaluantotnghiep.entity.PageEntity;
import com.khoaluantotnghiep.entity.UserEntity;

@Service
public interface IPageService {
	public List<PageEntity> findAllPage();

	public PageEntity findPageById(int page_id);

	public void addPage(PageEntity page);

	public void deletePage(int page_id);

	public void updatePage(PageEntity page);

	public void onOffPage(int page_id, UserEntity loginInfo);

	public void delTrash(int page_id, UserEntity loginInfo);

	public void reTrash(int page_id, UserEntity loginInfo);

	public List<PageEntity> findTrashPage();

	public List<PageEntity> GetDataPagePaginate(int start, int totalPage);

	public List<PageEntity> GetDataPageTrashPaginate(int start, int totalPage);

	public List<PageEntity> findAllPageShow();

	boolean isTitledExists(String email);

	boolean isTitledExists(String email, int id);

	boolean isSlugExists(String slug);

	boolean isSlugExists(String slug, int id);

	public PageEntity findPageBySlug(String slug);
}