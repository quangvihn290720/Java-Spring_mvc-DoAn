package com.khoaluantotnghiep.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.khoaluantotnghiep.entity.BannerEntity;
import com.khoaluantotnghiep.entity.UserEntity;

@Service
public interface IBannerService {
	public List<BannerEntity> findAllBannerShow();

	public List<BannerEntity> findAllBanner();

	public BannerEntity findBannerById(BannerEntity banner);

	public List<BannerEntity> findTrashBanner();

	public void deltrashBanner(int id,UserEntity loginInfo);

	public void retrashBanner(int id,UserEntity loginInfo);

	public void addBanner(BannerEntity banner);

	public void updateBanner(BannerEntity banner);

	public void deleteBanner(int id);

	public void onOffBanner(int id,UserEntity loginInfo);

	public List<BannerEntity> GetDataBannerPaginate(int start, int totalPage);

	public List<BannerEntity> GetDataBannerTrashPaginate(int start, int totalPage);

	public boolean isNameExists(String name, int id);

	public boolean isNameExists(String name);
}
