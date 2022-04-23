package com.khoaluantotnghiep.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khoaluantotnghiep.dao.ConfigwebDAO;
import com.khoaluantotnghiep.entity.ConfigwebEntity;
import com.khoaluantotnghiep.service.IConfigwebService;

@Service
public class ConfigwebServiceImpl implements IConfigwebService {
	@Autowired
	ConfigwebDAO configDAO;

	@Override
	public ConfigwebEntity findConfigweb() {
		return configDAO.findConfigweb();
	}

	@Override
	public void update(ConfigwebEntity config) {
		configDAO.update(config);
	}
	@Override
	public void changeStatus(int id) {
		configDAO.changeStatus(id);
		
	}

}
