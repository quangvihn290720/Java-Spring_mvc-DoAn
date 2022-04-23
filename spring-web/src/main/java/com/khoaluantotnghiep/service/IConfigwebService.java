package com.khoaluantotnghiep.service;

import org.springframework.stereotype.Service;

import com.khoaluantotnghiep.entity.ConfigwebEntity;

@Service
public interface IConfigwebService {
	public ConfigwebEntity findConfigweb();

	public void update(ConfigwebEntity config);

	public void changeStatus(int id);
}
