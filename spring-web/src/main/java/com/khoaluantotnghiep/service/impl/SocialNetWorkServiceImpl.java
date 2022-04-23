package com.khoaluantotnghiep.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khoaluantotnghiep.dao.SocialNetWorkDAO;
import com.khoaluantotnghiep.entity.SocialNetWorkEntity;
import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.service.ISocialNetWorkService;

@Service
public class SocialNetWorkServiceImpl implements ISocialNetWorkService {

	@Autowired
	SocialNetWorkDAO socialNetWorkDAO;

	@Override
	public List<SocialNetWorkEntity> GetDataSocialNetWorkPaginate(int start, int totalPage) {
		return socialNetWorkDAO.GetDataSocialNetWorkPaginate(start, totalPage);
	}

	@Override
	public List<SocialNetWorkEntity> GetDataSocialNetWorkTrashPaginate(int start, int totalPage) {
		return socialNetWorkDAO.GetDataSocialNetWorkTrashPaginate(start, totalPage);
	}

	@Override
	public List<SocialNetWorkEntity> findAllSocialNetWork() {
		return socialNetWorkDAO.findAllSocialNetWork();
	}

	@Override
	public List<SocialNetWorkEntity> findAllSocialNetWorkShow() {
		return socialNetWorkDAO.findAllSocialNetWorkShow();
	}

	@Override
	public SocialNetWorkEntity findSocialNetWorkById(SocialNetWorkEntity socialnetwork) {
		return socialNetWorkDAO.findSocialNetWorkById(socialnetwork);
	}

	@Override
	public List<SocialNetWorkEntity> findTrashSocialNetWork() {
		return socialNetWorkDAO.findTrashSocialNetWork();
	}

	@Override
	public void deltrashSocialNetWork(int id,UserEntity loginInfo) {
		socialNetWorkDAO.deltrashSocialNetWork(id, loginInfo);
	}

	@Override
	public void updateSocialNetWork(SocialNetWorkEntity socialnetwork) {
		socialNetWorkDAO.updateSocialNetWork(socialnetwork);
	}

	@Override
	public void addSocialNetWork(SocialNetWorkEntity socialnetwork) {
		socialNetWorkDAO.addSocialNetWork(socialnetwork);
	}

	@Override
	public void retrashSocialNetWork(int id,UserEntity loginInfo) {
		socialNetWorkDAO.retrashSocialNetWork(id, loginInfo);
	}

	@Override
	public void deleteSocialNetWork(int id) {
		socialNetWorkDAO.deleteSocialNetWork(id);
	}

	@Override
	public void onOffSocialNetWork(int id,UserEntity loginInfo) {
		socialNetWorkDAO.onOffSocialNetWork(id, loginInfo);
	}

	@Override
	public boolean isNameExists(String name) {
		return socialNetWorkDAO.isNameExists(name);
	}

	@Override
	public boolean isNameExists(String name, int id) {
		return socialNetWorkDAO.isNameExists(name, id);
	}

}
