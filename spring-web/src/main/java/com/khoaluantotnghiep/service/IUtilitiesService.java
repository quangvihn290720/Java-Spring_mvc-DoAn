package com.khoaluantotnghiep.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.entity.UtilitiesEntity;

@Service
public interface IUtilitiesService {
	public List<UtilitiesEntity> findAllUtilities();

	public List<UtilitiesEntity> findAllUtilitiesShow();

	public UtilitiesEntity findUtilById(UtilitiesEntity util);

	public void addUtilities(UtilitiesEntity util);

	public void updateUtilities(UtilitiesEntity util);

	public void deleteUtilities(int utilities_id);

	public List<UtilitiesEntity> GetDataUtilitiesPaginate(int start, int totalPage);

	public List<UtilitiesEntity> GetDataUtilitiesTrashPaginate(int start, int totalPage);

	public void deltrash(int utilities_id,UserEntity loginInfo);

	public void retrash(int utilities_id,UserEntity loginInfo);

	public void onOffTopic(int utilities_id,UserEntity loginInfo);
	
	public List<UtilitiesEntity> findAllTrashUtilities();

	public UtilitiesEntity findUtilityByName(UtilitiesEntity utility);

	public UtilitiesEntity findOtherUtilityByName(UtilitiesEntity utility);

}
