package com.khoaluantotnghiep.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khoaluantotnghiep.dao.BannerDAO;
import com.khoaluantotnghiep.entity.BannerEntity;
import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.service.IBannerService;

@Service
public class BannerServiceImpl implements IBannerService {

	@Autowired
	BannerDAO bannerDAO;

	@Override
	public List<BannerEntity> findAllBannerShow() {
		return bannerDAO.findAllBannerShow();
	}

	@Override
	public List<BannerEntity> findAllBanner() {
		return bannerDAO.findAllBanner();
	}

	@Override
	public BannerEntity findBannerById(BannerEntity banner) {
		return bannerDAO.findBannerById(banner);
	}

	@Override
	public List<BannerEntity> findTrashBanner() {
		return bannerDAO.findTrashBanner();
	}

	@Override
	public void deltrashBanner(int id,UserEntity loginInfo) {
		bannerDAO.deltrashBanner(id, loginInfo);
	}

	@Override
	public void retrashBanner(int id,UserEntity loginInfo) {
		bannerDAO.retrashBanner(id, loginInfo);
	}

	@Override
	public void addBanner(BannerEntity banner) {
		bannerDAO.addBanner(banner);
	}

	@Override
	public void updateBanner(BannerEntity banner) {
		bannerDAO.updateBanner(banner);
	}

	@Override
	public void deleteBanner(int id) {
		bannerDAO.deleteBanner(id);
	}

	@Override
	public void onOffBanner(int id,UserEntity loginInfo) {
		bannerDAO.onOffBanner(id, loginInfo);
	}

	@Override
	public List<BannerEntity> GetDataBannerPaginate(int start, int totalPage) {
		return bannerDAO.GetDataBannerPaginate(start, totalPage);
	}

	@Override
	public List<BannerEntity> GetDataBannerTrashPaginate(int start, int totalPage) {
		return bannerDAO.GetDataBannerTrashPaginate(start, totalPage);
	}
	@Override
	public boolean isNameExists(String name) {
		return bannerDAO.isNameExists(name);
	}

	@Override
	public boolean isNameExists(String name, int id) {
		return bannerDAO.isNameExists(name, id);
	}

}
