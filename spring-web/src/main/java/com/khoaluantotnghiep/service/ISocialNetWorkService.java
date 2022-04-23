package com.khoaluantotnghiep.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.khoaluantotnghiep.entity.SocialNetWorkEntity;
import com.khoaluantotnghiep.entity.UserEntity;

@Service
public interface ISocialNetWorkService {
	public List<SocialNetWorkEntity> GetDataSocialNetWorkPaginate(int start, int totalPage);

	public List<SocialNetWorkEntity> GetDataSocialNetWorkTrashPaginate(int start, int totalPage);

	public List<SocialNetWorkEntity> findAllSocialNetWork();

	public List<SocialNetWorkEntity> findAllSocialNetWorkShow();

	public SocialNetWorkEntity findSocialNetWorkById(SocialNetWorkEntity socialnetwork);

	public List<SocialNetWorkEntity> findTrashSocialNetWork();

	public void deltrashSocialNetWork(int id,UserEntity loginInfo);

	public void updateSocialNetWork(SocialNetWorkEntity socialnetwork);

	public void addSocialNetWork(SocialNetWorkEntity socialnetwork);

	public void retrashSocialNetWork(int id,UserEntity loginInfo);

	public void deleteSocialNetWork(int id);

	public void onOffSocialNetWork(int id,UserEntity loginInfo);

	public boolean isNameExists(String name);

	public boolean isNameExists(String name, int id);
}
