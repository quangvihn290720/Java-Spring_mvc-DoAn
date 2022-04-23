package com.khoaluantotnghiep.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khoaluantotnghiep.dao.OptiongroupsDAO;
import com.khoaluantotnghiep.entity.OptiongroupsEntity;
import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.service.IOptiongroupsService;

@Service
public class OptiongroupsServiceImpl implements IOptiongroupsService {
	@Autowired
	OptiongroupsDAO optiongroupDAO;

	@Override
	public List<OptiongroupsEntity> findAllOptionGroup() {
		return optiongroupDAO.findAllOptionGroup();
	}

	@Override
	public List<OptiongroupsEntity> findAllOptionGroupShow() {
		return optiongroupDAO.findAllOptionGroupShow();
	}

	@Override
	public List<OptiongroupsEntity> GetDataOptionGroupPaginate(int start, int totalPage) {
		return optiongroupDAO.GetDataOptionGroupPaginate(start, totalPage);
	}

	@Override
	public List<OptiongroupsEntity> GetDataOptionGroupTrashPaginate(int start, int totalPage) {
		return optiongroupDAO.GetDataOptionGroupTrashPaginate(start, totalPage);
	}

	@Override
	public List<OptiongroupsEntity> findAllTrashOptionGroup() {
		return optiongroupDAO.findAllTrashOptionGroup();
	}

	@Override
	public OptiongroupsEntity findOptionGroupById(OptiongroupsEntity optiongroup) {
		return optiongroupDAO.findOptionGroupById(optiongroup);
	}

	@Override
	public void addOptionGroup(OptiongroupsEntity optiongroup) {
		optiongroupDAO.addOptionGroup(optiongroup);
	}

	@Override
	public void updateOptionGroup(OptiongroupsEntity optiongroup) {
		optiongroupDAO.updateOptionGroup(optiongroup);
	}

	@Override
	public void deleteOptionGroup(int optiongroups_id) {
		optiongroupDAO.deleteOptionGroup(optiongroups_id);
	}

	@Override
	public void deltrash(int optiongroups_id,UserEntity loginInfo) {
		optiongroupDAO.deltrash(optiongroups_id,loginInfo);
	}

	@Override
	public void retrash(int optiongroups_id,UserEntity loginInfo) {
		optiongroupDAO.retrash(optiongroups_id,loginInfo);
	}

	@Override
	public void onOff(int optiongroups_id,UserEntity loginInfo) {
		optiongroupDAO.onOff(optiongroups_id,loginInfo);
	}

	@Override
	public Map<Integer, String> mapOpgroup() {
		Map<Integer, String> map = new HashMap<Integer, String>();
		List<OptiongroupsEntity> list = optiongroupDAO.findAllOptionGroup();
		for (OptiongroupsEntity ct : list) {
			map.put(ct.getOptiongroups_id(), ct.getOptiongroupname());
		}
		return map;
	}

	@Override
	public OptiongroupsEntity findOptionGroupId(int optiongroups_id) {
		return optiongroupDAO.findOptionGroupId(optiongroups_id);
	}
	@Override
	public boolean isNameExists(String name) {
		return optiongroupDAO.isNameExists(name);
	}

	@Override
	public boolean isNameExists(String name, int id) {
		return optiongroupDAO.isNameExists(name, id);
	}
}
