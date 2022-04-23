package com.khoaluantotnghiep.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.khoaluantotnghiep.entity.OptiongroupsEntity;
import com.khoaluantotnghiep.entity.UserEntity;

@Service
public interface IOptiongroupsService {
	public List<OptiongroupsEntity> findAllOptionGroup();

	public List<OptiongroupsEntity> findAllOptionGroupShow();

	public List<OptiongroupsEntity> GetDataOptionGroupPaginate(int start, int totalPage);

	public List<OptiongroupsEntity> GetDataOptionGroupTrashPaginate(int start, int totalPage);

	public List<OptiongroupsEntity> findAllTrashOptionGroup();

	public OptiongroupsEntity findOptionGroupById(OptiongroupsEntity optiongroup);

	public void addOptionGroup(OptiongroupsEntity optiongroup);

	public void updateOptionGroup(OptiongroupsEntity optiongroup);

	public void deleteOptionGroup(int optiongroups_id);

	public void deltrash(int optiongroups_id,UserEntity loginInfo);

	public void retrash(int optiongroups_id,UserEntity loginInfo);

	public void onOff(int optiongroups_id,UserEntity loginInfo);

	public OptiongroupsEntity findOptionGroupId(int optiongroups_id);

	public Map<Integer, String> mapOpgroup();

	public boolean isNameExists(String name);

	public boolean isNameExists(String name, int id);
}
