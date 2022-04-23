package com.khoaluantotnghiep.service;

import org.springframework.stereotype.Service;

import com.khoaluantotnghiep.dto.PaginateDTO;

@Service
public interface IPaginatesService {
	public PaginateDTO GetInfoPaginates(int totalData, int limit, int currentPage);
}
