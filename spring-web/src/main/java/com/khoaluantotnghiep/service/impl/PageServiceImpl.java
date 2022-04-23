package com.khoaluantotnghiep.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khoaluantotnghiep.dao.PageDAO;
import com.khoaluantotnghiep.entity.PageEntity;
import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.service.IPageService;

@Service
public class PageServiceImpl implements IPageService {
	@Autowired
	private PageDAO pageDAO;

	@Override
	public List<PageEntity> findAllPage() {
		return pageDAO.findAllPage();
	}

	@Override
	public PageEntity findPageById(int page_id) {
		return pageDAO.findPageById(page_id);
	}

	@Override
	public void addPage(PageEntity page) {
		pageDAO.addPage(page);
	}

	@Override
	public void updatePage(PageEntity page) {
		pageDAO.updatePage(page);
	}

	@Override
	public void deletePage(int page_id) {
		pageDAO.deletePage(page_id);
	}

	@Override
	public void onOffPage(int page_id, UserEntity loginInfo) {
		pageDAO.onOffPage(page_id,  loginInfo);
	}

	@Override
	public void delTrash(int page_id, UserEntity loginInfo) {
		pageDAO.delTrash(page_id,  loginInfo);
	}

	@Override
	public void reTrash(int page_id, UserEntity loginInfo) {
		pageDAO.reTrash(page_id,  loginInfo);
	}

	@Override
	public List<PageEntity> findTrashPage() {
		return pageDAO.findTrashPage();
	}

	@Override
	public List<PageEntity> GetDataPagePaginate(int start, int totalPage) {
		return pageDAO.GetDataPagePaginate(start, totalPage);
	}

	@Override
	public List<PageEntity> GetDataPageTrashPaginate(int start, int totalPage) {
		return pageDAO.GetDataPageTrashPaginate(start, totalPage);
	}

	@Override
	public List<PageEntity> findAllPageShow() {
		return pageDAO.findAllPageShow();
	}

	@Override
	public boolean isTitledExists(String email) {
		return pageDAO.isTitledExists(email);
	}

	@Override
	public boolean isTitledExists(String email, int id) {
		return pageDAO.isTitledExists(email, id);
	}

	@Override
	public boolean isSlugExists(String slug) {
		return pageDAO.isSlugExists(slug);
	}

	@Override
	public boolean isSlugExists(String slug, int id) {
		return pageDAO.isSlugExists(slug, id);
	}
	
	@Override
	public PageEntity findPageBySlug(String slug) {
		return pageDAO.findPageBySlug(slug);
	}
}
