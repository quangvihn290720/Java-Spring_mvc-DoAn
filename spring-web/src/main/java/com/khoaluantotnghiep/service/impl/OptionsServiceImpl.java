package com.khoaluantotnghiep.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khoaluantotnghiep.dao.OptionsDAO;
import com.khoaluantotnghiep.entity.OptionsEntity;
import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.service.IOptionsService;

@Service
public class OptionsServiceImpl implements IOptionsService {
	@Autowired
	OptionsDAO optionsDAO;

	@Override
	public List<OptionsEntity> findAllOption() {
		return optionsDAO.findAllOption();
	}

	@Override
	public List<OptionsEntity> findAllOptionShow() {
		return optionsDAO.findAllOptionShow();
	}

	@Override
	public List<OptionsEntity> GetDataOptionPaginate(int start, int totalPage) {
		return optionsDAO.GetDataOptionPaginate(start, totalPage);
	}

	@Override
	public List<OptionsEntity> GetDataOptionTrashPaginate(int start, int totalPage) {
		return optionsDAO.GetDataOptionTrashPaginate(start, totalPage);
	}

	@Override
	public List<OptionsEntity> findAllTrashOption() {
		return optionsDAO.findAllTrashOption();
	}

	@Override
	public OptionsEntity findOptionById(OptionsEntity option) {
		return optionsDAO.findOptionById(option);
	}

	@Override
	public void addOption(OptionsEntity option) {
		optionsDAO.addOption(option);
	}

	@Override
	public void updateOption(OptionsEntity option) {
		optionsDAO.updateOption(option);
	}

	@Override
	public void deleteOption(int options_id) {
		optionsDAO.deleteOption(options_id);
	}

	@Override
	public void deltrash(int options_id,UserEntity loginInfo) {
		optionsDAO.deltrash(options_id, loginInfo);
	}

	@Override
	public void retrash(int options_id,UserEntity loginInfo) {
		optionsDAO.retrash(options_id, loginInfo);
	}

	@Override
	public void onOff(int options_id,UserEntity loginInfo) {
		optionsDAO.onOff(options_id, loginInfo);
	}

	@Override
	public OptionsEntity findOptionByName(OptionsEntity option) {
		return optionsDAO.findOptionByName(option);
	}

	@Override
	public Map<Integer, String> mapOption() {
		Map<Integer, String> map = new HashMap<Integer, String>();
		List<OptionsEntity> list = optionsDAO.findAllOption();
		for (OptionsEntity ct : list) {
			map.put(ct.getOptions_id(), ct.getOptionname());
		}
		return map;
	}

	@Override
	public OptionsEntity findOptionId(int options_id) {
		return optionsDAO.findOptionId(options_id);
	}
	@Override
	public OptionsEntity findOtherOptionByName(OptionsEntity option) {
		return optionsDAO.findOtherOptionByName(option);
	}
}
