package com.khoaluantotnghiep.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.khoaluantotnghiep.entity.OptionsEntity;
import com.khoaluantotnghiep.entity.UserEntity;

@Service
public interface IOptionsService {
	public List<OptionsEntity> findAllOption();

	public List<OptionsEntity> findAllOptionShow();

	public List<OptionsEntity> GetDataOptionPaginate(int start, int totalPage);

	public List<OptionsEntity> GetDataOptionTrashPaginate(int start, int totalPage);

	public List<OptionsEntity> findAllTrashOption();

	public OptionsEntity findOptionById(OptionsEntity option);

	public void addOption(OptionsEntity option);

	public void updateOption(OptionsEntity option);

	public void deleteOption(int options_id);

	public void deltrash(int options_id,UserEntity loginInfo);

	public void retrash(int options_id,UserEntity loginInfo);

	public void onOff(int options_id,UserEntity loginInfo);
	
	public OptionsEntity findOptionByName(OptionsEntity option);
	
	public OptionsEntity findOptionId(int options_id);

	Map<Integer, String> mapOption();

	public OptionsEntity findOtherOptionByName(OptionsEntity option);
}
